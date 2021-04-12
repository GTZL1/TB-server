package database.card;

import database.card.cards.Card;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Named;

@Named("Spy")
public class JPASpyRepository implements CardRepository{
  @Override
  public CompletableFuture<List<Card>> getCards() {
    return null;
  }
}
