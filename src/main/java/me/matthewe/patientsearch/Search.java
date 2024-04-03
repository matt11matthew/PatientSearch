package me.matthewe.patientsearch;

import java.util.concurrent.TimeUnit;

public class Search {
    private Gender gender;
    private int age;
    private int id = -1;

    public boolean isMedicalHistory() {
        return medicalHistory;
    }

    public boolean isCardiovascularRiskFactors() {
        return cardiovascularRiskFactors;
    }

    public boolean isMedsPriorToArrival() {
        return medsPriorToArrival;
    }

    public boolean isPhysicalExamHeartRate() {
        return physicalExamHeartRate;
    }

    public boolean isPhysicalExamKillipClass() {
        return physicalExamKillipClass;
    }

    public int getId() {
        return id;
    }

    private boolean medicalHistory;
    private boolean cardiovascularRiskFactors;
    private boolean medsPriorToArrival;


    private long symptomsOnset; //Check within time range
    private boolean physicalExamHeartRate;
    private boolean physicalExamKillipClass;

    private SearchType searchType;

//    public Search(Gender gender, int age) { //REQUIRED SEARCH
//        id=-1;
//
//        this.age=age;//required
//        this.gender=gender; //required
//
//        medicalHistory=new YesNoValue(false,false);
//        cardiovascularRiskFactors=new YesNoValue(false,true);
//        medsPriorToArrival=new YesNoValue(false,true);
//        physicalExamHeartRate=new YesNoValue(false,true);
//        physicalExamKillipClass=new YesNoValue(false,true);
//        searchType=SearchType.STRICT; //DEFAULT
//        symptomsOnset=-1;
//    }

    public Search(Gender gender, int age, boolean medicalHistory, boolean cardiovascularRiskFactors, boolean medsPriorToArrival, long symptomsOnset, boolean physicalExamHeartRate, boolean physicalExamKillipClass, SearchType searchType) {
        this.gender = gender;
        this.age = age;
        this.medicalHistory = medicalHistory;
        this.cardiovascularRiskFactors = cardiovascularRiskFactors;
        this.medsPriorToArrival = medsPriorToArrival;
        this.symptomsOnset = symptomsOnset;
        this.physicalExamHeartRate = physicalExamHeartRate;
        this.physicalExamKillipClass = physicalExamKillipClass;
        this.searchType = searchType;
    }

//    public boolean isWithinOneDay() {
//        // Get current time in milliseconds
//        long currentTime = System.currentTimeMillis();
//
//        // Calculate the absolute difference in time
//        long timeDifference = Math.abs(symptomsOnset-currentTime );
//
//        // Convert the time difference to days
//        long daysDifference = TimeUnit.MILLISECONDS.toDays(timeDifference);
//
//        System.out.println(daysDifference);
//        // Check if the difference is less than or equal to 1
//        return daysDifference <= 1;
//    }

    public Search(Patient patient) {


        this.age =patient.getAge();
        this.gender=patient.getGender();
        medicalHistory=patient.isMedicalHistory();
        cardiovascularRiskFactors=patient.isCardiovascularRiskFactors();
        medsPriorToArrival=patient.isMedsPriorToArrival();
        physicalExamHeartRate=patient.getPhysicalExam().isHeartRate();

        physicalExamKillipClass=patient.getPhysicalExam().isKillipClass();

        searchType=SearchType.STRICT; //DEFAULT
        symptomsOnset=patient.getSymptomsOnset();
    }

    public boolean isStrict(){
        return searchType==SearchType.STRICT;
    }
    public Search setSearchType(SearchType searchType) {
        this.searchType = searchType;
        return this;
    }

    public SearchType getSearchType() {
        return searchType;
    }


    public Search setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Search setAge(int age) {
        this.age = age;
        return this;
    }









    public long getSymptomsOnset() {
        return symptomsOnset;
    }


    /*
        private long symptomsOnset;

        private PhysicalExam physicalExam;
         */



    public Search setId(int id) {
        this.id = id;
        return this;
    }

    public int getAge() {
        return age;
    }

//    public Search(Patient patient) {
//        this.gender = patient.getGender();
//        this.age = patient.getAge();
////        this.tempPatient = patient;
//
//    }

//    public Patient getTempPatient() {
//        return tempPatient;
//    }

    public Gender getGender() {
        return gender;
    }


}