package fr.diginamic.coursSpringBoot.dto;

public class VilleDto {
    private Long id;
    private String nom;
    private String DepartementCode;
    private String DepartementNom;
    private Long nbHabitant;

    /**
     * Getter
     *
     * @return id
     **/
    public Long getId() {
        return id;
    }

    /**
     * Setter
     *
     * @param : id
     **/
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter
     *
     * @return Nom
     **/
    public String getNom() {
        return nom;
    }

    /**
     * Setter
     *
     * @param : Nom
     **/
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter
     *
     * @return DepartementCode
     **/
    public String getDepartementCode() {
        return DepartementCode;
    }

    /**
     * Setter
     *
     * @param : DepartementCode
     **/
    public void setDepartementCode(String DepartementCode) {
        this.DepartementCode = DepartementCode;
    }

    /**
     * Getter
     *
     * @return DepartementNom
     **/
    public String getDepartementNom() {
        return DepartementNom;
    }

    /**
     * Setter
     *
     * @param : Departemen tNom
     **/
    public void setDepartementNom(String DepartementNom) {
        this.DepartementNom = DepartementNom;
    }

    /**
     * Getter
     *
     * @return nbHabitant
     **/
    public Long getNbHabitant() {
        return nbHabitant;
    }

    /**
     * Setter
     *
     * @param : nbHabitant
     **/
    public void setNbHabitant(Long nbHabitant) {
        this.nbHabitant = nbHabitant;
    }
}
