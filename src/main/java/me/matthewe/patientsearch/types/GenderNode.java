package me.matthewe.patientsearch.types;

import me.matthewe.patientsearch.Gender;
import me.matthewe.patientsearch.Patient;
import me.matthewe.patientsearch.Search;

import java.util.Random;
import java.util.UUID;

public class GenderNode  extends Node<AgeNode, AgeNode>{

    private Gender gender;

    public Gender getGender() {
        return gender;
    }

    public GenderNode(Gender gender) {
        super(new AgeNode(false),new AgeNode(true));
        this.gender = gender;
    }


    @Override
    public boolean matches( Search search, boolean isLeft) {
        if( search.getGender()!=gender) {

            return false;
        }
        return true;
    }

    public void insert(Patient patient) {
        patientIds.add(patient.getPatientNumber());
        if (patient.getAge()<=65){
            left.insert(patient);
        } else {
            right.insert(patient);

        }

    }
}
