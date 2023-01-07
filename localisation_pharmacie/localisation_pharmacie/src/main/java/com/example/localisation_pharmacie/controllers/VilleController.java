package com.example.localisation_pharmacie.controllers;


import com.example.localisation_pharmacie.beans.Pharmacie;
import com.example.localisation_pharmacie.beans.Ville;
import com.example.localisation_pharmacie.beans.Zone;
import com.example.localisation_pharmacie.dto.request.CreateUpdateVille;
import com.example.localisation_pharmacie.dto.response.VillePharmacieCount;
import com.example.localisation_pharmacie.exception.InvalidDataException;
import com.example.localisation_pharmacie.exception.NotFoundException;
import com.example.localisation_pharmacie.services.VilleService;
import com.example.localisation_pharmacie.services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("/villes")
public class VilleController {

    @Autowired
    VilleService villeService;

    @Autowired
    ZoneService zoneService;


    @PostMapping("/createVille")
    public ResponseEntity createVille (@RequestBody CreateUpdateVille createUpdateVille ){
        Map<Object, Object> model = new HashMap<>();

        if(createUpdateVille.getNom() ==null) {
            model.put("message","Veuillez preciser le nom de la ville");
            return new ResponseEntity<>(model , HttpStatus.BAD_REQUEST);
        }
        if(createUpdateVille.getNom().isEmpty()){
            model.put("message","Veuillez preciser le nom de la ville");
            return new ResponseEntity<>(model , HttpStatus.BAD_REQUEST);
        }
        if(createUpdateVille.getNom().length()<3){
            model.put("message","Veuillez preciser un nom valide");
            return new ResponseEntity<>(model , HttpStatus.BAD_REQUEST);
        }
        Ville ville = new Ville();
        ville.setNom(createUpdateVille.getNom());
        Ville createdVille = villeService.CreateVille(ville);

        return new ResponseEntity<>(createdVille , HttpStatus.OK);
    }

    @PutMapping("/updateVille/{id}")
    public ResponseEntity updateVille (@RequestBody String nom , @PathVariable(value = "id") Long id) {
        Map<Object, Object> model = new HashMap<>();
        try {
            Ville ville = villeService.getVilleById(id);
            if(nom.isEmpty() || nom == null) {
                model.put("message","Veuillez preciser le nom de la ville");
                return new ResponseEntity<>(model , HttpStatus.BAD_REQUEST);
            }
            if(nom.length()<3){
                model.put("message","Veuillez preciser un nom valide");
                return new ResponseEntity<>(model , HttpStatus.BAD_REQUEST);
            }

            ville.setNom(nom);
            Ville updateVille =villeService.updateVille(ville);

            return new ResponseEntity<>(updateVille , HttpStatus.OK);
        } catch (NotFoundException e) {
            model.put("message",e.getMessage());
            return new ResponseEntity<>(model , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getVille/{id}")
    public ResponseEntity getVilleById (@PathVariable (value = "id") Long id) {
        Map<Object, Object> model = new HashMap<>();
        try {
            Ville ville = villeService.getVilleById(id);
            return new ResponseEntity<>(ville , HttpStatus.OK);
        } catch (NotFoundException e) {
            model.put("message",e.getMessage());
            return new ResponseEntity<>(model,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getVile/nom")
    public ResponseEntity getVilleByNom (@RequestParam(value = "nom") String nom) {
        Map<Object, Object> model = new HashMap<>();
        Ville ville = villeService.getVilleByName(nom);
        return new ResponseEntity<>(ville , HttpStatus.OK);
    }

    @GetMapping("/allZoneqByVille/nom")
    public ResponseEntity getAllZoneByVilleName(@RequestParam(value = "nom") String nom) {
        Map<Object, Object> model = new HashMap<>();
        List<Zone> zoneList = null;
        try {
            Ville ville = villeService.getVilleByName(nom);
            zoneList = villeService.getAllZoneByVille(ville);
            return new ResponseEntity<>(zoneList , HttpStatus.OK);
        } catch (NotFoundException e) {
            model.put("message",e.getMessage());
            return new ResponseEntity<>(model , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allZoneqByVille/{id}")
    public ResponseEntity getAllZoneByVilleId (@PathVariable (value = "id") Long id) {
        Map<Object, Object> model = new HashMap<>();
        List<Zone> zoneList ;
        try {
            Ville ville = villeService.getVilleById(id);
            zoneList = villeService.getAllZoneByVille(ville);
            return new ResponseEntity<>(zoneList , HttpStatus.OK);
        } catch (NotFoundException e) {
            model.put("message",e.getMessage());
            return new ResponseEntity<>(model,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteVille/{id}")
    public ResponseEntity delateVille (@PathVariable (value = "id") Long id) {
        Map<Object, Object> model = new HashMap<>();
        try {
            Ville ville = villeService.getVilleById(id);
            villeService.deleteVille(ville);
            model.put("message","Ville supprimé avec succes !!!");
            return new ResponseEntity<>(model , HttpStatus.OK);
        } catch (NotFoundException e) {
            model.put("message",e.getMessage());
            return new ResponseEntity<>(model , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pharmacy-counts")
    public ResponseEntity getCityPharmacyCounts() {
        try {
            List<VillePharmacieCount> villePharmacieCounts=  villeService.getVillePharmacyCounts();
            return new ResponseEntity<>(villePharmacieCounts ,HttpStatus.OK);
        } catch (NotFoundException e) {
            return  new ResponseEntity<>(e.getMessage() ,HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/allPharmacieByVille/{villeNom}")
    public ResponseEntity getAllPharmacieByVille(@PathVariable String villeNom) {
        Ville ville = villeService.getVilleByName(villeNom);
        List<Pharmacie> pharmacies = villeService.getAllPharmacieByVille(ville);

        return new ResponseEntity<>(pharmacies ,HttpStatus.OK);
    }
}
