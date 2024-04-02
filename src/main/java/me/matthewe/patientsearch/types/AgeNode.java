package me.matthewe.patientsearch.types;

import me.matthewe.patientsearch.Search;

public class  AgeNode extends Node {
    private boolean over65;

    public AgeNode( boolean over65) {
        this.over65=over65;
        this.left=null;
        this.right=null;
    }


    @Override
    protected boolean matches(Search criteria) {
        if (over65){
            return criteria.getAge()>=65;
        }
        return criteria.getAge()<65;
    }
}
