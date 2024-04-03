package me.matthewe.patientsearch;

import java.util.concurrent.TimeUnit;

public class Search {
    private int id ;
    private Gender gender;
    private int age;

    private YesNoValue medicalHistory;
    private YesNoValue cardiovascularRiskFactors;
    private YesNoValue medsPriorToArrival;


    private long symptomsOnset; //Check within time range
    private YesNoValue physicalExamHeartRate;
    private YesNoValue physicalExamKillipClass;

    private SearchType searchType;

    public Search(Gender gender, int age) { //REQUIRED SEARCH
        id=-1;

        this.age=age;//required
        this.gender=gender; //required

        medicalHistory=new YesNoValue(false,false);
        cardiovascularRiskFactors=new YesNoValue(false,true);
        medsPriorToArrival=new YesNoValue(false,true);
        physicalExamHeartRate=new YesNoValue(false,true);
        physicalExamKillipClass=new YesNoValue(false,true);
        searchType=SearchType.STRICT; //DEFAULT
        symptomsOnset=-1;
    }

    public boolean isWithinOneDay(long patientSymptomsOnset) {
        // Get current time in milliseconds
        long currentTime = System.currentTimeMillis();

        // Calculate the absolute difference in time
        long timeDifference = Math.abs(currentTime - symptomsOnset);

        // Convert the time difference to days
        long daysDifference = TimeUnit.MILLISECONDS.toDays(timeDifference);

        // Check if the difference is less than or equal to 1
        return daysDifference <= 1;
    }

    public Search(Patient patient) {
        id=patient.getPatientNumber();


        this.age =patient.getAge();
        this.gender=patient.getGender();
        medicalHistory=new YesNoValue(patient.isMedicalHistory(),false);
        cardiovascularRiskFactors=new YesNoValue(patient.isCardiovascularRiskFactors(),false);
        medsPriorToArrival=new YesNoValue(patient.isMedsPriorToArrival(),false);
        physicalExamHeartRate=new YesNoValue(patient.getPhysicalExam().isHeartRate(), false);
        physicalExamKillipClass=new YesNoValue(patient.getPhysicalExam().isKillipClass(), false);
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

    public int getId() {
        return id;
    }

    public Search setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Search setAge(int age) {
        this.age = age;
        return this;
    }

    public Search setMedicalHistory(YesNoValue medicalHistory) {
        this.medicalHistory = medicalHistory;
        return this;
    }

    public Search setCardiovascularRiskFactors(boolean value) {
        this.cardiovascularRiskFactors.setValue(value);
        return this;
    }

    public Search setMedsPriorToArrival(boolean value) {
        this.medsPriorToArrival.setValue(value);
        return this;
    }

    public Search setSymptomsOnset(long symptomsOnset) {
        this.symptomsOnset = symptomsOnset;
        return this;
    }

    public Search setPhysicalExamHeartRate(boolean value) {
        this.physicalExamHeartRate.setValue(value);
        return this;
    }

    public Search setPhysicalExamKillipClass(boolean value) {
        this.physicalExamKillipClass.setValue(value);
        return this;
    }



    public YesNoValue getMedicalHistory() {
        return medicalHistory;
    }

    public YesNoValue getCardiovascularRiskFactors() {
        return cardiovascularRiskFactors;
    }

    public YesNoValue getMedsPriorToArrival() {
        return medsPriorToArrival;
    }

    public long getSymptomsOnset() {
        return symptomsOnset;
    }

    public YesNoValue getPhysicalExamHeartRate() {
        return physicalExamHeartRate;
    }

    public YesNoValue getPhysicalExamKillipClass() {
        return physicalExamKillipClass;
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