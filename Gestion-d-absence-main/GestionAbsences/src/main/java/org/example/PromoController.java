package org.example;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.DAO.PromoDaoImp;
import org.example.Model.Promo;
import org.example.MysqlConnect.ConnectionClass;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PromoController implements Initializable {

    @FXML
    private TextField promo_id;
    @FXML
    private TextField promo_nom;
    @FXML
    private TextField promo_annee;
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
      String query = "INSERT INTO promo (id_promo, nomPromo, anneeDePromo) VALUES ("+ promo_id.getText()+",'"+promo_nom.getText()+"','"+ promo_annee.getText()+"');";
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);
        shoPromo();
    }

    public void updatePromo() throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionClass.getMyConnexion();
        String query = "UPDATE promo SET nomPromo ='"+ promo_nom.getText()+"', anneeDePromo ='"+promo_annee.getText()+"' WHERE id_promo ="+ promo_id.getText()+";";
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);
        shoPromo();
    }

    public void supprimerPromo() throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionClass.getMyConnexion();
        String query = "DELETE FROM promo WHERE id_promo ="+ promo_id.getText()+";";
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);
        shoPromo();
    }
}
