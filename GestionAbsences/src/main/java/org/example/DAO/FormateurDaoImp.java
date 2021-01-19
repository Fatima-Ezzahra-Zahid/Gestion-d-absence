package org.example.DAO;

import org.example.Model.Absence;
import org.example.MysqlConnect.ConnectionClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FormateurDaoImp implements FormateurDao<Absence>{
    private Absence ab;
    private Statement st = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private static final String FIND_ALL_STUDENTS = "SELECT * FROM `absence`";
    private static final String FIND_A_STUDENT = "SELECT * FROM `absence` WHERE id_appr = ?";
    private static final String INSERT_AN_ABSENCE = "INSERT INTO `absence` (absences, date, justification, id_appr) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_AN_ABSENCE = "UPDATE ABSENCE SET absences = ?, date = ?, justification = ? WHERE id_appr = ?";
    private static final String DELETE_AN_ABSENCE = "DELETE FROM `absence` WHERE id_absence = ?";



    public FormateurDaoImp() {
    }


    @Override
    public void insertAbsence(Absence absence) throws SQLException, ClassNotFoundException {
        String query = INSERT_AN_ABSENCE;
        PreparedStatement st = ConnectionClass.getMyConnexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        System.out.println("creation de l'objet Statement");

        st.setString(1, absence.getAbsence());
        st.setString(2, absence.getDate());
        st.setString(3, absence.getJustification());
        st.setString(4, absence.getId_appr());
        st.executeUpdate();

        rs = st.getGeneratedKeys();
    }

    // saveAbsence(new Absence("Absence!!", "1/19/2021", "Non justifier", "EE858500"));

    @Override
    public Absence getEtudiantById(String id) throws SQLException, ClassNotFoundException {
        ab = null;
        String query = FIND_A_STUDENT;
        PreparedStatement ps = ConnectionClass.getMyConnexion().prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        if(rs.next()){
            int id_absence = rs.getInt("id_absence");
            String absence = rs.getString("absences");
            String date = rs.getString("date");
            String justification = rs.getString("justification");
            String id_appr = rs.getString("id_appr");

            ab = new Absence(id_absence, absence, date, justification, id_appr);
        }

        return ab;
    }

    @Override
    public List<Absence> getAllAbsence() throws SQLException, ClassNotFoundException {
        ArrayList<Absence> etudiants = new ArrayList<>();
        String query = FIND_ALL_STUDENTS;
        ps = ConnectionClass.getMyConnexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        rs = ps.executeQuery();

        while(rs.next()){
            int id_absence = rs.getInt(1);
            String absence = rs.getString(2);
            String date = rs.getString(3);
            String justification = rs.getString(4);
            String id_appr = rs.getString(5);

            etudiants.add(new Absence(id_absence, absence, date, justification, id_appr));
        }

        return etudiants;
    }

    @Override
    public boolean updateAbsence(Absence absence) throws SQLException, ClassNotFoundException {
        boolean rowUpdated;
        String query = UPDATE_AN_ABSENCE;
        ps = ConnectionClass.getMyConnexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,absence.getAbsence());
        ps.setString(2, absence.getDate());
        ps.setString(3, absence.getJustification());
        ps.setString(4, absence.getId_appr());

        rowUpdated = ps.executeUpdate() > 0;

        return rowUpdated;
    }

    @Override
    public boolean deleteAbsence(int id) throws SQLException, ClassNotFoundException {
        boolean rowDeleted;
        String query = DELETE_AN_ABSENCE;
        ps = ConnectionClass.getMyConnexion().prepareStatement(query);
        ps.setInt(1, id);
        rowDeleted = ps.executeUpdate() > 0;

        System.out.println("Row with the id: " + id + " was deleted");

        return rowDeleted;
    }
}
