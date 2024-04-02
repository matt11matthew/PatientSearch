package me.matthewe.patientsearch.nodes;

import me.matthewe.patientsearch.Patient;
import me.matthewe.patientsearch.Search;
import me.matthewe.patientsearch.types.Node;

public class CardiovascularRiskFactorsNode extends Node<MedsNode, GroupBooleanNode> {
    public CardiovascularRiskFactorsNode() {
        super(new MedsNode(), new GroupBooleanNode() {
            @Override
            protected boolean matches(Search criteria) {
                return !criteria.getTempPatient().isCardiovascularRiskFactors();
            }
        });
    }

    @Override
    protected boolean matches(Search criteria) {

        Patient tempPatient = criteria.getTempPatient();
        if (tempPatient.isCardiovascularRiskFactors())return true;
//        if (tempPatient.isCardiovascularRiskFactors())
        return false;
    }
}
