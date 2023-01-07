package com.example.localisation_pharmacie.beans;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Garde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeGarde type;

    @OneToMany(mappedBy = "garde", orphanRemoval = true)
    private Set<PharmacieGarde> pharmacieGardes = new LinkedHashSet<>();

    public Set<PharmacieGarde> getPharmacieGardes() {
        return pharmacieGardes;
    }

    public void setPharmacieGardes(Set<PharmacieGarde> pharmacieGardes) {
        this.pharmacieGardes = pharmacieGardes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeGarde getType() {
        return type;
    }

    public void setType(TypeGarde type) {
        this.type = type;
    }
}
