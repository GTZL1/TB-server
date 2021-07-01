package controllers;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.card.cards.Card;
import database.deck.Deck;
import database.deck.JPADeckRepository;
import database.deckCard.DeckCard;
import database.deckCard.DeckCardId;
import database.deckCard.JPADeckCardRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.inject.Inject;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

public class DeckController {

  private final JPADeckRepository jpaDeckRepository;
  private final JPADeckCardRepository jpaDeckCardRepository;
  private final CardController cardController;
  private final SessionController sessionController;

  @Inject
  public DeckController(JPADeckRepository jpaDeckRepository,
      JPADeckCardRepository jpaDeckCardRepository, CardController cardController,
      SessionController sessionController) {
    this.jpaDeckRepository = jpaDeckRepository;
    this.jpaDeckCardRepository = jpaDeckCardRepository;
    this.cardController = cardController;
    this.sessionController = sessionController;
  }

  public Result getPlayerDeck(Http.Request request)
      throws ExecutionException, InterruptedException {
    JsonNode json = request.body().asJson();
    if (json == null || !sessionController.verifyIdSession(json.findPath("idSession").asLong())) {
      return badRequest();
    }

    Long idxPlayer = sessionController.getIdPlayerSession(json.findPath("idSession").asLong());
    List<Deck> decks = jpaDeckRepository.getDeckPlayer(idxPlayer);

    List<JsonNode> result=new ArrayList<>();
    for (var d : decks) {
      List<JsonNode> cardsRecap = new ArrayList<>();
      //all deck_cards infos
      List<DeckCard> deckCards = jpaDeckCardRepository.getDeckCards(d.getIdDeck());

      //json objects <card name, quantity>
      //iterate through cards in the deck
      for (var c : cardController.getCards().stream().filter(
          c -> deckCards.stream().map(DeckCard::getIdxCard).collect(Collectors.toList())
              .contains(c.getIdCard()))
          .collect(Collectors.toList())) {
        cardsRecap.add(Json.newObject().put("name", c.getName())
            .put("quantity",
            deckCards.stream().filter(dc -> dc.getIdxCard().equals(c.getIdCard()))
            .collect(Collectors.toList()).get(0).getQuantity()));
      }

      result.add(Json.newObject().put("id", d.getIdDeck())
          .put("name", d.getName())
          .set("cards", Json.toJson(cardsRecap)));
    }
    return ok(Json.toJson(result));
  }

  public Result updatePlayerDeck(Http.Request request)
      throws ExecutionException, InterruptedException, JsonProcessingException {
    JsonNode jsonRequest = request.body().asJson();
    if (jsonRequest == null || !sessionController.verifyIdSession(jsonRequest.findPath("idSession").asLong())) {
      return badRequest();
    }

    Long idxPlayer = sessionController.getIdPlayerSession(jsonRequest.findPath("idSession").asLong());

    JsonNode jsonDeck = Json.parse(jsonRequest.findPath("deckType").textValue());
    Long idxDeck=jsonDeck.get("id").asLong();
    String deckName = jsonDeck.get("name").asText();

    Optional<Deck> playerDeck=jpaDeckRepository.getDeckPlayer(idxPlayer).stream().filter(
        deck -> deck.getIdDeck().equals(idxDeck)).findFirst();
    //if deck exists already
    if(playerDeck.isPresent() && !playerDeck.get().getName().equals(deckName)) {
      //update name
    } else if (idxDeck<0) { //new decks id always equal -1
      //insert new deck
    }

    JsonNode cards= jsonDeck.get("cards");
    List<Card> cardTypes =cardController.getCards();

    jpaDeckCardRepository.removeDeckCards(idxDeck);

    for (JsonNode card : cards) {
      Long idxCard = cardTypes.stream().filter(card1 -> card1.getName().equals(
          card.get("name").asText())).findFirst().get().getIdCard();

      DeckCard newCard= new DeckCard();
      newCard.setQuantity(card.get("quantity").asInt());
      newCard.setIdDeckCard(new DeckCardId(idxDeck, idxCard));

      jpaDeckCardRepository.addDeckCard(newCard);
    }

    return ok("Red leader, standing by");
  }
}
