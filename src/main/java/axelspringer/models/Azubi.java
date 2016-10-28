package axelspringer.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Represents an Azubi for this web application.
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


    public Azubi() {
    }

    public Azubi(long id) {
        this.id = id;
    }

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

    public long getId() {
        return id;
    }
    public void setId(long value) {
        this.id = value;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String value) {
        this.email = value;
    }


    public String getName() {
        return name;
    }
    public void setName(String value) {
        this.name = value;
    }


    public String getVorname() {
        return vorname;
    }

    public void setVorname(String value) {
        this.vorname = value;
    }


    public String getBeruf() {
        return beruf;
    }

    public void setBeruf(String value) {
        this.beruf = value;
    }


    public String getStrasse() {
        return Strasse;
    }

    public void setStrasse(String value) {
        this.Strasse = value;
    }


    public Integer getPLZ() {
        return plz;
    }

    public void setPLZ(Integer value) {
        this.plz = value;
    }


    public Date getGebDatum() {
        return gebDatum;
    }

    public void setGebDatum(Date value) {
        this.gebDatum = value;
    }


    public String getGebOrt() {
        return gebOrt;
    }

    public void setGebOrt(String value) {
        this.gebOrt = value;
    }

    public Integer getAusbildungsstart() {
        return ausbildungsstart;
    }

    public void setAusbildungsstart(Integer value) {
        this.ausbildungsstart = value;
    }

}
