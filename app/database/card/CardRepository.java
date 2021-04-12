package database.card;

import com.google.inject.ImplementedBy;
import database.card.cards.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CardRepository {
  CompletableFuture<List<Card>> getCards();
}

