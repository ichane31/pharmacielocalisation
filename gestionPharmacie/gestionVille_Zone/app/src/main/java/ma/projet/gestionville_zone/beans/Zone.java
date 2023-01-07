package ma.projet.gestionville_zone.beans;

public class Zone {
    private Long id;

    private String nom ;

    private String nomVille ;

    private int pharmacieCount;

    public Zone(Long id, String nom, String nomVille, int pharmacieCount) {
        this.id = id;
        this.nom = nom;
        this.nomVille = nomVille;
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

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public int getPharmacieCount() {
        return pharmacieCount;
    }

    public void setPharmacieCount(int pharmacieCount) {
        this.pharmacieCount = pharmacieCount;
    }
}
