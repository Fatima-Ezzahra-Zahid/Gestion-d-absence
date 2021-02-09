package org.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.example.DAO.ApprenantDao;
import org.example.DAO.ApprenantDaoImp;
import org.example.Model.Apprenant;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ApprenantController implements Initializable {

    @FXML
    private Label apprenant_nom;
    @FXML
    private Label abs_no_justif;
    @FXML
    private  Label abs_justif;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ApprenantDao apprenantDao = new ApprenantDaoImp();
        try {
            Apprenant apprenant = apprenantDao.selectApprenant("mail");
            apprenant_nom.setText(apprenant.getNom()+" "+apprenant.getPrenom());
            Apprenant apprJust = apprenantDao.selectJustifie("mail");
            abs_justif.setText(String.valueOf(apprJust.getCountJustifie()));
            Apprenant apprNonJust = apprenantDao.selectNonJustifie("mail");
            abs_no_justif.setText(String.valueOf(apprNonJust.getCountNonJustifie()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }
}
