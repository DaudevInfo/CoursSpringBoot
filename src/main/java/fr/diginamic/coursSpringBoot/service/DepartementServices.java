package fr.diginamic.coursSpringBoot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.diginamic.coursSpringBoot.bo.Departement;
import fr.diginamic.coursSpringBoot.repository.DepartementRepository;
import fr.diginamic.coursSpringBoot.exception.InvalidDepartementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementServices {

    @Value("${url_region_gouv}")
    String Urlregion;


    @Autowired
    DepartementRepository departementRepository;


    public String getRegion(String codeDepartement) throws JsonProcessingException {
        String requete = Urlregion + codeDepartement;
        RestTemplate restTemplate = new RestTemplate();
        String reponse = restTemplate.getForObject(requete, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(reponse);

        // Extraction de la valeur de "codeRegion"
        String codeRegion = jsonNode.get("codeRegion").asText();
        return codeRegion;
    }

    public List<Departement> afficherDepartements() {
        return departementRepository.findAll();
    }

    public Departement getDepartement(String code) {

        Optional<Departement> DepartementOpt = departementRepository.findByCode(code);
        if (DepartementOpt.isEmpty()) {
            throw new InvalidDepartementException("Département inexistant");
        }
        return DepartementOpt.get();
    }

    public Boolean insertDepartement(Departement departement) {
        Optional<Departement> departementOpt = departementRepository.findByCode(departement.getCode());
        if (departementOpt.isPresent()) {
            throw new InvalidDepartementException("Département déjà existant");
        } else {
            departementRepository.save(departement);
            return true;
        }

    }

    public Boolean modifierDepartement(Departement departement) {
        Optional<Departement> departementOpt = departementRepository.findByCode(departement.getCode());
        if (departementOpt.isPresent()) {
            departementRepository.save(departement);
            return true;
        }
        throw new InvalidDepartementException("Pas de departement avec ce code" + departement.getCode());
    }

    public Boolean supprimerDepartement(Long id) {
        Optional<Departement> departementOpt = departementRepository.findById(id);
        if (departementOpt.isPresent()) {
            departementRepository.delete(departementOpt.get());
            return true;
        }
        return false;
    }
}
