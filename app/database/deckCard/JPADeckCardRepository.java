package database.deckCard;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import database.DatabaseExecutionContext;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import play.db.jpa.JPAApi;

public class JPADeckCardRepository implements DeckCardRepository{
  private final JPAApi jpaApi;
  private final DatabaseExecutionContext executionContext;

  @Inject
  public JPADeckCardRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  @Override
  public List<DeckCard> getDeckCards(Long idxDeck) throws ExecutionException, InterruptedException {
    return searchDeckCards(idxDeck).get();
  }

  @Override
  public void addDeckCard(DeckCard deckCard) {
    CompletableFuture.supplyAsync(() ->
        (jpaApi.withTransaction(entityManager -> {
          entityManager.persist(deckCard);
          return deckCard;
        })), executionContext);
  }

  private CompletableFuture<List<DeckCard>> searchDeckCards(Long idxDeck) {
    return supplyAsync(() ->
        jpaApi.withTransaction(entityManager -> {
          return entityManager.createNativeQuery("select * from deck_card where idx_deck=\'"+idxDeck+"\'", DeckCard.class).getResultList();
        }), executionContext);
  }

  @Override
  public Object removeDeckCards(Long idxDeck) throws ExecutionException, InterruptedException {
    return deleteDeckCards(idxDeck).get();
  }

  private CompletableFuture<Object> deleteDeckCards(Long idxDeck) {
    return supplyAsync(() ->
        (jpaApi.withTransaction(entityManager -> {
          return entityManager.createNativeQuery(
              "delete from deck_card where idx_deck=\'" + idxDeck + "\'").executeUpdate();
        })), executionContext);
  }
}
