package com.example.localisation_pharmacie.dto.response;

public class PharmacieVilleZone {

    private Long id;
    private String nom;
    private String adresse;

    private double latitude ;

    private double longitude;
    private String nomZone ;

    private String nomVille;
    private String mobile;

    private String gardeStartTime ;

    private String gardeEndTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getNomZone() {
        return nomZone;
    }

    public void setNomZone(String nomZone) {
        this.nomZone = nomZone;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public String getGardeStartTime() {
        return gardeStartTime;
    }

    public void setGardeStartTime(String gardeStartTime) {
        this.gardeStartTime = gardeStartTime;
    }

    public String getGardeEndTime() {
        return gardeEndTime;
    }

    public void setGardeEndTime(String gardeEndTime) {
        this.gardeEndTime = gardeEndTime;
    }
}
