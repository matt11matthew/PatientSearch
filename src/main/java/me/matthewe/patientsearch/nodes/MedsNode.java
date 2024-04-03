package me.matthewe.patientsearch.nodes;

import me.matthewe.patientsearch.Patient;
import me.matthewe.patientsearch.Search;
import me.matthewe.patientsearch.types.Node;

/**
 * Created by Matthew E on 4/2/2024 at 12:07 PM for the project PatientSearch
 */
public class MedsNode  extends Node<BooleanNode, BooleanNode> {
    private boolean isLeft;

    public MedsNode(boolean left) {

        isLeft=left;
        this.left=null;
        this.right=null;
    }

    @Override
    public boolean matches( Search search, boolean isLeft) {
        return true;
    }

    @Override
    public void insert(Patient patient) {

        patientIds.add(patient.getPatientNumber());
    }
}
