package org.example.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.Model.AbUser;
import org.example.Model.Absence;
import org.example.MysqlConnect.ConnectionClass;

import java.sql.*;
import java.util.List;

public class FormateurDaoImp implements FormateurDao{
    private Absence ab;
    private AbUser ub;
    private Statement st = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private static final String FIND_ALL_STUDENTS = "select `nom`, `prenom`, `cin` from user,apprenant,salle where user.id_user=apprenant.id_user and apprenant.id_salle=salle.id_salle and nomDeSalle=?";
    private static final String FIND_A_STUDENT = "SELECT * FROM `absence` WHERE id_appr = ?";
    private static final String SHOW_STUDENT = "SELECT `id_absence`, `prenom`, `nom`, `dateAb`, `absences`, `cin` FROM user,apprenant,absence where user.id_user=apprenant.id_user AND absence.id_appr=apprenant.cin AND absence.dateAb=?";
    private static final String INSERT_AN_ABSENCE = "INSERT INTO `absence` (absences, dateAb, id_appr) VALUES (?, ?, ?)";
    private static final String UPDATE_AN_ABSENCE = "UPDATE `absence` SET absences=? WHERE id_appr=?";
    private static final String DELETE_AN_ABSENCE = "DELETE FROM `absence` WHERE id_absence = ? AND dateAb = CURRENT_DATE";



    public FormateurDaoImp() {
    }


    @Override
    public void insertAbsence(AbUser abuser) throws SQLException, ClassNotFoundException {
        String query = INSERT_AN_ABSENCE;
        PreparedStatement st = ConnectionClass.getMyConnexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        System.out.println("creation de l'objet Statement");

        st.setString(1, abuser.getAbsence());
        st.setString(2, abuser.getDateAb());
        st.setString(3, abuser.getIdAppr());
        st.executeUpdate();

        rs = st.getGeneratedKeys();
    }

    @Override
    public AbUser getEtudiantById(String id) throws SQLException, ClassNotFoundException {
        String query = FIND_A_STUDENT;
        PreparedStatement ps = ConnectionClass.getMyConnexion().prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        if(rs.next()){
            int id_absence = rs.getInt("id_absence");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String date = rs.getString("date");
            String id_appr = rs.getString("idAppr");

            ub = new AbUser(id_absence, nom, prenom, date ,id_appr);
        }

        return ub;
    }

//    @Override
//    public List<AbUser> getAllAbsence() throws SQLException, ClassNotFoundException {
//        return null;
//    }

    @Override
    public void updateAbsence(String absence, String id) throws SQLException, ClassNotFoundException {
        String query = UPDATE_AN_ABSENCE;
        ps = ConnectionClass.getMyConnexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, absence);
        ps.setString(2, id);
        ps.executeUpdate();

    }

    @Override
    public boolean deleteAbsence(String id) throws SQLException, ClassNotFoundException {
        boolean rowDeleted;
        String query = DELETE_AN_ABSENCE;
        ps = ConnectionClass.getMyConnexion().prepareStatement(query);
        ps.setString(1, id);
        rowDeleted = ps.executeUpdate() > 0;

        System.out.println("Row with the id: " + id + " was deleted");

        return rowDeleted;
    }

    @Override
    public ObservableList<AbUser> AfficheInfos(String dateArg) throws SQLException, ClassNotFoundException {

        ObservableList<AbUser> ApprenantsAbsentes = FXCollections.observableArrayList();

        String requete = SHOW_STUDENT;
        PreparedStatement statement = ConnectionClass.getMyConnexion().prepareStatement(requete);
        statement.setString(1, dateArg);
        ResultSet rs = statement.executeQuery();
        AbUser apprenantAbsente;

        while (rs.next()) {
            apprenantAbsente = new AbUser(rs.getInt("id_absence"), rs.getString("nom"), rs.getString("prenom"), rs.getString("dateAb"), rs.getString("absences"),rs.getString("cin"));
            ApprenantsAbsentes.add(apprenantAbsente);
        }

        return ApprenantsAbsentes;
    }

    //    public List<AbUser> getFullName() throws SQLException, ClassNotFoundException {
//        ObservableList<AbUser> userList = FXCollections.observableArrayList();
//        String query = SHOW_FULLNAME_STUDENT;
//        ps = ConnectionClass.getMyConnexion().prepareStatement(query);
//        rs = ps.executeQuery();
//
//        while(rs.next()){
//            String nom = rs.getString(1);
//            String prenom = rs.getString(2);
//
//            AbUser user = new AbUser(nom, prenom);
//            userList.add(user);
//        }
//
//        return userList;
//    }

   @Override
    public ObservableList<AbUser> getAllClass(String salle) throws SQLException, ClassNotFoundException {
        ObservableList<AbUser> etudiants = FXCollections.observableArrayList();
        String query = FIND_ALL_STUDENTS;
        ps = ConnectionClass.getMyConnexion().prepareStatement(query);
        ps.setString(1, salle);
        rs = ps.executeQuery();

        while(rs.next()){
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String idAppr = rs.getString("cin");

            AbUser etudiant = new AbUser(nom, prenom, idAppr);
            etudiants.add(etudiant);
        }

        return etudiants;
    }
}
