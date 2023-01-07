package com.example.localisation_pharmacie.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ville_id")
    private Ville ville;

    @OneToMany(mappedBy = "zone", orphanRemoval = true)
    private Set<Pharmacie> pharmacies = new LinkedHashSet<>();

    public Set<Pharmacie> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(Set<Pharmacie> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
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
}
