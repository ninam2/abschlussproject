package axelspringer.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * The type Vertrag.
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

    /**
     * Instantiates a new Vertrag.
     */
    public Vertrag() {
    }


    /**
     * Instantiates a new Vertrag.
     *
     * @param vertragsart   the vertragsart
     * @param azubi_id      the azubi id
     * @param vertragsvalue the vertragsvalue
     */
    public Vertrag(String vertragsart, Long azubi_id, String vertragsvalue) {
        this.azubi_id = azubi_id;
        this.vertragsart = vertragsart;
        this.vertragsvalue = vertragsvalue;
    }

    /**
     * Instantiates a new Vertrag.
     *
     * @param vertragsart the vertragsart
     * @param azubi_id    the azubi id
     */
    public Vertrag(String vertragsart, Long azubi_id) {
        this.azubi_id = azubi_id;
        this.vertragsart = vertragsart;
    }

    /**
     * Gets vertragsart.
     *
     * @return the vertragsart
     */
    public String getVertragsart() {
        return vertragsart;
    }

    /**
     * Sets vertragsart.
     *
     * @param vertragsart the vertragsart
     */
    public void setVertragsart(String vertragsart) {
        this.vertragsart = vertragsart;
    }

    /**
     * Gets azubi id.
     *
     * @return the azubi id
     */
    public Long getAzubi_id() {
        return azubi_id;
    }

    /**
     * Sets azubi id.
     *
     * @param azubi_id the azubi id
     */
    public void setAzubi_id(Long azubi_id) {
        this.azubi_id = azubi_id;
    }

    /**
     * Gets vertragsvalue.
     *
     * @return the vertragsvalue
     */
    public String getVertragsvalue() {
        return vertragsvalue;
    }

    /**
     * Sets vertragsvalue.
     *
     * @param value the value
     */
    public void setVertragsvalue(String value) {
        this.vertragsvalue = value;
    }

}
