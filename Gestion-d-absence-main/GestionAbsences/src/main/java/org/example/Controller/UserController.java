package org.example.Controller;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.example.App;
import org.example.DAO.SpecialiteDaoImp;
import org.example.DAO.UserDaoImp;
import org.example.Model.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    public Button btnBack;
    public Button btnSpecialite;
    public TextField filterField;
    public ImageView logoImg;
    public Button btnSalle;


    // list specialité
    List<Specialite> listSpecialite;
    List<Promo> listPromo;
    List<Salle> listSalle;
    UserDaoImp user = new UserDaoImp();
    SpecialiteDaoImp spe = new SpecialiteDaoImp();
    Salle salle = new Salle();
    Promo promos = new Promo();
    Specialite specialite = new Specialite();
    public void handelButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        user.getAllSalles();
        if (event.getSource() == btnAjouter){
            if(!(Pattern.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+.[a-zA-Z.]{2,15}", tfEmail.getText()))){
                JOptionPane.showMessageDialog(null, "Please enter a valide email");
            }else if(!(Pattern.matches("[a-zA-Z0-9]{8,20}", tfPassword.getText()))){
                JOptionPane.showMessageDialog(null, "Please enter a valide password");
            }else if (!(Pattern.matches("\\d{10}", tfTelephone.getText()))){
                JOptionPane.showMessageDialog(null, "Please enter a valide telephone");
            }else if(!(LocalDate.now().getYear() - tfDateNaissence.getValue().getYear() >= 18)){
                JOptionPane.showMessageDialog(null, "Please enter a valide Date de naissence");
            }
            else {
                if (tfRole.getValue()== "Admin" || tfRole.getValue() == "Secretaire"){
                    tfPromo.setValue("");
                    tfSpecialite.setValue("");
                    tfSale.setValue("");
                    tfCin.setText("");
                }else{
                    if (!(Pattern.matches("[A-Z]{1,2}+\\d{6,7}", tfCin.getText()))){
                        JOptionPane.showMessageDialog(null, "Please enter a cin");
                    }
                }
                //hase password

                user.creatUser(tfNom.getText(), tfPrenom.getText(), tfPassword.getText(), tfEmail.getText(), tfTelephone.getText(), tfDateNaissence.getValue(), tfRole.getValue(), tfCin.getText(), tfSpecialite.getValue().toString(), tfSale.getValue().toString(), tfPromo.getValue().toString());
                showUser();
            }

        }else if(event.getSource() == btnModifier){
            if (tfRole.getValue()== "Admin" || tfRole.getValue() == "Secretaire"){
                tfPromo.setValue("");
                tfSpecialite.setValue("");
                tfSale.setValue("");
                tfCin.setText("");
            }
            user.updateUser(Integer.parseInt(tfId.getText()),tfNom.getText(), tfPrenom.getText(), tfPassword.getText(), tfEmail.getText() ,tfTelephone.getText(), tfDateNaissence.getValue(), tfRole.getValue(), tfCin.getText(), tfSpecialite.getValue().toString(), tfSale.getValue().toString(), tfPromo.getValue().toString());
            showUser();
        }else if (event.getSource() == btnSupprimer){
            user.deleteUser(Integer.parseInt(tfId.getText()), tfRole.getValue());
            showUser();
            //
        }else if(event.getSource()== btnSpecialite){
            App.setRoot("Specialite");
        }else if (event.getSource() == btnSalle){
            App.setRoot("salle");
        }
        else if (event.getSource() == tfRole){
            if(tfRole.getValue()=="Apprenant"){
                tfSpecialite.setVisible(true);
                tfPromo.setVisible(true);
                tfCin.setVisible(true);
                tfSale.setVisible(true);
            }else if (tfRole.getValue()=="Formateur"){
                tfSpecialite.setVisible(true);
                tfPromo.setVisible(false);
                tfSale.setVisible(true);
                tfCin.setVisible(false);
            }
            else{
                tfSpecialite.setVisible(false);
                tfPromo.setVisible(false);
                tfCin.setVisible(false);
                tfSale.setVisible(false);
            }
        }
    }

    public void showUser(){
        ObservableList<Users> list = user.getUsersList();
        tvNom.setCellValueFactory(new PropertyValueFactory<Users, String>("nom"));
        tvPrenom.setCellValueFactory(new PropertyValueFactory<Users, String>("prenom"));
        tvEmail.setCellValueFactory(new PropertyValueFactory<Users, String>("email"));
        tvTelephone.setCellValueFactory(new PropertyValueFactory<Users, String>("tele"));
        tvDateNaissence.setCellValueFactory(new PropertyValueFactory<Users, String>("dateNaissance"));
        tvRole.setCellValueFactory(new PropertyValueFactory<Users, String>("role"));
        tvAdmin.setItems(list);




        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Users> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (user.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (user.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Users> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tvAdmin.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tvAdmin.setItems(sortedData);
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

            listSpecialite = spe.AfficheSpecialite();
            for (int i = 0; i<listSpecialite.size(); i++){
                tfSpecialite.getItems().add(listSpecialite.get(i).getNom_sp());

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        showUser();
    }
    public void handelMouseAction(MouseEvent event) {
        System.out.println(specialite.getNom_sp());
        System.out.println(salle.getNomDeSalle());
        System.out.println(promos.getNomPromo());
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

    public void btnBack(ActionEvent event) throws IOException {
        if(event.getSource() == btnBack){
            App.setRoot("Login");
        }

    }
}
