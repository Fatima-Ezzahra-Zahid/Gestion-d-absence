package org.example.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.Model.ApprenantAbsente;
import org.example.MysqlConnect.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ApprenantDaoImp implements ApprenantDao {


    public ArrayList<String> AfficheInfos( String email) {
        ArrayList<String> ApprenantInfos = new ArrayList<>();
        Connection conn = null;
        try {
            conn=ConnectionClass.getMyConnexion();

            String requete="SELECT nom,prenom FROM user,apprenant where user.id_user=apprenant.id_user and mail=?";
            PreparedStatement statement = conn.prepareStatement(requete);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ApprenantInfos.add(rs.getString("nom"));
                ApprenantInfos.add(rs.getString("prenom"));
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
        return ApprenantInfos;
    }

    @Override
    public ArrayList<Integer> NbrdAbsences(String email,String dateAb) throws SQLException, ClassNotFoundException {


        ArrayList<Integer> InfosJustifiée = new ArrayList<>();
        Connection conn = null;
        try {
            conn=ConnectionClass.getMyConnexion();

            String requete="select count(absences) as cn from absence,user,apprenant where user.id_user=apprenant.id_user and absence.id_appr=apprenant.cin and mail=? and MONTH(dateAbsence)=?";
            PreparedStatement statement = conn.prepareStatement(requete);
            statement.setString(1, email);
            statement.setString(2,dateAb);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                InfosJustifiée.add(rs.getInt("cn"));

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
        return InfosJustifiée;




    }

    @Override
    public ObservableList<ApprenantAbsente> DetailletAbsence(String email, String dateAb) throws SQLException, ClassNotFoundException {
        ObservableList<ApprenantAbsente> ApprenantsAbsentes= FXCollections.observableArrayList();
        Connection conn = null;
        try {
            conn=ConnectionClass.getMyConnexion();

            String requete="select absences,dateAbsence,justification from absence,user,apprenant where user.id_user=apprenant.id_user and absence.id_appr=apprenant.cin and mail=? and MONTH(dateAbsence)=?";
            PreparedStatement statement = conn.prepareStatement(requete);
            statement.setString(1, email);
            statement.setString(2, dateAb);
            ResultSet rs = statement.executeQuery();
            ApprenantAbsente apprenantAbsente;
            while (rs.next()) {
                apprenantAbsente = new  ApprenantAbsente(rs.getString("absences"),rs.getString("justification"),rs.getString("dateAbsence"));
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