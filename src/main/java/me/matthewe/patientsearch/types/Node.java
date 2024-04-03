package me.matthewe.patientsearch.types;

import me.matthewe.patientsearch.Patient;
import me.matthewe.patientsearch.Search;

import java.util.*;

public abstract class Node< A extends Node,B extends Node> {
    protected int id;

    public abstract boolean matches( Search search, boolean isLeft);
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
            builder.append(getClass().getSimpleName()+"{id=").append(id).append(", patientIds=").append(patientIds).append("}\n");
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


    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", left=" + left +
                ", right=" + right +
                ", patientIds=" + patientIds +
                '}';
    }

    public boolean isLeaf() {
        return this.left==null&&right==null;
    }

    protected HashSet<Integer> patientIds = new HashSet<>();

    public void addPatientId(int id) {
        patientIds.add(id);
    }

    public Node(A left, B right) {
        this.left = left;
        this.right = right;
        this.id = new Random().nextInt() + 1;
    }

    private static Random RAND = new Random();

    public Node() {
        id = RAND.nextInt(100000)+1;

    }

    public A getLeft() {
        return left;
    }

    public B getRight() {
        return right;
    }

    public abstract void insert(Patient patient);
}
