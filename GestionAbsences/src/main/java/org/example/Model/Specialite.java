package org.example.Model;

public class Specialite {

    private String NomSpecialite;
    private int NbrCompetence;

    public  Specialite(){

    }

    public Specialite( String nomSpecialite, int nbrCompetence) {
        NomSpecialite = nomSpecialite;
        NbrCompetence = nbrCompetence;
    }



    public String getNomSpecialite() {
        return NomSpecialite;
    }

    public void setNomSpecialite(String nomSpecialite) {
        NomSpecialite = nomSpecialite;
    }

    public int getNbrCompetence() {
        return NbrCompetence;
    }

    public void setNbrCompetence(int nbrCompetence) {
        NbrCompetence = nbrCompetence;
    }
}
