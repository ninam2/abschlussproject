package axelspringer.controllers;

import axelspringer.models.Azubi;
import axelspringer.models.AzubiDao;
import axelspringer.models.Vertrag;
import axelspringer.models.VertragDao;
import javafx.beans.binding.ListBinding;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * The type Azubi controller test.
 */
@RunWith(MockitoJUnitRunner.class)
public class AzubiControllerTest {

    /**
     * The Azubis.
     */
    public List<Azubi> azubis = new ArrayList<>();
    /**
     * The Vertraege.
     */
    public List<Vertrag> vertraege = new ArrayList<>();
    /**
     * The Formatter.
     */
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * The Date.
     */
    Date date = new Date(2004 - 06 - 27);
    /**
     * The Cur date.
     */
    final String curDate = formatter.format(date);
    /**
     * The Azubi.
     */
    Azubi azubi = new Azubi("test@email.de", "testname", "testvorname", "testberuf", "teststrasse", 98765, date, "testGebort", 2002);
    /**
     * The Azubi 2.
     */
    Azubi azubi2 = new Azubi("nina@email.de", "müller", "nina", "fachinformatiker", "teststrasse", 12345, date, "testGebort", 2016);
    /**
     * The Azubi 3.
     */
    Azubi azubi3 = new Azubi("test2@email.de", "mustermann", "max", "testberuf", "teststrasse", 98765, date, "testGebort", 2002);
    /**
     * The Vertrag 1.
     */
    Vertrag vertrag1 = new Vertrag("Testvertrag", (long) 1, "unbearbeitet");
    /**
     * The Vertrag 2.
     */
    Vertrag vertrag2 = new Vertrag("Azubivertrag", (long) 1, "bearbeitet");
    /**
     * The Vertrag 3.
     */
    Vertrag vertrag3 = new Vertrag("Testvertrag", (long) 2, "unbearbeitet");
    /**
     * The Vertrag 4.
     */
    Vertrag vertrag4 = new Vertrag("Azubivertrag", (long) 2, "unbearbeitet");
    /**
     * The Azubi dao.
     */
    @Mock
    AzubiDao azubiDao;

    /**
     * The Vertrag dao.
     */
    @Mock
    VertragDao vertragDao;

    /**
     * The Entity manager.
     */
    @Mock
    EntityManager entityManager;


    /**
     * The Azubi controller.
     */
    @InjectMocks
    AzubiController azubiController;


    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test update name.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateName() throws Exception {
        azubi.setAusbildungsstart(2002);
        when(azubiDao.getById(1)).thenReturn(azubi);
        doNothing().when(azubiDao).update(azubi);
        String response = azubiController.updateName(1, "test@email.de", "testname", "testvorname", "testberuf", "teststrasse", 98765, "testGebort", azubi.getAusbildungsstart());
        assertThat(response, equalTo("Azubi succesfully updated!"));

    }

    /**
     * Test delete.
     *
     * @throws Exception the exception
     */
    @Test
    public void testDelete() throws Exception {

        doNothing().when(vertragDao).deleteVertragById(vertrag1);
        doNothing().when(azubiDao).delete(azubi);
        String response = azubiController.delete(1);
        assertThat(response, equalTo("Azubi succesfully deleted!"));

    }

    /**
     * Test find all azubis.
     *
     * @throws Exception the exception
     */
    @Test
    public void testFindAllAzubis() throws Exception {
        azubi.setId(1);
        azubi2.setId(2);

        azubis.add(azubi);
        azubis.add(azubi2);

        vertraege.add(vertrag1);
        vertraege.add(vertrag2);
        vertraege.add(vertrag3);
        vertraege.add(vertrag4);

        when(azubiDao.getAll()).thenReturn(azubis);
        when(vertragDao.getAllVertragsartenByAzubiID(azubi.getId())).thenReturn(vertraege);
        String response = azubiController.findAllAzubis();
        String expectedJSON = "{\"azubiinfos\":[{\"beruf\":\"testberuf\",\"strasse\":\"teststrasse\",\"vorname\":\"testvorname\",\"name\":\"testname\",\"id\":1,\"gebOrt\":\"testGebort\",\"vertraege\":{\"vertraginfos\":[{\"vertragsvalue\":\"unbearbeitet\",\"vertragsattribut\":\"Testvertrag\"},{\"vertragsvalue\":\"bearbeitet\",\"vertragsattribut\":\"Azubivertrag\"},{\"vertragsvalue\":\"unbearbeitet\",\"vertragsattribut\":\"Testvertrag\"},{\"vertragsvalue\":\"unbearbeitet\",\"vertragsattribut\":\"Azubivertrag\"}]},\"email\":\"test@email.de\",\"gebDatum\":\"Thu Jan 01 01:00:01 CET 1970\",\"ausbildungsstart\":2002,\"plz\":98765},{\"beruf\":\"fachinformatiker\",\"strasse\":\"teststrasse\",\"vorname\":\"nina\",\"name\":\"müller\",\"id\":2,\"gebOrt\":\"testGebort\",\"vertraege\":{\"vertraginfos\":[]},\"email\":\"nina@email.de\",\"gebDatum\":\"Thu Jan 01 01:00:01 CET 1970\",\"ausbildungsstart\":2016,\"plz\":12345}]}";
        assertThat(response, equalTo(expectedJSON));
    }

    /**
     * Test find vertraege by azubi id.
     *
     * @throws Exception the exception
     */
    @Test
    public void testFindVertraegeByAzubiId() throws Exception {

        azubis.add(azubi);
        azubis.add(azubi2);

        vertraege.add(vertrag1);
        vertraege.add(vertrag2);
        vertraege.add(vertrag3);

        when(azubiDao.getAllByVertrag(vertrag1.getVertragsart())).thenReturn(azubis);
        String response = azubiController.findVertraegeByAzubiId(vertrag1.getVertragsart());
        String expectedJSON = "{\"azubiinfos\":[{\"beruf\":\"testberuf\",\"strasse\":\"teststrasse\",\"vorname\":\"testvorname\",\"name\":\"testname\",\"id\":0,\"gebOrt\":\"testGebort\",\"vertraege\":{\"vertraginfos\":[]},\"email\":\"test@email.de\",\"gebDatum\":\"Thu Jan 01 01:00:01 CET 1970\",\"ausbildungsstart\":2002,\"plz\":98765},{\"beruf\":\"fachinformatiker\",\"strasse\":\"teststrasse\",\"vorname\":\"nina\",\"name\":\"müller\",\"id\":0,\"gebOrt\":\"testGebort\",\"vertraege\":{\"vertraginfos\":[]},\"email\":\"nina@email.de\",\"gebDatum\":\"Thu Jan 01 01:00:01 CET 1970\",\"ausbildungsstart\":2016,\"plz\":12345}]}";
        assertThat(response, equalTo(expectedJSON));
    }

    /**
     * Test find vertraege by type and value.
     *
     * @throws Exception the exception
     */
    @Test
    public void testFindVertraegeByTypeAndValue() throws Exception {
        azubis.add(azubi2);
        vertraege.add(vertrag4);
        when(azubiDao.getAllByVertragsartAndValue("Azubivertrag", "unbearbeitet")).thenReturn(azubis);
        when(vertragDao.getAllVertragsartenByAzubiID(azubi.getId())).thenReturn(vertraege);
        String response = azubiController.findVertraegeByTypeAndValue("Azubivertrag", "unbearbeitet");

        String expectedJSON = "{\"azubiinfos\":[{\"beruf\":\"fachinformatiker\",\"strasse\":\"teststrasse\",\"vorname\":\"nina\",\"name\":\"müller\",\"id\":0,\"gebOrt\":\"testGebort\",\"vertraege\":{\"vertraginfos\":[{\"vertragsvalue\":\"unbearbeitet\",\"vertragsattribut\":\"Azubivertrag\"}]},\"email\":\"nina@email.de\",\"gebDatum\":\"Thu Jan 01 01:00:01 CET 1970\",\"ausbildungsstart\":2016,\"plz\":12345}]}";
        assertThat(response, equalTo(expectedJSON));
    }

    /**
     * Test find all azubis by year.
     *
     * @throws Exception the exception
     */
    @Test
    public void testFindAllAzubisByYear() throws Exception {
        azubis.add(azubi);
        azubis.add(azubi3);

        when(azubiDao.getAllByYear(2002)).thenReturn(azubis);
        String response = azubiController.findAllAzubisByYear(2002);

        String expectedJSON = "{\"azubiinfos\":[{\"beruf\":\"testberuf\",\"strasse\":\"teststrasse\",\"vorname\":\"testvorname\",\"name\":\"testname\",\"id\":0,\"gebOrt\":\"testGebort\",\"vertraege\":{\"vertraginfos\":[]},\"email\":\"test@email.de\",\"gebDatum\":\"Thu Jan 01 01:00:01 CET 1970\",\"ausbildungsstart\":2002,\"plz\":98765},{\"beruf\":\"testberuf\",\"strasse\":\"teststrasse\",\"vorname\":\"max\",\"name\":\"mustermann\",\"id\":0,\"gebOrt\":\"testGebort\",\"vertraege\":{\"vertraginfos\":[]},\"email\":\"test2@email.de\",\"gebDatum\":\"Thu Jan 01 01:00:01 CET 1970\",\"ausbildungsstart\":2002,\"plz\":98765}]}";
        assertThat(response, equalTo(expectedJSON));
    }

    /**
     * Test find all azubis by job.
     *
     * @throws Exception the exception
     */
    @Test
    public void testFindAllAzubisByJob() throws Exception {
        azubi.setId(1);
        azubi3.setId(3);
        azubis.add(azubi);
        azubis.add(azubi3);

        when(azubiDao.getAllByJob("testberuf")).thenReturn(azubis);
        String response = azubiController.findAllAzubisByJob("testberuf");

        String expectedJSON = "{\"azubiinfos\":[{\"beruf\":\"testberuf\",\"strasse\":\"teststrasse\",\"vorname\":\"testvorname\",\"name\":\"testname\",\"id\":1,\"gebOrt\":\"testGebort\",\"vertraege\":{\"vertraginfos\":[]},\"email\":\"test@email.de\",\"gebDatum\":\"Thu Jan 01 01:00:01 CET 1970\",\"ausbildungsstart\":2002,\"plz\":98765},{\"beruf\":\"testberuf\",\"strasse\":\"teststrasse\",\"vorname\":\"max\",\"name\":\"mustermann\",\"id\":3,\"gebOrt\":\"testGebort\",\"vertraege\":{\"vertraginfos\":[]},\"email\":\"test2@email.de\",\"gebDatum\":\"Thu Jan 01 01:00:01 CET 1970\",\"ausbildungsstart\":2002,\"plz\":98765}]}";
        assertThat(response, equalTo(expectedJSON));
    }

    /**
     * Test find all azubis jobs and years.
     *
     * @throws Exception the exception
     */
    @Test
    public void testFindAllAzubisJobsAndYears() throws Exception {
        List<Integer> jahre = new ArrayList<>();
        List<String> jobs = new ArrayList<>();
        List<String> vertragsarten = new ArrayList<>();

        jahre.add(2002);
        jahre.add(2016);

        jobs.add("testberuf");
        jobs.add("fachinformatiker");

        vertragsarten.add("Testvertrag");
        vertragsarten.add("Azubivertrag");


        when(azubiDao.getAllJobs()).thenReturn(jobs);
        when(azubiDao.getAllYears()).thenReturn(jahre);
        when(vertragDao.getAllVertragsarten()).thenReturn(vertragsarten);

        String response = azubiController.findAllAzubisJobsAndYears();

        String expectedJSON = "{\"vertragsarten\":[{\"vertragsart\":\"Testvertrag\"},{\"vertragsart\":\"Azubivertrag\"}],\"jobs\":[{\"job\":\"testberuf\"},{\"job\":\"fachinformatiker\"}],\"years\":[{\"year\":2002},{\"year\":2016}]}";
        assertThat(response, equalTo(expectedJSON));


    }
}