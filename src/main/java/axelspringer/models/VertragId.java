package axelspringer.models;

import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;


public class VertragId implements Serializable {

    @Id
    private String vertragsart;
    @Id
    private Long azubi_id;

    public VertragId() {
    }

    public VertragId(String vertragsart, Long azubi_id) {
        this.vertragsart = vertragsart;
        this.azubi_id = azubi_id;
    }

    public String getVertragsart() {
        return vertragsart;
    }

    public void setVertragsart(String vertragsart) {
        this.vertragsart = vertragsart;
    }

    public Long getAzubi_id() {
        return azubi_id;
    }

    public void setAzubi_id(Long azubi_id) {
        this.azubi_id = azubi_id;
    }
}
