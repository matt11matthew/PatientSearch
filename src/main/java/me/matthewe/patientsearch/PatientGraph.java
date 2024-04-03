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


    private  void  strictDFS(Search criteria, List<Node> matchingNodes) {


        if (left!=null&&left.isLeaf()){
            System.out.println(left);
        }
        if (right!=null&&right.isLeaf()){
            System.out.println(right);
        }


    }

    public List<Patient> search(Search search) {
        if (search.getId() != -1) return Collections.singletonList(table.get(search.getId()));//O(1)

        List<Patient> resList=new ArrayList<>();
        List<Node> nodes =new ArrayList<>();


//        strictDfs(search,nodes);

        for (Node node : nodes) {
            for (Object patientId : node.getPatientIds()) {
                resList.add(table.get(patientId));
            }
        }
        return resList;
    }
    /*


      public List<Node> search(Search criteria) {
        List<Node> matchingNodes = new ArrayList<>();
        dfs(criteria, matchingNodes);
        return matchingNodes;
    }

    // Modified DFS method to collect matching leaf nodes
    private void dfs(Search criteria, List<Node> matchingNodes) {
        // Check if this node matches the criteria
        if (matches(criteria)) {
            // If this is a leaf node, add it to the list of matching nodes
            if (isLeaf()) {
                matchingNodes.add(this);
            }
        }

        // Continue DFS traversal if this is not a leaf node
        if (!isLeaf()) {
            if (left != null) {
                left.dfs(criteria, matchingNodes);
            }
            if (right != null) {
                right.dfs(criteria, matchingNodes);
            }
        }
    }


ANOTHER
 private void dfs(Search criteria, List<Node> matchingNodes) {
        // Check if this node matches the criteria
        if (matches(criteria)) {
            // If this is a leaf node, add it to the list of matching nodes
            if (isLeaf()) {
                matchingNodes.add(this);
            }
        }

        // Continue DFS traversal only if this is not a leaf node
        if (!isLeaf()) {
            // Check if the current node's gender and age range match the search criteria
            if (gender == criteria.getGender() && minAge <= criteria.getAge() && maxAge >= criteria.getAge()) {
                // Continue DFS traversal if the criteria match
                if (left != null) {
                    left.dfs(criteria, matchingNodes);
                }
                if (right != null) {
                    right.dfs(criteria, matchingNodes);
                }
            }
            // If the current node's gender or age range doesn't match the criteria, stop traversal for this branch
        }
    }
     */
    public void print() {
      System.out.println(  toString("",false));

    }


    public void insert(Patient patient) {
        table.put(patient.getPatientNumber(), patient);


        if (patient.isMale()){
            left.insert(patient);
        } else {
            //INSERT RIGHT
            right.insert(patient);

        }
//        addChild(patient);

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
