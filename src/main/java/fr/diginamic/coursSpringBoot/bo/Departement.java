package fr.diginamic.coursSpringBoot.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity

public class Departement {

    @Id//@Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String nom;

    @OneToMany(mappedBy = "departement")
    @JsonIgnore
    private List<Ville> villes =  new ArrayList<>();


    public Departement() {
    }

    public Departement(int id, String code, String nom) {
        this.id = id;
        this.code = code;
        this.nom = nom;
    }

    /**
     * Getter
     * @return code
     **/
    public @NotNull String getCode() {
        return code;
    }

    /**
     * Setter
     * @param : code
     **/
    public void setCode(@NotNull String code) {
        this.code = code;
    }

    /**
     * Getter
     * @return id
     **/
    public int getId() {
        return id;
    }


    /**
     * Getter
     * @return nom
     **/
    public @NotNull @Size(min = 2) String getNom() {
        return nom;
    }

    /**
     * Setter
     * @param : nom
     **/
    public void setNom(@NotNull @Size(min = 2) String nom) {
        this.nom = nom;
    }

    /**
     * Getter
     * @return villes
     **/
    public List<Ville> getVilles() {
        return villes;
    }

    /**
     * Setter
     * @param : villes
     **/
    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }
}
