package database.card;

import database.card.cards.Card;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class JPAUnitRepository implements CardRepository{

  @Override
  public CompletableFuture<List<Card>> getCards() {
    return null;
  }
}
