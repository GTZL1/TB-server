package database.card;

import database.card.cards.Card;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Named;

@Named("Vehicle")
public class JPAVehicleRepository implements CardRepository{
  @Override
  public CompletableFuture<List<Card>> getCards() {
    return null;
  }
}
