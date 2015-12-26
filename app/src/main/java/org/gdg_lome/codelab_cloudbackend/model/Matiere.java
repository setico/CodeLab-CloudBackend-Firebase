package org.gdg_lome.codelab_cloudbackend.model;

import java.io.Serializable;

/**
 * Created by setico on 26/12/2015.
 */
public class Matiere implements Serializable {
    private String logo;
    private String nom;
    private String description;

    public Matiere() {
    }

    public Matiere(String logo, String nom, String description) {
        this.logo = logo;
        this.nom = nom;
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
