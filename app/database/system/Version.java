package database.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="version_system")
public class Version {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name="id_version")
  private Long idVersion;

  @Column(name="version_number")
  private Short versionNumber;

  @Column(name="sub_version_number")
  private Short subVersionNumber;

  public String getVersionNumber() {
    return versionNumber.toString()+"."+subVersionNumber.toString();
  }
}
