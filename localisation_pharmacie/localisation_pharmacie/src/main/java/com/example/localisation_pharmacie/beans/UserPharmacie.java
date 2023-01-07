package com.example.localisation_pharmacie.beans;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_pharmacie")
public class UserPharmacie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    private String mobile;

    @OneToMany(mappedBy = "userPharmacie")
    private List<Pharmacie> pharmacie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pharmacie> getPharmacie() {
        return pharmacie;
    }

    public void setPharmacie(List<Pharmacie> pharmacie) {
        this.pharmacie = pharmacie;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}