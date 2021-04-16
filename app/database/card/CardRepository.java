package database.card;

import database.card.cards.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CardRepository {
  List<? extends Card> getCards() throws ExecutionException, InterruptedException;
}

