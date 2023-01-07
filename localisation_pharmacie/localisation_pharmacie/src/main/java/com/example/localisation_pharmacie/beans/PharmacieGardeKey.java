package com.example.localisation_pharmacie.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PharmacieGardeKey implements Serializable {
    @Column(name = "pharmacie_id")
    private Long pharmacieId;

    @Column(name = "garde_id")
    private Long gardeId;

    public PharmacieGardeKey(Long pharmacieId, Long gardeId) {
        this.pharmacieId=pharmacieId;
        this.gardeId = gardeId;
    }

    public PharmacieGardeKey() {

    }

    public Long getPharmacieId() {
        return pharmacieId;
    }

    public void setPharmacieId(Long pharmacieId) {
        this.pharmacieId = pharmacieId;
    }

    public Long getGardeId() {
        return gardeId;
    }

    public void setGardeId(Long gardeId) {
        this.gardeId = gardeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pharmacieId, gardeId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PharmacieGardeKey
                that = (PharmacieGardeKey) o;
        return Objects.equals(pharmacieId, that.pharmacieId) &&
            Objects.equals(gardeId, that.gardeId);
    }
}
