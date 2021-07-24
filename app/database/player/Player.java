package database.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id_player")
  private Long idPlayer;

  @Column(name="username")
  private String username;

  @Column(name="password_hash")
  private String passwordHash;

  public Player() {

  }

  //used only in tests
  public Player(Long idPlayer, String username, String passwordHash) {
    this.idPlayer = idPlayer;
    this.username = username;
    this.passwordHash = passwordHash;
  }

  public Long getIdPlayer(){
    return idPlayer;
  }

  public String getUsername() {
    return this.username;
  }

  public String getPasswordHash(){
    return this.passwordHash;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }
}
