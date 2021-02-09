package org.example.Model;

public class Specialite {
    private int id;
    private String nom_sp;
    private int numberDeModule;

    public Specialite(int id, String nom_sp, int nbrCompetence) {
        this.id = id;
        this.nom_sp = nom_sp;
        this.numberDeModule = nbrCompetence;
    }
    public Specialite(){

    }

    public Specialite(String nomSpecialite, int numbreDeModule) {
        this.nom_sp=nomSpecialite;
        this.numberDeModule=numbreDeModule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_sp() {
        return nom_sp;
    }

    public void setNom_sp(String nom_sp) {
        this.nom_sp = nom_sp;
    }

    public int getNumberDeModule() {
        return numberDeModule;
    }

    public void setNumberDeModule(int numberDeModule) {
        this.numberDeModule = numberDeModule;
    }
}
