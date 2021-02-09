package org.example.DAO;

import javafx.collections.ObservableList;
import org.example.Model.Promo;
import org.example.Model.Salle;
import org.example.Model.Specialite;
import org.example.Model.Users;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface UserDao{
    public void creatUser(String nom, String prenom, String password, String Email, String Tele, LocalDate date, Object role, String cin, String Specialite, String Sale, String promo) throws SQLException, ClassNotFoundException;
    public void updateUser(int id_user, String nom, String prenom, String password, String email,String tele, LocalDate dateNaissance, Object role, String cin, String Specialite, String Sale, String promo) throws SQLException;
    public void deleteUser(int id, Object role) throws SQLException;
    public ObservableList<Users> getUsersList();
    public List<Salle> getAllSalles() throws SQLException;
    public List<Promo> getAllPromos() throws SQLException;
    public List<Specialite> getAllSpecialites() throws SQLException;

}
