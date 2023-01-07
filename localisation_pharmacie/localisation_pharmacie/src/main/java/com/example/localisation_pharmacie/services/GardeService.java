package com.example.localisation_pharmacie.services;

import com.example.localisation_pharmacie.beans.Garde;
import com.example.localisation_pharmacie.beans.TypeGarde;
import com.example.localisation_pharmacie.repositories.GardeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GardeService {

    @Autowired
    GardeRepository gardeRepository;

    public Garde createGarde (String type){
        Garde garde = new Garde();
        switch (type) {
            case "jour" :
                garde.setType(TypeGarde.JOUR);
                break;
            case "nuit" :
                garde.setType(TypeGarde.NUIT);
                break;
            default:
                garde.setType(TypeGarde.JOUR);
        }
         return  gardeRepository.save(garde);
    }

}
