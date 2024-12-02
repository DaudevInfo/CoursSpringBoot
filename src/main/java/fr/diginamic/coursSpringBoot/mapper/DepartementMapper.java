package fr.diginamic.coursSpringBoot.mapper;


import fr.diginamic.coursSpringBoot.bo.Departement;
import fr.diginamic.coursSpringBoot.bo.Ville;
import fr.diginamic.coursSpringBoot.dto.DepartementDto;
import org.springframework.stereotype.Component;

@Component
public class DepartementMapper {
    public DepartementDto toDto(Departement department) {
        DepartementDto dto = new DepartementDto();
        dto.setDepartementCode(department.getCode());
        dto.setDepartementNom(department.getNom());
        dto.setNbHabitants(department.getVilles().stream().mapToLong(Ville::getNbHabitant).sum());
        return dto;
    }

    // Impossible en l'état il manque l'ID du département
    public Departement fromDto(DepartementDto dto) {
        Departement department = new Departement();
        department.setCode(dto.getDepartementCode());
        department.setNom(dto.getDepartementNom());
        return department;
    }
}
