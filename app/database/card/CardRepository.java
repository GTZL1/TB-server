package database.card;

import database.card.cards.Card;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CardRepository {
  CompletableFuture<List<Card>> getCards();
}

