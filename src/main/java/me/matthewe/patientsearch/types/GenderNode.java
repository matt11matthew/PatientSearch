package me.matthewe.patientsearch.types;

import me.matthewe.patientsearch.Gender;
import me.matthewe.patientsearch.Search;

import java.util.Random;
import java.util.UUID;

public class GenderNode  extends Node<AgeNode, AgeNode>{

    private Gender gender;



    public GenderNode(Gender gender) {
        super(new AgeNode(false),new AgeNode(true));
        this.gender = gender;
    }

    @Override
    public boolean matches(Search criteria) {
        return this.gender==criteria.getGender();
    }


}
