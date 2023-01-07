package com.example.localisation_pharmacie.dto.request;

import javax.validation.constraints.NotBlank;

public class CreateUpdateVille {

    @NotBlank(message = "Veuillez preciser le noml de la ville")
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
