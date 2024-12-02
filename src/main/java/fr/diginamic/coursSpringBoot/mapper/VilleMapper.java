package fr.diginamic.coursSpringBoot.mapper;


import fr.diginamic.coursSpringBoot.bo.Departement;
import fr.diginamic.coursSpringBoot.bo.Ville;
import fr.diginamic.coursSpringBoot.dto.VilleDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VilleMapper {
    public VilleDto toDto(Ville ville) {
        VilleDto dto = new VilleDto();

        dto.setId(ville.getId());
        dto.setNom(ville.getNom());
        dto.setDepartementCode(ville.getDepartement().getCode());
        dto.setDepartementNom(ville.getDepartement().getNom());
        dto.setNbHabitant(ville.getNbHabitant());
        return dto;
    }

    public Ville fromDto(VilleDto dto) {
        Ville ville = new Ville();
        Departement department = new Departement();
        ville.setId(dto.getId());
        ville.setNom(dto.getNom());
        ville.setNbHabitant(dto.getNbHabitant());
        department.setCode(dto.getDepartementCode());
        ville.setDepartement(department);
        return ville;
    }

    public List<VilleDto> toDtos(List<Ville> villes) {
       ArrayList<VilleDto> dtos = new ArrayList<>();
       for (Ville ville : villes) {
           dtos.add(toDto(ville));
       }
       return dtos;
    }
    }
