package fr.diginamic.coursSpringBoot.controler;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControler {

    @GetMapping
    public String getIndex(Model model) {
        return "index";
    }
}
