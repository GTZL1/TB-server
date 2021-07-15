package database.system;

import com.google.inject.ImplementedBy;
import java.util.concurrent.ExecutionException;

@ImplementedBy(JPAVersionRepository.class)
public interface VersionRepository {
  String getVersionNumber() throws ExecutionException, InterruptedException;
}
