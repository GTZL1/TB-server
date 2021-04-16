package database.session;

import com.google.inject.ImplementedBy;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@ImplementedBy(JPASessionRepository.class)
public interface SessionRepository {
  CompletableFuture<Session> addSession(Session session);
  CompletableFuture<Optional<Session>> getSession(Long idSession);
  CompletableFuture<Session> removeSession(Session session);
}
