package database.deckCard;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DeckCardId implements Serializable {
  @Column(name = "idx_deck")
  private Long idxDeck;

  @Column(name = "idx_card")
  private Long idxCard;

  public DeckCardId(Long idxDeck, Long idxCard) {
    this.idxDeck = idxDeck;
    this.idxCard = idxCard;
  }
  public DeckCardId() {

  }

  public Long getIdxDeck() {
    return idxDeck;
  }

  public Long getIdxCard() {
    return idxCard;
  }
}
