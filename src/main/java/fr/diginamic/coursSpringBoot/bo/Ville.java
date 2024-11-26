package fr.diginamic.coursSpringBoot.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table (schema = "ville")
public class Ville {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotNull
    @Column (name= "NOM", nullable = false)
    private String nom;

    @Column (name= "NB_HABITANT")
    @Min(1)
    private int nbHabitant;

    @ManyToOne
    @JoinColumn (name= "ID_DEPARTEMENT")
    Departement departement;

    public Ville() {
    }
    public Ville(String nom, int nbHabitant) {
        this.nom = nom;
        this.nbHabitant = nbHabitant;
    }



//    public void addDepartement (Departement departement) {
//        if (this.departement != null) {
//            this.departement.getVilles().remove(this);
//        }
//
//        this.departement = departement;
//        if (this.departement != null) {
//            this.departement.getVilles().add(this);
//        }
//    }
     /**
     * Getter
     *
     * @return departement
     **/
    public Departement getDepartement() {
        return departement;
    }

    /**
     * Setter
     *
     * @param : departement
     **/
    public void setDepartement(Departement departement) {
        this.departement = departement;
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
