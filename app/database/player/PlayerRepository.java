package database.player;

import com.google.inject.ImplementedBy;
import java.util.concurrent.CompletionStage;

@ImplementedBy(JPAPlayerRepository.class)
public interface PlayerRepository {
  CompletionStage<Player> addPlayer(Player player);
}
