package database.game;

import com.google.inject.ImplementedBy;
import java.util.List;
import java.util.concurrent.ExecutionException;

@ImplementedBy(JPAGameRepository.class)
public interface GameRepository {
  Game addnewGame(Game game) throws ExecutionException, InterruptedException;
  boolean gameAlreadyExists(Game game) throws ExecutionException, InterruptedException;
  List<Game> getPlayerGames(Long idxPlayer) throws ExecutionException, InterruptedException;
}
