package com.example.localisation_pharmacie.dto.request;

import javax.validation.constraints.NotBlank;

public class CreateUpdatePharmacie {

    @NotBlank(message = "Veuillez preciser le nom de votre pharmacie !!!")
    private String nom;

    @NotBlank(message = "Veuillez preciser l'adresse de votre pharmacie !!!")
    private String adresse;

    @NotBlank(message = "Veuillez preciser le latitude ")
    private double latitude;

    @NotBlank(message = "Veuillez preciser la longitude")
    private double longitude;

    @NotBlank(message = "Veuillez selectionner une ville ")
    private String ville ;

    @NotBlank(message = "Veuillez selectionnez une zone")
    private String zone;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
