package org.example.Model;

public class ApprenantAbsente {

    private String cin;
    private String nom;
    private String prenom;
    private String nom_sp;
    private String absences;
    private String justification;
    private String dateAbsence;



    public ApprenantAbsente(){

    }

    public ApprenantAbsente(String cin, String nom, String prenom, String nom_sp, String absences, String justification, String dateAbsence) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.nom_sp = nom_sp;
        this.absences = absences;
        this.justification = justification;
        this.dateAbsence = dateAbsence;
    }
    public ApprenantAbsente( String absences, String justification, String dateAbsence) {
        this.absences = absences;
        this.justification = justification;
        this.dateAbsence = dateAbsence;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
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

    public String getNom_sp() {
        return nom_sp;
    }

    public void setNom_sp(String nom_sp) {
        this.nom_sp = nom_sp;
    }

    public String getAbsences() {
        return absences;
    }

    public void setAbsences(String absences) {
        this.absences = absences;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getDateAbsence() {
        return dateAbsence;
    }

    public void setDateAbsence(String dateAbsence) {
        this.dateAbsence = dateAbsence;
    }
}

