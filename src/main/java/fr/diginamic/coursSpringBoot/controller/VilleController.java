package fr.diginamic.coursSpringBoot.controller;

import fr.diginamic.coursSpringBoot.mapper.VilleMapper;
import fr.diginamic.coursSpringBoot.service.VilleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/villes")
public class VilleController {

    @Autowired
    private VilleServices villeServices;


    private VilleMapper villeMapper = new VilleMapper();

    @GetMapping()
    public String afficherVilles(Model model, Authentication auth) {
        model.addAttribute("villesDto", villeMapper.toDtos(villeServices.extractVilles()));
        model.addAttribute("authentication", auth);
        return "villes/listeVilles";
    }

    @GetMapping("/supprimerVille/{villeDtoId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String supprimerVille(@PathVariable Long villeDtoId) {
        villeServices.supprimerVille(villeDtoId);
        return "redirect:/villes";
    }
}
