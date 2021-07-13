package database.system;

import com.google.inject.ImplementedBy;
import java.util.concurrent.ExecutionException;

@ImplementedBy(JPAVersionRepository.class)
public interface VersionRepository {
  Double getVersionNumber() throws ExecutionException, InterruptedException;
}
