package axelspringer.services;

import axelspringer.models.Azubi;

/**
 * Created by nmuelle2 on 06.09.16.
 */
public class AzubiDummy {
    public Azubi getdefaultAzubi() {
        Azubi azubi = new Azubi();
        azubi.setEmail("test@sowieso.de");
        azubi.setName("defaultname");
        return azubi;
    }
}
