package axelspringer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {

        return "getAllAzubis.html";
    }

    @RequestMapping("/getAll")
    public String vertrags() {

        return "getAllAzubis.html";
    }

    @RequestMapping("/add")
    public String add() {

        return "addAzubi.html";
    }
}
