package database.system;

import database.DatabaseExecutionContext;
import database.deck.Deck;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import play.db.jpa.JPAApi;

public class JPAVersionRepository implements VersionRepository {
  private final JPAApi jpaApi;
  private final DatabaseExecutionContext executionContext;

  @Inject
  public JPAVersionRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  @Override
  public String getVersionNumber() throws ExecutionException, InterruptedException {
    return searchVersion().get().stream().findFirst().get().getVersionNumber();
  }

  private CompletableFuture<List<Version>> searchVersion() {
    return CompletableFuture.supplyAsync(() ->
        jpaApi.withTransaction(entityManager -> {
          return entityManager.createNativeQuery("select * from version_system", Version.class).getResultList();
        }), executionContext);
  }
}
