package me.matthewe.patientsearch;

public class Search {
    Gender gender;
    private int age;

    public Search(Gender gender, int age) {
        this.gender = gender;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public Search(Patient patient) {
        this.gender = patient.getGender();
        this.age = patient.getAge();
    }

    public Gender getGender() {
        return gender;
    }
}