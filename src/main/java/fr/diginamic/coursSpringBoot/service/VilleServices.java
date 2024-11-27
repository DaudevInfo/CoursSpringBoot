package fr.diginamic.coursSpringBoot.service;


import fr.diginamic.coursSpringBoot.bo.Ville;
import fr.diginamic.coursSpringBoot.dao.VilleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VilleServices {

    @Autowired
    VilleRepository villeRepository ;



    public List<Ville> extractVilles () {
        // FIXME : Mettre en place la pagination
        return villeRepository.findAll();
    }

    public Ville extractVilleID(int id) {
        Ville ville = villeRepository.findById(id).get();
        return ville;
    }

    public Ville extractVille(String nom) {
        return  villeRepository.findByNom(nom);
    }

    public List<Ville> insertVille(Ville ville) {
        villeRepository.save(ville);
        return extractVilles();
    }

    /*
    public List<Ville> modifierVille (String nom, Ville ville) {
        // FIXME : Faut il tester l'existence avant l'appel, tester update en cas d'absence
        villeRepository.updateVilleByNom(ville,nom);
        return extractVilles();
    }

     */

    public List<Ville> supprimerVille(int id) {
        Optional <Ville> ville = villeRepository.findById(id);
        if (ville  != null) {
            villeRepository.delete(ville.get());
        }
        return extractVilles();
    }

    public List<Ville> listeVilleCommencePar(String debutChaine) {
        villeRepository.findByNomStartingWith(debutChaine);
        return extractVilles();
    }

    public List<Ville> listeVillePopulationSuperieurA(int   populationMini) {
        villeRepository.findByNbHabitantIsGreaterThan(populationMini);
        return extractVilles();
    }

    public List<Ville> listeVillePopulationEntre(int   populationMini, int populationMaxi) {
        villeRepository.findByNbHabitantIsBetween(populationMini, populationMaxi);
        return extractVilles();
    }

    public List<Ville> listeVillePopulationSuperieurPourUnDepartement(int   populationMini, String nom) {
        villeRepository.findByNbHabitantIsGreaterThanAndDepartement_Nom(populationMini, nom);
        return extractVilles();
    }

    public List<Ville> listeVillePopulationEntrePourUnDepartement(int   populationMini, int populationMax, String nom) {
        villeRepository.findByNbHabitantIsBetweenAndDepartement_Nom(populationMini,populationMax, nom);
        return extractVilles();
    }

    //public List<Ville> listeNVillePlusPeuplePourUnDepartement(int nomnbreVille, String nomDepartement) {
    //    villeRepository.listeNVillePlusPeuplePourUnDepartement(nomnbreVille,nomDepartement);
    //    return extractVilles();
    //}

}
