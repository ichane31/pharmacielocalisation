package com.example.localisation_pharmacie.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity(name = "PharmacieGarde")
@Table(name = "pharmacie_garde")
public class PharmacieGarde {

    @EmbeddedId
    private PharmacieGardeKey id;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "pharmacie_id",insertable=false, updatable=false)
    private Pharmacie pharmacie;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "garde_id" ,insertable=false, updatable=false)
    private Garde garde;

    @Column
    private LocalDateTime startDate ;

    @Column
    private LocalDateTime  endDate ;

    public PharmacieGarde(PharmacieGardeKey id, Pharmacie pharmacie, Garde garde) {
        this.id = new PharmacieGardeKey(pharmacie.getId(),garde.getId());
        this.pharmacie = pharmacie;
        this.garde = garde;
    }

    public PharmacieGarde() {

    }

    public PharmacieGardeKey getId() {
        return id;
    }

    public void setId(PharmacieGardeKey id) {
        this.id = id;
    }

    public Pharmacie getPharmacie() {
        return pharmacie;
    }

    public void setPharmacie(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    public Garde getGarde() {
        return garde;
    }

    public void setGarde(Garde garde) {
        this.garde = garde;
    }

    public LocalDateTime  getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime  startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime  getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime  endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pharmacie, garde);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PharmacieGarde that = (PharmacieGarde) o;
        return Objects.equals(pharmacie, that.pharmacie) &&
                Objects.equals(garde, that.garde);
    }
}
