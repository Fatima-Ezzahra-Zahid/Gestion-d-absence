package org.example.TestConsole;

import org.example.DAO.SecretaireDao;
import org.example.DAO.SecretaireDaoImp;
import org.example.Model.Secretaire;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //getAll()
        SecretaireDaoImp daoSecretaire = new SecretaireDaoImp();

        List<Secretaire> secretaires= new ArrayList<Secretaire>();
        secretaires = daoSecretaire.getAll();

        for (int i = 0; i <  secretaires.size(); i++) {
            System.out.println( secretaires.get(i).getEmail());

        }

        /*Secretaire S2;
        S2=daoSecretaire.sauveSecretaire("essafir","marwa","fa@gmail.com","ff123","secretaire","1996-11-27","264744888");
        daoSecretaire.updateSecretaire(4,"ziii","oumaima","ouma@gmail.com","ouma123","secretaire","1996-11-27","099876675");*/

        daoSecretaire.deleteById(3);

        }
}
