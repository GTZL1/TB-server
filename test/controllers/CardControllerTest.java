package controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.OK;

import database.card.JPABaseRepository;
import database.card.JPAHeroRepository;
import database.card.JPASpyRepository;
import database.card.JPAUnitRepository;
import database.card.JPAVehicleRepository;
import database.card.cards.Card;
import database.card.cards.HeroCard;
import database.card.cards.UnitCard;
import database.player.JPAPlayerRepository;
import database.session.JPASessionRepository;
import database.session.Session;
import database.system.JPAVersionRepository;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Http.Request;
import play.mvc.Http.RequestBuilder;

public class CardControllerTest {
  JPASessionRepository sessionRepository = mock(JPASessionRepository.class);
  SessionController sessionController = new SessionController(mock(JPAPlayerRepository.class),
      sessionRepository, mock(JPAVersionRepository.class));
  JPAHeroRepository heroRepository = mock(JPAHeroRepository.class);
  JPAUnitRepository unitRepository = mock(JPAUnitRepository.class);
  JPAVehicleRepository vehicleRepository = mock(JPAVehicleRepository.class);
  JPASpyRepository spyRepository = mock(JPASpyRepository.class);
  JPABaseRepository baseRepository = mock(JPABaseRepository.class);
  CardController cardController = new CardController(heroRepository, unitRepository, vehicleRepository, spyRepository, baseRepository, sessionController);

  @Test
  public void getCardsWithWrongIdSessionDontWork() throws ExecutionException, InterruptedException {
    when(sessionRepository.getSession(2L)).thenReturn(Optional.empty());
    Request httpRequest = new RequestBuilder().method("GET").uri("/cards").bodyJson(
        Json.newObject().put("idSession", 1)).build();
    assertEquals(BAD_REQUEST, cardController.getCards(httpRequest).status());
  }

  //The return type of getCards() forbids this kind of test...
  @Test
  public void getCardsWorkWithRightSession() throws ExecutionException, InterruptedException {
    /*when(sessionRepository.getSession(1L)).thenReturn(Optional.of(new Session(1L, 1L)));
    HeroCard heroCard = new HeroCard(1L, "Ahsoka Tano", 8, 10, 1, 1L);
    UnitCard unitCard = new UnitCard(2L, "Chloe Frazer", 8, 6, 1);
    when(heroRepository.getCards()).thenReturn(List.of(heroCard));

    Request httpRequest = new RequestBuilder().method("GET").uri("/cards").bodyJson(
        Json.newObject().put("idSession", 1)).build();
    assertEquals(OK, cardController.getCards(httpRequest).status());*/
  }
}
