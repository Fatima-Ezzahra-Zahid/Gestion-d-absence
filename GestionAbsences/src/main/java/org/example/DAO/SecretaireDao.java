package org.example.DAO;

import org.example.Model.Secretaire;
import org.example.Model.ApprenantAbsente;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import java.util.List;

public interface SecretaireDao {

    public List<Secretaire> getAll() throws ClassNotFoundException, SQLException;

    public Secretaire getById(int id) throws ClassNotFoundException, SQLException;

    public Secretaire sauveSecretaire(String nom, String prenom, String email, String password, String role, String dateNaissance,String tele) throws ClassNotFoundException, SQLException;

    public void updateSecretaire(int id,String nom, String prenom, String email, String password, String role, String dateNaissance,String tele)throws ClassNotFoundException, SQLException;

    public void deleteById(int id_user) throws ClassNotFoundException, SQLException;

    public  ObservableList<ApprenantAbsente>AfficheApprantAbsence(String specialite);

    public void UpdateJustification(String justification,String cin);

}
