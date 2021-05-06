package database.deckCard;

import java.io.Serializable;

public class DeckCardId implements Serializable {
  private Long idxDeck;
  private Long idxCard;

  public DeckCardId(Long idxDeck, Long idxCard) {
    this.idxDeck = idxDeck;
    this.idxCard = idxCard;
  }
}
