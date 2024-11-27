package fr.diginamic.coursSpringBoot.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Set;

@Entity
@Table (schema = "departement")
public class Departement {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column (name= "CODE", nullable = false)
    private String code;

    @NotNull
    @Column (name= "NOM", nullable = false)
    private String nom;


    @OneToMany(mappedBy = "departement")
    private ArrayList<Ville> villes;

    {
        villes = new ArrayList<>();
    }

    public Departement() {
    }

    /**
     * Getter
     *
     * @return code
     **/
    public @NotNull String getCode() {
        return code;
    }

    /**
     * Setter
     *
     * @param : code
     **/
    public void setCode(@NotNull String code) {
        this.code = code;
    }

    /**
     * Getter
     *
     * @return id
     **/
    public int getId() {
        return id;
    }


    /**
     * Getter
     *
     * @return nom
     **/
    public @NotNull @Size(min = 2) String getNom() {
        return nom;
    }

    /**
     * Setter
     *
     * @param : nom
     **/
    public void setNom(@NotNull @Size(min = 2) String nom) {
        this.nom = nom;
    }

    /**
     * Getter
     *
     * @return villes
     **/
    public ArrayList<Ville> getVilles() {
        return villes;
    }

    /**
     * Setter
     *
     * @param : villes
     **/
    public void setVilles(ArrayList<Ville> villes) {
        this.villes = villes;
    }
}
