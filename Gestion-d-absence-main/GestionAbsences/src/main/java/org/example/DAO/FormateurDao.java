package org.example.DAO;

import javafx.collections.ObservableList;
import org.example.Model.AbUser;
import org.example.Model.Absence;

import java.sql.SQLException;
import java.util.List;

public interface FormateurDao {

    // Create absence or list of absence
    void insertAbsence(AbUser Items) throws SQLException, ClassNotFoundException;

    // Read one student or list of students
    AbUser getEtudiantById(String id) throws SQLException, ClassNotFoundException;
    List<AbUser> getAllClass(String salle) throws SQLException, ClassNotFoundException;

    // Update changer l'absence au etudiant
    void updateAbsence(String absence, String id) throws SQLException, ClassNotFoundException;

    // Delete l'absence letudiant
    boolean deleteAbsence(String id) throws SQLException, ClassNotFoundException;

    public ObservableList<AbUser>AfficheInfos(String dateArg) throws SQLException, ClassNotFoundException;
}
