package database.card;

import database.card.cards.Card;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Named;

@Named("Hero")
public class JPAHeroRepository implements CardRepository{

  public CompletableFuture<List<Card>> getCards() {
    return null;
  }
}
