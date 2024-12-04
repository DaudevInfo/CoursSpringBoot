package fr.diginamic.coursSpringBoot.RestControler;

import fr.diginamic.coursSpringBoot.bo.Departement;
import fr.diginamic.coursSpringBoot.bo.Ville;
import fr.diginamic.coursSpringBoot.repository.VilleRepository;
import fr.diginamic.coursSpringBoot.service.VilleServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VilleAPIControleurTest {


    @MockitoBean
    private VilleRepository villeRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAfficherVille() throws Exception {
        Departement departement=new Departement(37,"","");
        Optional<Ville> ville = Optional.of(new Ville(13231L, "Paris", departement, 1000000));

        when(villeRepository.findByNom("Nom")).thenReturn(ville) ;
        this.mockMvc.perform(MockMvcRequestBuilders.get("/villes/API/nom/Paris")).andDo(print())
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) jsonPath("$[0].nom", is("Paris")));;
    }



}
