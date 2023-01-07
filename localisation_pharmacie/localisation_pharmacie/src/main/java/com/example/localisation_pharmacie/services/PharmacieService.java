package com.example.localisation_pharmacie.services;

import com.example.localisation_pharmacie.beans.*;
import com.example.localisation_pharmacie.dto.request.CreateUpdatePharmacie;
import com.example.localisation_pharmacie.dto.response.PharmacieVilleZone;
import com.example.localisation_pharmacie.exception.InvalidDataException;
import com.example.localisation_pharmacie.exception.NotFoundException;
import com.example.localisation_pharmacie.repositories.PharmacieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PharmacieService {

    @Autowired
    PharmacieRepository pharmacieRepository;

    @Autowired
    ZoneService zoneService;

    @Autowired
    VilleService villeService;
    public List<Pharmacie> getAllPharmacies() {
        List<Pharmacie> pharmacies = pharmacieRepository.findAll();
        return pharmacies;
    }

    public Pharmacie createPharmacie (CreateUpdatePharmacie createUpdatePharmacie , UserPharmacie userPharmacie) throws InvalidDataException, NotFoundException {
        if(createUpdatePharmacie.getVille()==null){
            throw new InvalidDataException("Veuillez selectionnez une ville");
        }
        if (createUpdatePharmacie.getZone()==null){
            throw new InvalidDataException("Veuillez selectionnez une zone");
        }

        Ville ville = villeService.getVilleByName(createUpdatePharmacie.getVille());
        List<Zone> zones = villeService.getAllZoneByVille(ville);

        if(zones ==null){
            throw new NotFoundException("Aucune zone dans la ville selectionner ");
        }
        Zone zone = zoneService.getZoneByName(createUpdatePharmacie.getZone());

        Pharmacie pharmacie = new Pharmacie();
        pharmacie.setNom(createUpdatePharmacie.getNom());
        pharmacie.setAdresse(createUpdatePharmacie.getAdresse());
        pharmacie.setLatitude(createUpdatePharmacie.getLatitude());
        pharmacie.setLongitude(createUpdatePharmacie.getLongitude());
        pharmacie.setZone(zone);
        pharmacie.setUserPharmacie(userPharmacie);
        pharmacie.setEtat(Etat.ATTENTE);

        return pharmacieRepository.save(pharmacie);
    }

    public Pharmacie getPharmacieById (Long id) throws NotFoundException {
        Pharmacie pharmacie = pharmacieRepository.findById(id).get();
        if(pharmacie ==null) {
            throw new NotFoundException("Pharmacie non trouvé ");
        }
        return pharmacie;
    }

    public List<Pharmacie> getAllPharmacieByZone(Zone zone) throws NotFoundException {
        List<Pharmacie> pharmacies = new ArrayList<>();
        if(zone ==null){
            throw new NotFoundException("zone non trouvé ");
        }
        List<Pharmacie> pharmacieList = getAllPharmacies();
        for (Pharmacie z : pharmacieList) {
            if(z.getZone().getId()==zone.getId()) {
                pharmacies.add(z);
            }
        }
        return pharmacies;
    }

    public List<PharmacieVilleZone> getAllPharmacieVilleZone() {
        List<Pharmacie> pharmacies = pharmacieRepository.findAll();

        // create a list to store the pharmacy data
        List<PharmacieVilleZone> pharmacyCityZones = new ArrayList<>();

        // for each pharmacy, get the city and zone data
        for (Pharmacie pharmacy : pharmacies) {
            // create a PharmacyCityZone object to store the pharmacy data
            PharmacieVilleZone pcz = new PharmacieVilleZone();
            pcz.setId(pharmacy.getId());
            pcz.setNom(pharmacy.getNom());
            pcz.setAdresse(pharmacy.getAdresse());
            pcz.setLatitude(pharmacy.getLatitude());
            pcz.setLongitude(pharmacy.getLongitude());
            pcz.setNomVille(pharmacy.getZone().getVille().getNom());
            pcz.setNomZone(pharmacy.getZone().getNom());

            pharmacyCityZones.add(pcz);
        }

        return pharmacyCityZones;
    }
}
