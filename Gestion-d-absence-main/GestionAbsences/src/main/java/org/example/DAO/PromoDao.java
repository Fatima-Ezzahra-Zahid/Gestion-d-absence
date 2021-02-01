package org.example.DAO;

import javafx.collections.ObservableList;
import org.example.Model.Promo;

import java.sql.SQLException;
import java.util.List;

public interface PromoDao {
    List<Promo> getAll() throws ClassNotFoundException, SQLException;

    public ObservableList<Promo> getPromo() throws SQLException, ClassNotFoundException;

    public Promo getById(int id_promo) throws ClassNotFoundException, SQLException;

    public void SavePromo(Promo promo) throws ClassNotFoundException, SQLException;

    public void updatePromo(Promo promo)throws ClassNotFoundException, SQLException;

    public void deleteById(int id_promo) throws ClassNotFoundException, SQLException;
}
