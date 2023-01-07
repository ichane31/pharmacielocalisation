package com.example.localisation_pharmacie.services;

import com.example.localisation_pharmacie.beans.Pharmacie;
import com.example.localisation_pharmacie.beans.Ville;
import com.example.localisation_pharmacie.beans.Zone;
import com.example.localisation_pharmacie.dto.response.VillePharmacieCount;
import com.example.localisation_pharmacie.exception.InvalidDataException;
import com.example.localisation_pharmacie.exception.NotFoundException;
import com.example.localisation_pharmacie.repositories.PharmacieRepository;
import com.example.localisation_pharmacie.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VilleService {

    @Autowired
    VilleRepository villeRepository;

    @Autowired
    ZoneService zoneService;


    @Autowired
    PharmacieRepository pharmacieRepository;

    public Ville CreateVille (Ville ville) {
        return villeRepository.save(ville);
    }

    public List<Ville> getAllVilles()  {
        List<Ville> villeList =  villeRepository.findAll();
        return villeList;
    }

    public Ville updateVille(Ville ville) {
        return  villeRepository.save(ville);
    }

    public Ville getVilleById (Long id) throws NotFoundException {
        Ville ville = villeRepository.findById(id).get();
        if(ville ==null){
            throw new NotFoundException("Ville non trouvé pour l'id = " + id);
        }
        else {
            return ville;
        }
    }

    public  Ville getVilleByName1(String name) throws NotFoundException, InvalidDataException {
        List<Ville> villes = villeRepository.findAll();
        Ville ville = null;
        if(name==null) {
            throw new InvalidDataException("Le nom de la ville ne doit pas etre  null");
        }
        for(Ville con : villes) {
            if(con.getNom().equalsIgnoreCase(name)) {
                ville=con;
            }
        }
        if(ville ==null) {
            throw new NotFoundException(String.format("Ville non trouvé pour le nom  =%s " ,name));
        }
        return ville;
    }

    public Ville getVilleByName(String nom) {
        return  villeRepository.findByNomLikeIgnoreCase(nom);
    }

    public void deleteVille (Ville ville) throws NotFoundException {
        if(ville==null) {
            throw new NotFoundException("Impossible de supprimer une ville qui n'existe pas !!!");
        }
        villeRepository.delete(ville);
    }

    public List<Zone> getAllZoneByVille (Ville ville ) throws NotFoundException {
        List<Zone> zoneList = new ArrayList<>();
        if(ville ==null){
            throw new NotFoundException("Ville non trouvé ");
        }
        List<Zone> zones = zoneService.getAllZone();
        for (Zone z : zones) {
            if(z.getVille().getId()==ville.getId()) {
                zoneList.add(z);
            }
        }
        return zoneList;
    }

    public List<Pharmacie> getAllPharmacieByVille (Ville ville) {
        List<Pharmacie> pharmacies = new ArrayList<>();
        if(ville !=null) {
            List<Pharmacie> pharmacieList = pharmacieRepository.findAll();
            for(Pharmacie p : pharmacieList) {
                if(p.getZone().getVille().getId() ==ville.getId()) {
                    pharmacies.add(p);
                }
            }
        }
        return pharmacies;
    }

    public List<VillePharmacieCount> getVillePharmacyCounts() throws NotFoundException {
        // get all cities
        List<Ville> cities = villeRepository.findAll();
        // create a map to store the city names and pharmacy counts
        List<VillePharmacieCount> cityPharmacyCounts = new ArrayList<>();

        // for each city, get the pharmacy count
        for (Ville city : cities) {
            // get all zones for the city
            List<Zone> zones = zoneService.zoneRepository.findAllByVille(city);

            // count the pharmacies for the city
            int pharmacyCount = 0;
            for (Zone zone : zones) {
                // get pharmacies for the zone
                List<Pharmacie> pharmacies = pharmacieRepository.findByZone(zone);
                pharmacyCount += pharmacies.size();
            }

            // create a CityPharmacyCount object to store the city data
            VillePharmacieCount cpc = new VillePharmacieCount();
            cpc.setId(city.getId());
            cpc.setNom(city.getNom());
            cpc.setPharmacieCount(pharmacyCount);

            cityPharmacyCounts.add(cpc);
        }

        return cityPharmacyCounts;
    }
}
