package controllers;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

import com.fasterxml.jackson.databind.JsonNode;
import database.game.Game;
import database.game.GameId;
import database.game.JPAGameRepository;
import database.player.JPAPlayerRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import play.mvc.Http;
import play.mvc.Result;

public class GameController {
  private final JPAGameRepository gameRepository;
  private final SessionController sessionController;
  private final JPAPlayerRepository jpaPlayerRepository;

  @Inject
  public GameController(JPAGameRepository gameRepository,
      SessionController sessionController, JPAPlayerRepository jpaPlayerRepository) {
    this.gameRepository = gameRepository;
    this.sessionController = sessionController;
    this.jpaPlayerRepository = jpaPlayerRepository;
  }

  public Result addNewGame(Http.Request request) throws ExecutionException, InterruptedException {
    JsonNode jsonRequest = request.body().asJson();
    if (jsonRequest == null || !sessionController.verifyIdSession(jsonRequest.findPath("idSession").asLong())) {
      return badRequest();
    }

    LocalDateTime date = LocalDateTime.parse(jsonRequest.findPath("date").asText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    GameId idGame = new GameId(jpaPlayerRepository.getPlayer(jsonRequest.findPath("winner").asText()).get().get().getIdPlayer(),
        jpaPlayerRepository.getPlayer(jsonRequest.findPath("looser").asText()).get().get().getIdPlayer(),
        date);
    if(!gameRepository.gameAlreadyExists(new Game(idGame))){
      gameRepository.addnewGame(new Game(idGame));
    }

    return ok();
  }
}
