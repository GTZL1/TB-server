package database.player;

import database.DatabaseExecutionContext;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import play.db.jpa.JPAApi;

public class JPAPlayerRepository implements PlayerRepository{
  private JPAApi jpaApi;
  private DatabaseExecutionContext executionContext;

  @Inject
  public JPAPlayerRepository(JPAApi api, DatabaseExecutionContext executionContext) {
    this.jpaApi = api;
    this.executionContext = executionContext;
  }

  @Override
  public CompletionStage<Player> addPlayer(Player player) {
    return null;
  }
}
