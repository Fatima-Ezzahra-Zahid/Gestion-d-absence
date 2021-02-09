package org.example.Model;

public class Formateur extends User {
    private int id_formateur;
    private int id_sp;
    private int id_salle;
    private int id_user;

    public Formateur() {
    }

    public Formateur(int id_formateur, int id_sp, int id_salle, int id_user) {
        this.id_formateur = id_formateur;
        this.id_sp = id_sp;
        this.id_salle = id_salle;
        this.id_user = id_user;
    }

    public Formateur(int id_user, String nom, String prenom, String email, String password, String role, String dateNaissance, int id_formateur, int id_sp, int id_salle, int id_user1) {
        super(id_user, nom, prenom, email, password, role, dateNaissance);
        this.id_formateur = id_formateur;
        this.id_sp = id_sp;
        this.id_salle = id_salle;
        this.id_user = id_user1;
    }

    public int getId_formateur() {
        return id_formateur;
    }

    public void setId_formateur(int id_formateur) {
        this.id_formateur = id_formateur;
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

    @Override
    public int getId_user() {
        return id_user;
    }

    @Override
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }


}
