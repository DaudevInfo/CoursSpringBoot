package fr.diginamic.coursSpringBoot.service;


import fr.diginamic.coursSpringBoot.bo.Ville;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VilleServices {

    List<Ville> villes = new ArrayList<Ville>();

    public List<Ville> getVilles() {
        villes.add(new Ville("Nice", 343000));
        villes.add(new Ville("Carcassonne", 47800));
        villes.add(new Ville("Narbonne", 53400));
        villes.add(new Ville("Lyon", 484000));
        villes.add(new Ville("Foix", 9700));
        villes.add(new Ville("Pau", 77200));
        villes.add(new Ville("Marseille", 850700));
        villes.add(new Ville("Tarbes", 40600));
        return villes;

    }

    public Ville getVille(int id) {
        for (Ville v : villes) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    public boolean ajoutVille(Ville ville) {
        for (Ville v : villes) {
            if (v.getId() == ville.getId()) {
                return false;
            }
        }
        villes.add(ville);
        return true;
    }

    public boolean modifierVille(Ville ville) {
        for (Ville v : villes) {
            if (v.getId() == ville.getId()) {
                v.setNom(ville.getNom());
                v.setNbHabitant(ville.getNbHabitant());
                return true;
            }
        }
        return false;
    }

    public boolean supprimerVille(Ville ville) {
        for (Ville v : villes) {
            if (v.getId() == ville.getId()) {
                villes.remove(v);
                return true;
            }
        }
        return false;
    }

    public boolean controlerVille(Ville ville) {
        if (ville.getId() >0 && ville.getNom() != null && ville.getNom().length() >2 && ville.getNbHabitant()>0 ) {
            return true;
        }
        return false;
    }
}
