package controllers;

import static play.mvc.Results.*;

import com.fasterxml.jackson.databind.JsonNode;
import database.power.JPAPowerRepository;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.inject.Inject;
import play.libs.Json;
import play.mvc.*;

public class PowerController {
  private final JPAPowerRepository powerRepository;
  private final SessionController sessionController;

  @Inject
  public PowerController(JPAPowerRepository powerRepository,
      SessionController sessionController) {
    this.powerRepository = powerRepository;
    this.sessionController = sessionController;
  }

  public Result getPowers(Http.Request request) throws ExecutionException, InterruptedException {
    JsonNode json = request.body().asJson();
    if (json == null || !sessionController.verifyIdSession(json.findPath("idSession").asLong())) {
      return badRequest();
    }

    return ok(Json.toJson(powerRepository.getPowers().get().stream().map(Json::toJson).collect(
        Collectors.toList())));
  }
}
