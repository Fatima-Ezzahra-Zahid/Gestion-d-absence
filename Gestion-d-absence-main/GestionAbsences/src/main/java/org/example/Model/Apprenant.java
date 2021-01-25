package org.example.Model;

public class Apprenant extends User {

    private char cin;
    private int id_sp;
    private int id_salle;
    private int id_prom;
    private int countJustifie;
    private int countNonJustifie;
    public Apprenant(){

    }

    public Apprenant(int id_user, String nom, String prenom, String email, String password, String role, String dateNaissance, char cin, int id_sp, int id_salle, int id_prom) {
        super(id_user, nom, prenom, email, password, role, dateNaissance);
        this.cin = cin;
        this.id_sp = id_sp;
        this.id_salle = id_salle;
        this.id_prom = id_prom;
    }

    public Apprenant(String nom, String prenom) {
        super(nom, prenom);
    }

    public Apprenant(int count, boolean isJustifie) {
        if (isJustifie) {
            this.countJustifie = count;
        }else {
            this.countNonJustifie = count;
        }
    }


    public long getCin() {
        return cin;
    }

    public void setCin(char cin) {
        this.cin = cin;
    }

    public int getId_sp() {
        return id_sp;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }

    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public int getId_prom() {
        return id_prom;
    }

    public void setId_prom(int id_prom) {
        this.id_prom = id_prom;
    }

    public int getCountJustifie() {
        return countJustifie;
    }

    public void setCountJustifie(int countJustifie) {
        this.countJustifie = countJustifie;
    }

    public int getCountNonJustifie() {
        return countNonJustifie;
    }

    public void setCountNonJustifie(int countNonJustifie) {
        this.countNonJustifie = countNonJustifie;
    }

    @Override
    public String toString() {
        return "Apprenant{" +
                "Non justifiée =" + countNonJustifie + " justifiée = " + countJustifie +
                '}';
    }
}
