package database.card;

import database.DatabaseExecutionContext;
import database.card.cards.BaseCard;
import database.card.cards.Card;
import java.util.List;
import java.util.concurrent.*;
import javax.inject.Inject;
import javax.inject.Named;
import play.db.jpa.JPAApi;

@Named("Base")
public class JPABaseRepository implements CardRepository {

  private final JPAApi jpaApi;
  private final DatabaseExecutionContext executionContext;

  @Inject
  public JPABaseRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  @Override
  public List<? extends Card> getCards() throws ExecutionException, InterruptedException {
    return getBaseCards().get();
  }

  private CompletableFuture<List<BaseCard>> getBaseCards() {
    return CompletableFuture.supplyAsync(() ->
        jpaApi.withTransaction(entityManager -> {
          return entityManager
              .createNativeQuery("select id_card, name, life_points, attack_points, max_number_deck"
                      + " from card inner join base_card on id_card = idx_card"
                  , BaseCard.class).getResultList();
        }), executionContext);
  }
}
