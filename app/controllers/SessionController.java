package controllers;

import static play.mvc.Results.*;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.player.*;
import database.session.*;
import database.system.JPAVersionRepository;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import play.libs.Json;
import play.mvc.*;

public class SessionController {
  private final JPAPlayerRepository playerRepository;
  private final JPASessionRepository sessionRepository;
  private final JPAVersionRepository versionRepository;

  @Inject
  public SessionController(JPAPlayerRepository playerRepository,
      JPASessionRepository sessionRepository,
      JPAVersionRepository versionRepository) {
    this.playerRepository = playerRepository;
    this.sessionRepository = sessionRepository;
    this.versionRepository = versionRepository;
  }

  public Result login(Http.Request request) throws ExecutionException, InterruptedException {
    JsonNode json = request.body().asJson();
    ObjectNode result = Json.newObject();
    if (json == null) {
      throw new AuthentificationFailedException();
    }
    String name = json.findPath("username").textValue();
    String password = json.findPath("password").textValue();
    Double clientVersion = json.findPath("version").asDouble();
    if (name == null || password == null) {
      throw new AuthentificationFailedException();
    }

    Optional<Player> player = playerRepository.getPlayer(name).get();
    if(player.isEmpty()){
      return badRequest("User doesn't exist");
    }

    if (!BCrypt.verifyer().verify(password.toCharArray(),player.get().getPasswordHash()).verified) {
      return badRequest("Wrong password");
    }

    if(!versionRepository.getVersionNumber().equals(clientVersion)){
      return badRequest("Wrong client version");
    }

    Session newSession = new Session();
    newSession.setIdxPlayer(player.get().getIdPlayer());
      try {
        result.put("granted", true)
            .put("idSession", sessionRepository.addSession(newSession).get().getIdSession());
        return ok(result);
      } catch (Exception exception){
        return badRequest("User already connected");
      }
  }

  public Result logout(Http.Request request) throws ExecutionException, InterruptedException {
    JsonNode json = request.body().asJson();
    if (json == null) {
      throw new AuthentificationFailedException();
    }

    Long idSession=json.findPath("idSession").asLong();
    if(!verifyIdSession(idSession)){
      return badRequest();
    }
    ObjectNode result = Json.newObject();
    sessionRepository.removeSession(idSession);
    result.put("granted", false);
    return ok(result);
  }

  public boolean verifyIdSession(Long idSession) throws ExecutionException, InterruptedException {
    return sessionRepository.getSession(idSession).get().isPresent();
  }

  public Long getIdPlayerSession(Long idSession) throws ExecutionException, InterruptedException {
    return sessionRepository.getSession(idSession).get().get().getIdxPlayer();
  }
}
