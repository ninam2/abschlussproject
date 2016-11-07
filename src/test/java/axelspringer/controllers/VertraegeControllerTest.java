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
 * Created by nmuelle2 on 04.11.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class VertraegeControllerTest {


    public List<Vertrag> vertraege = new ArrayList<>();
    protected List<Integer> azubiIDs = new ArrayList<>();
    @InjectMocks
    VertraegeController vertragController;
    @Mock
    VertragDao vertragDao;
    @Mock
    EntityManager entityManager;
    Vertrag vertrag1 = new Vertrag("Testvertrag", (long) 1, "unbearbeitet");
    Vertrag vertrag2 = new Vertrag("Azubivertrag", (long) 1, "bearbeitet");
    Vertrag vertrag3 = new Vertrag("Testvertrag", (long) 2, "unbearbeitet");
    Vertrag vertrag4 = new Vertrag("Azubivertrag", (long) 2, "unbearbeitet");

    @Test
    public void testCreateVertraege() throws Exception {

        when(vertragDao.getVertragById((long) 1, "Testvertrag")).thenReturn(vertrag1);
        doNothing().when(vertragDao).createVertraege(vertrag1);
        String response = vertragController.createVertraege("Testvertrag", (long) 1, "unbearbeitet");
        assertThat(response, equalTo("Contruct succesfully created!"));
    }

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

    @Test
    public void testDeleteVertragById() throws Exception {
        vertraege.add(vertrag2);
        vertraege.add(vertrag3);
        vertraege.add(vertrag4);
        doNothing().when(vertragDao).deleteVertragById(vertrag1);
        String response = vertragController.deleteVertragById("Testvertrag", (long) 1);
        assertThat(response, equalTo("Contruct succesfully deleted!"));
    }

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

    @Test
    public void testFindAllVertraegeByAzubiId() throws Exception {
        vertraege.add(vertrag1);
        vertraege.add(vertrag2);
        when(vertragDao.getAllVertragsartenByAzubiID((long) 1)).thenReturn(vertraege);
        String response = vertragController.findAllVertraegeByAzubiId((long) 1);
        assertThat(response, equalTo("{\"vertraginfos\":[{\"vertragsart\":\"Testvertrag\",\"azubiId\":1,\"vertragsvalue\":\"unbearbeitet\"},{\"vertragsart\":\"Azubivertrag\",\"azubiId\":1,\"vertragsvalue\":\"bearbeitet\"}]}"));
    }
}