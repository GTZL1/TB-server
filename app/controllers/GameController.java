package controllers;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

import com.fasterxml.jackson.databind.JsonNode;
import database.game.Game;
import database.game.GameId;
import database.game.JPAGameRepository;
import database.player.JPAPlayerRepository;
import database.player.Player;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import play.libs.Json;
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
      return badRequest("Session not valid");
    }

    LocalDateTime date = LocalDateTime.parse(jsonRequest.findPath("date").asText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    GameId idGame = new GameId(jpaPlayerRepository.getPlayer(jsonRequest.findPath("winner").asText()).get().get().getIdPlayer(),
        jpaPlayerRepository.getPlayer(jsonRequest.findPath("looser").asText()).get().get().getIdPlayer(),
        date);
    if(!gameRepository.gameAlreadyExists(new Game(idGame))){
      gameRepository.addnewGame(new Game(idGame));
    } else {
      return badRequest("Problem in deletion");
    }

    return ok();
  }

  public Result getGames(Http.Request request) throws ExecutionException, InterruptedException {
    JsonNode jsonRequest = request.body().asJson();
    if (jsonRequest == null || !sessionController.verifyIdSession(jsonRequest.findPath("idSession").asLong())) {
      return badRequest();
    }

    Long idxPlayer = sessionController
        .getIdPlayerSession(jsonRequest.findPath("idSession").asLong());

    List<JsonNode> result = new ArrayList<>();
    for(Game g: gameRepository.getPlayerGames(idxPlayer)) {
      Player winner = jpaPlayerRepository.getPlayer(g.getIdGame().getIdxWinner()).get().get();
      String looser = jpaPlayerRepository.getPlayer(g.getIdGame().getIdxLooser()).get().get().getUsername();
      String date = g.getIdGame().getDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));

      result.add(Json.newObject().put("date", date).put("winner", winner.getUsername())
      .put("looser", looser).put("issue", (winner.getIdPlayer().equals(idxPlayer))));
    }

    return ok(Json.toJson(result));
  }
}
