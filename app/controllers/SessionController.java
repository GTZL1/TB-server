package controllers;

import static play.mvc.Results.ok;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.player.JPAPlayerRepository;
import database.player.Player;
import database.session.AuthentificationFailedException;
import database.session.JPASessionRepository;
import database.session.Session;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

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
      System.out.println("hello");
      Session newSession = new Session();
      newSession.setIdxPlayer(player.get().getIdPlayer());
      result.put("granted", true)
          .put("idSession", sessionRepository.addSession(newSession).get().getIdSession());
      return ok(result);
    } else {
      throw new AuthentificationFailedException();
    }
  }
}
