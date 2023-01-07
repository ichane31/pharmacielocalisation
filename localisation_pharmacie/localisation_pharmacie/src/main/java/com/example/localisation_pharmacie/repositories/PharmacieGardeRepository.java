package com.example.localisation_pharmacie.repositories;

import com.example.localisation_pharmacie.beans.PharmacieGarde;
import com.example.localisation_pharmacie.beans.TypeGarde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface PharmacieGardeRepository extends JpaRepository<PharmacieGarde, Long> {
    List<PharmacieGarde> findByPharmacieIdAndEndDateGreaterThanEqual(Long pharmacieId, LocalDateTime endDate);
    List<PharmacieGarde> findByGardeIdAndEndDateGreaterThanEqual(Long gardeId, LocalDateTime  endDate);
    List<PharmacieGarde> findByEndDateLessThanEqual(LocalDateTime  endDate);

    List<PharmacieGarde> findByPharmacieId(Long pharmacieId);
    List<PharmacieGarde> findByGardeId(Long gardeId);

    List<PharmacieGarde> findByGardeTypeAndEndDateGreaterThanEqual(TypeGarde type, LocalDateTime  endDate);

    List<PharmacieGarde> findByGardeTypeAndStartDateLessThanEqual(TypeGarde type,LocalDateTime  startDate);

    List<PharmacieGarde> findByGardeTypeAndStartDateLessThanEqualAndEndDateGreaterThanEqual(TypeGarde type, LocalDateTime  startDate, LocalDateTime  endDate);
}
