package controllers;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.card.JPAHeroRepository;
import database.card.JPASpyRepository;
import database.card.JPAUnitRepository;
import database.card.JPAVehicleRepository;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.inject.Inject;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

public class CardController {
 private final JPAHeroRepository heroRepository;
 private final JPAUnitRepository unitRepository;
 private final JPAVehicleRepository vehicleRepository;
 private final JPASpyRepository spyRepository;
 private final SessionController sessionController;

  @Inject
  public CardController(JPAHeroRepository heroRepository,
      JPAUnitRepository unitRepository, JPAVehicleRepository vehicleRepository,
      JPASpyRepository spyRepository,
      SessionController sessionController) {
    this.heroRepository = heroRepository;
    this.unitRepository = unitRepository;
    this.vehicleRepository = vehicleRepository;
    this.spyRepository = spyRepository;
    this.sessionController = sessionController;
  }

  public Result getCards(Http.Request request) throws ExecutionException, InterruptedException {
    JsonNode json = request.body().asJson();
    if (json == null || !sessionController.verifyIdSession(json.findPath("idSession").asLong())) {
      return badRequest();
    }

    ObjectNode result=Json.newObject();
    result.set("heroes", Json.toJson(heroRepository.getCards().stream().map(Json::toJson).collect(
        Collectors.toList())));
    result.set("units", Json.toJson(unitRepository.getCards().stream().map(Json::toJson).collect(
        Collectors.toList())));
    result.set("vehicles", Json.toJson(vehicleRepository.getCards().stream().map(Json::toJson).collect(
        Collectors.toList())));
    result.set("spies", Json.toJson(spyRepository.getCards().stream().map(Json::toJson).collect(
        Collectors.toList())));
    return ok(result);
  }
}
