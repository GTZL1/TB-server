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
import database.card.cards.SpyCard;
import database.card.cards.UnitCard;
import database.card.cards.VehicleCard;
import database.deck.Deck;
import database.deck.JPADeckRepository;
import database.deckCard.DeckCard;
import database.deckCard.DeckCardId;
import database.deckCard.JPADeckCardRepository;
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

public class DeckControllerTest {
  JPASessionRepository sessionRepository = mock(JPASessionRepository.class);
  SessionController sessionController = new SessionController(mock(JPAPlayerRepository.class),
      sessionRepository, mock(JPAVersionRepository.class));
  CardController cardController = new CardController(
      mock(JPAHeroRepository.class), mock(JPAUnitRepository.class),
      mock(JPAVehicleRepository.class), mock(JPASpyRepository.class),
      mock(JPABaseRepository.class), sessionController);
  JPADeckRepository deckRepository = mock(JPADeckRepository.class);
  JPADeckCardRepository deckCardRepository = mock(JPADeckCardRepository.class);
  DeckController deckController = new DeckController(deckRepository, deckCardRepository, cardController, sessionController);

  @Test
  public void getPlayerDeckWithRightSessionWork() throws ExecutionException, InterruptedException {
    when(sessionRepository.getSession(1L)).thenReturn(Optional.of(new Session(1L, 1L)));
    when(deckRepository.getDeckPlayer(1L)).thenReturn(List.of(new Deck(1L, 1L, "default")));
    Card heroCard = new HeroCard(1L, "Ahsoka Tano", 8, 10, 1, 1L);
    Card unitCard = new UnitCard(2L, "Chloe Frazer", 8, 6, 1);
    Card vehicleCard = new VehicleCard(3L, "Avatar gunship", 5, 6, 2);
    Card spyCard = new SpyCard(4L, "Vanasha", 5, 5, 1);
    when(cardController.getCards()).thenReturn(List.of(heroCard, unitCard, vehicleCard, spyCard));
    when(deckCardRepository.getDeckCards(1L)).thenReturn(List.of(
        new DeckCard(new DeckCardId(1L, 1L), (short) 1),
        new DeckCard(new DeckCardId(1L, 2L), (short) 1),
        new DeckCard(new DeckCardId(1L, 3L), (short) 2),
        new DeckCard(new DeckCardId(1L, 4L), (short) 1)
    ));

    Request httpRequest = new RequestBuilder().method("GET").uri("/decks").bodyJson(
        Json.newObject().put("idSession", 1)).build();

    assertEquals(OK, deckController.getPlayerDeck(httpRequest).status());
  }

  @Test
  public void getPlayerDeckWithWrongSessionDontWork() throws ExecutionException, InterruptedException {
    when(sessionRepository.getSession(1L)).thenReturn(Optional.empty());
    when(deckRepository.getDeckPlayer(1L)).thenReturn(List.of(new Deck(1L, 1L, "default")));
    Card heroCard = new HeroCard(1L, "Ahsoka Tano", 8, 10, 1, 1L);
    Card unitCard = new UnitCard(2L, "Chloe Frazer", 8, 6, 1);
    Card vehicleCard = new VehicleCard(3L, "Avatar gunship", 5, 6, 2);
    Card spyCard = new SpyCard(4L, "Vanasha", 5, 5, 1);
    when(cardController.getCards()).thenReturn(List.of(heroCard, unitCard, vehicleCard, spyCard));
    when(deckCardRepository.getDeckCards(1L)).thenReturn(List.of(
        new DeckCard(new DeckCardId(1L, 1L), (short) 1),
        new DeckCard(new DeckCardId(1L, 2L), (short) 1),
        new DeckCard(new DeckCardId(1L, 3L), (short) 2),
        new DeckCard(new DeckCardId(1L, 4L), (short) 1)
    ));

    Request httpRequest = new RequestBuilder().method("GET").uri("/decks").bodyJson(
        Json.newObject().put("idSession", 1)).build();

    assertEquals(BAD_REQUEST, deckController.getPlayerDeck(httpRequest).status());
  }
}
