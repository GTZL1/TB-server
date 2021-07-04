package database.player;

import database.DatabaseExecutionContext;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
  public CompletableFuture<Player> addPlayer(Player player) {
    return CompletableFuture.supplyAsync(() ->
    (jpaApi.withTransaction(entityManager -> {
      entityManager.persist(player);
      return player;
    })), executionContext);
  }

  @Override
  public CompletableFuture<Optional<Player>> getPlayer(String username){
    return CompletableFuture.supplyAsync(() ->
       jpaApi.withTransaction(entityManager -> {
          List players= entityManager.createNativeQuery("select * from player where player.username=\'"+username+"\'", Player.class).getResultList();
          return players.stream().findFirst();
        }), executionContext);
  }

  @Override
  public CompletableFuture<Optional<Player>> getPlayer(Long idPlayer) {
    return CompletableFuture.supplyAsync(() ->
        jpaApi.withTransaction(entityManager -> {
          List players= entityManager.createNativeQuery("select * from player where player.id_player=\'"+idPlayer+"\'", Player.class).getResultList();
          return players.stream().findFirst();
        }), executionContext);
  }

  public boolean existsPlayerUsername(String username)
      throws ExecutionException, InterruptedException {
    return getPlayer(username).get().isPresent();
  }
}
