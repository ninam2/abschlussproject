package axelspringer.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Represents an Azubi for this web application.
 */
@Entity
@Table(name = "vertraege")
@IdClass(VertragId.class)
public class Vertrag implements Serializable {

    @Id
    private String vertragsart;
    @Id
    private Long azubi_id;
    @NotNull
    private String vertragsvalue;

    public Vertrag() {
    }


    public Vertrag(String vertragsart, Long azubi_id, String vertragsvalue) {
        this.azubi_id = azubi_id;
        this.vertragsart = vertragsart;
        this.vertragsvalue = vertragsvalue;
    }

    public Vertrag(String vertragsart, Long azubi_id) {
        this.azubi_id = azubi_id;
        this.vertragsart = vertragsart;
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

    public String getVertragsvalue() {
        return vertragsvalue;
    }

    public void setVertragsvalue(String value) {
        this.vertragsvalue = value;
    }

}
