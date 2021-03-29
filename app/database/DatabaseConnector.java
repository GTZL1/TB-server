package database;

import java.sql.Connection;
import javax.inject.*;
import play.db.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

class DatabaseConnector {
  private Database db;
  private DatabaseExecutionContext executionContext;

  @Inject
  public void JavaJdbcConnection(Database db, DatabaseExecutionContext executionContext) {
    this.db = db;
    this.executionContext = executionContext;
  }

  public CompletionStage<Void> createDatabase() {
    return CompletableFuture.runAsync(
        () -> {
          // get jdbc connection
          Connection connection = db.getConnection();

          return;
        },
        executionContext);
  }
}
