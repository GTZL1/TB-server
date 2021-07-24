package database.session;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import database.DatabaseExecutionContext;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
  public Session addSession(Session session) throws ExecutionException, InterruptedException {
    return supplyAsync(() ->
        (jpaApi.withTransaction(entityManager -> {
          entityManager.persist(session);
          return session;
        })), executionContext).get();
  }

  @Override
  public Optional<Session> getSession(Long idSession)
      throws ExecutionException, InterruptedException {
    return fetchSession(idSession).get();
  }

  private CompletableFuture<Optional<Session>> fetchSession(Long idSession) {
    return CompletableFuture.supplyAsync(() ->
        jpaApi.withTransaction(entityManager -> {
          List sessions= entityManager.createNativeQuery("select * from session where session.id_session=\'"+idSession+"\'", Session.class).getResultList();
          return sessions.stream().findFirst();
        }), executionContext);
  }

  @Override
  public CompletableFuture<Long> removeSession(Long idSession) {
    return CompletableFuture.supplyAsync(() ->
        (jpaApi.withTransaction(entityManager -> {
          entityManager.createNativeQuery("delete from session where id_session=\'"+idSession+"\'").executeUpdate();
          return idSession;
        })), executionContext);
  }
}
