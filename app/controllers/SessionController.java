package controllers;

import static play.mvc.Results.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.player.*;
import database.session.*;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import play.libs.Json;
import play.mvc.*;

public class SessionController {

  private final JPAPlayerRepository playerRepository;
  private final JPASessionRepository sessionRepository;

  @Inject
  public SessionController(JPAPlayerRepository playerRepository,
      JPASessionRepository sessionRepository) {
    this.playerRepository = playerRepository;
    this.sessionRepository = sessionRepository;
  }

  public Result login(Http.Request request) throws ExecutionException, InterruptedException {
    JsonNode json = request.body().asJson();
    ObjectNode result = Json.newObject();
    if (json == null) {
      throw new AuthentificationFailedException();
    }
    String name = json.findPath("username").textValue();
    String password = json.findPath("password").textValue();
    if (name == null || password == null) {
      throw new AuthentificationFailedException();
    }

    Optional<Player> player = playerRepository.getPlayer(name).get();
    if (player.isPresent()) {
      Session newSession = new Session();
      newSession.setIdxPlayer(player.get().getIdPlayer());
      result.put("granted", true)
          .put("idSession", sessionRepository.addSession(newSession).get().getIdSession());
      return ok(result);
    } else {
      throw new AuthentificationFailedException();
    }
  }

  public Result logout(Http.Request request) throws ExecutionException, InterruptedException {
    JsonNode json = request.body().asJson();
    if (json == null) {
      throw new AuthentificationFailedException();
    }

    Long idSession=json.findPath("idSession").asLong();
    Optional<Session> session =sessionRepository.getSession(idSession).get();

    if(session.isEmpty()){
      return badRequest();
    }
    ObjectNode result = Json.newObject();
    sessionRepository.removeSession(session.get());
    result.put("granted", false);
        //.put("idSession", suppr.getIdSession());
    return ok(result);
  }
}
