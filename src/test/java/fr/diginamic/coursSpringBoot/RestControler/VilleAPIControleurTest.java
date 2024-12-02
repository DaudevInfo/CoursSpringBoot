package fr.diginamic.coursSpringBoot.RestControler;

import fr.diginamic.coursSpringBoot.bo.Departement;
import fr.diginamic.coursSpringBoot.bo.Ville;
import fr.diginamic.coursSpringBoot.service.VilleServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VilleAPIControleurTest {

    @MockitoBean
    private VilleServices villeServices;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAfficherVille() throws Exception {
        Departement departement=new Departement(37,"","");
        Ville ville =new Ville(13231L,"Paris",departement, 1000000);


        when(villeServices.extractVille("Nom")).thenReturn(ville) ;
        this.mockMvc.perform(MockMvcRequestBuilders.get("/villes/nom/Paris")).andDo(print())
            .andExpect(status().isOk());
            //.andExpect(content().string(containsString("Paris")));
    }



}
