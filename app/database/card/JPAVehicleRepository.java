package database.card;

import database.DatabaseExecutionContext;
import database.card.cards.*;
import java.util.List;
import java.util.concurrent.*;
import javax.inject.*;
import play.db.jpa.JPAApi;

@Named("Vehicle")
public class JPAVehicleRepository implements CardRepository{
  private final JPAApi jpaApi;
  private final DatabaseExecutionContext executionContext;

  @Inject
  public JPAVehicleRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  @Override
  public List<? extends Card> getCards() throws ExecutionException, InterruptedException {
    return getVehicleCards().get();
  }

  private CompletableFuture<List<VehicleCard>> getVehicleCards(){
    return CompletableFuture.supplyAsync(() ->
        jpaApi.withTransaction(entityManager -> {
          return entityManager.createNativeQuery("select id_card, name, life_points, attack_points, max_number_deck, image"
                  + " from card inner join vehicle_card on id_card = idx_card"
              , VehicleCard.class).getResultList();
        }), executionContext);
  }
}
