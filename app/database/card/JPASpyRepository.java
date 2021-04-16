package database.card;

import database.DatabaseExecutionContext;
import database.card.cards.Card;
import database.card.cards.HeroCard;
import database.card.cards.SpyCard;
import database.card.cards.VehicleCard;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import javax.inject.Named;
import play.db.jpa.JPAApi;

@Named("Spy")
public class JPASpyRepository implements CardRepository{
  private final JPAApi jpaApi;
  private final DatabaseExecutionContext executionContext;

  @Inject
  public JPASpyRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  @Override
  public List<? extends Card> getCards() throws ExecutionException, InterruptedException {
    return getSpyCards().get();
  }

  private CompletableFuture<List<SpyCard>> getSpyCards(){
    return CompletableFuture.supplyAsync(() ->
        jpaApi.withTransaction(entityManager -> {
          return entityManager.createNativeQuery("select id_card, name, life_points, attack_points, max_number_deck"
                  + " from card inner join spy_card on id_card = idx_card"
              , SpyCard.class).getResultList();
        }), executionContext);
  }
}
