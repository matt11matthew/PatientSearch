package me.matthewe.patientsearch;

public class Search{
        Gender gender;
        private int age;

        public Search(Gender gender, int age) {
            this.gender = gender;
            this.age = age;
        }

    public Search(Patient patient) {
        this.gender=patient.getGender();
        this.gender=patient.getGender();
    }

    public Gender getGender() {
        return gender;
    }
}