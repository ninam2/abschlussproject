package axelspringer.models;

import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;


/**
 * The type Vertrag id.
 */
public class VertragId implements Serializable {

    @Id
    private String vertragsart;
    @Id
    private Long azubi_id;

    /**
     * Instantiates a new Vertrag id.
     */
    public VertragId() {
    }

    /**
     * Instantiates a new Vertrag id.
     *
     * @param vertragsart the vertragsart
     * @param azubi_id    the azubi id
     */
    public VertragId(String vertragsart, Long azubi_id) {
        this.vertragsart = vertragsart;
        this.azubi_id = azubi_id;
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
}
