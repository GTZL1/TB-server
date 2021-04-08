package database.player;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import database.DatabaseExecutionContext;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import play.db.jpa.JPAApi;

public class JPAPlayerRepository implements PlayerRepository{
  private final JPAApi jpaApi;
  private final DatabaseExecutionContext executionContext;

  @Inject
  public JPAPlayerRepository(JPAApi api, DatabaseExecutionContext executionContext) {
    this.jpaApi = api;
    this.executionContext = executionContext;
  }

  @Override
  public CompletionStage<Player> addPlayer(Player player) {
    return supplyAsync(() ->
    (jpaApi.withTransaction(entityManager -> {
      entityManager.persist(player);
      return player;
    })), executionContext);
  }

  /*public CompletionStage<Optional<Player>> getPlayer(String username){
    return supplyAsync(() ->
        jpaApi.withTransaction(entityManager -> entityManager.)
        );
  }*/

  public void test(){
    System.out.println("It works !");
  }
}
