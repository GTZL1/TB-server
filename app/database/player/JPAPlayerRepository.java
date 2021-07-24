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
  public Player addPlayer(Player player) throws ExecutionException, InterruptedException {
    return CompletableFuture.supplyAsync(() ->
    (jpaApi.withTransaction(entityManager -> {
      entityManager.persist(player);
      return player;
    })), executionContext).get();
  }

  @Override
  public Optional<Player> getPlayer(String username)
      throws ExecutionException, InterruptedException {
    return fetchPlayer(username).get();
  }

  @Override
  public Optional<Player> getPlayer(Long idPlayer)
      throws ExecutionException, InterruptedException {
    return fetchPlayer(idPlayer).get();
  }

  private CompletableFuture<Optional<Player>> fetchPlayer(String username) {
    return CompletableFuture.supplyAsync(() ->
       jpaApi.withTransaction(entityManager -> {
          List players= entityManager.createNativeQuery("select * from player where player.username=\'"+username+"\'", Player.class).getResultList();
          return players.stream().findFirst();
        }), executionContext);
  }

  private CompletableFuture<Optional<Player>> fetchPlayer(Long idPlayer) {
    return CompletableFuture.supplyAsync(() ->
        jpaApi.withTransaction(entityManager -> {
          List players= entityManager.createNativeQuery("select * from player where player.id_player=\'"+idPlayer+"\'", Player.class).getResultList();
          return players.stream().findFirst();
        }), executionContext);
  }

  public boolean existsPlayerUsername(String username)
      throws ExecutionException, InterruptedException {
    return getPlayer(username).isPresent();
  }
}
