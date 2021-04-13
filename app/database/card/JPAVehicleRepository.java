package database.card;

import database.card.cards.HeroCard;
import java.util.List;
import javax.inject.Named;

@Named("Vehicle")
public class JPAVehicleRepository implements CardRepository{
  @Override
  public List<HeroCard> getCards() {
    return null;
  }
}
