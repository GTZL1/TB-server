package database.card.cards;

import javax.persistence.Entity;

@Entity

public class UnitCard extends Card{

  public UnitCard(Long idCard, String name, int lifePoints, int attackPoints, int maxNumberInDeck) {
    super(idCard, name, lifePoints, attackPoints, maxNumberInDeck);
  }

  public UnitCard() {
    super();
  }
}
