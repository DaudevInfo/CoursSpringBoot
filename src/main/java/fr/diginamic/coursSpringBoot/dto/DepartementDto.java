package fr.diginamic.coursSpringBoot.dto;

public class DepartementDto {
    private String departementCode;
    private String departementNom;
    private Long nbhabitants;

    /**
     * Getter
     *
     * @return DepartementCode
     **/
    public String getDepartementCode() {
        return departementCode;
    }

    /**
     * Setter
     *
     * @param : DepartementCode
     **/
    public void setDepartementCode(String departementCode) {
        this.departementCode = departementCode;
    }

    /**
     * Getter
     *
     * @return departementNom
     **/
    public String getDepartementNom() {
        return departementNom;
    }

    /**
     * Setter
     *
     * @param : departementNom
     **/
    public void setDepartementNom(String departementNom) {
        this.departementNom = departementNom;
    }

    /**
     * Getter
     *
     * @return habitantsNb
     **/
    public Long getNbHabitants() {
        return nbhabitants;
    }

    /**
     * Setter
     *
     * @param : HabitantsNb
     **/
    public void setNbHabitants(Long nbhabitants) {
        this.nbhabitants = nbhabitants;
    }
}
