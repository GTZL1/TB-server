package database.card.cards;

import javax.persistence.Entity;

@Entity
public class VehicleCard extends Card {

  public VehicleCard(Long idCard, String name, int lifePoints, int attackPoints,
      int maxNumberInDeck) {
    super(idCard, name, lifePoints, attackPoints, maxNumberInDeck);
  }

  public VehicleCard() {
    super();
  }
}
