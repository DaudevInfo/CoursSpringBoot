package fr.diginamic.coursSpringBoot.service;


import fr.diginamic.coursSpringBoot.bo.Departement;
import fr.diginamic.coursSpringBoot.bo.Ville;
import fr.diginamic.coursSpringBoot.repository.VilleRepository;
import fr.diginamic.coursSpringBoot.exception.InvalidVilleException;
import fr.diginamic.coursSpringBoot.exception.VilleNonTrouvee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VilleServices {

    @Autowired
    private VilleRepository villeRepository;


    public List<Ville> extractVilles() {
        // FIXME : Mettre en place la pagination
        return villeRepository.findAll();
    }

    public List<Ville> extractVilles(Departement departement) {
        if (departement.getNom() == null){
            throw new VilleNonTrouvee("Département passé en paramètre est null");
        }
        List <Ville> villes = villeRepository.findByDepartement(departement);
        if (!villes.isEmpty()) {
            return villes;
        }
        throw new VilleNonTrouvee("Pas de ville démarrant pour le département" + departement.getNom());
    }
    public Ville extractVille(Long id) {
        Optional<Ville> villeOpt = villeRepository.findById(id);
        if (villeOpt.isPresent()) {
            return (Ville) villeOpt.get();
        }
        throw new VilleNonTrouvee("Pas de ville démarrant avec l'id" + id);
    }

    public Ville extractVille(String nom) {
        Optional<Ville> villeOpt = villeRepository.findByNom(nom);
        if (villeOpt.isEmpty()) {
            throw new VilleNonTrouvee("Pas de ville démarrant avec le nom" + nom);
        }
        return villeOpt.get();
    }

    @Transactional
    public Boolean insertVille(Ville ville) {
        if (ville.getNbHabitant() < 10) {
            throw new InvalidVilleException("La ville doit avoir au moins 10 habitants");
        }
        if (ville.getNom().length() < 2) {
            throw new InvalidVilleException("Le nom de la ville doit avoir au moins 2 caractères");
        }
        Optional<Ville> villeOpt = villeRepository.findByNom(ville.getNom());
        if (villeOpt.isPresent()) {
            throw new InvalidVilleException("Ville déjà existante");
        } else {
            villeRepository.save(ville);
            return true;
        }
    }

    /**
     * modifierVille : Effectue la modification de la ville, si la ville (recherche sur le nom) n'existe pas
     *
     * @param : Ville
     * @return : true si la modification a été effectuée
     */
    @Transactional
    public Boolean modifierVille(Ville ville) {
        Optional<Ville> villeOpt = villeRepository.findByNom(ville.getNom());
        if (villeOpt.isEmpty()) {
            villeRepository.save(ville);
            return true;
        }
        throw new VilleNonTrouvee("Pas de ville avec ce nom" + ville.getNom());

    }


    @Transactional
    public Boolean supprimerVille(Long id) {
        Optional<Ville> villeOpt = villeRepository.findById(id);
        if (villeOpt.isEmpty()) {
            villeRepository.delete(villeOpt.get());
            return true;
        }
        return false;
    }

    public List<Ville> listeVilleCommencePar(String debutChaine) {

        List<Ville> villes = villeRepository.findByNomStartingWith(debutChaine);
        if (villes.isEmpty()) {
            throw new VilleNonTrouvee("Pas de ville démarrant avec " + debutChaine);
        }
        return villes;
    }

    public List<Ville> listeVillePopulationSuperieurA(int populationMini) {
        List<Ville> villes = villeRepository.findByNbHabitantIsGreaterThan(populationMini);
        if (villes.isEmpty()) {
            throw new VilleNonTrouvee("Pas de ville avec moins de " + populationMini + " habitants");
        }
        return villes;
    }

    public List<Ville> listeVillePopulationEntre(int populationMini, int populationMaxi) {
        villeRepository.findByNbHabitantIsBetween(populationMini, populationMaxi);
        return extractVilles();
    }

    public List<Ville> listeVillePopulationSuperieurPourUnDepartement(int populationMini, String nom) {
        villeRepository.findByNbHabitantIsGreaterThanAndDepartement_Nom(populationMini, nom);
        return extractVilles();
    }

    public List<Ville> listeVillePopulationEntrePourUnDepartement(int populationMini, int populationMax, String nom) {
        villeRepository.findByNbHabitantIsBetweenAndDepartement_Nom(populationMini, populationMax, nom);
        return extractVilles();
    }

//    public List<Ville> listeNVillePlusPeuplePourUnDepartement(int nomnbreVille, String codeDepartement) {
//        villeRepository.findByDepartmentCodeOrderByNbInhabitantsDesc(codeDepartement, Pageable.ofSize(nomnbreVille));
//        return extractVilles();
//    }

}
