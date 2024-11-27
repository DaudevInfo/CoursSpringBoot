package fr.diginamic.coursSpringBoot.service;

import fr.diginamic.coursSpringBoot.bo.Departement;
import fr.diginamic.coursSpringBoot.dao.DepartementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementServices {



    @Autowired
    DepartementDao departementDao ;

    public Departement extractDepartement(int id) {
        return departementDao.afficherDepartement(id);
    }

    public List<Departement> insertDepartement(Departement departement) {
        departementDao.insertDepartements(departement);
        return departementDao.listeDepartement();
    }

    public List<Departement> modifierDepartement (int id, Departement departement) {
        if (extractDepartement(id) != null) {
            departementDao.mergeDepartement(id, departement);
        }
        return departementDao.listeDepartement();
    }

    public List<Departement> supprimerDepartement(int id) {
        Departement departement = extractDepartement(id);
        if (departement != null) {
            departementDao.supprimerDepartement(departement);
        }
        return departementDao.listeDepartement();
    }
}
