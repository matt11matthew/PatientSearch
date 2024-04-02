package me.matthewe.patientsearch;

import com.google.gson.annotations.Expose;
import me.matthewe.patientsearch.types.AgeNode;
import me.matthewe.patientsearch.types.GenderNode;
import me.matthewe.patientsearch.types.Node;

import javax.xml.validation.SchemaFactoryConfigurationError;
import java.util.*;

public class PatientGraph  extends Node< GenderNode,GenderNode> {


    private Map<Integer, Patient> table =new HashMap<>();
    public PatientGraph() {
        super(new GenderNode(Gender.MALE),new GenderNode(Gender.FEMALE));
        this.root=true;
    }

    public void print() {
      System.out.println(  toString("",false));

    }
    @Override
    public boolean matches(Search criteria) {

        if (criteria.gender==Gender.MALE)return left.matches(criteria);
        if (criteria.gender==Gender.FEMALE)return right.matches(criteria);
        return false;//Always allow parent node tracking
    }

    public void insert(Patient patient) {
        table.put(patient.getPatientNumber(), patient);


        addChild(patient);

    }

    public Patient getById(int patientId) {
        return table.get(patientId);
    }

    public Map<Integer, Patient> getTable() {
        return table;
    }
}
