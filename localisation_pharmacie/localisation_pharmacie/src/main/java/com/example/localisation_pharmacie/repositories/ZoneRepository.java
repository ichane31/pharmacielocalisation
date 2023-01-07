package com.example.localisation_pharmacie.repositories;

import com.example.localisation_pharmacie.beans.Ville;
import com.example.localisation_pharmacie.beans.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Zone findByNomLikeIgnoreCase(@NonNull String nom);
    List<Zone> findAllByVille(Ville ville);
}
