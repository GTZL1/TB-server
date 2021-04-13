package database.card;

import database.card.cards.HeroCard;
import java.util.List;
import javax.inject.Named;

@Named("Spy")
public class JPASpyRepository implements CardRepository{
  @Override
  public List<HeroCard> getCards() {
    return null;
  }
}
