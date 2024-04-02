package me.matthewe.patientsearch.types;

import me.matthewe.patientsearch.Patient;
import me.matthewe.patientsearch.Search;

import java.util.*;

public abstract class Node< A extends Node,B extends Node> {
    protected int id; //related to graph traversal
//    protected V selfType;

    protected boolean root = false;
    public Set<Integer> getPatientIds() {
        return patientIds;
    }

    protected A left;
    protected B right;
    public String toString(String prefix, boolean isTail) {
        StringBuilder builder = new StringBuilder();

        builder.append(prefix).append(isTail ? "└── " : "├── ");
        if (patientIds.isEmpty()) {
            builder.append(getClass().getSimpleName()+"{id=").append(id).append("}\n");
        } else {
            builder.append("Leaf{id=").append(id).append(", patientIds=").append(patientIds).append("}\n");
        }

        List<Node> children = new ArrayList<>();
        if (left != null) children.add(left);
        if (right != null) children.add(right);

        for (int i = 0; i < children.size() - 1; i++) {
            builder.append(children.get(i).toString(prefix + (isTail ? "    " : "│   "), false));
        }

        if (children.size() > 0) {
            builder.append(children.get(children.size() - 1)
                    .toString(prefix + (isTail ? "    " : "│   "), true));
        }

        return builder.toString();
    }

    public HashSet<Integer> dfs(Search criteria) {
        // Create a HashSet to store patient IDs from matching leaf nodes
        HashSet<Integer> matchedPatientIds = new HashSet<>();

        // If this is a leaf node and matches the criteria, add its patient IDs to the result set
        if ( this.matches(criteria)){

            if (isLeaf() ) {
                matchedPatientIds.addAll(this.patientIds);
            } else {
                // If not a leaf or doesn't match criteria, continue DFS traversal
                if (this.left != null) {
                    matchedPatientIds.addAll(this.left.dfs(criteria));
                }
                if (this.right != null) {
                    matchedPatientIds.addAll(this.right.dfs(criteria));
                }
            }
        }

        return matchedPatientIds;
    }
    // Perform DFS on this node and all subtrees
//    public  HashSet<Integer>  dfs(Search criteria, List<Node> results) {
//
//
//        if (isLeaf()){
//            return patientIds;
//        }
//
//        if (this.matches(criteria)) {
//            results.add(this);
//        }
//
//        if (this.left != null) {
//            this.left.dfs(criteria, results);
//        }
//
//        // Check if right child exists before recursing
//        if (this.right != null) {
//            this.right.dfs(criteria, results);
//        }
//        return null;
//
//    }

//    public List<Node> search(Search search){
//        List<Node> nodes = new ArrayList<>();
//        this.dfs(search, nodes);
//        return nodes;
//    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", left=" + left +
                ", right=" + right +
                ", patientIds=" + patientIds +
                '}';
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
    public boolean isLeaf() {
        return this.left==null&&right==null;
    }

    protected abstract boolean matches(Search criteria);


//    public abstract Node search(Search search);//FINDS CONNECTED NODES

    private HashSet<Integer> patientIds = new HashSet<>();

    public void addPatientId(int id) {
        patientIds.add(id);
    }

    public Node(A left, B right) {
        this.left = left;
        this.right = right;
        this.id = new Random().nextInt() + 1;
    }

    public boolean addChild(Patient patient) {
        // If the current node is a leaf, add the patient ID to this node
        Search search = new Search(patient);
        if (isLeaf() && matches(search)) {
            patientIds.add(patient.getPatientNumber());
            return true; // Patient added, end the recursion
        } else {
            // If it's not a leaf, try to add the patient to the left and right subtrees
            for (Node child : Arrays.asList(left, right)) {
                if (child != null && child.matches(search)) {
                    // If a child matches, try to add the patient there
                    boolean added = child.addChild(patient);
                    if (added) {
                        return true; // Patient added successfully, propagate success back up
                    }
                    // Implicit backtracking occurs here as we finish one recursive call without success
                    // and continue to the next child (if any)
                }
            }
        }
        return false; // Patient could not be added to any subtree
    }

// Ensure `matches` method in `Node` class is defined to use the `Search` object


    public Node() {
    }

    public A getLeft() {
        return left;
    }

    public B getRight() {
        return right;
    }

}
