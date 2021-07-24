package database.card.cards;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class HeroCard extends Card{
  @Column(name = "idx_power")
  private Long idxPower;

  public HeroCard(Long idCard, String name, int lifePoints, int attackPoints, int maxNumberInDeck,
      Long idxPower) {
    super(idCard, name, lifePoints, attackPoints, maxNumberInDeck);
    this.idxPower = idxPower;
  }

  public HeroCard() {
    super();
  }

  public Long getIdxPower() {
    return idxPower;
  }
}
