package database.session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="session")
public class Session {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name="id_session")
  private Long idSession;

  @Column(name="idx_player")
  private Long idxPlayer;

  //used for tests only
  public Session(Long idSession, Long idxPlayer) {
    this.idSession = idSession;
    this.idxPlayer = idxPlayer;
  }

  public Session() {
  }

  public void setIdxPlayer(Long idxPlayer) {
    this.idxPlayer = idxPlayer;
  }

  public Long getIdSession() {
    return idSession;
  }

  public Long getIdxPlayer() {
    return idxPlayer;
  }
}
