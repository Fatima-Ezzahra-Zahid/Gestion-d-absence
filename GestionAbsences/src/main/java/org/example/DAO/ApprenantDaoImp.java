package org.example.DAO;

import org.example.Model.Apprenant;
import org.example.MysqlConnect.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApprenantDaoImp implements ApprenantDao {
    @Override
    public Apprenant selectApprenant(String email) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionClass.getMyConnexion();

        String query = "Select nom, prenom From user WHERE mail=? AND role='Apprenant';";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
               Apprenant apprenant  = new Apprenant(resultSet.getString("nom"), resultSet.getString("prenom"));
                return apprenant;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Apprenant selectJustifie(String email) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionClass.getMyConnexion();

        String query = "select count(justification) as cn from absence,user,apprenant where user.id_user=apprenant.id_user and  mail=? and justification= 'Justifiée';";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Apprenant apprenant = new Apprenant(resultSet.getInt("cn"), true);
                return apprenant;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Apprenant selectNonJustifie(String email) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionClass.getMyConnexion();

        String query = "select count(justification) as cn from absence,user,apprenant where user.id_user=apprenant.id_user and  mail=? and justification= 'Non justifiée';";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

                Apprenant apprenant = new Apprenant(resultSet.getInt("cn"), false);
                return apprenant;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}