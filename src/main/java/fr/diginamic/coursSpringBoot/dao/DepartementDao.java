package fr.diginamic.coursSpringBoot.dao;

import fr.diginamic.coursSpringBoot.bo.Departement;
import fr.diginamic.coursSpringBoot.bo.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartementDao {

    @PersistenceContext
    private EntityManager em;



    public List<Departement> listeDepartement() {
        TypedQuery<Departement> query = em.createQuery("select d from Departement d", Departement.class);
        return query.getResultList();
    }
    public Departement afficherDepartement(int id) {
        return em.find(Departement.class, id);
    }

    @Transactional
    public void insertDepartements(Departement departement) {
        em.persist(departement);
    }

    @Transactional
    public void mergeDepartement(int id, Departement departement ) {
        em.merge(departement);
    }

    @Transactional
    public void supprimerDepartement(Departement departement) {
        em.remove(departement);
    }



}
