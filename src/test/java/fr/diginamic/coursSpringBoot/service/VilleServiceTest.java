package fr.diginamic.coursSpringBoot.service;

import fr.diginamic.coursSpringBoot.bo.Departement;
import fr.diginamic.coursSpringBoot.bo.Ville;
import fr.diginamic.coursSpringBoot.exception.VilleNonTrouvee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class VilleServiceTest {

    @Autowired
    public VilleServices villeServices;

    @Autowired
    public DepartementServices departementServices;



    // Tests extractVilles()
    // la méthode retourne une information non nulle
    @Test
    public void testExtratVillesListeNonVide() {
        List <Ville> villes = villeServices.extractVilles();
        assertNotEquals(0, villes.size());
    }

    // Tests extractVilles(Departement)
    // la méthode retourne une information non nulle quand le département existe
    @Test
    public void testExtratVillesListeNonVideDepartement() {
        Departement departement = new Departement();
        List <Ville> villes = villeServices.extractVilles();
        departement = villes.stream().findFirst().get().getDepartement();
        assertNotNull(villeServices.extractVilles(departement));
    }

    // la méthode génère une erreur si le departement passé en paramètre est vide
    @Test
    public void testExtratVillesListeVideDepartement() {
        Departement departement = new Departement();
        assertThrows(VilleNonTrouvee.class, () -> villeServices.extractVilles(departement));
    }

    // la méthode génère une erreur si la base ne contient pas de villes pour ce département
    // Utilisation du département 9999
    @Test
    public void testExtratVillesListeVideDepartementSansVille() {
        Departement departement = departementServices.getDepartement("9999");
        assertThrows(VilleNonTrouvee.class, () -> villeServices.extractVilles(departement));
    }




}
