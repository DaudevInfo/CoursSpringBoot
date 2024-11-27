package fr.diginamic.coursSpringBoot.dao;

import fr.diginamic.coursSpringBoot.bo.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Integer> {
    public Ville findByNom(String nom);


    //public void updateVilleByNom(Ville ville,String nom);

    public List<Ville> findByNomStartingWith(String nom);

    public List<Ville> findByNbHabitantIsGreaterThan(int nbHabitant);

    public List<Ville> findByNbHabitantIsBetween(int nbHabitantMinimum, int nbHabitantMaximum);

    public List<Ville> findByNbHabitantIsGreaterThanAndDepartement_Nom(int nbHabitantMinimum, String nom);

    public List<Ville> findByNbHabitantIsBetweenAndDepartement_Nom(int nbHabitantMinimum, int nbHabitantMaximum, String nom);

    //@Query("SELECT Ville FROM Ville WHERE Departement.nom = :nomDepartement ORDER BY nbHabitant DESC LIMIT :nomnbreVille")
    //public List<Ville> listeNVillePlusPeuplePourUnDepartement(int nomnbreVille, String nomDepartement);

}
