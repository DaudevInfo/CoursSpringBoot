package fr.diginamic.coursSpringBoot.controler;

import fr.diginamic.coursSpringBoot.service.VilleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class VilleControleur {

    Autowired
    private VilleServices villeServices;

}
