package database.game;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GameId implements Serializable {
  @Column(name="idx_player_winner")
  private Long idxWinner;

  @Column(name = "idx_player_looser")
  private Long idxLooser;

  @Column(name="date")
  private LocalDateTime date;

  public GameId(Long idxWinner, Long idxLooser, LocalDateTime date) {
    this.idxWinner = idxWinner;
    this.idxLooser = idxLooser;
    this.date = date;
  }

  public GameId() {
  }

  public Long getIdxWinner() {
    return idxWinner;
  }

  public Long getIdxLooser() {
    return idxLooser;
  }

  public LocalDateTime getDate() {
    return date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GameId)) {
      return false;
    }
    GameId gameId = (GameId) o;
    return idxWinner.equals(gameId.idxWinner) && idxLooser.equals(gameId.idxLooser) && date
        .equals(gameId.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idxWinner, idxLooser, date);
  }
}
