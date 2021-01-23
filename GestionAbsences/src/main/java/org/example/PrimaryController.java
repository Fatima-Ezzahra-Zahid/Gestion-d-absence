/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.DAO.FormateurDaoImp;
import org.example.Model.Absence;
import org.example.MysqlConnect.ConnectionClass;

/**
 * FXML Controller class
 *
 * @author Anassifi
 */
public class PrimaryController implements Initializable {

    FormateurDaoImp test = new FormateurDaoImp();
    @FXML
    private Label fkId;
    @FXML
    private Label fkAbsences;
    @FXML
    private Label fkDate;
    @FXML
    private Label fkJustification;
    @FXML
    private Label fkIdAppr;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfAbsences;
    @FXML
    private TextField tfDate;
    @FXML
    private TextField tfJustification;
    @FXML
    private TextField tfIdAppr;
    @FXML
    private TableView<Absence> tvAbsence;
    @FXML
    private TableColumn<Absence, Integer> colId;
    @FXML
    private TableColumn<Absence, String> colAbsences;
    @FXML
    private TableColumn<Absence, String> colDate;
    @FXML
    private TableColumn<Absence, String> colJustification;
    @FXML
    private TableColumn<Absence, String> colIdAppr;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            showAbsences();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(event.getSource() == btnInsert) {
            insertAb();
        } else if(event.getSource() == btnUpdate){
            updateAb();
        } else if(event.getSource() == btnDelete){
            deleteAb();
        }
    }

    public ObservableList<Absence> getAbsenceList() throws SQLException, ClassNotFoundException {

        ObservableList<Absence> absenceList = (ObservableList<Absence>) test.getAllAbsence();
        return absenceList;
    }

    public void showAbsences() throws SQLException, ClassNotFoundException{
        ObservableList<Absence> list = getAbsenceList();

        colId.setCellValueFactory(new PropertyValueFactory<Absence, Integer>("id_absence"));
        colAbsences.setCellValueFactory(new PropertyValueFactory<Absence, String>("absence"));
        colDate.setCellValueFactory(new PropertyValueFactory<Absence, String>("date"));
        colJustification.setCellValueFactory(new PropertyValueFactory<Absence, String>("justification"));
        colIdAppr.setCellValueFactory(new PropertyValueFactory<Absence, String>("id_appr"));

        tvAbsence.setItems(list);
    }

    private void insertAb() throws SQLException, ClassNotFoundException {
        test.insertAbsence(new Absence(tfAbsences.getText(), tfDate.getText(), tfJustification.getText(), tfIdAppr.getText()));
        System.out.println("Insert successful");
        showAbsences();
    }

    private void updateAb() throws SQLException, ClassNotFoundException {
        test.updateAbsence(tfAbsences.getText(), tfDate.getText(), tfJustification.getText(), tfID.getText());
        System.out.println("Update successful");
        showAbsences();
    }

    private void deleteAb() throws SQLException, ClassNotFoundException {
        test.deleteAbsence(Integer.parseInt(tfID.getText()));
        System.out.println("Delete successful");
        showAbsences();
    }

}
