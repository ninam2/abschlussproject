package axelspringer.controllers;

import axelspringer.models.Azubi;
import axelspringer.models.Vertrag;
import axelspringer.models.VertragDao;
import axelspringer.models.VertragId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class vertragController
 */
@Controller
public class VertraegeController {

    @Autowired
    private VertragDao vertragDao;

    @RequestMapping(value = "/createVertrag", method = RequestMethod.POST)
    @ResponseBody
    public String createVertraege(String vertragsart, Long azubi_id, String vertrags_value) {
        try {
            Vertrag vertrag = new Vertrag(vertragsart, azubi_id, vertrags_value);
            vertragDao.createVertraege(vertrag);
        } catch (Exception ex) {
            return "Error creating the vertrag: " + ex.toString();
        }
        return "vertrag succesfully created!";
    }


    @RequestMapping(value = "/sfsfsfsf", method = RequestMethod.POST)
    @ResponseBody
    public String updateVertraege(String vertragsart, Long azubi_id, String vertrags_value) {
        try {
            System.out.println("1");
            Vertrag vertrag = vertragDao.getVertragById(azubi_id, vertragsart);

            System.out.println("2");
            // vertrag.setVertragsart(vertragsart);
            //vertrag.setAzubi_id(azubi_id);

            System.out.println(vertrags_value);
            vertrag.setVertragsvalue(vertrags_value);

            vertragDao.updateVertraege(vertrag);


        } catch (Exception ex) {
            ex.printStackTrace();

            return "Error updating the vertrag: ";
        }
        return "vertrag succesfully updated!";
    }


    @RequestMapping(value = "/deleteVertrag", method = RequestMethod.POST)
    @ResponseBody
    public String deleteVertragById(String vertragsart, Long azubi_id) {
        try {
            Vertrag vertrag = new Vertrag(vertragsart, azubi_id);
            vertragDao.deleteVertragById(vertrag);
        } catch (Exception ex) {
            return "Error deleting the vertrag: " + ex.toString();
        }
        return "vertrag succesfully deleted!";
    }

    @RequestMapping(value = "/findAllVertraege")
    @ResponseBody
    public String findAllvertrags() throws IOException {
        List<Vertrag> vertrag = vertragDao.getAllVertraege();
        return putInformationsTovertragJSON(vertrag);
    }

    @RequestMapping(value = "/findVertraegeByAzubiId")
    @ResponseBody
    public String findAllVertraegeByAzubiId(Long azubi_id) throws IOException {
        List<Vertrag> vertrag = vertragDao.getAllVertragsartenByAzubiID(azubi_id);
        String vertraege = putInformationsTovertragJSON(vertrag);
        return vertraege;
    }


    private String putInformationsTovertragJSON(List<Vertrag> vertrag) throws IOException {
        System.out.println("v2: " + vertrag);
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