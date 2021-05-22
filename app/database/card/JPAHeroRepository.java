package database.card;

import database.DatabaseExecutionContext;
import database.card.cards.*;
import java.util.List;
import java.util.concurrent.*;
import javax.inject.*;
import play.db.jpa.JPAApi;

@Named("Hero")
public class JPAHeroRepository implements CardRepository{
  private final JPAApi jpaApi;
  private final DatabaseExecutionContext executionContext;

  @Inject
  public JPAHeroRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  @Override
  public List<? extends Card> getCards() throws ExecutionException, InterruptedException {
    return getHeroCards().get();
  }

  private CompletableFuture<List<HeroCard>> getHeroCards(){
    return CompletableFuture.supplyAsync(() ->
        jpaApi.withTransaction(entityManager -> {
          return entityManager.createNativeQuery("select id_card, name, life_points, attack_points, max_number_deck, idx_power, image"
                  + " from card inner join hero_card on id_card = idx_card"
              , HeroCard.class).getResultList();
        }), executionContext);
  }
}
