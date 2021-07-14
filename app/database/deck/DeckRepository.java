package database.deck;

import com.google.inject.ImplementedBy;
import java.util.List;
import java.util.concurrent.ExecutionException;

@ImplementedBy(JPADeckRepository.class)
public interface DeckRepository {
  Deck addNewDeck (Deck deck) throws ExecutionException, InterruptedException;
  List<Deck> getDeckPlayer(Long idxPlayer) throws ExecutionException, InterruptedException;
  String changeDeckName(Long idDeck, String name) throws ExecutionException, InterruptedException;
  Long removeDeck(Long idDeck) throws ExecutionException, InterruptedException;
}
