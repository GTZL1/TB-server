package database.player;

import com.google.inject.ImplementedBy;
import java.util.Optional;
import java.util.concurrent.*;

@ImplementedBy(JPAPlayerRepository.class)
public interface PlayerRepository {
  CompletableFuture<Player> addPlayer(Player player);
  CompletableFuture<Optional<Player>> getPlayer(String username);
  CompletableFuture<Optional<Player>> getPlayer(Long idPlayer);
}
