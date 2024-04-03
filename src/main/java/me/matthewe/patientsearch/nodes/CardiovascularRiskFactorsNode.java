package me.matthewe.patientsearch.nodes;

import me.matthewe.patientsearch.Patient;
import me.matthewe.patientsearch.Search;
import me.matthewe.patientsearch.YesNoValue;
import me.matthewe.patientsearch.types.Node;

public class CardiovascularRiskFactorsNode extends Node<MedsNode, MedsNode> {
    public CardiovascularRiskFactorsNode() {
        super(new MedsNode(true), new MedsNode(false));

    }
    @Override
    public boolean matches(Search search, boolean isLeft) {
        return true;
    }

    @Override
    public void insert(Patient patient) {
        if (patient.isMedsPriorToArrival()){
            left.insert(patient);
        } else {
            right.insert(patient);

        }
//        patientIds.add(patient.getPatientNumber());
    }


}
