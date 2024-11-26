package fr.diginamic.coursSpringBoot.hello.controlleurs;

import fr.diginamic.coursSpringBoot.bo.Ville;
import fr.diginamic.coursSpringBoot.service.VilleServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {


    @Autowired
    public VilleServices villeServices;


    @GetMapping("/liste")
    public List<Ville> afficherVilles() {

        return villeServices.extractVilles();
    }

    @GetMapping(path="/id/{id}")
    public Ville getVille(@PathVariable int id) {
        Ville result = villeServices.extractVille(id);
        return result;
    }

    @GetMapping(path="/nom/{nom}")
    public Ville getVille(@PathVariable String nom) {
        Ville result = villeServices.extractVille(nom);
        return result;
    }

    @PostMapping
    public ResponseEntity <String> insertVille(@Valid @RequestBody Ville ville, BindingResult result)
            throws Exception
    {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }
        villeServices.insertVille(ville);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> modifierVille(@Valid @RequestBody Ville ville, BindingResult result)
        throws Exception{
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }
        villeServices.modifierVille(ville.getId(), ville);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping
    public ResponseEntity<String> supprimerVille(@Valid @RequestBody int id, BindingResult result) {
        villeServices.supprimerVille(id);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }

        return ResponseEntity.ok().build();
    }

}
