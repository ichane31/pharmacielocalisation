package com.example.localisation_pharmacie.services;

import com.example.localisation_pharmacie.beans.Pharmacie;
import com.example.localisation_pharmacie.beans.Ville;
import com.example.localisation_pharmacie.beans.Zone;
import com.example.localisation_pharmacie.dto.request.CreateUpdateZone;
import com.example.localisation_pharmacie.dto.response.VillePharmacieCount;
import com.example.localisation_pharmacie.dto.response.ZoneList;
import com.example.localisation_pharmacie.exception.InvalidDataException;
import com.example.localisation_pharmacie.exception.NotFoundException;
import com.example.localisation_pharmacie.repositories.PharmacieRepository;
import com.example.localisation_pharmacie.repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZoneService {

    @Autowired
    ZoneRepository zoneRepository;

    @Autowired
    PharmacieRepository pharmacieRepository;

    public List<Zone> getAllZone (){
        List<Zone> zoneList = zoneRepository.findAll();
        return zoneList;
    }

    public Zone createZone(CreateUpdateZone createUpdateZone ,Ville ville) {

            Zone zone = new Zone();
            zone.setNom(createUpdateZone.getNom());
            zone.setVille(ville);
            Zone createdZone = zoneRepository.save(zone);
            return createdZone;

    }

    public Zone getZoneById(Long id) throws NotFoundException {
        Zone zone = zoneRepository.findById((long) id).get();
        if(zone==null){
            throw new NotFoundException("Zone non trouvé");
        }
        return zone;
    }

    public Zone updateZone (CreateUpdateZone createUpdateZone , Long id ,Ville ville) throws NotFoundException, InvalidDataException {
        Zone zone = getZoneById(id);
        if(createUpdateZone.getVille() !=null && !createUpdateZone.getVille().isEmpty()) {
            zone.setVille(ville);
        }

        zone.setNom(createUpdateZone.getNom());
        zoneRepository.save(zone);
        return zone;
    }

    public Zone getZoneByName1 (String name) throws InvalidDataException, NotFoundException {
        List<Zone> users = zoneRepository.findAll();
        Zone zone = null;
        if(name==null) {
            throw new InvalidDataException("Le nom de la zone ne doit pas etre null ");
        }
        for(Zone con : users) {
            if(con.getNom().equalsIgnoreCase(name)) {
                zone=con;
            }
        }
        if(zone ==null) {
            throw new NotFoundException(String.format("Zone non trouvé pour le nom =%s " ,name));
        }
        return zone;
    }

    public Zone getZoneByName (String nom) {
        return zoneRepository.findByNomLikeIgnoreCase(nom);
    }

    public List<ZoneList> getAllZoneVille () {
        List<Zone> zoneList = zoneRepository.findAll();
        List<ZoneList> zoneListList = new ArrayList<>();

        for (Zone z : zoneList) {

            List<Pharmacie> pharmacies = pharmacieRepository.findByZone(z);

            ZoneList zoneList1 = new ZoneList();
            zoneList1.setId(z.getId());
            zoneList1.setNom(z.getNom());
            zoneList1.setNomVille(z.getVille().getNom());
            zoneList1.setPharmacieCount(pharmacies.size());

            zoneListList.add(zoneList1);
        }
        return zoneListList;
     }
 }
