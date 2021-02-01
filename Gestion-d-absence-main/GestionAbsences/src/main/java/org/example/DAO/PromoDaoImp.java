package org.example.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.Model.Promo;
import org.example.MysqlConnect.ConnectionClass;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PromoDaoImp implements PromoDao {
    @Override
    public List<Promo> getAll() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionClass.getMyConnexion();

        List<Promo> promos = new LinkedList<>();
        String query = "SELECT * FROM promo;";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Promo promo = new Promo(resultSet.getInt("id_promo"), resultSet.getString("nomPromo"), resultSet.getString("anneeDePromo"));
                promos.add(promo);
            }
            return promos;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ObservableList<Promo> getPromo() throws SQLException, ClassNotFoundException {
        ObservableList<Promo> promos = FXCollections.observableArrayList();
        Connection conn = ConnectionClass.getMyConnexion();
        String query = "SELECT * FROM promo";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Promo promo = new Promo(resultSet.getInt("id_promo"), resultSet.getString("nomPromo"), resultSet.getString("anneeDePromo"));
                promos.add(promo);
            }
            return promos;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return promos;
    }

    @Override
    public Promo getById(int id_promo) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionClass.getMyConnexion();


        String query = "SELECT * FROM promo WHERE id_promo=?;";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1, id_promo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Promo promo = new Promo(resultSet.getInt("id_promo"), resultSet.getString("nomPromo"), resultSet.getString("anneeDePromo"));
                return promo;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void SavePromo(Promo promo) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionClass.getMyConnexion();

        String query = "INSERT INTO promo (id_promo, nomPromo, anneeDePromo) VALUES (?, ?, ?);";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){


            preparedStatement.setInt(1, promo.getId_promo());
            preparedStatement.setString(2, promo.getNomPromo());
            preparedStatement.setString(3, promo.getAnneeDePromo());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    public void updatePromo(Promo promo) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionClass.getMyConnexion();

        String query = "UPDATE promo SET nomPromo = ?, anneeDePromo = ? WHERE id_promo = ?;";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){

            preparedStatement.setString(1, promo.getNomPromo());
            preparedStatement.setString(2,  promo.getAnneeDePromo());
            preparedStatement.setInt(3, promo.getId_promo());



            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void deleteById(int id_promo) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionClass.getMyConnexion();

        String query = "DELETE FROM promo WHERE id_promo = ?;";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){

            preparedStatement.setInt(1,id_promo);


            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
