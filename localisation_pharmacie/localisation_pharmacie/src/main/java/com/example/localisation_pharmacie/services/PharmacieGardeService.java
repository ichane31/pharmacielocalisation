package com.example.localisation_pharmacie.services;

import com.example.localisation_pharmacie.beans.*;
import com.example.localisation_pharmacie.dto.response.PharmacieVilleZone;
import com.example.localisation_pharmacie.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacieGardeService {

    @Autowired
    PharmacieGardeRepository pharmacieGardeRepository;

    @Autowired
    private PharmacieRepository pharmacieRepository;

    @Autowired
    private GardeRepository gardeRepository;

    @Autowired
    ZoneRepository zoneRepository;

    @Autowired
    VilleRepository villeRepository;

    public List<PharmacieGarde> findByPharmacieIdAndEndDateGreaterThanEqual(Long pharmacieId, LocalDateTime  endDate) {
        return pharmacieGardeRepository.findByPharmacieIdAndEndDateGreaterThanEqual(pharmacieId, endDate);
    }

    public List<PharmacieGarde> findByGardeIdAndEndDateGreaterThanEqual(Long gardeId, LocalDateTime  endDate) {
        return pharmacieGardeRepository.findByGardeIdAndEndDateGreaterThanEqual(gardeId, endDate);
    }

    public List<PharmacieGarde> findByEndDateLessThanEqual(LocalDateTime endDate) {
        return pharmacieGardeRepository.findByEndDateLessThanEqual(endDate);
    }

    public PharmacieGarde assignGardeToPharmacie(Long pharmacieId, Long gardeId, LocalDateTime  startDate, LocalDateTime  endDate) {
        Pharmacie pharmacie = pharmacieRepository.findById(pharmacieId).orElseThrow(() -> new EntityNotFoundException());
        Garde garde = gardeRepository.findById(gardeId).orElseThrow(() -> new EntityNotFoundException());

        PharmacieGarde pharmacieGarde = new PharmacieGarde();
        pharmacieGarde.setPharmacie(pharmacie);
        pharmacieGarde.setGarde(garde);
        pharmacieGarde.setStartDate(startDate);
        pharmacieGarde.setEndDate(endDate);

        PharmacieGardeKey id = new PharmacieGardeKey();
        id.setPharmacieId(pharmacie.getId());
        id.setGardeId(garde.getId());
        pharmacieGarde.setId(id);

        return pharmacieGardeRepository.save(pharmacieGarde);
    }

    public List<Garde> findGardeByPharmacieId(Long id){
        List<PharmacieGarde> pharmacieGardes = pharmacieGardeRepository.findByPharmacieId(id);

        List<Garde> gardes = pharmacieGardes.stream().map(PharmacieGarde::getGarde).collect(Collectors.toList());

        return gardes;
    }

    public List<PharmacieVilleZone> findPharmacieByGardeId(Long id){
        List<PharmacieGarde> pharmacieGardes = pharmacieGardeRepository.findByGardeId(id);

        List<PharmacieVilleZone> pharmacies = new ArrayList<>();
        for (PharmacieGarde pharmacieGarde : pharmacieGardes) {
            if(LocalDateTime.now().isBefore(pharmacieGarde.getEndDate())) {

                pharmacies.add(setPharmacieVilleZone(pharmacieGarde));
            }
        }
        return pharmacies;
    }

    public List<PharmacieVilleZone> getPharmaciesByGardeByVille(Long gardeId , String villeNom) {
        List<PharmacieGarde> pharmacieGardes = pharmacieGardeRepository.findByGardeId(gardeId);
        Ville ville = villeRepository.findByNomLikeIgnoreCase(villeNom);

        List<PharmacieVilleZone> pharmacies = new ArrayList<>();
        for (PharmacieGarde pharmacieGarde : pharmacieGardes) {
            if(LocalDateTime.now().isBefore(pharmacieGarde.getEndDate())) {
                if(pharmacieGarde.getPharmacie().getZone().getVille().getId()== ville.getId()) {
                    pharmacies.add(setPharmacieVilleZone(pharmacieGarde));
                }
            }
        }
        return pharmacies;
    }

    public List<PharmacieVilleZone> getPharmaciesByGardeByZone(Long gardeId , String zoneNom) {
        List<PharmacieGarde> pharmacieGardes = pharmacieGardeRepository.findByGardeId(gardeId);
        Zone zone = zoneRepository.findByNomLikeIgnoreCase(zoneNom);
        List<PharmacieVilleZone> pharmacies = new ArrayList<>();
        for (PharmacieGarde pharmacieGarde : pharmacieGardes) {
            if(LocalDateTime.now().isBefore(pharmacieGarde.getEndDate())) {
                if(pharmacieGarde.getPharmacie().getZone().getId()== zone.getId()) {
                    pharmacies.add(setPharmacieVilleZone(pharmacieGarde));
                }
            }
        }
        return pharmacies;
    }


    public List<Pharmacie> fingPharmacieByGardeTypeAndEndDate (TypeGarde type , LocalDateTime  endDate){
        List<PharmacieGarde> pharmacieGardes = pharmacieGardeRepository.findByGardeTypeAndEndDateGreaterThanEqual(type, endDate);
        List<Pharmacie> pharmacies = pharmacieGardes.stream().map(PharmacieGarde::getPharmacie).collect(Collectors.toList());
        return pharmacies;
    }

    public List<Pharmacie> fingPharmacieByGardeTypeAndStartDate (TypeGarde type ){
        LocalDateTime  startDate = LocalDateTime.now();
        List<PharmacieGarde> pharmacieGardes = pharmacieGardeRepository.findByGardeTypeAndStartDateLessThanEqual(type, startDate);
        List<Pharmacie> pharmacies = pharmacieGardes.stream().map(PharmacieGarde::getPharmacie).collect(Collectors.toList());
        return pharmacies;
    }

    public List<Pharmacie> fingPharmacieByGardeTypeAndStartDateEndDate (TypeGarde type , LocalDateTime  date){
        List<PharmacieGarde> pharmacieGardes = pharmacieGardeRepository.findByGardeTypeAndStartDateLessThanEqualAndEndDateGreaterThanEqual(type, date,date);
        List<Pharmacie> pharmacies = pharmacieGardes.stream().map(PharmacieGarde::getPharmacie).collect(Collectors.toList());
        return pharmacies;
    }

    public PharmacieVilleZone setPharmacieVilleZone(PharmacieGarde pharmacieGarde) {
        PharmacieVilleZone pharmacieVilleZone = new PharmacieVilleZone();
        pharmacieVilleZone.setId(pharmacieGarde.getPharmacie().getId());
        pharmacieVilleZone.setNomZone(pharmacieGarde.getPharmacie().getZone().getNom());
        pharmacieVilleZone.setNomVille(pharmacieGarde.getPharmacie().getZone().getVille().getNom());
        pharmacieVilleZone.setNom(pharmacieGarde.getPharmacie().getNom());
        pharmacieVilleZone.setAdresse(pharmacieGarde.getPharmacie().getAdresse());
        pharmacieVilleZone.setLatitude(pharmacieGarde.getPharmacie().getLatitude());
        pharmacieVilleZone.setLongitude(pharmacieGarde.getPharmacie().getLongitude());
        pharmacieVilleZone.setGardeStartTime(pharmacieGarde.getStartDate().getHour()+":"+pharmacieGarde.getStartDate().getMinute());
        pharmacieVilleZone.setGardeEndTime(pharmacieGarde.getEndDate().getHour()+":"+pharmacieGarde.getEndDate().getMinute());
        return pharmacieVilleZone;
    }
}
