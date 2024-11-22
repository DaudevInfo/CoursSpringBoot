package fr.diginamic.coursSpringBoot.bo;

import org.springframework.stereotype.Component;

@Component
public class Ville {
    private String nom;
    private int nbHabitant;

    public Ville() {
    }

    public Ville(String nom, int nbHabitant) {
        this.nom = nom;
        this.nbHabitant = nbHabitant;

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
