package org.example.DAO;

import java.sql.SQLException;
import java.util.List;

public interface FormateurDao<T> {

    // Create absence or list of absence
    void insertAbsence(T item) throws SQLException, ClassNotFoundException;

    // Read one student or list of students
    T getEtudiantById(String id) throws SQLException, ClassNotFoundException;
    List<T> getAllAbsence() throws SQLException, ClassNotFoundException;

    // Update changer l'absence au etudiant
    void updateAbsence(String ab, String jt, String id) throws SQLException, ClassNotFoundException;

    // Delete l'absence letudiant
    boolean deleteAbsence(int id) throws SQLException, ClassNotFoundException;
}
