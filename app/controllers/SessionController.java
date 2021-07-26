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

/**
 * Execute requests to login and logout a client.
 * Provides idSession verification for other players.
 */
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

  /**
   * Create a new session for a client
   * @param request username and password of client sending the request
   * @return ok if success, badRequest if user doesn't exist, already connected, password is wrong or client version is wrong
   * @throws ExecutionException if a problem happens accessing the database
   * @throws InterruptedException if a problem happens accessing the database
   */
  public Result login(Http.Request request) throws ExecutionException, InterruptedException {
    JsonNode json = request.body().asJson();
    ObjectNode result = Json.newObject();
    if (json == null) {
      throw new AuthenticationFailedException();
    }
    String name = json.findPath("username").textValue();
    String password = json.findPath("password").textValue();
    String clientVersion = json.findPath("version").asText();
    if (name == null || password == null) {
      throw new AuthenticationFailedException();
    }

    Optional<Player> player = playerRepository.getPlayer(name);
    if(player.isEmpty()){
      return badRequest("User doesn't exist");
    }

    if (!BCrypt.verifyer().verify(password.toCharArray(),player.get().getPasswordHash()).verified) {
      return badRequest("Wrong password");
    }

    if(!versionRepository.getVersionNumber().equals(clientVersion)){
      return badRequest("Wrong client version, you use "+clientVersion+" instead of "+versionRepository.getVersionNumber());
    }

    Session newSession = new Session();
    newSession.setIdxPlayer(player.get().getIdPlayer());
      try {
        result.put("granted", true)
            .put("idSession", sessionRepository.addSession(newSession).getIdSession());
        return ok(result);
      } catch (Exception exception){
        return badRequest("User already connected");
      }
  }

  /**
   * Remove a client's session
   * @param request idSession to remove
   * @return ok if success, badRequest if idSession not valid
   * @throws ExecutionException if a problem happens accessing the database
   * @throws InterruptedException if a problem happens accessing the database
   */
  public Result logout(Http.Request request) throws ExecutionException, InterruptedException {
    JsonNode json = request.body().asJson();
    if (json == null) {
      throw new AuthenticationFailedException();
    }

    Long idSession=json.findPath("idSession").asLong();
    if(!verifyIdSession(idSession)){
      return badRequest("Session not valid");
    }
    ObjectNode result = Json.newObject();
    sessionRepository.removeSession(idSession);
    result.put("granted", false);
    return ok(result);
  }

  /**
   * Verify an idSession exists (and so is logged in). Used by other controllers.
   * @param idSession to verify
   * @return if idSession exists or not
   * @throws ExecutionException if a problem happens accessing the database
   * @throws InterruptedException if a problem happens accessing the database
   */
  public boolean verifyIdSession(Long idSession) throws ExecutionException, InterruptedException {
    return sessionRepository.getSession(idSession).isPresent();
  }

  /**
   * Fetch an idPlayer according to an idSession. idSession must be verified before.
   * @param idSession to link with an idPlayer
   * @return idPlayer found
   * @throws ExecutionException if a problem happens accessing the database
   * @throws InterruptedException if a problem happens accessing the database
   */
  public Long getIdPlayerSession(Long idSession) throws ExecutionException, InterruptedException {
    return sessionRepository.getSession(idSession).get().getIdxPlayer();
  }
}
