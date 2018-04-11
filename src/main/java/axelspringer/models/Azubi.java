package axelspringer.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Azubi.
 */
@Entity
@Table(name = "Azubis")
public class Azubi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String email;

    @NotNull
    private String name;

    private String vorname;
    private String beruf;
    private String Strasse;
    private Integer plz;
    private Date gebDatum;
    private String gebOrt;
    private Integer ausbildungsstart;


    /**
     * Instantiates a new Azubi.
     */
    public Azubi() {
    }

    /**
     * Instantiates a new Azubi.
     *
     * @param id the id
     */
    public Azubi(long id) {
        this.id = id;
    }

    /**
     * Instantiates a new Azubi.
     *
     * @param email            the email
     * @param name             the name
     * @param vorname          the vorname
     * @param beruf            the beruf
     * @param strasse          the strasse
     * @param plz              the plz
     * @param gebDatum         the geb datum
     * @param gebOrt           the geb ort
     * @param ausbildungsstart the ausbildungsstart
     */
    public Azubi(String email, String name, String vorname, String beruf, String strasse, Integer plz, Date gebDatum, String gebOrt, Integer ausbildungsstart) {
        this.email = email;
        this.name = name;
        this.vorname = vorname;
        this.beruf = beruf;
        this.Strasse = strasse;
        this.plz = plz;
        this.gebDatum = gebDatum;
        this.gebOrt = gebOrt;
        this.ausbildungsstart = ausbildungsstart;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param value the value
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param value the value
     */
    public void setEmail(String value) {
        this.email = value;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param value the value
     */
    public void setName(String value) {
        this.name = value;
    }


    /**
     * Gets vorname.
     *
     * @return the vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Sets vorname.
     *
     * @param value the value
     */
    public void setVorname(String value) {
        this.vorname = value;
    }


    /**
     * Gets beruf.
     *
     * @return the beruf
     */
    public String getBeruf() {
        return beruf;
    }

    /**
     * Sets beruf.
     *
     * @param value the value
     */
    public void setBeruf(String value) {
        this.beruf = value;
    }


    /**
     * Gets strasse.
     *
     * @return the strasse
     */
    public String getStrasse() {
        return Strasse;
    }

    /**
     * Sets strasse.
     *
     * @param value the value
     */
    public void setStrasse(String value) {
        this.Strasse = value;
    }


    /**
     * Gets plz.
     *
     * @return the plz
     */
    public Integer getPLZ() {
        return plz;
    }

    /**
     * Sets plz.
     *
     * @param value the value
     */
    public void setPLZ(Integer value) {
        this.plz = value;
    }


    /**
     * Gets geb datum.
     *
     * @return the geb datum
     */
    public Date getGebDatum() {
        System.out.print(gebDatum);
        System.out.print(gebDatum);

        return gebDatum;
    }

    /**
     * Sets geb datum.
     *
     * @param value the value
     */
    public void setGebDatum(Date value) {
        this.gebDatum = value;
    }


    /**
     * Gets geb ort.
     *
     * @return the geb ort
     */
    public String getGebOrt() {
        return gebOrt;
    }

    /**
     * Sets geb ort.
     *
     * @param value the value
     */
    public void setGebOrt(String value) {
        this.gebOrt = value;
    }

    /**
     * Gets ausbildungsstart.
     *
     * @return the ausbildungsstart
     */
    public Integer getAusbildungsstart() {
        return ausbildungsstart;
    }

    /**
     * Sets ausbildungsstart.
     *
     * @param value the value
     */
    public void setAusbildungsstart(Integer value) {
        this.ausbildungsstart = value;
    }

}
