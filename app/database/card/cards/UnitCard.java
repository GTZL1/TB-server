package database.card.cards;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Table(name="unit_card")
public class UnitCard extends Card{
  /*@Column(name = "idx_card")
  private Long idxCard;

  public Long getIdxCard() {
    return idxCard;
  }*/
}
