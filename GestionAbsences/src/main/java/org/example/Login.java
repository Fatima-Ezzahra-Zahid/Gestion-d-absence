package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.MysqlConnect.ConnectionClass;
import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login implements Initializable {
    public TextField tfEmail;
    private Stage stage;
    public TextField tfPassword;
    public ComboBox tfRole;
    public Button btnLogin;
    public Button btnClose;
    Connection conn;
    ResultSet rs;
    public void Login(ActionEvent event)throws Exception {
        String email = tfEmail.getText();
        String pass = tfPassword.getText();
        String role = (String) tfRole.getValue();
        if (email == "" || pass == "" || role.equals(null)){
            JOptionPane.showMessageDialog(null, "UserName or Passsword Blank");
        }else{
            conn = ConnectionClass.getMyConnexion();
            try {
                String sql = "SELECT * FROM user WHERE mail=? AND password=?  AND role=?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, pass);
                preparedStatement.setString(3, role);
                rs = preparedStatement.executeQuery();
                if (rs.next()){
                    JOptionPane.showMessageDialog(null, "Login Success");
                    if (role =="Admin"){
                        App.setRoot("Admin");
                    }else if (role == "Secretaire"){
                        App.setRoot("Secretaire");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Login Failed");
                    tfEmail.setText("");
                    tfRole.setValue("");
                    tfPassword.setText("");
                }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfRole.getItems().add("Admin");
        tfRole.getItems().add("Apprenant");
        tfRole.getItems().add("Formateur");
        tfRole.getItems().add("Secretaire");
    }

    public void Close(ActionEvent event){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
