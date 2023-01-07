package ma.projet.gestionville_zone.beans;


import java.io.Serializable;
import java.util.ArrayList;

public class LoginResponse implements Serializable {
    private long id;
    private String name;
    private String username;
    private String password;
    private ArrayList<Pharmacie> pharmacies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<Pharmacie> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(ArrayList<Pharmacie> pharmacies) {
        this.pharmacies = pharmacies;
    }
}