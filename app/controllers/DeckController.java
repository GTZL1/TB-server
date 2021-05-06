package controllers;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.deck.Deck;
import database.deck.JPADeckRepository;
import database.deckCard.DeckCard;
import database.deckCard.JPADeckCardRepository;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.inject.Inject;
import play.libs.Json;
import play.mvc.*;

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

  public Result getPlayerDeck(Http.Request request) throws ExecutionException, InterruptedException {
    JsonNode json = request.body().asJson();
    if (json == null || !sessionController.verifyIdSession(json.findPath("idSession").asLong())) {
      return badRequest();
    }

    Long idxPlayer=sessionController.getIdPlayerSession(json.findPath("idSession").asLong());
    List<Deck> decks=jpaDeckRepository.getDeckPlayer(idxPlayer);

    ObjectNode result= Json.newObject();
    ObjectNode deckTemp=Json.newObject();
    for(var d: decks){
     List<DeckCard> deckCards=jpaDeckCardRepository.getDeckCards(d.getIdDeck());
     deckTemp.put("name",d.getName());
     deckTemp.set("cards",
         Json.toJson(cardController.getCards().stream().filter(c -> deckCards.stream().map(dc -> dc.getIdxCard()).collect(
             Collectors.toList()).contains(c.getIdCard())).map(Json::toJson).collect(Collectors.toList())));
     result.set("deck",deckTemp);
    }
    return ok(result);
  }
}
