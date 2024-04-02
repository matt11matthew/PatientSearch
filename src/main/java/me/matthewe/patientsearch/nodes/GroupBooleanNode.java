package me.matthewe.patientsearch.nodes;

import me.matthewe.patientsearch.Search;
import me.matthewe.patientsearch.types.Node;

/**
 * Created by Matthew E on 4/2/2024 at 12:21 PM for the project PatientSearch
 */
public abstract class GroupBooleanNode extends Node {


    public GroupBooleanNode() {
        this.left=null;
        this.right=null;
    }
}
