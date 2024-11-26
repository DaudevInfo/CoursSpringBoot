package fr.diginamic.coursSpringBoot.service;


import fr.diginamic.coursSpringBoot.bo.Ville;
import fr.diginamic.coursSpringBoot.dao.VilleDao;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VilleServices {



    @Autowired
    VilleDao villeDao ;


    @PostConstruct
    public void init()
    {
        villeDao.insertVilles(new Ville("Nice", 343000));
        villeDao.insertVilles(new Ville("Carcassonne", 47800));
        villeDao.insertVilles(new Ville("Narbonne", 53400));
        villeDao.insertVilles(new Ville("Lyon", 484000));
        villeDao.insertVilles(new Ville("Foix", 9700));
        villeDao.insertVilles(new Ville("Pau", 77200));
        villeDao.insertVilles(new Ville("Marseille", 850700));
        villeDao.insertVilles(new Ville("Tarbes", 40600));

    }

    public List<Ville> extractVilles () {
        return villeDao.extractVilles();
    }

    public Ville extractVille(int id) {
        return villeDao.extractVille(id);
    }

    public Ville extractVille(String nom) {
        return villeDao.extractVille(nom);
    }

    public List<Ville> insertVille(Ville ville) {
        villeDao.insertVilles(ville);
        return extractVilles();
    }

    public List<Ville> modifierVille (int id, Ville ville) {
        if (extractVille(id) != null) {
            villeDao.mergeVilles(id, ville);
        }
        return extractVilles();
    }

    public List<Ville> supprimerVille(int id) {
        Ville ville = extractVille(id);
        if (ville  != null) {
            villeDao.supprimerVilles(ville);
        }
        return extractVilles();
    }



}
