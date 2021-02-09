package org.example.Controller;

import javafx.collections.FXCollections;
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
import org.example.DAO.SalleDaoImp;
import org.example.Model.Salle;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SalleController implements Initializable {

    public Button btnAjouter;
    public Button btnModifier;
    public Button btnSupprimer;
    @FXML
    private TableView<Salle> TableAffiche;
    @FXML
    private TableColumn<Salle,Integer> col_idSalle;
    @FXML
    private TableColumn<Salle, String>  col_nomSalle;


    @FXML
    private TextField txt_IdSalle;

    @FXML
    private TextField txt_Salle;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showSalles();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void showSalles() throws SQLException, ClassNotFoundException{


            SalleDaoImp salleDaoImp=new SalleDaoImp();
            col_idSalle.setCellValueFactory(new PropertyValueFactory<Salle,Integer>("id_salle"));
            col_nomSalle.setCellValueFactory(new PropertyValueFactory<Salle, String>("nomDeSalle"));
            ObservableList<Salle> observableArrayList = FXCollections.observableArrayList(salleDaoImp.getAll());
            TableAffiche.setItems(observableArrayList);

        }

    int index = -1;
    @FXML
    void getSelected(MouseEvent event){
        index = TableAffiche.getSelectionModel().getSelectedIndex();

        if (index <= -1){
            return;
        }
        txt_IdSalle.setText(col_idSalle.getCellData(index).toString());
        txt_Salle.setText(col_nomSalle.getCellData(index).toString());


    }

    SalleDaoImp s=new SalleDaoImp();


    public void InserSalle() throws SQLException, ClassNotFoundException
    {


       s.SaveSalle(txt_Salle.getText());

        JOptionPane.showMessageDialog(null, "Ajouté avec succès");
        showSalles();
    }

    public void UpdateSalle() throws SQLException,ClassNotFoundException
    {
        s.updateSalle(Integer.parseInt(txt_IdSalle.getText()), txt_Salle.getText());
        JOptionPane.showMessageDialog(null, "Modifié avec succès");
        showSalles();
    }

    public void DeleteSalle() throws SQLException,ClassNotFoundException
    {
        s.deleteById(Integer.parseInt(txt_IdSalle.getText()));
        JOptionPane.showMessageDialog(null, "Supprimé avec succès");
        showSalles();
    }

    public void ActionBottun(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (actionEvent.getSource() == btnAjouter){
            InserSalle();
            showSalles();
        }
        if (actionEvent.getSource() == btnModifier){
            UpdateSalle();
            showSalles();
        }
        if (actionEvent.getSource() == btnSupprimer){
            DeleteSalle();
            showSalles();
        }
    }
}
