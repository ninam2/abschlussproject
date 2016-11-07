package axelspringer.controllers;

import axelspringer.models.Vertrag;
import axelspringer.models.VertragDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;

/**
 * Class vertragController
 */
@Controller
public class VertraegeController {

    @Autowired
    private VertragDao vertragDao;

    @RequestMapping(value = "/createVertrag", method = RequestMethod.POST)
    @ResponseBody
    public String createVertraege(String vertragsart, Long azubi_id, String vertragsvalue) {
        try {
            List azubi_ids = vertragDao.getAllAzubiIds();
            for (Object test_id : azubi_ids) {
                Long curAzubiId = (Long) test_id;
                Vertrag vertrag = new Vertrag();
                vertrag.setVertragsart(vertragsart);
                vertrag.setVertragsvalue("unbearbeitet");
                vertrag.setAzubi_id(curAzubiId);

                if (Objects.equals(azubi_id, test_id)) {
                    vertrag.setVertragsvalue(vertragsvalue);
                }
                vertrag.setAzubi_id(curAzubiId);
                vertragDao.createVertraege(vertrag);
            }

        } catch (Exception ex) {
            return "Error creating the vertrag: " + ex.toString();
        }
        return "Contruct succesfully created!";
    }


    @RequestMapping(value = "/vertraegeUpdate", method = RequestMethod.POST)
    @ResponseBody
    public String updateVertraege(String vertragsart, Long azubi_id, String vertragsvalue) {
        try {
            Vertrag vertrag = vertragDao.getVertragById(azubi_id, vertragsart);
            vertrag.setVertragsvalue(vertragsvalue);

            vertragDao.createVertraege(vertrag);


        } catch (Exception ex) {
            ex.printStackTrace();

            return "Error updating the vertrag: ";
        }
        return "Contruct succesfully updated!";
    }


    @RequestMapping(value = "/deleteVertrag", method = RequestMethod.POST)
    @ResponseBody
    public String deleteVertragById(String vertragsart, Long azubi_id) {
        try {
            Vertrag vertrag = new Vertrag(vertragsart, azubi_id);
            vertragDao.deleteVertragById(vertrag);  //warum nicht azubi_id?
        } catch (Exception ex) {
            return "Error deleting the vertrag: " + ex.toString();
        }
        return "Contruct succesfully deleted!";
    }

    @RequestMapping(value = "/findAllVertraege")
    @ResponseBody
    public String findAllvertraege() throws IOException {
        List<Vertrag> vertrag = vertragDao.getAllVertraege();
        return putInformationsTovertragJSON(vertrag);
    }

    @RequestMapping(value = "/findVertraegeByAzubiId")
    @ResponseBody
    public String findAllVertraegeByAzubiId(Long azubi_id) throws IOException {
        List<Vertrag> vertrag = vertragDao.getAllVertragsartenByAzubiID(azubi_id);
        return putInformationsTovertragJSON(vertrag);
    }


    private String putInformationsTovertragJSON(List<Vertrag> vertrag) throws IOException {
        String jsonText;
        StringWriter out = new StringWriter();
        JSONObject vertragjson = new JSONObject();
        JSONArray vertragInfosArray = new JSONArray();


        for (Vertrag anVertrag : vertrag) {

            JSONObject vertragInformations = new JSONObject();
            vertragInformations.put("vertragsart", anVertrag.getVertragsart());
            vertragInformations.put("azubiId", anVertrag.getAzubi_id());
            vertragInformations.put("vertragsvalue", anVertrag.getVertragsvalue());
            vertragInfosArray.add(vertragInformations);
        }

        vertragjson.put("vertraginfos", vertragInfosArray);
        vertragjson.writeJSONString(out);
        jsonText = out.toString();
        return jsonText;
    }

}