package database.power;

import com.google.inject.ImplementedBy;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@ImplementedBy(JPAPowerRepository.class)
public interface PowerRepository {
  CompletableFuture<List<Power>> getPowers();
}
