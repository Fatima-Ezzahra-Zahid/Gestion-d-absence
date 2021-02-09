package org.example.Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.DAO.SecretaireDaoImp;
import org.example.DAO.SpecialiteDaoImp;
import org.example.Model.ApprenantAbsente;
import org.example.Model.Specialite;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SpecialiteController implements Initializable {

    public TableColumn<Specialite, String> col_spe;
    public TableColumn<Specialite, Integer> col_nbr;
    public TableView<Specialite> TableAffiche;
    @FXML
    private TextField txt_nomSpecialite;

    @FXML
    private TextField txt_comp;

    SpecialiteDaoImp Sp=new SpecialiteDaoImp();
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            showSpecialite();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public ObservableList<Specialite> getAllSpecialite() throws SQLException, ClassNotFoundException{
        ObservableList<Specialite> listSpecilite = Sp.AfficheSpecialite();

        return listSpecilite;
    }

    public void showSpecialite() throws SQLException, ClassNotFoundException{
        ObservableList<Specialite> list = getAllSpecialite();

        col_spe.setCellValueFactory(new PropertyValueFactory<Specialite, String>("NomSpecialite"));
        col_nbr.setCellValueFactory(new PropertyValueFactory<Specialite, Integer>("NbrCompetence"));

        TableAffiche.setItems(list);
    }

    int index = -1;
    @FXML
    void getSelected (MouseEvent event){
        index = TableAffiche.getSelectionModel().getSelectedIndex();

        if (index <= -1){

            return;
        }
        txt_nomSpecialite.setText(col_spe.getCellData(index).toString());
        txt_comp.setText(col_nbr.getCellData(index).toString());


    }

   
    public void InserSpecialite() throws SQLException, ClassNotFoundException
    {
        Sp.SaveSpecialite(txt_nomSpecialite.getText(), Integer.parseInt(txt_comp.getText()));
        JOptionPane.showMessageDialog(null, "Ajouté avec succès");
        showSpecialite();
    }

    public void UpdateSpecialite() throws SQLException,ClassNotFoundException
    {
        Sp.updateSpecialite(txt_nomSpecialite.getText(), Integer.parseInt(txt_comp.getText()));
        JOptionPane.showMessageDialog(null, "Modifié avec succès");
        showSpecialite();
    }

    public void DeleteSpecialite() throws SQLException,ClassNotFoundException
    {
        Sp.deleteById(txt_nomSpecialite.getText());
        JOptionPane.showMessageDialog(null, "Supprimé avec succès");
        showSpecialite();
    }
}
