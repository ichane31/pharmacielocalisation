package com.example.localisation_pharmacie.controllers;

import com.example.localisation_pharmacie.beans.Pharmacie;
import com.example.localisation_pharmacie.beans.UserPharmacie;
import com.example.localisation_pharmacie.beans.Zone;
import com.example.localisation_pharmacie.dto.request.CreateUpdatePharmacie;
import com.example.localisation_pharmacie.dto.response.PharmacieVilleZone;
import com.example.localisation_pharmacie.exception.InvalidDataException;
import com.example.localisation_pharmacie.exception.NotFoundException;
import com.example.localisation_pharmacie.repositories.UserPharmacieRepository;
import com.example.localisation_pharmacie.services.PharmacieService;
import com.example.localisation_pharmacie.services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PharmacieController {

    @Autowired
    PharmacieService pharmacieService;

    @Autowired
    ZoneService zoneService;

    @Autowired
    UserPharmacieRepository userPharmacieRepository;

    @PostMapping("/createPharmacie/{userId}")
    public ResponseEntity createPharmacie(@RequestBody CreateUpdatePharmacie createUpdatePharmacie ,@PathVariable int userId) {
        Map<Object, Object> model = new HashMap<>();
        if(createUpdatePharmacie.getNom()==null ||createUpdatePharmacie.getNom().isEmpty()) {
            model.put("message","Veuillez preciser le nom de la pharmacie !!!");
            return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
        }
        try {
            UserPharmacie userPharmacie = userPharmacieRepository.findById(userId).get();
            Pharmacie pharmacie = pharmacieService.createPharmacie(createUpdatePharmacie ,userPharmacie);
            return new ResponseEntity<>(pharmacie, HttpStatus.BAD_REQUEST);
        } catch (InvalidDataException e) {
            model.put("message",e.getMessage());
            return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            model.put("message",e.getMessage());
            return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/getAllPharmacieVilleZone")
    private ResponseEntity getAllPharmacieVilleZone() {
        List<PharmacieVilleZone> pharmacyCityZones = pharmacieService.getAllPharmacieVilleZone();
        return new ResponseEntity<>(pharmacyCityZones , HttpStatus.OK) ;
    }

    @GetMapping("/getAllPharmacieByZone/{nomZone}")
    public ResponseEntity getAllPharmacieByZone(@PathVariable String nomZone) {
        Zone zone = zoneService.getZoneByName(nomZone);
        try {
            List<Pharmacie> pharmacieList = pharmacieService.getAllPharmacieByZone(zone);
            return new ResponseEntity<>(pharmacieList ,HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage() ,HttpStatus.NOT_FOUND);
        }

    }

}
