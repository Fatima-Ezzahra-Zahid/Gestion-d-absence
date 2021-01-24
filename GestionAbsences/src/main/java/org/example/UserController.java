package org.example;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.DAO.UserDaoImp;
import org.example.Model.Promo;
import org.example.Model.Salle;
import org.example.Model.Specialite;
import org.example.Model.Users;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    public TextField tfNom;
    public TextField tfPrenom;
    public TextField tfPassword;
    public TextField tfEmail;
    public TextField tfTelephone;
    public DatePicker tfDateNaissence;
    public ComboBox tfRole;
    public TableColumn<Users, Integer> tvId;
    public TableColumn<Users, String> tvNom;
    public TableColumn<Users, String> tvPrenom;
    public TableColumn<Users, String> tvEmail;
    public TableColumn<Users, String> tvPassword;
    public TableColumn<Users, String> tvTelephone;
    public TableColumn<Users, String> tvDateNaissence;
    public TableColumn<Users, String> tvRole;
    public Button btnAjouter;
    public Button btnModifier;
    public Button btnSupprimer;
    public TableView<Users> tvAdmin;
    public TextField tfId;
    public TextField tfCin;
    public ComboBox tfSpecialite;
    public ComboBox tfSale;
    public ComboBox tfPromo;
    public Label LCin;
    public Label LSpecialite;
    public Label LSale;
    public Label LPromo;

    // list specialité
    List<Specialite> listSpecialite;
    Specialite specialite= new Specialite();
    Promo promo = new Promo();
    List<Promo> listPromo;
    List<Salle> listSalle;
    Salle salle = new Salle();
    UserDaoImp user = new UserDaoImp();

    public void handelButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        user.getAllSalles();
        if (event.getSource() == btnAjouter){
            user.creatUser(tfNom.getText(), tfPrenom.getText(), tfPassword.getText(), tfEmail.getText() ,tfTelephone.getText(), tfDateNaissence.getValue(), tfRole.getValue(), tfCin.getText(), tfSpecialite.getValue().toString(), tfSale.getValue().toString(), tfPromo.getValue().toString());
            showUser();

        }else if(event.getSource() == btnModifier){
            user.updateUser(Integer.parseInt(tfId.getText()),tfNom.getText(), tfPrenom.getText(), tfPassword.getText(), tfEmail.getText() ,tfTelephone.getText(), tfDateNaissence.getValue(), tfRole.getValue(), tfCin.getText(), tfSpecialite.getValue().toString(), tfSale.getValue().toString(), tfPromo.getValue().toString());
            showUser();
        }else if (event.getSource() == btnSupprimer){
            user.deleteUser(Integer.parseInt(tfId.getText()), tfRole.getValue());
            showUser();
            //
        }else if (event.getSource() == tfRole){
            if(tfRole.getValue()=="Apprenant"){
                tfSpecialite.setVisible(true);
                tfPromo.setVisible(true);
                tfCin.setVisible(true);
                tfSale.setVisible(true);
                LCin.setVisible(true);
                LPromo.setVisible(true);
                LSale.setVisible(true);
                LSpecialite.setVisible(true);
            }else if (tfRole.getValue()=="Formateur"){
                tfSpecialite.setVisible(true);
                tfPromo.setVisible(false);
                tfSale.setVisible(true);
                LPromo.setVisible(false);
                LSale.setVisible(true);
                LSpecialite.setVisible(true);
                tfCin.setVisible(false);
                LCin.setVisible(false);
            }
            else{
                tfSpecialite.setVisible(false);
                tfPromo.setVisible(false);
                tfCin.setVisible(false);
                tfSale.setVisible(false);
                LCin.setVisible(false);
                LPromo.setVisible(false);
                LSale.setVisible(false);
                LSpecialite.setVisible(false);
            }
        }
    }

    public void showUser(){
        ObservableList<Users> list = user.getUsersList();
        tvId.setCellValueFactory(new PropertyValueFactory<Users, Integer>("id_user"));
        tvNom.setCellValueFactory(new PropertyValueFactory<Users, String>("nom"));
        tvPrenom.setCellValueFactory(new PropertyValueFactory<Users, String>("prenom"));
        tvPassword.setCellValueFactory(new PropertyValueFactory<Users, String>("password"));
        tvEmail.setCellValueFactory(new PropertyValueFactory<Users, String>("email"));
        tvTelephone.setCellValueFactory(new PropertyValueFactory<Users, String>("tele"));
        tvDateNaissence.setCellValueFactory(new PropertyValueFactory<Users, String>("dateNaissance"));
        tvRole.setCellValueFactory(new PropertyValueFactory<Users, String>("role"));
        tvAdmin.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfRole.getItems().add("Admin");
        tfRole.getItems().add("Apprenant");
        tfRole.getItems().add("Formateur");
        tfRole.getItems().add("Secretaire");

        try {
            listSalle = user.getAllSalles();
            for (int i = 0; i<listSalle.size(); i++){
                tfSale.getItems().add(listSalle.get(i).getNomDeSalle());
            }
            listPromo = user.getAllPromos();
            for (int i = 0; i<listPromo.size(); i++){

                tfPromo.getItems().add(listPromo.get(i).getNomPromo());
            }

            listSpecialite = user.getAllSpecialites();
            for (int i = 0; i<listSpecialite.size(); i++){
                tfSpecialite.getItems().add(listSpecialite.get(i).getNom_sp());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        showUser();


    }

    public void handelMouseAction(MouseEvent event) {
        Users user = tvAdmin.getSelectionModel().getSelectedItem();
        tfId.setText(""+ user.getId_user());
        tfNom.setText(user.getNom());
        tfPrenom.setText(user.getPrenom());
        tfPassword.setText(user.getPassword());
        tfEmail.setText(user.getEmail());
        tfTelephone.setText(user.getTele());
        tfDateNaissence.setValue(LocalDate.parse(user.getDateNaissance()));
        tfRole.setValue(user.getRole());

    }
}
