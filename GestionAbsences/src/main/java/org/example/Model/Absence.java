package org.example.Model;

public class Absence {
        private int id_absence;
        private String absence;
        private String date;
        private String justification;
        private String id_appr;

        public Absence() {

        }

        public Absence(String absence, String date, String justification, String id_appr) {
            this.absence = absence;
            this.date = date;
            this.justification = justification;
            this.id_appr = id_appr;
        }

        public Absence(int id_absence, String absence, String date, String justification, String id_appr) {
            this.id_absence = id_absence;
            this.absence = absence;
            this.date = date;
            this.justification = justification;
            this.id_appr = id_appr;
        }

        public Absence(String absence, String date, String justifier, int i) {
                this.absence = absence;
                this.date = date;
                this.justification = justifier;
                this.id_absence = i;
        }

    public Absence(String text, String text1, String text2, String text3, String id) {
    }


    public int getId_absence() {
            return id_absence;
        }

        public void setId_absence(int id_absence) {
            this.id_absence = id_absence;
        }

        public String getAbsence() {
            return absence;
        }

        public void setAbsence(String absence) {
            this.absence = absence;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getJustification() {
            return justification;
        }

        public void setJustification(String justification) {
            this.justification = justification;
        }

        public String getId_appr() {
            return id_appr;
        }

        public void setId_appr( String id_appr) {
            this.id_appr = id_appr;
        }
}
