package org.example.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.DAO.SecretaireDaoImp;
import org.example.Model.ApprenantAbsente;

import javax.swing.*;


public class SecretaireController implements Initializable {


    @FXML
    private TableView<ApprenantAbsente> TableAffiche;
    @FXML
    private TableColumn<ApprenantAbsente, String> colcin;
    @FXML
    private TableColumn<ApprenantAbsente, String> colNom;
    @FXML
    private TableColumn<ApprenantAbsente, String> colPrenom;
    @FXML
    private TableColumn<ApprenantAbsente, String>  colNomspe;
    @FXML
    private TableColumn<ApprenantAbsente, String> colabsence;
    @FXML
    private TableColumn<ApprenantAbsente, String> coljust;
    @FXML
    private TableColumn<ApprenantAbsente, String> coldateAbsence;


    @FXML
    private TextField txt_cin;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_prenom;

    @FXML
    private TextField txt_nomspe;

    @FXML
    private TextField txt_absence;

    @FXML
    private ComboBox combo_jist;

    @FXML
    private ComboBox<String> combo_spe;
    @FXML
    private TextField txt_dateabsence;

    public SecretaireController() {
    }

    @FXML

    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            showApprenantsAbsences();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    SecretaireDaoImp secretaireDaoImp=new SecretaireDaoImp();

    @FXML
    private void updateAb() throws SQLException, ClassNotFoundException {
        secretaireDaoImp.UpdateJustification(combo_jist.getValue().toString(),txt_cin.getText());
        JOptionPane.showMessageDialog(null, "la justification bien modifier");
        showApprenantsAbsences();
    }


    public ObservableList<ApprenantAbsente> getAllApprenantsAbsence() throws SQLException, ClassNotFoundException{
        SecretaireDaoImp secretaire = new SecretaireDaoImp();

        ObservableList<ApprenantAbsente> apprenantList = secretaire.AfficheApprantAbsence(combo_spe.getValue());

        return apprenantList;
    }

    public void showApprenantsAbsences() throws SQLException, ClassNotFoundException{
        if(combo_spe.getValue() != null) {
        ObservableList<ApprenantAbsente> list = getAllApprenantsAbsence();

        colcin.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("cin"));
        colNom.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("prenom"));
        colNomspe.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("nom_sp"));
        colabsence.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente ,String>("absences"));
        coljust.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("justification"));
        coldateAbsence.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("dateAbsence"));

        TableAffiche.setItems(list);
        }
    }

    int index = -1;
    @FXML
    void getSelected (MouseEvent event){
        index = TableAffiche.getSelectionModel().getSelectedIndex();

        if (index <= -1){

            return;
        }
        txt_cin.setText(colcin.getCellData(index).toString());
        txt_nom.setText(colNom.getCellData(index).toString());
        txt_prenom.setText(colPrenom.getCellData(index).toString());
        txt_nomspe.setText(colNomspe.getCellData(index).toString());
        txt_absence.setText(colabsence.getCellData(index).toString());
        txt_dateabsence.setText(coldateAbsence.getCellData(index).toString());


    }





}
