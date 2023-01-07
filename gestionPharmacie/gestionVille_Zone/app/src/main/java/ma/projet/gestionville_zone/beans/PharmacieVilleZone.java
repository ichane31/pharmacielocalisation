package ma.projet.gestionville_zone.beans;

import java.io.Serializable;

public class PharmacieVilleZone implements Serializable {

    private Long id;
    private String nom;
    private String adresse;

    private double latitude ;

    private double longitude;
    private String nomZone ;

    private String nomVille;

    private String gardeStartTime ;

    private String gardeEndTime;

    private boolean expansable ;

    public PharmacieVilleZone(Long id, String nom, String adresse, double latitude, double longitude, String nomZone, String nomVille, String gardeStartTime, String gardeEndTime, boolean expansable) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nomZone = nomZone;
        this.nomVille = nomVille;
        this.gardeStartTime = gardeStartTime;
        this.gardeEndTime = gardeEndTime;
        this.expansable = expansable;
    }

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

    public boolean isExpansable() {
        return expansable;
    }

    public void setExpansable(boolean expansable) {
        this.expansable = expansable;
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

