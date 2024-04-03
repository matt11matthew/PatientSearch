package me.matthewe.patientsearch.nodes;

import me.matthewe.patientsearch.Patient;
import me.matthewe.patientsearch.Search;
import me.matthewe.patientsearch.types.Node;

/**
 * Created by Matthew E on 4/2/2024 at 12:28 PM for the project PatientSearch
 */
public class SymptomsOnsetNode  extends Node<MedicalHistoryNode, PhysicalExamNode> {
    boolean isLeft;
    public SymptomsOnsetNode(boolean b) {
        this.isLeft=b;
        right=new PhysicalExamNode();
        left=new MedicalHistoryNode();
    }

    @Override
    public boolean matches( Search search, boolean isLeft) {
        return true;
    }

    @Override
    public void insert(Patient patient) {
        //NO HEART ISSUES

        if (patient.isWithinOneDayOfSymptoms(System.currentTimeMillis())){
            left.insert(patient);
        } else {
            right.insert(patient);
        }
        patientIds.add(patient.getPatientNumber());
    }
}
