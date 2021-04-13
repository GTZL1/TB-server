package database.card.cards;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="card")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Card {
  @Id
  @GeneratedValue(strategy= GenerationType.TABLE)
  @Column(name="id_card")
  private Long idCard;

  @Column(name="name")
  private String name;

  @Column(name = "life_points")
  private int lifePoints;

  @Column(name = "attack_points")
  private int attackPoints;

  @Column(name = "max_number_deck")
  private int maxNumberInDeck;

  public void setName(String name) {
    this.name = name;
  }

  public void setLifePoints(int lifePoints) {
    this.lifePoints = lifePoints;
  }

  public void setAttackPoints(int attackPoints) {
    this.attackPoints = attackPoints;
  }

  public void setMaxNumberInDeck(int maxNumberInDeck) {
    this.maxNumberInDeck = maxNumberInDeck;
  }

  public Long getIdCard() {
    return idCard;
  }

  public String getName() {
    return name;
  }

  public int getLifePoints() {
    return lifePoints;
  }

  public int getAttackPoints() {
    return attackPoints;
  }

  public int getMaxNumberInDeck() {
    return maxNumberInDeck;
  }
}
