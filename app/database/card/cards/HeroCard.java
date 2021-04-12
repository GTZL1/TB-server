package database.card.cards;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hero_card")
public class HeroCard extends UnitCard{
  @Column(name = "idx_card")
  private Long idxCard;

  @Column(name = "idx_power")
  private Long idxPower;

  @Override
  public Long getIdxCard() {
    return idxCard;
  }

  public Long getIdxPower() {
    return idxPower;
  }
}
