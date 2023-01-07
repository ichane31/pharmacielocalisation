package ma.projet.gestionville_zone.beans;

public class Ville {
    private Long id ;
    private String nom;
    private int pharmacieCount;

    public Ville(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Ville() {
    }

    public int getPharmacieCount() {
        return pharmacieCount;
    }

    public void setPharmacieCount(int pharmacieCount) {
        this.pharmacieCount = pharmacieCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
