package org.example.DAO;

import org.example.Model.Salle;
import org.example.MysqlConnect.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SalleDaoImp implements SalleDao {

    @Override
    public List<Salle> getAll() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionClass.getMyConnexion();

        List<Salle> salles = new LinkedList<>();
        String query = "SELECT * FROM salle";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Salle salle = new Salle(resultSet.getInt("id_salle"), resultSet.getString("nomDeSalle"));
                salles.add(salle);
            }
                return salles;
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
    public Salle getById(int id_salle) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionClass.getMyConnexion();


        String query = "SELECT * FROM salle WHERE id_salle=?;";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1, id_salle);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Salle salle = new Salle(resultSet.getInt("id_salle"), resultSet.getString("nomDeSalle"));
                return salle;
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
    public void SaveSalle(String nomDeSalle) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionClass.getMyConnexion();

        String query = "INSERT INTO salle (nomDeSalle) VALUES (?);";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){

            preparedStatement.setString(1, nomDeSalle);

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
    public void updateSalle(int id, String nomDeSalle) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionClass.getMyConnexion();

        String query = "UPDATE salle SET nomDeSalle = ? WHERE id_salle = ?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){

            preparedStatement.setString(1, nomDeSalle);
            preparedStatement.setInt(2, id);


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
    public void deleteById(int id_salle) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionClass.getMyConnexion();

        String query = "DELETE FROM salle WHERE id_salle = ?;";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){

            preparedStatement.setInt(1,id_salle);


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
