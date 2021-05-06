package controllers;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.card.*;
import database.card.cards.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

public class CardController {
 private final JPAHeroRepository heroRepository;
 private final JPAUnitRepository unitRepository;
 private final JPAVehicleRepository vehicleRepository;
 private final JPASpyRepository spyRepository;
 private final JPABaseRepository baseRepository;
 private final SessionController sessionController;

  @Inject
  public CardController(JPAHeroRepository heroRepository,
      JPAUnitRepository unitRepository, JPAVehicleRepository vehicleRepository,
      JPASpyRepository spyRepository,
      JPABaseRepository baseRepository, SessionController sessionController) {
    this.heroRepository = heroRepository;
    this.unitRepository = unitRepository;
    this.vehicleRepository = vehicleRepository;
    this.spyRepository = spyRepository;
    this.baseRepository = baseRepository;
    this.sessionController = sessionController;
  }

  List<Card> getCards() throws ExecutionException, InterruptedException {
    return Stream.of(heroRepository.getCards(),
        unitRepository.getCards(),
        vehicleRepository.getCards(),
        spyRepository.getCards(),
        baseRepository.getCards()).flatMap(Collection::stream).collect(Collectors.toList());
  }

  public Result getCards(Http.Request request) throws ExecutionException, InterruptedException {
    JsonNode json = request.body().asJson();
    if (json == null || !sessionController.verifyIdSession(json.findPath("idSession").asLong())) {
      return badRequest();
    }

    ObjectNode result=Json.newObject();
    result.set("hero", Json.toJson(heroRepository.getCards().stream().map(Json::toJson).collect(
        Collectors.toList())));
    result.set("unit", Json.toJson(unitRepository.getCards().stream().map(Json::toJson).collect(
        Collectors.toList())));
    result.set("vehicle", Json.toJson(vehicleRepository.getCards().stream().map(Json::toJson).collect(
        Collectors.toList())));
    result.set("spy", Json.toJson(spyRepository.getCards().stream().map(Json::toJson).collect(
        Collectors.toList())));
    result.set("base", Json.toJson(baseRepository.getCards().stream().map(Json::toJson).collect(
        Collectors.toList())));
    return ok(result);
  }
}
