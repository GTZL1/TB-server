package database.card;

import database.card.cards.Card;
import database.card.cards.HeroCard;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.checkerframework.checker.units.qual.C;

public class JPAHeroRepository implements CardRepository{
  @Override
  public CompletableFuture<List<Card>> getCards() {
    return null;
  }
}
