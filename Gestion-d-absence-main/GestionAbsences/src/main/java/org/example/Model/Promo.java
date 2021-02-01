package org.example.Model;

public class Promo {
    private int id_promo;
    private String nomPromo;
    private String anneeDePromo;

    public Promo() {

    }

    public Promo(int id_promo, String nomPromo, String anneeDePromo) {
        this.id_promo = id_promo;
        this.nomPromo = nomPromo;
        this.anneeDePromo = anneeDePromo;
    }

    public int getId_promo() {
        return id_promo;
    }

    public void setId_promo(int id_promo) {
        this.id_promo = id_promo;
    }

    public String getNomPromo() {
        return nomPromo;
    }

    public void setNomPromo(String nomPromo) {
        this.nomPromo = nomPromo;
    }

    public String getAnneeDePromo() {
        return anneeDePromo;
    }

    public void setAnneeDePromo(String anneeDePromo) {
        this.anneeDePromo = anneeDePromo;
    }

    @Override
    public String toString() {
        return "Promo{" +
                "id_promo=" + id_promo +
                ", nomPromo='" + nomPromo + '\'' +
                ", anneeDePromo=" + anneeDePromo +
                '}';
    }
}
