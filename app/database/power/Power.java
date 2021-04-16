package database.power;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="power")
public class Power {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name="id_power")
  private Long idPower;

  @Column(name="name")
  private String name;

  public void setName(String name) {
    this.name = name;
  }

  public Long getIdPower() {
    return idPower;
  }

  public String getName() {
    return name;
  }
}
