package database.player;

import com.google.inject.ImplementedBy;
import java.util.Optional;
import java.util.concurrent.*;

@ImplementedBy(JPAPlayerRepository.class)
public interface PlayerRepository {
  Player addPlayer(Player player) throws ExecutionException, InterruptedException;
  Optional<Player> getPlayer(String username) throws ExecutionException, InterruptedException;
  Optional<Player> getPlayer(Long idPlayer) throws ExecutionException, InterruptedException;
}
