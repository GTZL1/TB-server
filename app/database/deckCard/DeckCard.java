package database.deckCard;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="deck_card")
public class DeckCard {
  @EmbeddedId
  private DeckCardId idDeckCard;

  @Column(name = "quantity")
  private short quantity;

  public Long getIdxDeck() {
    return idDeckCard.getIdxDeck();
  }

  public Long getIdxCard() {
    return idDeckCard.getIdxCard();
  }

  public short getQuantity() {
    return quantity;
  }

  public void setIdDeckCard(DeckCardId idDeckCard) {
    this.idDeckCard = idDeckCard;
  }

  public void setQuantity(int quantity) {
    this.quantity = (short) quantity;
  }
}
