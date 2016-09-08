package axelspringer.controllers;

import axelspringer.models.Azubi;
import axelspringer.models.AzubiDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Class AzubiController
 */
@Controller
public class AzubiController {

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    // Wire the AzubiDao used inside this controller.
    @Autowired
    private AzubiDao AzubiDao;


    /**
     * Create a new Azubi with an auto-generated id and email and name as passed
     * values.
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    public String create(String email, String name) {
        try {
            Azubi azubi = new Azubi(email, name);
            AzubiDao.create(azubi);
        } catch (Exception ex) {
            return "Error creating the Azubi: " + ex.toString();
        }
        return "Azubi succesfully created!";
    }

    /**
     * Delete the Azubi with the passed id.
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            Azubi azubi = new Azubi(id);
            AzubiDao.delete(azubi);
        } catch (Exception ex) {
            return "Error deleting the Azubi: " + ex.toString();
        }
        return "Azubi succesfully deleted!";
    }

    /**
     * Retrieve the id for the Azubi with the passed email address.
     */
    @RequestMapping(value = "/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
        String AzubiId;
        try {
            Azubi Azubi = AzubiDao.getByEmail(email);
            AzubiId = String.valueOf(Azubi.getId());
        } catch (Exception ex) {
            return "Azubi not found: " + ex.toString();
        }
        return "The Azubi id is: " + AzubiId;
    }


    @RequestMapping(value = "/findAll")
    @ResponseBody
    public String findAllAzubis() throws IOException {
        String jsonText;
        StringWriter out = new StringWriter();
        JSONObject azubijson = new JSONObject();
        JSONArray azubiInfosArray = new JSONArray();
        List<Azubi> Azubi = AzubiDao.getAll();

        for (Azubi anAzubi : Azubi) {
            JSONObject azubiInformations = new JSONObject();
            azubiInformations.put("id", anAzubi.getId());
            azubiInformations.put("name", anAzubi.getName());
            azubiInformations.put("email", anAzubi.getEmail());
            azubiInfosArray.add(azubiInformations);
        }

        azubijson.put("azubiinfos", azubiInfosArray);
        azubijson.writeJSONString(out);
        jsonText = out.toString();
        return jsonText;
    }


    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    /**
     * Update the email and the name for the Azubi indentified by the passed id.
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateName(long id, String email, String name) {
        try {
            Azubi Azubi = AzubiDao.getById(id);
            Azubi.setEmail(email);
            Azubi.setName(name);
            AzubiDao.update(Azubi);
        } catch (Exception ex) {
            return "Error updating the Azubi: " + ex.toString();
        }
        return "Azubi succesfully updated!";
    }


} // class AzubiController
