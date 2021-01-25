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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.DAO.FormateurDaoImp;
import org.example.Model.Absence;
import org.example.MysqlConnect.ConnectionClass;

import javax.swing.*;

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
    private Label tfID;
    @FXML
    private RadioButton rbJournee;
    @FXML
    private RadioButton rbDemiJournee;
    @FXML
    private  RadioButton rbJustifiee;
    @FXML
    private  RadioButton rbNonJustifiee;
    @FXML
    private TextField tfDate;
    @FXML
    private Label dateNow;
    @FXML
    private TextField tfJustification;
    @FXML
    private Label tfIdAppr;
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
            dateSysNow();
            getSelected();
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

    /* UX Methods */
    public String journeeSelected() {
        String text = "";
        if(rbJournee.isSelected()){
            text += rbJournee.getText();
        } else if (rbDemiJournee.isSelected()) {
            text += rbDemiJournee.getText();
        }
        return text;
    }

    public String justifeSelected() {
        String justife = "";
        if(rbNonJustifiee.isSelected()){
            justife += rbNonJustifiee.getText();
        } else if (rbJustifiee.isSelected()) {
            justife += rbJustifiee.getText();
        }
        return justife;
    }

    public void dateSysNow(){
        String dateText = "";
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        Label lbl = new Label(date);

        dateText += lbl.getText();
        dateNow.setText(dateText);
    }

    private void getSelected(){
        tvAbsence.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String idAbsence = "";
                String idApp = "";
                int ID = tvAbsence.getSelectionModel().getSelectedIndex();
                if(ID <= -1) {
                    return;
                }
                idAbsence += colId.getCellData(ID).toString();
                idApp += colIdAppr.getCellData(ID);
                Label lbl1 = new Label(idAbsence);
                Label lbl2 = new Label(idApp);

                tfID.setText(lbl1.getText());
                tfIdAppr.setText(lbl2.getText());
            }
        });
    }

    /* CRUD methods */
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
        test.insertAbsence(new Absence(journeeSelected(), justifeSelected(), tfIdAppr.getText()));
        System.out.println("Insert successful");
        showAbsences();
    }

    private void updateAb() throws SQLException, ClassNotFoundException {
        test.updateAbsence(journeeSelected(), justifeSelected(), tfID.getText());
        System.out.println("Update successful");
        showAbsences();
    }

    private void deleteAb() throws SQLException, ClassNotFoundException {
        test.deleteAbsence(Integer.parseInt(tfID.getText()));
        System.out.println("Delete successful");
        showAbsences();
    }

}
