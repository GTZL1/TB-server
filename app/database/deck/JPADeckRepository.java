package database.deck;

import database.DatabaseExecutionContext;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import play.db.jpa.JPAApi;

public class JPADeckRepository implements DeckRepository{
  private final JPAApi jpaApi;
  private final DatabaseExecutionContext executionContext;

  @Inject
  public JPADeckRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  @Override
  public List<Deck> getDeckPlayer(Long idxPlayer) throws ExecutionException, InterruptedException {
    return searchDecks(idxPlayer).get();
  }

  private CompletableFuture<List<Deck>> searchDecks(Long idxPlayer) {
    return CompletableFuture.supplyAsync(() ->
        jpaApi.withTransaction(entityManager -> {
          return entityManager.createNativeQuery("select * from deck where idx_player=\'"+idxPlayer+"\'", Deck.class).getResultList();
        }), executionContext);
  }
}
