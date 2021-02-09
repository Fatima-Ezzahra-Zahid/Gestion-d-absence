package org.example.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import org.example.Model.ApprenantAbsente;
import org.example.Model.Secretaire;
import org.example.MysqlConnect.ConnectionClass;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SecretaireDaoImp implements SecretaireDao {

    Statement statement=null;
    @Override
    public List<Secretaire> getAll() throws ClassNotFoundException, SQLException {

        List<Secretaire> secretaires= new ArrayList<Secretaire>();


        statement = ConnectionClass.getMyConnexion().createStatement();
        System.out.println("creation de l'objet Statement");

        //4- selectionner la table secretaire
        ResultSet resultat;
        String requete = "Select * From user";

        resultat = statement.executeQuery(requete);

        while (resultat.next()) {
            int id = resultat.getInt("id_user");
            String nom = resultat.getString("nom");
            String prenom = resultat.getString("prenom");
            String email=resultat.getString("mail");
            String password=resultat.getString("password");
            String role=resultat.getString("role");
            String dateNaissance=resultat.getString("dateDeNaissance");
            String tele=resultat.getString("tele");
            // Creer l'objet Personne
            Secretaire s = new Secretaire(id, nom, prenom,email,password,role,dateNaissance,tele);
            secretaires.add(s);
        }

        return secretaires;
    }

    @Override
    public Secretaire getById(int id) throws ClassNotFoundException, SQLException {
        Secretaire secretaire = null;

        String requete = "Select * From user Where id_user  = ?";
        PreparedStatement statement = ConnectionClass.getMyConnexion().prepareStatement(requete);
        statement.setInt(1, id);
        ResultSet resultat = statement.executeQuery();

        if (resultat.next()) {
            String nom = resultat.getString("nom");
            String prenom = resultat.getString("prenom");
            String email=resultat.getString("mail");
            String password=resultat.getString("password");
            String role=resultat.getString("role");
            String dateNaissance=resultat.getString("dateDeNaissance");
            String tele=resultat.getString("tele");

            secretaire = new Secretaire(id, nom, prenom,email,password,role,dateNaissance,tele);
        }

        return secretaire;
    }

    @Override
    public Secretaire sauveSecretaire(String nom, String prenom, String email, String password, String role, String dateNaissance,String tele) throws ClassNotFoundException, SQLException {
        Secretaire reponse = null;
        long id = -1;

        String requete = "Insert into user (nom, prenom,mail,password,role,dateDeNaissance,tele) Values (?,?,?,?,?,?,?)";
        PreparedStatement statement = ConnectionClass.getMyConnexion().prepareStatement(requete);

        statement.setString(1, nom);
        statement.setString(2, prenom);
        statement.setString(3,email );
        statement.setString(4, password);
        statement.setString(5, role);
        statement.setString(6, dateNaissance);
        statement.setString(7, tele);
        statement.executeUpdate();

        ResultSet rs = statement.getGeneratedKeys();

        if (rs.next()) {
            id = rs.getInt(1);
        }

        reponse = new Secretaire((int) id, nom, prenom,email,password,role,dateNaissance,tele);

        return reponse;
    }

    @Override
    public void updateSecretaire(int id,String nom, String prenom, String email, String password, String role, String dateNaissance,String tele)throws ClassNotFoundException, SQLException {
        Connection conn = null;
        try{
            conn=ConnectionClass.getMyConnexion();
            String requete = "Update user set nom = ?, prenom = ? ,mail= ?,password= ?, role= ?,dateDeNaissance= ?, tele= ?  Where id_user = ?";
            PreparedStatement statement = conn.prepareStatement(requete);

            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3,email );
            statement.setString(4, password);
            statement.setString(5, role);
            statement.setString(6, dateNaissance);
            statement.setString(7, tele);
            statement.setInt(8, id);
            statement.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }




    }

    @Override
    public void deleteById(int id_user) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        try {
            conn=ConnectionClass.getMyConnexion();
            String requete="DELETE FROM user WHERE id_user = ?";
            PreparedStatement statement = conn.prepareStatement(requete);
            statement.setInt(1, id_user);
            statement.executeUpdate();
            System.out.println("Secretaire Supprimé");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public ObservableList<ApprenantAbsente> AfficheApprantAbsence(String specialite, Date date) {
        ObservableList<ApprenantAbsente> ApprenantsAbsentes= FXCollections.observableArrayList();
        Connection conn = null;
        try {
            conn=ConnectionClass.getMyConnexion();

            String requete="SELECT cin,nom,prenom,nom_sp, absences ,justification,dateAbsence FROM apprenant,specialite,absence,user WHERE apprenant.cin=absence.id_appr AND apprenant.id_sp=specialite.id_specialite AND user.id_user=apprenant.id_user AND  nom_sp=? AND dateAbsence=?";
            PreparedStatement statement = conn.prepareStatement(requete);
            statement.setString(1, specialite);
            statement.setDate(2, date);
            ResultSet rs = statement.executeQuery();
            ApprenantAbsente apprenantAbsente;
            while (rs.next()) {
                apprenantAbsente = new  ApprenantAbsente(rs.getString("cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("nom_sp"),rs.getString("absences"),rs.getString("justification"),rs.getString("dateAbsence"));
                ApprenantsAbsentes.add(apprenantAbsente);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return ApprenantsAbsentes;
    }

    @Override
    public void UpdateJustification(String justification, String cin,String dateAbsence) {

        Connection conn = null;
        try{
            conn=ConnectionClass.getMyConnexion();
            String requete = "Update absence set justification= ? where id_appr = ? AND dateAbsence=?";
            PreparedStatement statement = conn.prepareStatement(requete);

            statement.setString(1, justification);
            statement.setString(2, cin);
            statement.setString(3, dateAbsence);
            statement.executeUpdate();
        }
        catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public ObservableList<ApprenantAbsente> AfficheAllpprantAbsence() {

        ObservableList<ApprenantAbsente> ApprenantsAbsentes= FXCollections.observableArrayList();
        Connection conn = null;
        try {

            conn=ConnectionClass.getMyConnexion();
            String requete="SELECT cin,nom,prenom,nom_sp, absences ,justification,dateAbsence FROM apprenant,specialite,absence,user WHERE apprenant.cin=absence.id_appr AND apprenant.id_sp=specialite.id_specialite AND user.id_user=apprenant.id_user";
            PreparedStatement statement =conn.prepareStatement(requete);
            ResultSet rs = statement.executeQuery();
            ApprenantAbsente apprenantAbsente;
            while (rs.next()) {
                apprenantAbsente = new  ApprenantAbsente(rs.getString("cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("nom_sp"),rs.getString("absences"),rs.getString("justification"),rs.getString("dateAbsence"));
                ApprenantsAbsentes.add(apprenantAbsente);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return ApprenantsAbsentes;

    }


}
