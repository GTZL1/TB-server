package database.deck;

import static java.util.concurrent.CompletableFuture.supplyAsync;

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
  public CompletableFuture<Deck> addNewDeck(Deck deck) {
    return supplyAsync(() ->
        (jpaApi.withTransaction(entityManager -> {
          entityManager.persist(deck);
          return deck;
        })), executionContext);
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

  @Override
  public String changeDeckName(Long idDeck, String name)
      throws ExecutionException, InterruptedException {
    return updateDeckName(idDeck, name).get();
  }

  private CompletableFuture<String> updateDeckName(Long idDeck, String name) {
    return supplyAsync(() ->
        (jpaApi.withTransaction(entityManager -> {
          entityManager.createNativeQuery(
              "update deck set name= \'"+name+"\' where id_deck=\'" + idDeck + "\'").executeUpdate();
          return name;
        })), executionContext);
  }
}
