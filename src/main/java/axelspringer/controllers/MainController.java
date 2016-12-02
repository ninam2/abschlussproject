package axelspringer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Main controller.
 */
@Controller
public class MainController {

    /**
     * Index string.
     *
     * @return the string
     */
    @RequestMapping("/")
    public String index() {

        return "getAllAzubis.html";
    }

    /**
     * Vertrags string.
     *
     * @return the string
     */
    @RequestMapping("/getAll")
    public String vertrags() {

        return "getAllAzubis.html";
    }

    /**
     * Vertraege string.
     *
     * @return the string
     */
    @RequestMapping("/getAll?vertragsart")
    public String vertraege() {

        return "getVertraege.html";
    }

    /**
     * Add string.
     *
     * @return the string
     */
    @RequestMapping("/add")
    public String add() {

        return "addAzubi.html";
    }
}
