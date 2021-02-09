package org.example.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.App;
import org.example.DAO.SecretaireDaoImp;
import org.example.DAO.SpecialiteDaoImp;
import org.example.Model.ApprenantAbsente;
import org.example.Model.Specialite;

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

    @FXML
    private DatePicker datePick;

    @FXML
    private Button btnBack;

    List<Specialite> listSpecialite;



    public SecretaireController() {
    }

    @FXML

    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        SpecialiteDaoImp specialiteDaoImp=new SpecialiteDaoImp();
        datePick.setValue(LocalDate.now());
        try {
            listSpecialite =specialiteDaoImp.AfficheSpecialite();
            for (int i = 0; i<listSpecialite.size(); i++){
                combo_spe.getItems().add(listSpecialite.get(i).getNom_sp());

            }
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
        secretaireDaoImp.UpdateJustification(combo_jist.getValue().toString(),txt_cin.getText(),txt_dateabsence.getText());
        JOptionPane.showMessageDialog(null, "la justification bien modifier");
        showApprenantsAbsences();
    }


    public ObservableList<ApprenantAbsente> getApprenantsAbsence() throws SQLException, ClassNotFoundException{
        SecretaireDaoImp secretaire = new SecretaireDaoImp();

        ObservableList<ApprenantAbsente> apprenantList = secretaire.AfficheApprantAbsence(combo_spe.getValue(), Date.valueOf(datePick.getValue()));

        return apprenantList;
    }

    public ObservableList<ApprenantAbsente> getAllApprenantsAbsence() throws SQLException, ClassNotFoundException{
        SecretaireDaoImp secretaire = new SecretaireDaoImp();

        ObservableList<ApprenantAbsente> apprenantList = secretaire.AfficheAllpprantAbsence();

        return apprenantList;
    }

    public void showApprenantsAbsences() throws SQLException, ClassNotFoundException{
        if(combo_spe.getValue() == null ) {
            ObservableList<ApprenantAbsente> list = getAllApprenantsAbsence();

            colcin.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("cin"));
            colNom.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("nom"));
            colPrenom.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("prenom"));
            colNomspe.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("nom_sp"));
            colabsence.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("absences"));
            coljust.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("justification"));
            coldateAbsence.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("dateAbsence"));
            TableAffiche.setItems(list);
        }
        else  if(datePick.getValue().compareTo(LocalDate.now())>0)
        {
            JOptionPane.showMessageDialog(null, "vous ne pouvez pas sélectionner une date superieur a une date actuelle");
        }
        else
        {
            ObservableList<ApprenantAbsente> list = getApprenantsAbsence();

            colcin.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("cin"));
            colNom.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("nom"));
            colPrenom.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("prenom"));
            colNomspe.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("nom_sp"));
            colabsence.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("absences"));
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
        txt_cin.setText(colcin.getCellData(index));
        txt_nom.setText(colNom.getCellData(index));
        txt_prenom.setText(colPrenom.getCellData(index));
        txt_nomspe.setText(colNomspe.getCellData(index));
        txt_absence.setText(colabsence.getCellData(index));
        txt_dateabsence.setText(coldateAbsence.getCellData(index));
        combo_jist.setValue(coljust.getCellData(index));


    }

    public void ActionBtn(ActionEvent event) throws IOException {
        if (event.getSource() == btnBack){
            App.setRoot("Login");
        }
    }





}
