package fr.diginamic.coursSpringBoot.repository;

import fr.diginamic.coursSpringBoot.bo.Departement;
import fr.diginamic.coursSpringBoot.bo.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface VilleRepository extends JpaRepository<Ville, Long> {

    public Optional<Ville> findByNom(String nom);

    public List<Ville> findByDepartement(Departement departement);

    public List<Ville> findByNomStartingWith(String nom);

    public List<Ville> findByNbHabitantIsGreaterThan(int nbHabitant);

    public List<Ville> findByNbHabitantIsBetween(int nbHabitantMinimum, int nbHabitantMaximum);

    public List<Ville> findByNbHabitantIsGreaterThanAndDepartement_Nom(int nbHabitantMinimum, String nom);

    public List<Ville> findByNbHabitantIsBetweenAndDepartement_Nom(int nbHabitantMinimum, int nbHabitantMaximum, String nom);

   // public List<Ville> findByDepartmentCodeOrderByNbInhabitantsDesc(String departementCode, Pageable pageable) ;

}
