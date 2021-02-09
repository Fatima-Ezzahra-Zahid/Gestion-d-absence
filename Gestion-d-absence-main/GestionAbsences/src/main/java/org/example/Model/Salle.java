package org.example.Model;

public class Salle {
    private int id_salle;
    private String nomDeSalle;

    public Salle(){

    }

    public Salle(int id_salle, String nomDeSalle) {
        this.id_salle = id_salle;
        this.nomDeSalle = nomDeSalle;
    }

    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public String getNomDeSalle() {
        return nomDeSalle;
    }

    public void setNomDeSalle(String nomDeSalle) {
        this.nomDeSalle = nomDeSalle;
    }
}
