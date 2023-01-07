package com.example.localisation_pharmacie.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Pharmacie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nom;
    private String adresse;

    private double latitude ;

    private double longitude;

    private Etat etat;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @JsonIgnore
    @ManyToOne
    private UserPharmacie userPharmacie;
    @OneToMany(mappedBy = "pharmacie", orphanRemoval = true)
    private Set<PharmacieGarde> pharmacieGardes = new LinkedHashSet<>();

    public Set<PharmacieGarde> getPharmacieGardes() {
        return pharmacieGardes;
    }

    public void setPharmacieGardes(Set<PharmacieGarde> pharmacieGardes) {
        this.pharmacieGardes = pharmacieGardes;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
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

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public UserPharmacie getUserPharmacie() {
        return userPharmacie;
    }

    public void setUserPharmacie(UserPharmacie userPharmacie) {
        this.userPharmacie = userPharmacie;
    }
}
