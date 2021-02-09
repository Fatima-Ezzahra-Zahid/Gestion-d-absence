package org.example.DAO;

import javafx.collections.ObservableList;
import org.example.Model.ApprenantAbsente;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ApprenantDao {

    public ArrayList<String> AfficheInfos(String email);

    public ArrayList<Integer>NbrdAbsences(String email,String dateAb) throws SQLException, ClassNotFoundException;

    public ObservableList<ApprenantAbsente> DetailletAbsence(String email,String dateAb)throws SQLException, ClassNotFoundException;
}
