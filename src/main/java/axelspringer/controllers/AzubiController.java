package axelspringer.controllers;

import axelspringer.models.Azubi;
import axelspringer.models.AzubiDao;
import axelspringer.models.Vertrag;
import axelspringer.models.VertragDao;
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
 * The type Azubi controller.
 */
@Controller
public class AzubiController {

    @Autowired
    private AzubiDao azubiDao;

    @Autowired
    private VertragDao vertragDao;

    /**
     * Create string.
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
     * @return the string
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String create(String email, String name, String vorname, String beruf, String strasse, Integer plz, String gebDatum, String gebOrt, Integer ausbildungsstart) throws IOException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(gebDatum);

            Azubi azubi = new Azubi(email, name, vorname, beruf, strasse, plz, date, gebOrt, ausbildungsstart);
            azubiDao.create(azubi);

            List<String> Vertragsarten = vertragDao.getAllVertragsarten();
            for (String vertragsart : Vertragsarten) {
                Vertrag vertrag = new Vertrag();
                vertrag.setAzubi_id(azubi.getId());
                vertrag.setVertragsart(vertragsart);
                vertrag.setVertragsvalue("unbearbeitet");
                vertragDao.updateVertraege(vertrag);

            }


        } catch (Exception ex) {
            return "Error creating the Azubi: " + ex.toString();
        }
        return "Azubi succesfully created!";
    }


    /**
     * Update name string.
     *
     * @param id               the id
     * @param email            the email
     * @param name             the name
     * @param vorname          the vorname
     * @param beruf            the beruf
     * @param strasse          the strasse
     * @param plz              the plz
     * @param gebOrt           the geb ort
     * @param ausbildungsstart the ausbildungsstart
     * @return the string
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateName(long id, String email, String name, String vorname, String beruf, String strasse, Integer plz, String gebOrt, Integer ausbildungsstart) {
        try {
            Azubi azubi = azubiDao.getById(id);
            azubi.setEmail(email);
            azubi.setName(name);
            azubi.setVorname(vorname);
            azubi.setBeruf(beruf);
            azubi.setStrasse(strasse);
            azubi.setPLZ(plz);
            azubi.setGebOrt(gebOrt);
            azubi.setAusbildungsstart(ausbildungsstart);
            azubiDao.update(azubi);

        } catch (Exception ex) {
            return "Error updating the Azubi: " + ex.toString();
        }
        return "Azubi succesfully updated!";
    }


    /**
     * Delete string.
     *
     * @param id the id
     * @return the string
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(long id) {
        try {
            Vertrag vertrag = new Vertrag();
            vertrag.setAzubi_id(id);
            vertragDao.deleteVertragById(vertrag);
            Azubi azubi = new Azubi(id);
            azubiDao.delete(azubi);
        } catch (Exception ex) {
            return "Error deleting the Azubi: " + ex.toString();
        }
        return "Azubi succesfully deleted!";
    }

    /**
     * Find all azubis string.
     *
     * @return the string
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/findAll")
    @ResponseBody
    public String findAllAzubis() throws IOException {
        List<Azubi> Azubi = azubiDao.getAll();
        return putInformationsToAzubiJSON(Azubi);
    }


    /**
     * Find vertraege by azubi id string.
     *
     * @param vertragsart the vertragsart
     * @return the string
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/findAllByVertrag")
    @ResponseBody
    public String findVertraegeByAzubiId(String vertragsart) throws IOException {
        List azubis = azubiDao.getAllByVertrag(vertragsart);
        return putInformationsToAzubiJSON(azubis);
    }

    /**
     * Find vertraege by type and value string.
     *
     * @param vertragsart   the vertragsart
     * @param vertragsvalue the vertragsvalue
     * @return the string
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/findAllByVertragAndValue")
    @ResponseBody
    public String findVertraegeByTypeAndValue(String vertragsart, String vertragsvalue) throws IOException {
        List<Azubi> azubis = azubiDao.getAllByVertragsartAndValue(vertragsart, vertragsvalue);
        return putInformationsToAzubiJSON(azubis);
    }

    /**
     * Find all azubis by year string.
     *
     * @param year the year
     * @return the string
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/findAllByYear")
    @ResponseBody
    public String findAllAzubisByYear(Integer year) throws IOException {
        List<Azubi> azubi = azubiDao.getAllByYear(year);
        return putInformationsToAzubiJSON(azubi);
    }


    /**
     * Find all azubis by job string.
     *
     * @param beruf the beruf
     * @return the string
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/findAllByJob")
    @ResponseBody
    public String findAllAzubisByJob(String beruf) throws IOException {
        List<Azubi> azubi = azubiDao.getAllByJob(beruf);
        return putInformationsToAzubiJSON(azubi);
    }


    /**
     * Find all azubis jobs and years string.
     *
     * @return the string
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/findAllJobsAndYears")
    @ResponseBody
    public String findAllAzubisJobsAndYears() throws IOException {
        List Jobs = azubiDao.getAllJobs();
        List Years = azubiDao.getAllYears();
        List Vertraege = vertragDao.getAllVertragsarten();
        List attributes = new ArrayList();
        attributes.add(Jobs);
        attributes.add(Years);
        attributes.add(Vertraege);
        return makeJSON(Jobs, Years, Vertraege);
    }

    private String makeJSON(List jobs, List years, List vertraege) throws IOException {
        String jsonText;
        StringWriter out = new StringWriter();
        JSONObject azubijson = new JSONObject();
        JSONArray azubiJobsArray = new JSONArray();
        JSONArray azubiYearsArray = new JSONArray();
        JSONArray vertraegeVertragArray = new JSONArray();


        buildJSONArrayForEachElement(years, "year", azubiYearsArray);
        buildJSONArrayForEachElement(jobs, "job", azubiJobsArray);
        buildJSONArrayForEachElement(vertraege, "vertragsart", vertraegeVertragArray);

        azubijson.put("jobs", azubiJobsArray);
        azubijson.put("years", azubiYearsArray);
        azubijson.put("vertragsarten", vertraegeVertragArray);

        azubijson.writeJSONString(out);
        jsonText = out.toString();

        return jsonText;
    }

    private void buildJSONArrayForEachElement(List jsonList, String attributname, JSONArray azubiYearsArray) {
        for (Object jsonElement : jsonList) {
            JSONObject azubiInformations = new JSONObject();
            azubiInformations.put(attributname, jsonElement);
            azubiYearsArray.add(azubiInformations);
        }
    }

    /**
     * Put informations tovertrag json json object.
     *
     * @param vertrag the vertrag
     * @return the json object
     * @throws IOException the io exception
     */
    protected JSONObject putInformationsTovertragJSON(List<Vertrag> vertrag) throws IOException {

        JSONObject vertragjson = new JSONObject();
        JSONArray vertragInfosArray = new JSONArray();


        for (Vertrag anVertrag : vertrag) {

            JSONObject vertragInformations = new JSONObject();
            vertragInformations.put("vertragsattribut", anVertrag.getVertragsart());
            vertragInformations.put("vertragsvalue", anVertrag.getVertragsvalue());
            vertragInfosArray.add(vertragInformations);
        }

        vertragjson.put("vertraginfos", vertragInfosArray);
        return vertragjson;
    }

    /**
     * Put informations to azubi json string.
     *
     * @param azubi the azubi
     * @return the string
     * @throws IOException the io exception
     */
    protected String putInformationsToAzubiJSON(List<Azubi> azubi) throws IOException {
        String jsonText;
        StringWriter out = new StringWriter();
        JSONObject azubijson = new JSONObject();
        JSONArray azubiInfosArray = new JSONArray();


        for (Azubi anAzubi : azubi) {


            JSONObject azubiInformations = new JSONObject();


            List<Vertrag> vertrag = vertragDao.getAllVertragsartenByAzubiID(anAzubi.getId());
            JSONObject vertraegeJson = putInformationsTovertragJSON(vertrag);

            azubiInformations.put("id", anAzubi.getId());
            azubiInformations.put("email", anAzubi.getEmail());
            azubiInformations.put("name", anAzubi.getName());
            azubiInformations.put("vorname", anAzubi.getVorname());
            azubiInformations.put("beruf", anAzubi.getBeruf());
            azubiInformations.put("strasse", anAzubi.getStrasse());
            azubiInformations.put("plz", anAzubi.getPLZ());
            azubiInformations.put("gebDatum", anAzubi.getGebDatum().toString().substring(0, 10));
            azubiInformations.put("gebOrt", anAzubi.getGebOrt());
            azubiInformations.put("ausbildungsstart", anAzubi.getAusbildungsstart());
            azubiInformations.put("vertraege", vertraegeJson);
            azubiInfosArray.add(azubiInformations);
        }

        azubijson.put("azubiinfos", azubiInfosArray);
        azubijson.writeJSONString(out);
        jsonText = out.toString();

        return jsonText;
    }

}