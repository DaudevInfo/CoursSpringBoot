package fr.diginamic.coursSpringBoot.hello.controlleurs;

import fr.diginamic.coursSpringBoot.HelloServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloControleur {

    @Autowired
    private HelloServices helloServices;

    @GetMapping
    public String direHello() {
        String retour = helloServices.toString();
        System.out.println(retour);
        return retour ;
    }


}
