package org.example.test;

import org.example.DAO.FormateurDaoImp;
import org.example.Model.Absence;
import org.example.Model.Formateur;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Formateur Tijani = new Formateur(1, "Abdelatif", "Tijani", "tijaniabdel@gmal.com", "1234567", "Formateur", "1/1/1990", 1, 3, 1, 1)

        FormateurDaoImp TijaniAbsence = new FormateurDaoImp();

        //Afficher la liste des absence
        List<Absence> absenceList = TijaniAbsence.getAllAbsence();

        //Insert absence
//        TijaniAbsence.insertAbsence(new Absence("Journee 00", "2021-01-19", "None", "EE554900"));
        //Update absence
//        TijaniAbsence.updateAbsence(new Absence("Journee 00", "2021-01-19 00:00:00", "Justifier", "EE554900"));
        //Delete absence
//        TijaniAbsence.deleteAbsence(6);

        System.out.println("-----------------------------------");

        for(Absence absence : absenceList)
        {
            System.out.println(absence.getId_absence());
            System.out.println(absence.getAbsence());
            System.out.println(absence.getDate());
            System.out.println(absence.getJustification());
            System.out.println(absence.getId_appr());

        }

        System.out.println("-----------------------------------");

        //Afficher un seul etudiant
        Absence absence = TijaniAbsence.getEtudiantById("EE858533");
        System.out.println(absence.getId_absence());
        System.out.println(absence.getAbsence());
        System.out.println(absence.getJustification());
        System.out.println(absence.getDate());
        System.out.println(absence.getId_appr());

        System.out.println("-----------------------------------");


    }
}
