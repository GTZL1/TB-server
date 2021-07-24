package controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.OK;

import at.favre.lib.crypto.bcrypt.BCrypt;
import database.game.Game;
import database.game.GameId;
import database.game.JPAGameRepository;
import database.player.JPAPlayerRepository;
import database.player.Player;
import database.session.JPASessionRepository;
import database.session.Session;
import database.system.JPAVersionRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import play.libs.Json;
import play.mvc.Http.Request;
import play.mvc.Http.RequestBuilder;

public class GameControllerTest {
  JPASessionRepository sessionRepository = mock(JPASessionRepository.class);
  SessionController sessionController = new SessionController(mock(JPAPlayerRepository.class),
      sessionRepository, mock(JPAVersionRepository.class));
  JPAPlayerRepository playerRepository = mock(JPAPlayerRepository.class);
  JPAGameRepository gameRepository = mock(JPAGameRepository.class);
  GameController gameController = new GameController(gameRepository, sessionController, playerRepository);

  @Test
  public void insertAGameWithRightInfosWork() throws ExecutionException, InterruptedException {
    when(sessionRepository.getSession(1L)).thenReturn(Optional.of(new Session(1L, 1L)));
    when(playerRepository.getPlayer("aloy")).thenReturn(Optional.of(new Player(1L, "aloy",
        BCrypt.withDefaults().hashToString(8, "chieftain".toCharArray()))));
    when(playerRepository.getPlayer("ikrie")).thenReturn(Optional.of(new Player(2L, "ikrie",
        BCrypt.withDefaults().hashToString(8, "banuk".toCharArray()))));
    when(gameRepository.addnewGame(any(Game.class))).then(AdditionalAnswers.returnsFirstArg());

    Request httpRequest = new RequestBuilder().method("POST").uri("/game").bodyJson(
        Json.newObject().put("idSession", 1).put("date", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .put("winner", "aloy").put("looser", "ikrie")).build();

    assertEquals(OK, gameController.addNewGame(httpRequest).status());
  }

  @Test
  public void insertAGameWithWrongSessionDontWork() throws ExecutionException, InterruptedException {
    when(sessionRepository.getSession(1L)).thenReturn(Optional.empty());
    when(playerRepository.getPlayer("aloy")).thenReturn(Optional.of(new Player(1L, "aloy",
        BCrypt.withDefaults().hashToString(8, "chieftain".toCharArray()))));
    when(playerRepository.getPlayer("ikrie")).thenReturn(Optional.of(new Player(2L, "ikrie",
        BCrypt.withDefaults().hashToString(8, "banuk".toCharArray()))));
    when(gameRepository.addnewGame(any(Game.class))).then(AdditionalAnswers.returnsFirstArg());

    Request httpRequest = new RequestBuilder().method("POST").uri("/game").bodyJson(
        Json.newObject().put("idSession", 1).put("date", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .put("winner", "aloy").put("looser", "ikrie")).build();

    assertEquals(BAD_REQUEST, gameController.addNewGame(httpRequest).status());
  }

  @Test
  public void getGamesWithRightInfoWork() throws ExecutionException, InterruptedException {
    when(sessionRepository.getSession(1L)).thenReturn(Optional.of(new Session(1L, 1L)));
    when(playerRepository.getPlayer(1L)).thenReturn(Optional.of(new Player(1L, "aloy",
        BCrypt.withDefaults().hashToString(8, "chieftain".toCharArray()))));
    when(playerRepository.getPlayer(2L)).thenReturn(Optional.of(new Player(2L, "ikrie",
        BCrypt.withDefaults().hashToString(8, "banuk".toCharArray()))));
    when(gameRepository.getPlayerGames(any(Long.class))).thenReturn(List.of(
        new Game(new GameId(1L, 2L, LocalDateTime.now()))
    ));

    Request httpRequest = new RequestBuilder().method("POST").uri("/game").bodyJson(
        Json.newObject().put("idSession", 1)).build();

    assertEquals(OK, gameController.getGames(httpRequest).status());
  }
}
