package database.game;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="game")
public class Game {
  @EmbeddedId
  private GameId idGame;

  public Game(GameId idGame) {
    this.idGame = idGame;
  }

  public Game() {

  }

  public GameId getIdGame() {
    return idGame;
  }

  public void setIdGame(GameId idGame) {
    this.idGame = idGame;
  }
}
