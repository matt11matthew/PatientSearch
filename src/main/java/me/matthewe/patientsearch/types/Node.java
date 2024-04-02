package me.matthewe.patientsearch.types;

import me.matthewe.patientsearch.Patient;
import me.matthewe.patientsearch.Search;

import java.util.*;

public abstract class Node< T extends Node> {
    protected int id; //related to graph traversal
//    protected V selfType;

    protected boolean root = false;
    public Set<Integer> getPatientIds() {
        return patientIds;
    }

    protected T left;
    protected T right;
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

    // Perform DFS on this node and all subtrees
    public void dfs(Search criteria, List<Node> results) {

//        if (isLeaf()){
//            for (Integer patientId : patientIds) {
//                System.out.println(patientId);
//            }
//        }

        if (!root&&this.matches(criteria)) {
            results.add(this);
        }

        // Check if left child exists before recursing
        if (this.left != null) {
            this.left.dfs(criteria, results);
        }

        // Check if right child exists before recursing
        if (this.right != null) {
            this.right.dfs(criteria, results);
        }

    }

    public List<Node> search(Search search){
        List<Node> nodes = new ArrayList<>();
        this.dfs(search, nodes);
        return nodes;
    }

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

    public Node(T left, T right) {
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

    public T getLeft() {
        return left;
    }

    public T getRight() {
        return right;
    }

}
