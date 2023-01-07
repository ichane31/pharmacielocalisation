package com.example.localisation_pharmacie.repositories;

import com.example.localisation_pharmacie.beans.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {
    Ville findByNomLikeIgnoreCase(@NonNull String nom);
}
