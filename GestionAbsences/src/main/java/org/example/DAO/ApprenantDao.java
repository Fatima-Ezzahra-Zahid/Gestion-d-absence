package org.example.DAO;

import org.example.Model.Apprenant;

import java.sql.SQLException;

public interface ApprenantDao {

    Apprenant selectApprenant(String email) throws SQLException, ClassNotFoundException;

    Apprenant selectJustifie(String email) throws SQLException, ClassNotFoundException;

    Apprenant selectNonJustifie(String email) throws SQLException, ClassNotFoundException;
}
