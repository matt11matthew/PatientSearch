package me.matthewe.patientsearch;

import me.matthewe.patientsearch.types.AgeNode;
import me.matthewe.patientsearch.types.GenderNode;
import me.matthewe.patientsearch.types.Node;

import java.util.*;

public class PatientGraph  extends Node<HashMap<Long,Patient>, GenderNode> {


    public PatientGraph() {
        super(new HashMap<>(), new GenderNode(Gender.MALE), new GenderNode(Gender.MALE));
    }



    public void print(){
        StringBuilder out =new StringBuilder();

        out.append("      Gender\n");
        out.append("    /      \\\n");
        out.append("   /        \\\n");
        out.append("  MALE      FEMALE\n");
        System.out.println(out);
    }

    public void dfs(Search search,   Set<Integer> visited) {
        if (visited.contains(id)) {
            return;
        }
        visited.add(id);

        // Perform the search at this node and process the matches
        search(search).
    }

    @Override
    public GenderNode search(Search search) {
        if (search.getGender()==Gender.MALE)return left;
        if (search.getGender()==Gender.FEMALE)return right;

        return null;
    }

    public void insert(Patient patient) {
    }
}
