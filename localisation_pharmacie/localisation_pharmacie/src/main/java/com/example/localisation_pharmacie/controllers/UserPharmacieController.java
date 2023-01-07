package com.example.localisation_pharmacie.controllers;

import com.example.localisation_pharmacie.beans.UserPharmacie;
import com.example.localisation_pharmacie.repositories.UserPharmacieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userpharmacie")
public class UserPharmacieController {
    @Autowired
    UserPharmacieRepository userPharmacieRepository;

    @GetMapping("/all")
    public List<UserPharmacie> findAll(){
        return userPharmacieRepository.findAll();
    }
    @PostMapping("/add")
    public UserPharmacie addUserPharmacie(@RequestBody UserPharmacie userPharmacie) {
        return userPharmacieRepository.save(userPharmacie);
    }
    @PostMapping("/auth")
    public UserPharmacie auth(@RequestBody UserPharmacie userPharmacie){
        return userPharmacieRepository.auth(userPharmacie.getUsername(), userPharmacie.getPassword());
    }
}
