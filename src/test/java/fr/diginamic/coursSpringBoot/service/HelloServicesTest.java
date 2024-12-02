package fr.diginamic.coursSpringBoot.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HelloServicesTest {

    @Test
    public void testHello() {
        HelloServices helloServices = new HelloServices();
        String result = "Je suis la classe de service et je vous dis Bonjour";
        assertEquals(result,helloServices.salutations());
    }
}
