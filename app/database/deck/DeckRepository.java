package database.deck;

import com.google.inject.ImplementedBy;
import java.util.List;
import java.util.concurrent.ExecutionException;

@ImplementedBy(JPADeckRepository.class)
public interface DeckRepository {
  List<Deck> getDeckPlayer(Long idxPlayer) throws ExecutionException, InterruptedException;
  String changeDeckName(Long idDeck, String name) throws ExecutionException, InterruptedException;
}
