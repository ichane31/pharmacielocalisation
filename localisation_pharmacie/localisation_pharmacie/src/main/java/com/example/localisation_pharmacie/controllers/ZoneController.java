package com.example.localisation_pharmacie.controllers;

import com.example.localisation_pharmacie.beans.Pharmacie;
import com.example.localisation_pharmacie.beans.Ville;
import com.example.localisation_pharmacie.beans.Zone;
import com.example.localisation_pharmacie.dto.request.CreateUpdateZone;
import com.example.localisation_pharmacie.dto.response.ZoneList;
import com.example.localisation_pharmacie.exception.InvalidDataException;
import com.example.localisation_pharmacie.exception.NotFoundException;
import com.example.localisation_pharmacie.services.VilleService;
import com.example.localisation_pharmacie.services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("/zones")
public class ZoneController {

    @Autowired
    ZoneService zoneService;

    @Autowired
    VilleService villeService;

    @PostMapping("/createZone")
    public ResponseEntity createZone (@RequestBody  CreateUpdateZone createUpdateZone) {
        Map<Object, Object> model = new HashMap<>();
        Ville ville = null;
        if(createUpdateZone.getNom().isEmpty() || createUpdateZone.getNom() ==null) {
            model.put("message","Veuillez preciser le nom de la zone !!!");
            return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
        }
        if(createUpdateZone.getVille()!=null) {
            ville = villeService.getVilleByName(createUpdateZone.getVille());
        }
        Zone zone = zoneService.createZone(createUpdateZone ,ville);
        return new ResponseEntity<>(zone, HttpStatus.OK);
    }

    @GetMapping("/getZoneById/{id}")
    public ResponseEntity getZoneById (@PathVariable(value = "id") Long id) {
        Map<Object, Object> model = new HashMap<>();
        try {
            Zone zone = zoneService.getZoneById(id);
            return new ResponseEntity<>(zone , HttpStatus.OK);
        } catch (NotFoundException e) {
            model.put("message",e.getMessage());
            return new ResponseEntity<>(model,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateZone")
    public ResponseEntity updateZone (@RequestBody  CreateUpdateZone createUpdateZone , @PathVariable(value = "id") Long id) {
        Map<Object, Object> model = new HashMap<>();
        Ville ville = null;
        if(createUpdateZone.getNom() ==null || createUpdateZone.getNom().isEmpty()) {
            model.put("message","Veuillez preciser le non de la zone ");
            return new ResponseEntity<>(model,HttpStatus.BAD_REQUEST);
        }
        try {
            if(createUpdateZone.getVille()!=null) {
             ville = villeService.getVilleByName(createUpdateZone.getVille());
                
            }
            Zone zone = zoneService.updateZone(createUpdateZone , id ,ville);
            return new ResponseEntity(zone , HttpStatus.OK);
        } catch (InvalidDataException e) {
            model.put("message",e.getMessage());
            return new ResponseEntity<>(model,HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            model.put("message",e.getMessage());
            return new ResponseEntity<>(model,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/zones")
    public ResponseEntity getAllZones() {
        List<Zone> zoneList = zoneService.getAllZone();
        return new ResponseEntity<>(zoneList ,HttpStatus.OK);
    }

    @GetMapping("/getZonesVille")
    public ResponseEntity getAllZonesVilles() {
        List<ZoneList> zoneListList = zoneService.getAllZoneVille();

        return  new ResponseEntity(zoneListList ,HttpStatus.OK);
    }
}
