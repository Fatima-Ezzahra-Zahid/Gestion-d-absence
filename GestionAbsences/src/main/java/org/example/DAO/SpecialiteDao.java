package org.example.DAO;

import javafx.collections.ObservableList;
import org.example.Model.Specialite;

import java.sql.SQLException;
import java.util.List;

public interface SpecialiteDao {


    public List<Specialite> getAll() throws ClassNotFoundException, SQLException;

    public Specialite getById(int id) throws ClassNotFoundException, SQLException;

    public Specialite SaveSpecialite( String nomSpecialite, int nbrCompetence) throws ClassNotFoundException, SQLException;

    public void updateSpecialite( String nomSpecialite, int nbrCompetence)throws ClassNotFoundException, SQLException;

    public void deleteById(String Specialite) throws ClassNotFoundException, SQLException;

    public ObservableList<Specialite> AfficheSpecialite();


}
