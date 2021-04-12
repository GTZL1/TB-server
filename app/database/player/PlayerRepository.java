package database.player;

import com.google.inject.ImplementedBy;
import java.util.concurrent.*;

@ImplementedBy(JPAPlayerRepository.class)
public interface PlayerRepository {
  CompletableFuture<Player> addPlayer(Player player);
}
