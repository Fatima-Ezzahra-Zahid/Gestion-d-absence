package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.DAO.UserDaoImp;
import org.example.Model.User;
import org.example.MysqlConnect.ConnectionClass;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class Login  {
    public TextField tfEmail;
    public TextField tfPassword;
    public ComboBox tfRole;
    public Button btnLogin;
    public Button btnClose;
    Connection conn;
    ResultSet rs;
    User user = new User();
    UserDaoImp userDaoImp = new UserDaoImp();
    Preferences userPreference = Preferences.userRoot();
    public void Login(ActionEvent event) throws Exception {
        String email = tfEmail.getText();
        String pass = tfPassword.getText();
        if (email == "" || pass == "" ){
            JOptionPane.showMessageDialog(null, "email ou le mot de passe vide");
        }else{
            conn = ConnectionClass.getMyConnexion();
            user.setEmail(email);
            for (int b = 0; b < userDaoImp.getUsersList().size(); b++) {
                if (userDaoImp.getUsersList().get(b).getEmail().equals(email)) {
                    user.setPassword(userDaoImp.getUsersList().get(b).getPassword());
                    user.setRole(userDaoImp.getUsersList().get(b).getRole());
                    if (BCrypt.checkpw(pass, user.getPassword())){
                        String sql = "SELECT * FROM user WHERE mail=?";
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, email);
                        rs = preparedStatement.executeQuery();
                        if (rs.next()){
                            if (user.getRole().equals("Admin")){
                            }
                            else if (user.getRole().equals("Secretaire"))
                            {
                                App.setRoot("Secretaire");
                            }
                            else if (user.getRole().equals("Formateur")) {
                                App.setRoot("Formateur");
                            }
                            else if(user.getRole().equals("Apprenant")) {
                                user = new User();
                                user.setEmail(email);
                                userPreference.put("email",user.getEmail());
                                App.setRoot("Apprenant");
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "mot de passe inccorecte");
                        tfEmail.setText("");
                        tfPassword.setText("");
                    }
                    break;
                }else {
                    tfEmail.setText("");
                    tfPassword.setText("");
                }

            }

        }
    }


}
