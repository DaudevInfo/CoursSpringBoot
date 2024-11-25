package fr.diginamic.coursSpringBoot.hello.controlleurs;

import fr.diginamic.coursSpringBoot.bo.Ville;
import fr.diginamic.coursSpringBoot.service.VilleServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {


    public VilleServices villeServices =  new VilleServices();


    @GetMapping("/liste")
    public List<Ville> afficherVilles() {

        return villeServices.getVilles();
    }

    @GetMapping(path="{id}")
    public Ville getVille(@PathVariable int id) {
        Ville result = villeServices.getVille(id);
        return result;
    }

    @PostMapping
    public ResponseEntity <String> ajouterVille(@Valid @RequestBody Ville ville, BindingResult result) throws Exception

    {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }
        if (villeServices.ajoutVille(ville))  {
           return ResponseEntity.ok("Ville insérée avec succès");
       }
       else {
           return ResponseEntity.badRequest().body("Ville déjà existante");
       }

    }

    @PutMapping
    public ResponseEntity<String> modifierVille(@RequestBody Ville ville) {
        if (!villeServices.controlerVille(ville)) {
            return ResponseEntity.badRequest().body("Format ville invalide");
        }
        if (villeServices.modifierVille(ville))  {
            return ResponseEntity.ok("Ville modifiée avec succès");
        }
        else {
            return ResponseEntity.badRequest().body("Ville inconnue");
        }
    }


    @DeleteMapping
    public ResponseEntity<String> supprimerVille(@RequestBody Ville ville) {
        if (villeServices.supprimerVille(ville))  {
            return ResponseEntity.ok("Ville supprimée avec succès");
        }
        else {
            return ResponseEntity.badRequest().body("Ville inconnue");
        }
    }

}
