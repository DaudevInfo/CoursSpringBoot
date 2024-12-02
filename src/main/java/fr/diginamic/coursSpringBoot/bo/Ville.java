package fr.diginamic.coursSpringBoot.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity

public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nom;

    @ManyToOne
    Departement departement;

    private long nbHabitant;



    public Ville() {
    }

    public Ville(Long id, String nom, Departement departement, long nbHabitant) {
        this.id = id;
        this.nom = nom;
        this.departement = departement;
        this.nbHabitant = nbHabitant;
    }

    /**
     * Getter
     * @return departement
     **/
    public Departement getDepartement() {
        return departement;
    }

    /**
     * Setter
     * @param : departement
     **/
    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    /**
     * Getter
     * @return id
     **/
    public long getId() {
        return id;
    }

    /**
     * Setter
     * @param : id
     **/
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter
     * @return nom
     **/
    public String getNom() {
        return nom;
    }

    /**
     * Setter
     * @param : nom
     **/
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter
     * @return nbHabitant
     **/
    public long getNbHabitant() {
        return nbHabitant;
    }

    /**
     * Setter
     * @param : nbHabitant
     **/
    public void setNbHabitant(Long nbHabitant) {
        this.nbHabitant = nbHabitant;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ville{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", departement=").append(departement);
        sb.append(", nbHabitant=").append(nbHabitant);
        sb.append('}');
        return sb.toString();
    }
}