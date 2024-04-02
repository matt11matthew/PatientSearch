package me.matthewe.patientsearch.types;

import me.matthewe.patientsearch.Patient;
import me.matthewe.patientsearch.Search;

import java.util.*;

public abstract class Node<V, T extends Node> {
    protected int id; //related to graph traversal
    protected V selfType;

    protected T left;
    protected T right;

    // Perform DFS on this node and all subtrees
    public void dfs(Search criteria, List<Node> results) {
        if (this.matches(criteria)) {
            results.add(this);
        }
        for (Node child : Arrays.asList(this.left, this.right)) {
            child.dfs(criteria, results);
        }
    }


//        public List<T> search(Search criteria) {
//        List<T> matches = new ArrayList<>();
//        // Check if this node matches the criteria
//        if (left.matches(criteria)) {
//            matches.add(left);
//        }
//        if (right.matches(criteria)) {
//            matches.add(right);
//        }
//        // Include logic to check for matches and add to the list
//        return matches;
//    }

    protected abstract boolean matches(Search criteria);


//    public abstract Node search(Search search);//FINDS CONNECTED NODES

    private HashSet<Long> patientIds = new HashSet<>();

    public void addPatientId(long id) {
        patientIds.add(id);
    }

    public Node(V selfType, T left, T right) {
        this.left = left;
        this.selfType=selfType;
        this.right = right;
    }
    public Node(V selfType) {
        this.selfType=selfType;

    }



    public T getLeft() {
        return left;
    }

    public T getRight() {
        return right;
    }

}
