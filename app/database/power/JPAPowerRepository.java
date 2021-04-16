package database.power;

import database.DatabaseExecutionContext;
import database.card.cards.HeroCard;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;
import play.db.jpa.JPAApi;

public class JPAPowerRepository implements PowerRepository{
  private final JPAApi jpaApi;
  private final DatabaseExecutionContext executionContext;

  @Inject
  public JPAPowerRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  @Override
  public CompletableFuture<List<Power>> getPowers(){
    return CompletableFuture.supplyAsync(() ->
        jpaApi.withTransaction(entityManager -> {
          return entityManager.createNativeQuery("select * from power;"
              , Power.class).getResultList();
        }), executionContext);
  }
}
