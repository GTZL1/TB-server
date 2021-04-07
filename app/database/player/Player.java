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
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id_player")
  private Long idPlayer;

  @Column(name="username")
  private String username;

  @Column(name="password_hash")
  private String passwordHash;

  public Long getIdPlayer(){
    return idPlayer;
  }

  public String getUsername() {
    return this.username;
  }

  public String getPasswordHash(){
    return this.passwordHash;
  }
}
