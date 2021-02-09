package org.example.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.Model.Specialite;
import org.example.MysqlConnect.ConnectionClass;

import java.sql.*;
import java.util.List;

public class SpecialiteDaoImp implements SpecialiteDao{
    @Override
    public List<Specialite> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public Specialite getById(int id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public Specialite SaveSpecialite( String nomSpecialite, int nbrCompetence) throws ClassNotFoundException, SQLException {
        Specialite reponse = null;

        String requete = "Insert into specialite (nom_sp, numbreDeModule) Values (?,?)";
        PreparedStatement statement = ConnectionClass.getMyConnexion().prepareStatement(requete);

        statement.setString(1,nomSpecialite);
        statement.setInt(2, nbrCompetence);
        statement.executeUpdate();



        reponse = new Specialite( nomSpecialite, nbrCompetence);

        return reponse;
    }

    @Override
    public void updateSpecialite( String nomSpecialite, int nbrCompetence) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        try{
            String requete = "Update specialite set nom_sp=?,numbreDeModule=? where nom_sp=?";
            PreparedStatement statement = ConnectionClass.getMyConnexion().prepareStatement(requete);

            statement.setString(1, nomSpecialite);
            statement.setInt(2, nbrCompetence);
            statement.setString(3,nomSpecialite);
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
    public void deleteById(String Specialite) throws ClassNotFoundException, SQLException {


        Connection conn = null;
        try {
            String requete="DELETE FROM specialite WHERE nom_sp = ?";
            PreparedStatement statement = ConnectionClass.getMyConnexion().prepareStatement(requete);
            statement.setString(1, Specialite);
            statement.executeUpdate();
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
    public ObservableList<Specialite> AfficheSpecialite()
    {
        ObservableList<Specialite> listSpecialite= FXCollections.observableArrayList();
        Connection conn = null;
        try {

            String requete="SELECT nom_sp,numbreDeModule FROM specialite";
            PreparedStatement statement = ConnectionClass.getMyConnexion().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();
            Specialite specialite;
            while (rs.next()) {
                specialite = new Specialite(rs.getString("nom_sp"),rs.getInt("numbreDeModule"));
                listSpecialite.add(specialite);
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
        return listSpecialite;
    }
}
