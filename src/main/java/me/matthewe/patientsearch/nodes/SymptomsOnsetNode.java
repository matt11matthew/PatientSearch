package me.matthewe.patientsearch.nodes;

import me.matthewe.patientsearch.Search;
import me.matthewe.patientsearch.types.Node;

/**
 * Created by Matthew E on 4/2/2024 at 12:28 PM for the project PatientSearch
 */
public class SymptomsOnsetNode  extends Node {
    @Override
    protected boolean matches(Search criteria) {
        return true;
    }
}
