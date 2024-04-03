package me.matthewe.patientsearch.nodes;

import me.matthewe.patientsearch.Patient;
import me.matthewe.patientsearch.Search;
import me.matthewe.patientsearch.types.Node;

public class MedicalHistoryNode  extends Node {
    public MedicalHistoryNode() {

    }
    @Override
    public boolean matches( Search search, boolean isLeft) {
        return true;
    }

    @Override
    public void insert(Patient patient) {
        patientIds.add(patient);
    }
}
