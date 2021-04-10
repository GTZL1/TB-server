package database.session;

import com.google.inject.ImplementedBy;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ImplementedBy(JPASessionRepository.class)
public interface SessionRepository {
  CompletableFuture<Session> addSession(Session session);
  CompletionStage<Session> getSessionPlayerId(Long idxPlayer);
  CompletionStage<Session> removeSession(Session session);
}
