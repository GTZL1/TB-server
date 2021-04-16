package controllers;

import static play.mvc.Results.ok;

import com.fasterxml.jackson.databind.node.ObjectNode;
import database.power.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.inject.*;
import play.libs.Json;
import play.mvc.*;

public class PowerController {
  private final JPAPowerRepository powerRepository;

  @Inject
  public PowerController(JPAPowerRepository powerRepository) {
    this.powerRepository = powerRepository;
  }

  public Result getPowers() throws ExecutionException, InterruptedException {
    // TODO require and verify idSession

    return ok(Json.toJson(powerRepository.getPowers().get().stream().map(Json::toJson).collect(
        Collectors.toList())));
  }
}
