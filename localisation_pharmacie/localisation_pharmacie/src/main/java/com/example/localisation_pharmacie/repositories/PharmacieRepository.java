package com.example.localisation_pharmacie.repositories;

import com.example.localisation_pharmacie.beans.Garde;
import com.example.localisation_pharmacie.beans.Pharmacie;
import com.example.localisation_pharmacie.beans.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacieRepository extends JpaRepository<Pharmacie, Long> {
    List<Pharmacie> findByZone(Zone zone);
}
