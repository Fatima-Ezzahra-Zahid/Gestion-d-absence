package org.example.Model;

public class AbUser {
    private int id;
    private String nom;
    private String prenom;
    private String dateAb;
    private String absence;
    private String idAppr;

    public AbUser(){

    }


    public AbUser(int id_absence, String nom, String prenom, String date, String id_appr) {
        this.id = id_absence;
        this.nom = nom;
        this.prenom = prenom;
        this.dateAb = date;
        this.idAppr = id_appr;
    }

    public AbUser(int id, String nom, String prenom, String date, String absence, String idAppr) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateAb = date;
        this.absence = absence;
        this.idAppr = idAppr;
    }

    public AbUser(int id, String absence, String date, String idAppr){
        this.id = id;
        this.absence = absence;
        this.dateAb = date;
        this.idAppr = idAppr;
    }

    public AbUser(String nom, String prenom, String idAppr) {
        this.nom = nom;
        this.prenom = prenom;
        this.idAppr = idAppr;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateAb() {
        return dateAb;
    }

    public void setDateAb(String dateAb) {
        this.dateAb = dateAb;
    }

    public String getAbsence() {
        return absence;
    }

    public void setAbsence(String absence) {
        this.absence = absence;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdAppr() {
        return idAppr;
    }

    public void setIdAppr(String idAppr) {
        this.idAppr = idAppr;
    }

    @Override
    public String toString() {
        return "AbUser{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", idAppr='" + idAppr + '\'' +
                '}';
    }
}
