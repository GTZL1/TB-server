package database.game;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import database.DatabaseExecutionContext;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import play.db.jpa.JPAApi;

public class JPAGameRepository implements GameRepository {
  private final JPAApi jpaApi;
  private final DatabaseExecutionContext executionContext;

  @Inject
  public JPAGameRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  @Override
  public CompletableFuture<Game> addnewGame(Game game) {
    return supplyAsync(() ->
        (jpaApi.withTransaction(entityManager -> {
          entityManager.persist(game);
          return game;
        })), executionContext);
  }

  @Override
  public boolean gameAlreadyExists(Game game) throws ExecutionException, InterruptedException {
    for(Game g: getPlayerGames(game.getIdGame().getIdxWinner())){
      if(g.getIdGame().getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-hh-mm"))
      .equals(game.getIdGame().getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-hh-mm")))) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<Game> getPlayerGames(Long idxPlayer) throws ExecutionException, InterruptedException {
    return searchPlayerGames(idxPlayer).get();
  }

  private CompletableFuture<List<Game>> searchPlayerGames(Long idxPlayer) {
    return CompletableFuture.supplyAsync(() ->
        jpaApi.withTransaction(entityManager -> {
          return entityManager.createNativeQuery("select * from game where idx_player_winner=\'"+idxPlayer+"\'"
                                                  +" or idx_player_looser=\'"+idxPlayer+"\' order by date", Game.class).getResultList();
        }), executionContext);
  }
}
