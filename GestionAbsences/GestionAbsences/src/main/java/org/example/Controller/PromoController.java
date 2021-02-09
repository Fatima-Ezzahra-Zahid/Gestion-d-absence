package org.example.Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.App;
import org.example.DAO.PromoDaoImp;
import org.example.Model.Promo;
import org.example.Model.User;
import org.example.MysqlConnect.ConnectionClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PromoController implements Initializable {

    public Button btnBack;
    @FXML
    private TextField promo_id;
    @FXML
    private TextField promo_nom;
    @FXML
    private DatePicker promo_anne;
    @FXML
    private Button promo_ajout;
    @FXML
    private Button promo_update;
    @FXML
    private Button promo_delete;
    @FXML
    private TableView<Promo> table_promo;
    @FXML
    private TableColumn<Promo, Integer> col_id_promo;
    @FXML
    private TableColumn<Promo, String> col_nomPromo;
    @FXML
    private TableColumn<Promo, String> col_annee;
    @FXML
    private void ButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(event.getSource() == promo_ajout){
            ajouterPromo();
        }else if(event.getSource() == promo_update){
            updatePromo();
        }else if(event.getSource() == promo_delete){
            supprimerPromo();
        }
    }
    PromoDaoImp promo = new PromoDaoImp();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            shoPromo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    public ObservableList<Promo> getAllPromo() throws SQLException, ClassNotFoundException{
        ObservableList<Promo> listPromo = promo.getPromo();

        return listPromo;
    }

    public void shoPromo() throws SQLException, ClassNotFoundException {
        ObservableList<Promo> list=getAllPromo();

        col_id_promo.setCellValueFactory(new PropertyValueFactory<Promo, Integer>("id_promo"));
        col_nomPromo.setCellValueFactory(new PropertyValueFactory<Promo, String>("nomPromo"));
        col_annee.setCellValueFactory(new PropertyValueFactory<Promo, String>("anneeDePromo"));


        table_promo.setItems(list);
    }

    public void ajouterPromo() throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionClass.getMyConnexion();
        String query = "INSERT INTO promo (nomPromo, anneeDePromo) VALUES (?,?);";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, promo_nom.getText());
        preparedStatement.setString(2, String.valueOf(promo_anne.getValue()));
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null , "Ajouter avec succès");

        shoPromo();
    }

    public void updatePromo() throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionClass.getMyConnexion();
        String query = "UPDATE promo SET nomPromo =?, anneeDePromo =? WHERE id_promo=?;";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, promo_nom.getText());
        preparedStatement.setString(2, String.valueOf(promo_anne.getValue()));
        preparedStatement.setString(3, promo_id.getText());
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null , "Modifier avec succès");
        shoPromo();
    }

    public void supprimerPromo() throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionClass.getMyConnexion();
        String query = "DELETE FROM promo WHERE id_promo ="+ promo_id.getText()+";";
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);
        JOptionPane.showMessageDialog(null , "Supprimer avec succès");
        shoPromo();
    }

    public void handelMouseAction(MouseEvent mouseEvent) {
        Promo promo = table_promo.getSelectionModel().getSelectedItem();
        promo_id.setText(String.valueOf(promo.getId_promo()));
        promo_nom.setText(promo.getNomPromo());
        promo_anne.setValue(LocalDate.parse(promo.getAnneeDePromo()));
    }

    public void ActionBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnBack){
            App.setRoot("Admin");
        }
    }
}
