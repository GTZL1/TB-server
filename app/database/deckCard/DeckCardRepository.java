package database.deckCard;

import com.google.inject.ImplementedBy;
import java.util.List;
import java.util.concurrent.ExecutionException;

@ImplementedBy(JPADeckCardRepository.class)
public interface DeckCardRepository {
  List<DeckCard> getDeckCards(Long idxDeck) throws ExecutionException, InterruptedException;
}
