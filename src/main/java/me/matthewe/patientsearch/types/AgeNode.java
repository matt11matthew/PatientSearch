package me.matthewe.patientsearch.types;

import me.matthewe.patientsearch.Patient;
import me.matthewe.patientsearch.Search;
import me.matthewe.patientsearch.nodes.CardiovascularRiskFactorsNode;
import me.matthewe.patientsearch.nodes.SymptomsOnsetNode;

//public class  AgeNode extends Node<CardiovascularRiskFactorsNode, > {
public class  AgeNode extends Node<CardiovascularRiskFactorsNode, SymptomsOnsetNode>  {
    private boolean over65;

    public boolean isOver65() {
        return over65;
    }

    public AgeNode(boolean over65) {
        this.over65=over65;
//        this.left=new CardiovascularRiskFactorsNode();
        this.left=new CardiovascularRiskFactorsNode();
        this.right=new SymptomsOnsetNode(false);
    }


    @Override
    public boolean matches( Search search, boolean isLeft) {

        if (!search.getCardiovascularRiskFactors().isUnset()){

            return true;
        }

        return true;
    }

    @Override
    public void insert(Patient patient) {
        patientIds.add(patient.getPatientNumber());
        if (patient.isCardiovascularRiskFactors()){
            left.insert(patient);
        } else {
            right.insert(patient);

        }
    }
}
