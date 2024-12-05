package fr.diginamic.coursSpringBoot.repository;

import fr.diginamic.coursSpringBoot.bo.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

    public Optional<Departement> findByCode(String code);

    public void deleteByCode(String code);



}