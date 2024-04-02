package me.matthewe.patientsearch.nodes;

import me.matthewe.patientsearch.Search;
import me.matthewe.patientsearch.types.Node;

public class CardiovascularRiskFactorsNode extends Node<MedsNode, BooleanNode> {
    public CardiovascularRiskFactorsNode(MedsNode left, BooleanNode right) {
        super(left, new BooleanNode() {
            @Override
            protected boolean matches(Search criteria) {
                return false;
            }
        });
    }

    @Override
    protected boolean matches(Search criteria) {
        return false;
    }
}
