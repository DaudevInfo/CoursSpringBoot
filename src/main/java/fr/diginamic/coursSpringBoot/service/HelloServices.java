package fr.diginamic.coursSpringBoot.service;


import org.springframework.stereotype.Component;

@Component
public class HelloServices {

    public String salutations() {
        return "Je suis la classe de service et je vous dis Bonjour";
    }


}
