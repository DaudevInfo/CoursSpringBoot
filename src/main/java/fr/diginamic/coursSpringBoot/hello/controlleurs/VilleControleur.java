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
        Ville result = villeServices.extractVilleID(id);
        return result;
    }

    @GetMapping(path="/nom/{nom}")
    public Ville getVille(@PathVariable String nom) {
        Ville result = villeServices.extractVille(nom);
        return result;
    }

    @GetMapping(path="/debute/{nom}")
    public List<Ville> listeVilleCommencePar(@PathVariable String nom) {
        return villeServices.listeVilleCommencePar(nom);
    }

    @GetMapping(path="/PopMin/{nbrMin}")
    public List<Ville> listeVillePopulationSuperieurA(@PathVariable int nbrMin) {
        return villeServices.listeVillePopulationSuperieurA(nbrMin);
    }

    @GetMapping(path="/PopMin/{nbrMin}/PopMax/{nbrMax}")
    public List<Ville> listeVillePopulationEntre(@PathVariable int nbrMin, int nbrMax) {
        return villeServices.listeVillePopulationEntre(nbrMin,nbrMax);
    }

    @GetMapping(path="/Dpt/{dpt}/PopMin/{nbrMin}")
    public List<Ville> listeVillePopulationSuperieurPourUnDepartement(@PathVariable String dpt, int nbrMin) {
        return villeServices.listeVillePopulationSuperieurPourUnDepartement(nbrMin,dpt);
    }

    @GetMapping(path="/Dpt/{dpt}/PopMin/{nbrMin}/PopMax/{nbrMax}")
    public List<Ville> listeVillePopulationEntrePourUnDepartement(@PathVariable String dpt, int nbrMin,int nbrMax) {
        return villeServices.listeVillePopulationEntrePourUnDepartement(nbrMin,nbrMax,dpt);
    }

    //@GetMapping(path="/Dpt/{dpt}/NbVille/{nbr}")
//    public List<Ville> listeNVillePlusPeuplePourUnDepartement(@PathVariable String dpt, int nbr) {
//        return villeServices.listeNVillePlusPeuplePourUnDepartement(nbr,dpt);
//    }

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

//    @PutMapping
//    public ResponseEntity<String> modifierVille(@Valid @RequestBody Ville ville, BindingResult result)
//        throws Exception{
//        if (result.hasErrors()) {
//            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
//        }
//        villeServices.modifierVille(ville.getNom(), ville);
//        return ResponseEntity.ok().build();
//    }


    @DeleteMapping
    public ResponseEntity<String> supprimerVille(@Valid @RequestBody int id, BindingResult result) {
        villeServices.supprimerVille(id);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }
        return ResponseEntity.ok().build();
    }

}
