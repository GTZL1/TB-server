package controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.OK;

import at.favre.lib.crypto.bcrypt.BCrypt;
import database.player.JPAPlayerRepository;
import database.player.Player;
import database.session.JPASessionRepository;
import database.session.Session;
import database.system.JPAVersionRepository;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import javax.persistence.EntityExistsException;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import play.libs.Json;
import play.mvc.Http.Request;
import play.mvc.Http.RequestBuilder;

public class SessionControllerTest {
  JPAVersionRepository versionRepository= mock(JPAVersionRepository.class);
  JPASessionRepository sessionRepository = mock(JPASessionRepository.class);
  JPAPlayerRepository playerRepository = mock(JPAPlayerRepository.class);
  SessionController sessionController = new SessionController(playerRepository, sessionRepository, versionRepository);

  @Test
  public void loginWithRightInfosWork() throws ExecutionException, InterruptedException {
    when(playerRepository.getPlayer("aloy")).thenReturn(Optional.of(new Player(1L, "aloy",
        BCrypt.withDefaults().hashToString(8, "chieftain".toCharArray()))));
    when(versionRepository.getVersionNumber()).thenReturn("1.1");
    when(sessionRepository.addSession(any(Session.class))).then(AdditionalAnswers.returnsFirstArg());
    Request httpRequest = new RequestBuilder().method("GET").uri("/login").bodyJson(
        Json.newObject().put("username", "aloy").put("password", "chieftain").put("version", 1.1)).build();

    assertEquals(OK, sessionController.login(httpRequest).status());
  }

  @Test
  public void loginWithWrongPasswordOrVersionFails()
      throws ExecutionException, InterruptedException {
    when(playerRepository.getPlayer("aloy")).thenReturn(Optional.of(new Player(1L, "aloy",
        BCrypt.withDefaults().hashToString(8, "chieftain".toCharArray()))));
    when(versionRepository.getVersionNumber()).thenReturn("1.1");
    when(sessionRepository.addSession(any(Session.class))).then(AdditionalAnswers.returnsFirstArg());

    Request wrongPwdRequest = new RequestBuilder().method("GET").uri("/login").bodyJson(
        Json.newObject().put("username", "aloy").put("password", "chieftan").put("version", 1.1)).build();
    Request wrongVersionRequest = new RequestBuilder().method("GET").uri("/login").bodyJson(
        Json.newObject().put("username", "aloy").put("password", "chieftain").put("version", 1.0)).build();

    assertEquals(BAD_REQUEST, sessionController.login(wrongPwdRequest).status());
    assertEquals(BAD_REQUEST, sessionController.login(wrongVersionRequest).status());
  }

  @Test
  public void nonExistingUserCannotLogin() throws ExecutionException, InterruptedException {
    when(playerRepository.getPlayer("aloy")).thenReturn(Optional.of(new Player(1L, "aloy",
        BCrypt.withDefaults().hashToString(8, "chieftain".toCharArray()))));
    when(playerRepository.getPlayer("ikrie")).thenReturn(Optional.empty());
    when(versionRepository.getVersionNumber()).thenReturn("1.1");
    when(sessionRepository.addSession(any(Session.class))).then(AdditionalAnswers.returnsFirstArg());

    Request httpRequest = new RequestBuilder().method("GET").uri("/login").bodyJson(
        Json.newObject().put("username", "ikrie").put("password", "banuk").put("version", 1.1)).build();

    assertEquals(BAD_REQUEST, sessionController.login(httpRequest).status());
  }

  @Test
  public void userCannotLoginTwice() throws ExecutionException, InterruptedException {
    when(playerRepository.getPlayer("aloy")).thenReturn(Optional.of(new Player(1L, "aloy",
        BCrypt.withDefaults().hashToString(8, "chieftain".toCharArray()))));
    when(versionRepository.getVersionNumber()).thenReturn("1.1");
    when(sessionRepository.addSession(any(Session.class))).then(AdditionalAnswers.returnsFirstArg());

    Request httpRequest = new RequestBuilder().method("GET").uri("/login").bodyJson(
        Json.newObject().put("username", "aloy").put("password", "chieftain").put("version", 1.1)).build();
    assertEquals(OK, sessionController.login(httpRequest).status());

    when(sessionRepository.addSession(any(Session.class))).thenThrow(new EntityExistsException());
    assertEquals(BAD_REQUEST, sessionController.login(httpRequest).status());
  }
}
