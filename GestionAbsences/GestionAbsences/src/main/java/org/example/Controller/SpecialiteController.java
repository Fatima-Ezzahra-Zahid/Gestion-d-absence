package org.example.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.App;
import org.example.DAO.SpecialiteDaoImp;
import org.example.Model.Specialite;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SpecialiteController implements Initializable {

    public TableColumn<Specialite, String> col_spe;
    public TableColumn<Specialite, Integer> col_nbr;
    public TableColumn<Specialite, Integer> colId;
    public TableView<Specialite> TableAffiche;
    public Button btnBack;
    @FXML
    private TextField txt_nomSpecialite;

    @FXML
    private TextField txt_comp;

    @FXML
    private TextField tfId;

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

        col_spe.setCellValueFactory(new PropertyValueFactory<Specialite, String>("nom_sp"));
        col_nbr.setCellValueFactory(new PropertyValueFactory<Specialite, Integer>("numberDeModule"));
        colId.setCellValueFactory(new PropertyValueFactory<Specialite, Integer>("id"));

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
        tfId.setText(colId.getCellData(index).toString());

    }

   
    public void InserSpecialite() throws SQLException, ClassNotFoundException
    {
        Sp.SaveSpecialite(txt_nomSpecialite.getText(), Integer.parseInt(txt_comp.getText()));
        JOptionPane.showMessageDialog(null, "Ajouter avec succès");
        showSpecialite();
    }

    public void UpdateSpecialite() throws SQLException,ClassNotFoundException
    {
        Sp.updateSpecialite(txt_nomSpecialite.getText(), Integer.parseInt(txt_comp.getText()), Integer.parseInt(tfId.getText()));
        JOptionPane.showMessageDialog(null, "Modifier avec succès");
        showSpecialite();
    }

    public void DeleteSpecialite() throws SQLException,ClassNotFoundException
    {
        Sp.deleteById(txt_nomSpecialite.getText());
        JOptionPane.showMessageDialog(null, "Supprimer avec succès");
        showSpecialite();
    }

    public void actionClick(ActionEvent event) throws IOException {
        if (event.getSource() == btnBack){
            App.setRoot("admin");
        }
    }
}
