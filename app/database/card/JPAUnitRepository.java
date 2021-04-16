package database.card;

import database.DatabaseExecutionContext;
import database.card.cards.*;
import java.util.List;
import java.util.concurrent.*;
import javax.inject.*;
import play.db.jpa.JPAApi;

@Named("Unit")
public class JPAUnitRepository implements CardRepository{
  private final JPAApi jpaApi;
  private final DatabaseExecutionContext executionContext;

  @Inject
  public JPAUnitRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  @Override
  public List<? extends Card> getCards() throws ExecutionException, InterruptedException {
    return getUnitCards().get();
  }

  private CompletableFuture<List<UnitCard>> getUnitCards(){
    return CompletableFuture.supplyAsync(() ->
        jpaApi.withTransaction(entityManager -> {
          return entityManager.createNativeQuery("select id_card, name, life_points, attack_points, max_number_deck from card "
                  + "inner join ((select * from unit_card)"
                  + " except (select idx_card from hero_card)"
                  + " except (select * from vehicle_card)"
                  + " except (select * from spy_card) order by idx_card)"
                  + " as units on id_card=units.idx_card;"
              , UnitCard.class).getResultList();
        }), executionContext);
  }
}
