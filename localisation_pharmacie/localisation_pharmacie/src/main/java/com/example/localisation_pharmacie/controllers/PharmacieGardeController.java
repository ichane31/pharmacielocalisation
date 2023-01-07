package com.example.localisation_pharmacie.controllers;

import com.example.localisation_pharmacie.beans.PharmacieGarde;
import com.example.localisation_pharmacie.dto.request.CreateOrUpdatePharmacieGarde;
import com.example.localisation_pharmacie.dto.response.PharmacieVilleZone;
import com.example.localisation_pharmacie.services.PharmacieGardeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class PharmacieGardeController {

    @Autowired
    PharmacieGardeService pharmacieGardeService;

    @PostMapping("/assignGarde/{gardeid}/ToPharmacie/{pharid}")
    public ResponseEntity assignGardeToPharmacie(@PathVariable Long gardeid , @PathVariable Long pharid , @RequestBody CreateOrUpdatePharmacieGarde createOrUpdatePharmacieGarde) {
        String str = createOrUpdatePharmacieGarde.getStartDate();
        String strEnd = createOrUpdatePharmacieGarde.getEndDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeStart = LocalDateTime.parse(str, formatter);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(strEnd ,formatter);
        PharmacieGarde pharmacieGarde = pharmacieGardeService.assignGardeToPharmacie(pharid ,gardeid , dateTimeStart ,dateTimeEnd);
        return new ResponseEntity(pharmacieGarde, HttpStatus.OK);
    }

    @GetMapping("/allPharmacieGarde/{gardeid}")
    public ResponseEntity getAllPharmacieGarde(@PathVariable Long gardeid) {
        List<PharmacieVilleZone> pharmacieVilleZoneList = pharmacieGardeService.findPharmacieByGardeId(gardeid);
        return new ResponseEntity<>(pharmacieVilleZoneList , HttpStatus.OK);
    }
    @GetMapping("/allPharmacieGarde/{gardeid}/ByVille/{villeId}")
    public ResponseEntity getAllPharmacieGardeByVille(@PathVariable Long gardeid , @PathVariable String villeId) {
        List<PharmacieVilleZone> pharmacieVilleZoneList = pharmacieGardeService.getPharmaciesByGardeByVille(gardeid , villeId);
        return new ResponseEntity<>(pharmacieVilleZoneList , HttpStatus.OK);
    }

    @GetMapping("/allPharmacieGarde/{gardeid}/ByZone/{zoneId}")
    public ResponseEntity getAllPharmacieGardeByZone(@PathVariable Long gardeid ,@PathVariable String zoneId) {
        List<PharmacieVilleZone> pharmacieVilleZoneList = pharmacieGardeService.getPharmaciesByGardeByZone(gardeid ,zoneId);
        return new ResponseEntity<>(pharmacieVilleZoneList , HttpStatus.OK);
    }
}
