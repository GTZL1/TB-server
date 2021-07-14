package database.card.cards;

import javax.persistence.Entity;

@Entity
public class SpyCard extends Card{

  public SpyCard(Long idCard, String name, int lifePoints, int attackPoints, int maxNumberInDeck) {
    super(idCard, name, lifePoints, attackPoints, maxNumberInDeck);
  }

  public SpyCard() {
    super();
  }
}
