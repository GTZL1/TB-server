package database.session;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import database.DatabaseExecutionContext;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import play.db.jpa.JPAApi;

public class JPASessionRepository implements SessionRepository {
  private final JPAApi jpaApi;
  private final DatabaseExecutionContext executionContext;

  @Inject
  public JPASessionRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  @Override
  public CompletableFuture<Session> addSession(Session session) {
    return supplyAsync(() ->
        (jpaApi.withTransaction(entityManager -> {
          entityManager.persist(session);
          return session;
        })), executionContext);
  }

  @Override
  public CompletionStage<Session> getSessionPlayerId(Long idxPlayer) {
    return null;
  }

  @Override
  public CompletionStage<Session> removeSession(Session session) {
    return null;
  }
}
