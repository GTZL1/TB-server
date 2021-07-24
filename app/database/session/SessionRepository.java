package database.session;

import com.google.inject.ImplementedBy;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ImplementedBy(JPASessionRepository.class)
public interface SessionRepository {
  Session addSession(Session session) throws ExecutionException, InterruptedException;
  Optional<Session> getSession(Long idSession) throws ExecutionException, InterruptedException;
  CompletableFuture<Long> removeSession(Long idSession);
}
