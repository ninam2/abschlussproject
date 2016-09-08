package axelspringer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "hello.html";
    }

    @RequestMapping("/getAllAzubis")
    public String azubis() {
        return "getAllAzubis.html";
    }


}
