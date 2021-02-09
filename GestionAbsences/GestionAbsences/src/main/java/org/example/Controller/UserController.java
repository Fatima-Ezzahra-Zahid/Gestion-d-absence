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
    public TableColumn<User, Integer> tvId;
    public TableColumn<User, String> tvNom;
    public TableColumn<User, String> tvPrenom;
    public TableColumn<User, String> tvEmail;
    public TableColumn<User, String> tvTelephone;
    public TableColumn<User, String> tvDateNaissence;
    public TableColumn<User, String> tvRole;
    public Button btnAjouter;
    public Button btnModifier;
    public Button btnSupprimer;
    public TableView<User> tvAdmin;
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
    public Button btnPromo;


    // list specialité
    List<Specialite> listSpecialite;
    List<Promo> listPromo;
    List<Salle> listSalle;
    UserDaoImp user = new UserDaoImp();
    SpecialiteDaoImp spe = new SpecialiteDaoImp();
    public void handelButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        user.getAllSalles();
        if (event.getSource() == btnAjouter){
            if(!(Pattern.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+.[a-zA-Z.]{2,15}", tfEmail.getText()))){
                JOptionPane.showMessageDialog(null, "S'il vous plait entrez  email correct");
            }else if(!(Pattern.matches("[a-zA-Z0-9]{8,20}", tfPassword.getText()))){
                JOptionPane.showMessageDialog(null, "S'il vous plait entrez  mot de passe correct");
            }else if (!(Pattern.matches("\\d{10}", tfTelephone.getText()))){
                JOptionPane.showMessageDialog(null, "S'il vous plait entrez  Telephone correct");
            }else if(!(LocalDate.now().getYear() - tfDateNaissence.getValue().getYear() >= 18)){
                JOptionPane.showMessageDialog(null, "S'il vous entrez la  Date de naissence correct");
            }else if (tfRole.getValue().equals("Apprenant") && !(Pattern.matches("[A-Z]{1,2}+\\d{6,7}", tfCin.getText()))){
                JOptionPane.showMessageDialog(null, "\n" +
                        "le CIN est incorrect");
            }
            else {
                if (tfRole.getValue()== "Admin" || tfRole.getValue() == "Secretaire"){
                    tfPromo.setValue("");
                    tfSpecialite.setValue("");
                    tfSale.setValue("");
                    tfCin.setText("");
                }else if (tfRole.getValue().equals("Formateur")){
                    tfPromo.setValue("");
                    tfCin.setText("");
                }
                user.creatUser(tfNom.getText(), tfPrenom.getText(), tfPassword.getText(), tfEmail.getText(), tfTelephone.getText(), tfDateNaissence.getValue(), tfRole.getValue(), tfCin.getText(), tfSpecialite.getValue().toString(), tfSale.getValue().toString(), tfPromo.getValue().toString());
                showUser();

            }

        }else if(event.getSource() == btnModifier){
            if(!(Pattern.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+.[a-zA-Z.]{2,15}", tfEmail.getText()))){
                JOptionPane.showMessageDialog(null, "S'il vous plait entrez  email correct");
            }else if(!(Pattern.matches("[a-zA-Z0-9]{8,20}", tfPassword.getText()))){
                JOptionPane.showMessageDialog(null, "S'il vous plait entrez  mot de passe correct");
            }else if (!(Pattern.matches("\\d{10}", tfTelephone.getText()))){
                JOptionPane.showMessageDialog(null, "S'il vous plait entrez  Telephone correct");
            }else if(!(LocalDate.now().getYear() - tfDateNaissence.getValue().getYear() >= 18)){
                JOptionPane.showMessageDialog(null, "S'il vous plait entrez la Date de naissance correct");
            }else if (tfRole.getValue().equals("Apprenant") && !(Pattern.matches("[A-Z]{1,2}+\\d{6,7}", tfCin.getText()))){
                JOptionPane.showMessageDialog(null, "le CIN est incorrect");
            }else {
                if (tfRole.getValue() == "Admin" || tfRole.getValue() == "Secretaire") {
                    tfPromo.setValue("");
                    tfSpecialite.setValue("");
                    tfSale.setValue("");
                    tfCin.setText("");
                } else if (tfRole.getValue().equals("Formateur")) {
                    tfPromo.setValue("");
                    tfCin.setText("");
                }
                user.updateUser(Integer.parseInt(tfId.getText()), tfNom.getText(), tfPrenom.getText(), tfPassword.getText(), tfEmail.getText(), tfTelephone.getText(), tfDateNaissence.getValue(), tfRole.getValue(), tfCin.getText(), tfSpecialite.getValue().toString(), tfSale.getValue().toString(), tfPromo.getValue().toString());
                showUser();
            }
        }else if (event.getSource() == btnSupprimer){
            user.deleteUser(Integer.parseInt(tfId.getText()), tfRole.getValue());
            showUser();
            //
        }else if(event.getSource()== btnSpecialite){
            App.setRoot("Specialite");
        }else if (event.getSource() == btnSalle){
            App.setRoot("salle");
        }else if (event.getSource() == btnPromo){
            App.setRoot("Promo");
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
        ObservableList<User> list = user.getUsersList();
        tvNom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        tvPrenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        tvEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        tvTelephone.setCellValueFactory(new PropertyValueFactory<User, String>("tele"));
        tvDateNaissence.setCellValueFactory(new PropertyValueFactory<User, String>("dateNaissance"));
        tvRole.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        tvAdmin.setItems(list);

        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<User> filteredData = new FilteredList<>(list, b -> true);

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
        SortedList<User> sortedData = new SortedList<>(filteredData);

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

    int index = -1;
    public void handelMouseAction(MouseEvent event) {
       /* User user = tvAdmin.getSelectionModel().getSelectedItem();
        tfId.setText(""+ user.getId_user());
        tfNom.setText(user.getNom());
        tfPrenom.setText(user.getPrenom());
        tfPassword.setText(user.getPassword());
        tfEmail.setText(user.getEmail());
        tfTelephone.setText(user.getTele());
        tfDateNaissence.setValue(LocalDate.parse(user.getDateNaissance()));
        tfRole.setValue(user.getRole());*/

       index = tvAdmin.getSelectionModel().getSelectedIndex();

        if (index <= -1){

            return;
        }
        User user = tvAdmin.getSelectionModel().getSelectedItem();
        tfId.setText(""+ user.getId_user());
        tfNom.setText(tvNom.getCellData(index));
        tfPrenom.setText(tvPrenom.getCellData(index));
        tfPassword.setText(tvEmail.getCellData(index));
        tfEmail.setText(tvEmail.getCellData(index));
        tfTelephone.setText(tvTelephone.getCellData(index));
        tfDateNaissence.setValue(LocalDate.parse(tvDateNaissence.getCellData(index)));
        tfRole.setValue(tvRole.getCellData(index));





    }


    public void btnBack(ActionEvent event) throws IOException {
        if(event.getSource() == btnBack){
            App.setRoot("Login");
        }

    }
}
