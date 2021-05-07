package database.deckCard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
//@IdClass(DeckCardId.class)
@Table(name="deck_card")
public class DeckCard {
  //@Id
  @Column(name = "idx_deck")
  private Long idxDeck;

  @Id
  @Column(name = "idx_card")
  private Long idxCard;

  @Column(name = "quantity")
  private short quantity;

  public Long getIdxDeck() {
    return idxDeck;
  }

  public Long getIdxCard() {
    return idxCard;
  }

  public short getQuantity() {
    return quantity;
  }
}
