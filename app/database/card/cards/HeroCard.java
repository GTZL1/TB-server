package database.card.cards;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class HeroCard extends Card{
  @Column(name = "idx_power")
  private Long idxPower;

  public Long getIdxPower() {
    return idxPower;
  }
}
