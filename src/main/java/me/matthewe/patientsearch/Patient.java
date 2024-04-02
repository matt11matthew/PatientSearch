package me.matthewe.patientsearch;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.time.Instant;
import java.time.ZonedDateTime;

public class Patient {
    private Gender gender;
    private int age;
    private boolean medicalHistory;
    private long symptomsOnset;
    private int patientNumber;
    private boolean cardiovascularRiskFactors;
    private boolean medsPriorToArrival;

    private PhysicalExam physicalExam;

    @Override
    public String toString() {
        return "Patient{" +
                "gender=" + gender +
                ", age=" + age +
                ", medicalHistory=" + medicalHistory +
                ", symptomsOnset=" + symptomsOnset +
                ", patientNumber=" + patientNumber +
                ", cardiovascularRiskFactors=" + cardiovascularRiskFactors +
                ", medsPriorToArrival=" + medsPriorToArrival +
                ", physicalExam=" + physicalExam +
                '}';
    }

    public Patient(Gender gender, int age, boolean medicalHistory, long symptomsOnset, int patientNumber, boolean cardiovascularRiskFactors, boolean medsPriorToArrival, PhysicalExam physicalExam) {
        this.gender = gender;
        this.age = age;
        this.medicalHistory = medicalHistory;
        this.symptomsOnset = symptomsOnset;
        this.patientNumber = patientNumber;
        this.cardiovascularRiskFactors = cardiovascularRiskFactors;
        this.medsPriorToArrival = medsPriorToArrival;
        this.physicalExam = physicalExam;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public boolean isMedicalHistory() {
        return medicalHistory;
    }

    public long getSymptomsOnset() {
        return symptomsOnset;
    }

    public int getPatientNumber() {
        return patientNumber;
    }

    public boolean isCardiovascularRiskFactors() {
        return cardiovascularRiskFactors;
    }

    public boolean isMedsPriorToArrival() {
        return medsPriorToArrival;
    }

    public PhysicalExam getPhysicalExam() {
        return physicalExam;
    }

    private static boolean getBooleanFromString(String input) {
        return input.equalsIgnoreCase("yes");
    }
    public static Patient fromJson(JsonElement element) {
        JsonObject obj = element.getAsJsonObject();
        Gender gender = Gender.valueOf(obj.get("gender").getAsString().toUpperCase());
        int age = obj.get("age").getAsInt();
        boolean medicalHistory = getBooleanFromString(obj.get("medicalHistory").getAsString());

        Instant instant = Instant.parse(obj.get("symptomsOnset").getAsString());
        long symptomsOnset = instant.toEpochMilli();;

        int patientNumber = obj.get("patientNumber").getAsInt();
        boolean cardiovascularRiskFactors = getBooleanFromString(obj.get("cardiovascularRiskFactors").getAsString());
        boolean medsPriorToArrival = getBooleanFromString(obj.get("medsPriorToArrival").getAsString());

        JsonObject physicalExamObj = obj.get("physicalExam").getAsJsonObject();
        boolean heartRate =getBooleanFromString( physicalExamObj.get("heartRate").getAsString());
        boolean killipClass =getBooleanFromString( physicalExamObj.get("killipClass").getAsString());

        PhysicalExam physicalExam =new PhysicalExam(heartRate, killipClass);



        return new Patient(gender, age, medicalHistory, symptomsOnset,patientNumber,cardiovascularRiskFactors,medsPriorToArrival,physicalExam);
    }

    public boolean isMale() {
        return gender==Gender.MALE;
    }
    public boolean isFemale(){
        return !isMale();
    }

    public  static class PhysicalExam{
        private boolean heartRate;
        private boolean killipClass;

        @Override
        public String toString() {
            return "PhysicalExam{" +
                    "heartRate=" + heartRate +
                    ", killipClass=" + killipClass +
                    '}';
        }

        public PhysicalExam(boolean heartRate, boolean killipClass) {
            this.heartRate = heartRate;
            this.killipClass = killipClass;
        }

        public boolean isHeartRate() {
            return heartRate;
        }

        public boolean isKillipClass() {
            return killipClass;
        }
    }

}

//  {
//    "gender": "Female",
//    "age": 58,
//    "medicalHistory": "yes",
//    "symptomsOnset": "2024-01-25T09:08:59.194Z",
//    "patientNumber": 1,
//    "cardiovascularRiskFactors": "no",
//    "medsPriorToArrival": "yes",
//    "physicalExam": {
//      "heartRate": "no",
//      "killipClass": "yes"
//    }
//  },