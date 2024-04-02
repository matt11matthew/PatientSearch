package me.matthewe.patientsearch.types;

import me.matthewe.patientsearch.Search;
import me.matthewe.patientsearch.nodes.CardiovascularRiskFactorsNode;
import me.matthewe.patientsearch.nodes.SymptomsOnsetNode;

//public class  AgeNode extends Node<CardiovascularRiskFactorsNode, > {
public class  AgeNode extends Node<CardiovascularRiskFactorsNode, SymptomsOnsetNode>  {
    private boolean over65;

    public AgeNode( boolean over65) {
        this.over65=over65;
//        this.left=new CardiovascularRiskFactorsNode();
        this.left=new CardiovascularRiskFactorsNode();
        this.right=new SymptomsOnsetNode();
    }


    @Override
    protected boolean matches(Search criteria) {
        if (over65){
            return criteria.getAge()>=65;
        }
        return criteria.getAge()<65;
    }
}
