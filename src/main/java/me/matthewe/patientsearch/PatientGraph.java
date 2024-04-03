package me.matthewe.patientsearch;

import com.google.gson.annotations.Expose;
import me.matthewe.patientsearch.types.AgeNode;
import me.matthewe.patientsearch.types.GenderNode;
import me.matthewe.patientsearch.types.Node;

import java.util.*;

public class PatientGraph  extends Node< GenderNode,GenderNode> {


    private Map<Integer, Patient> table =new HashMap<>();
    public PatientGraph() {
        super(new GenderNode(Gender.MALE),new GenderNode(Gender.FEMALE));
        this.root=true;
    }

    @Override
    public boolean matches( Search search, boolean isLeft) {
        if (search.getGender()==Gender.MALE){
            return left.matches(search, isLeft);

        }
        return right.matches(search, isLeft);
    }


    public List<Patient> search(Search search) {
        if (search.getId() != -1) return Collections.singletonList(table.get(search.getId()));//O(1)

        List<Patient> resList=new ArrayList<>();

        DFS dfs=new DFS();

        Set<Integer> matches = dfs.findMatches(this, search);

        for (int id : matches) {
            resList.add(getById(id));
        }


        return resList;
    }



    public void print() {
      System.out.println(  toString("",false));

    }


    @Override
    public void insert(Patient patient) {
        table.put(patient.getPatientNumber(), patient);


        if (patient.isMale()){
            left.insert(patient);
        } else {
            //INSERT RIGHT
            right.insert(patient);

        }

    }

    public Patient getById(int patientId) {
        return table.get(patientId);
    }

    public Map<Integer, Patient> getTable() {
        return table;
    }

    public void check(int... ints) {
        for (int anInt : ints) {
            System.out.println(table.get(anInt));
        }
    }
}
