/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.App;
import org.example.DAO.FormateurDaoImp;
import org.example.DAO.SpecialiteDaoImp;
import org.example.Model.AbUser;
import org.example.Model.Specialite;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Anassifi
 */
public class FormateurController implements Initializable {

    public Button btnback;
    FormateurDaoImp test = new FormateurDaoImp();
    AbUser useIt = new AbUser();
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
    private DatePicker dateNow;
    @FXML
    private Label tfIdAppr;
    @FXML
    private TableView<AbUser> tvAbsence;
    @FXML
    private TableColumn<AbUser, Integer> colId;
    @FXML
    private TableColumn<AbUser, String> colFirstName;
    @FXML
    private TableColumn<AbUser, String> colLastName;
    @FXML
    private TableColumn<AbUser, String> colDate;
    @FXML
    private TableColumn<AbUser, String> colAbsence;
    @FXML
    private TableColumn<AbUser, String> colIdAppr;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    @FXML
    private ComboBox comb_sp;
    /**
     * Initializes the controller class.
     */

    List<Specialite> listSpecialite;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpecialiteDaoImp specialiteDaoImp=new SpecialiteDaoImp();
        dateNow.setValue(LocalDate.parse(rtnTodayDate()));
        // TODO
        try {
            listSpecialite =specialiteDaoImp.AfficheSpecialite();
            for (int i = 0; i<listSpecialite.size(); i++) {
                comb_sp.getItems().add(listSpecialite.get(i).getNom_sp());
            }
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

    public String rtnTodayDate(){

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        return date;
    }

    public void dateSysNow(){
        String dateText = rtnTodayDate();
        dateNow.setValue(LocalDate.parse(dateText));
    }

    private void getSelected(){
        tvAbsence.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String idApp = "";
                String absence = "";

                int ID = tvAbsence.getSelectionModel().getSelectedIndex();
                if(ID <= -1) {
                    return;
                }
                absence += colAbsence.getCellData(ID);
                idApp += colIdAppr.getCellData(ID);
                Label lbl2 = new Label(idApp);

                if("Journée".equals(absence)){
                    rbJournee.setSelected(true);
                } else if ("Demi-journée".equals(absence)){
                    rbDemiJournee.setSelected(true);
                } else {
                    rbJournee.setSelected(false);
                    rbDemiJournee.setSelected(false);
                }

                tfIdAppr.setText(lbl2.getText());
            }
        });
    }

    /* CRUD methods */
    public void showAbsences() throws SQLException, ClassNotFoundException{

        ObservableList<AbUser> list = test.AfficheInfos(java.sql.Date.valueOf(dateNow.getValue()).toString());

        colId.setCellValueFactory(new PropertyValueFactory<AbUser, Integer>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<AbUser, String>("prenom"));
        colLastName.setCellValueFactory(new PropertyValueFactory<AbUser, String>("nom"));
        colDate.setCellValueFactory(new PropertyValueFactory<AbUser, String>("dateAb"));
        colAbsence.setCellValueFactory(new PropertyValueFactory<AbUser, String>("absence"));
        colIdAppr.setCellValueFactory(new PropertyValueFactory<AbUser, String>("idAppr"));

        tvAbsence.setItems(list);
    }

    public void showListAppr() throws SQLException, ClassNotFoundException{
        if(comb_sp.getValue()==null)
        {
            JOptionPane.showMessageDialog(null, "s'il vous plait choisir votre spécialite!!");
        }

        else
        {
            ObservableList<AbUser> listAppr = test.getAllClass(comb_sp.getValue().toString());

            colFirstName.setCellValueFactory(new PropertyValueFactory<AbUser, String>("prenom"));
            colLastName.setCellValueFactory(new PropertyValueFactory<AbUser, String>("nom"));
            colIdAppr.setCellValueFactory(new PropertyValueFactory<AbUser, String>("idAppr"));

            tvAbsence.setItems(listAppr);
        }

    }

    private void insertAb() throws SQLException, ClassNotFoundException {
        test.insertAbsence(new AbUser(colId.getCellData(tvAbsence.getSelectionModel().getSelectedIndex()), journeeSelected(), String.valueOf(dateNow.getValue()), colIdAppr.getCellData(tvAbsence.getSelectionModel().getSelectedIndex())));
        System.out.println("Insert successful");
        showAbsences();
    }

    private void updateAb() throws SQLException, ClassNotFoundException {
        test.updateAbsence(journeeSelected() ,tfIdAppr.getText());
        System.out.println("Update successful");
        showAbsences();
    }

    private void deleteAb() throws SQLException, ClassNotFoundException {
        test.deleteAbsence(String.valueOf(colId.getCellData(tvAbsence.getSelectionModel().getSelectedIndex())));
        System.out.println("Delete successful");
        showAbsences();
    }

    public void ActionBack(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == btnback){
            App.setRoot("Login");
        }
    }
}
