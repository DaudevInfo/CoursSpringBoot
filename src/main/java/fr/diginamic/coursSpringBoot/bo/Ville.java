package fr.diginamic.coursSpringBoot.bo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;


public class Ville {

    @NotNull
    private int id;
    @NotNull
    @Size(min = 2)
    private String nom;
    @Min(1)
    private int nbHabitant;
    private static int compteurId = 0;

    public Ville() {
    }

    public Ville(String nom, int nbHabitant) {
        this.nom = nom;
        this.nbHabitant = nbHabitant;
        this.id = compteurId++;
    }

    /**
     * Getter
     * @return id
     **/
    public int getId() {
        return id;
    }

    /**
     * Setter
     *
     * @param : id
     **/
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter
     *
     * @return nom
     **/
    public String getNom() {
        return nom;
    }

    /**
     * Setter
     *
     * @param : nom
     **/
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter
     *
     * @return nbHabitant
     **/
    public int getNbHabitant() {
        return nbHabitant;
    }

    /**
     * Setter
     *
     * @param : nbHabitant
     **/
    public void setNbHabitant(int nbHabitant) {
        this.nbHabitant = nbHabitant;
    }
}
