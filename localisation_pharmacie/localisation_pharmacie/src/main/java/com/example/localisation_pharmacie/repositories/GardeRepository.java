package com.example.localisation_pharmacie.repositories;

import com.example.localisation_pharmacie.beans.Garde;
import com.example.localisation_pharmacie.beans.TypeGarde;
import com.example.localisation_pharmacie.beans.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GardeRepository extends JpaRepository<Garde, Long> {
    Garde findByType(TypeGarde typeGarde);
}
