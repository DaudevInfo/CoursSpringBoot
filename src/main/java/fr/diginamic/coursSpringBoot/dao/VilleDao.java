package fr.diginamic.coursSpringBoot.dao;

import fr.diginamic.coursSpringBoot.bo.Ville;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VilleDao {

    @PersistenceContext
    private EntityManager em;




    public List<Ville> extractVilles() {
        TypedQuery<Ville>  query = em.createQuery("select v from Ville v", Ville.class);
        return query.getResultList();
    }

    public Ville extractVille(int id) {
        return em.find(Ville.class, id);
    }

    public Ville extractVille(String nom) {
        TypedQuery<Ville>  query = em.createQuery("select v from Ville v where v.nom = :nom ", Ville.class);
        return query.getSingleResult();
    }

    @Transactional
    public void insertVilles(Ville ville) {
        em.persist(ville);
    }

    @Transactional
    public void mergeVilles(int id, Ville ville) {
        em.merge(ville);
    }

    @Transactional
    public void supprimerVilles(Ville ville) {
        em.remove(ville);
    }

}

