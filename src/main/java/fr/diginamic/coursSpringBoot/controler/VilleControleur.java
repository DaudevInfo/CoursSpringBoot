package fr.diginamic.coursSpringBoot.controler;

import fr.diginamic.coursSpringBoot.mapper.VilleMapper;
import fr.diginamic.coursSpringBoot.service.VilleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleServices villeServices;


    private VilleMapper villeMapper = new VilleMapper();

    @GetMapping()
    public String afficherVilles(Model model) {
        model.addAttribute("villesDto", villeMapper.toDtos(villeServices.extractVilles()));
        return "villes/listeVilles";
    }

    @GetMapping("/supprimerVille/{villeDtoId}")
    public String supprimerVille(@PathVariable Long villeDtoId) {
        villeServices.supprimerVille(villeDtoId);
        return "redirect:/villes";
    }
}
