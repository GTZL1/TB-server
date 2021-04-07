package database.player;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {

  @Id
  private Long idPlayer;
  private String username;
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
