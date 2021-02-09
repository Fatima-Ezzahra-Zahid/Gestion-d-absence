package org.example.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.DAO.ApprenantDaoImp;
import org.example.DAO.SecretaireDaoImp;
import org.example.Model.ApprenantAbsente;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class ApprenantController implements Initializable {

    @FXML
    private TableView<ApprenantAbsente> TableAffiche;
    @FXML
    private TableColumn<ApprenantAbsente, String> colabsence;
    @FXML
    private TableColumn<ApprenantAbsente, String> coljust;
    @FXML
    private TableColumn<ApprenantAbsente, String> coldateAbsence;

    public Button btnBack;
    @FXML
    private Label apprenant_nom;
    @FXML
    private Label nbrAbsence;
    @FXML
    private ComboBox id_mois;

    private Preferences userPreferences = Preferences.userRoot();


    public ApprenantController() {
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {



        try {
            afficheInfos();

            //AfficheNonjustufie();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




    }
    ApprenantDaoImp apprenantDaoImp=new ApprenantDaoImp();

    public void afficheInfos()throws SQLException, ClassNotFoundException
    {

        ArrayList<String> ApparentInfo = apprenantDaoImp.AfficheInfos(userPreferences.get("email",""));
        apprenant_nom.setText(ApparentInfo.get(0)+" "+ApparentInfo.get(1));

    }

    public void afficheAbsence() throws SQLException, ClassNotFoundException {
        ArrayList<Integer> NbrJustifiee=apprenantDaoImp.NbrdAbsences(userPreferences.get("email",""),id_mois.getValue().toString());
        nbrAbsence.setText(NbrJustifiee.get(0).toString());
    }

    public ObservableList<ApprenantAbsente> getAbsence() throws SQLException, ClassNotFoundException{

        ObservableList<ApprenantAbsente> ListAbsences =apprenantDaoImp.DetailletAbsence(userPreferences.get("email",""),id_mois.getValue().toString());

        return ListAbsences;
    }


    public void DetailleAbsences() throws SQLException,ClassNotFoundException{
        if(id_mois.getValue()!=null)
        {
            ObservableList<ApprenantAbsente> list = getAbsence();
            colabsence.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("absences"));
            coljust.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("justification"));
            coldateAbsence.setCellValueFactory(new PropertyValueFactory<ApprenantAbsente, String>("dateAbsence"));
            TableAffiche.setItems(list);
        }
    }


    public void ActionBtn(ActionEvent event) throws IOException {
        if (event.getSource() == btnBack) {
            App.setRoot("Login");
        }
    }
}
