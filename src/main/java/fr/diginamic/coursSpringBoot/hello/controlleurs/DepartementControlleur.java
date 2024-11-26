package fr.diginamic.coursSpringBoot.hello.controlleurs;

import fr.diginamic.coursSpringBoot.service.DepartementServices;
import fr.diginamic.coursSpringBoot.service.VilleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Departement")
public class DepartementControlleur     {

    @Autowired
    public DepartementServices DepartementServices;



}
