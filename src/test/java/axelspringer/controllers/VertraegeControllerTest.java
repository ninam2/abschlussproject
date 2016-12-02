package axelspringer.controllers;

import axelspringer.models.Vertrag;
import axelspringer.models.VertragDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * The type Vertraege controller test.
 */
@RunWith(MockitoJUnitRunner.class)
public class VertraegeControllerTest {


    /**
     * The Vertraege.
     */
    public List<Vertrag> vertraege = new ArrayList<>();
    /**
     * The Azubi i ds.
     */
    protected List<Integer> azubiIDs = new ArrayList<>();
    /**
     * The Vertrag controller.
     */
    @InjectMocks
    VertraegeController vertragController;
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
     * Test create vertraege.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCreateVertraege() throws Exception {

        when(vertragDao.getVertragById((long) 1, "Testvertrag")).thenReturn(vertrag1);
        doNothing().when(vertragDao).createVertraege(vertrag1);
        String response = vertragController.createVertraege("Testvertrag", (long) 1, "unbearbeitet");
        assertThat(response, equalTo("Contruct succesfully created!"));
    }

    /**
     * Test update vertraege.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUpdateVertraege() throws Exception {
        azubiIDs.add(0);
        azubiIDs.add(1);
        azubiIDs.add(2);
        azubiIDs.add(3);
        when(vertragDao.getVertragById((long) 1, "Testvertrag")).thenReturn(vertrag1);
        doNothing().when(vertragDao).createVertraege(vertrag1);
        String response = vertragController.updateVertraege("Testvertrag", (long) 1, "bearbeitet");
        assertThat(response, equalTo("Contruct succesfully updated!"));
    }

    /**
     * Test delete vertrag by id.
     *
     * @throws Exception the exception
     */
    @Test
    public void testDeleteVertragById() throws Exception {
        vertraege.add(vertrag2);
        vertraege.add(vertrag3);
        vertraege.add(vertrag4);
        doNothing().when(vertragDao).deleteVertragById(vertrag1);
        String response = vertragController.deleteVertragById("Testvertrag", (long) 1);
        assertThat(response, equalTo("Contruct succesfully deleted!"));
    }

    /**
     * Test find allvertraege.
     *
     * @throws Exception the exception
     */
    @Test
    public void testFindAllvertraege() throws Exception {
        vertraege.add(vertrag1);
        vertraege.add(vertrag2);
        vertraege.add(vertrag3);
        vertraege.add(vertrag4);
        when(vertragDao.getAllVertraege()).thenReturn(vertraege);
        String response = vertragController.findAllvertraege();
        assertThat(response, equalTo("{\"vertraginfos\":[{\"vertragsart\":\"Testvertrag\",\"azubiId\":1,\"vertragsvalue\":\"unbearbeitet\"},{\"vertragsart\":\"Azubivertrag\",\"azubiId\":1,\"vertragsvalue\":\"bearbeitet\"},{\"vertragsart\":\"Testvertrag\",\"azubiId\":2,\"vertragsvalue\":\"unbearbeitet\"},{\"vertragsart\":\"Azubivertrag\",\"azubiId\":2,\"vertragsvalue\":\"unbearbeitet\"}]}"));
    }

    /**
     * Test find all vertraege by azubi id.
     *
     * @throws Exception the exception
     */
    @Test
    public void testFindAllVertraegeByAzubiId() throws Exception {
        vertraege.add(vertrag1);
        vertraege.add(vertrag2);
        when(vertragDao.getAllVertragsartenByAzubiID((long) 1)).thenReturn(vertraege);
        String response = vertragController.findAllVertraegeByAzubiId((long) 1);
        assertThat(response, equalTo("{\"vertraginfos\":[{\"vertragsart\":\"Testvertrag\",\"azubiId\":1,\"vertragsvalue\":\"unbearbeitet\"},{\"vertragsart\":\"Azubivertrag\",\"azubiId\":1,\"vertragsvalue\":\"bearbeitet\"}]}"));
    }
}