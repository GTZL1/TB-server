package database.deck;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="deck")
public class Deck {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "id_deck")
  private Long idDeck;

  @Column(name = "idx_player")
  private Long idxPlayer;

  @Column(name = "name")
  private String name;

  public Long getIdDeck() {
    return idDeck;
  }

  public Long getIdxPlayer() {
    return idxPlayer;
  }

  public String getName() {
    return name;
  }
}
