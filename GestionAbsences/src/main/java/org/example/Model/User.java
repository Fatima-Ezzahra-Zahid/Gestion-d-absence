package org.example.Model;

public class User {

    private int id_user;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String role;
    private String DateNaissance;
<<<<<<< HEAD
    private String tele;
=======
>>>>>>> main

    public User()
    {

    }

<<<<<<< HEAD
    public User(int id_user, String nom, String prenom, String email, String password, String role, String dateNaissance, String tele) {
=======
    public User(int id_user, String nom, String prenom, String email, String password, String role, String dateNaissance) {
>>>>>>> main
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        DateNaissance = dateNaissance;
<<<<<<< HEAD
        this.tele = tele;
=======
>>>>>>> main
    }

    public User(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        DateNaissance = dateNaissance;
    }
<<<<<<< HEAD

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }
=======
>>>>>>> main
}
