package controllers;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

import com.fasterxml.jackson.databind.JsonNode;
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

/**
 * Execute requests to get, update and delete decks
 */
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

  /**
   * Return a player's serialized decks
   * @param request idSession to respond
   * @return player's serialized decks
   * @throws ExecutionException if a problem happens accessing the database
   * @throws InterruptedException if a problem happens accessing the database
   */
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

  /**
   * Register a new deck if not existing, update its cards otherwise
   * @param request idSession of client sending the request
   * @return ok if all is good, badRequest if idSession not valid
   * @throws ExecutionException if a problem happens accessing the database
   * @throws InterruptedException if a problem happens accessing the database
   */
  public Result updatePlayerDeck(Http.Request request)
      throws ExecutionException, InterruptedException {
    JsonNode jsonRequest = request.body().asJson();
    if (jsonRequest == null || !sessionController.verifyIdSession(jsonRequest.findPath("idSession").asLong())) {
      return badRequest();
    }

    Long idxPlayer = sessionController.getIdPlayerSession(jsonRequest.findPath("idSession").asLong());

    JsonNode jsonDeck = Json.parse(jsonRequest.findPath("deckType").textValue());
    Long idDeck=jsonDeck.get("id").asLong();
    String deckName = jsonDeck.get("name").asText();

    Long finalIdDeck = idDeck;
    Optional<Deck> playerDeck=jpaDeckRepository.getDeckPlayer(idxPlayer).stream().filter(
        deck -> deck.getIdDeck().equals(finalIdDeck)).findFirst();

    //if deck exists already
    if(playerDeck.isPresent() && !playerDeck.get().getName().equals(deckName)) {
      jpaDeckRepository.changeDeckName(idDeck, deckName);
    } else if (idDeck<0) { //if deck doesn't exist, new decks id always equal -1
      Deck newDeck = new Deck();
      newDeck.setPlayerAndName(idxPlayer, deckName);
      Long newIdDeck=jpaDeckRepository.addNewDeck(newDeck).getIdDeck();

      //return true new id
      if(!newIdDeck.equals(idDeck)){
        idDeck=newIdDeck;
      }
    }

    JsonNode cards= jsonDeck.get("cards");
    List<Card> cardTypes =cardController.getCards();

    //remove all cards of the deck
    jpaDeckCardRepository.removeDeckCards(idDeck);

    //register all cards of the deck
    for (JsonNode card : cards) {
      Long idxCard = cardTypes.stream().filter(card1 -> card1.getName().equals(
          card.get("name").asText())).findFirst().get().getIdCard();

      DeckCard newCard= new DeckCard();
      newCard.setQuantity(card.get("quantity").asInt());
      newCard.setIdDeckCard(new DeckCardId(idDeck, idxCard));

      jpaDeckCardRepository.addDeckCard(newCard);
    }

    return ok(Json.newObject().put("idDeck", idDeck));
  }

  /**
   * Delete a player's deck of the database
   * @param request idSession of client sending the request
   * @return ok if deletion succeeded, badRequest if idSession not valid
   * @throws ExecutionException if a problem happens accessing the database
   * @throws InterruptedException if a problem happens accessing the database
   */
  public Result removePlayerDeck(Http.Request request)
      throws ExecutionException, InterruptedException {
    JsonNode jsonRequest = request.body().asJson();
    if (jsonRequest == null || !sessionController
        .verifyIdSession(jsonRequest.findPath("idSession").asLong())) {
      return badRequest();
    }

    JsonNode jsonDeck = Json.parse(jsonRequest.findPath("deckType").textValue());
    jpaDeckRepository.removeDeck(jsonDeck.get("id").asLong());

    return ok();
  }
}
