package com.example.localisation_pharmacie.dto.request;

import javax.validation.constraints.NotBlank;

public class CreateUpdateZone {

    @NotBlank(message = "Veuillez entrer le nom de la zone !!!")
    private String nom;

    @NotBlank(message = "Veuillez preciser le nom de la ville !!!!")
    private String ville ;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
