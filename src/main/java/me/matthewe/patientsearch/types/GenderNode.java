package me.matthewe.patientsearch.types;

import me.matthewe.patientsearch.Gender;
import me.matthewe.patientsearch.Search;

import java.util.Random;
import java.util.UUID;

public class GenderNode  extends Node<Gender, AgeNode>{


    public GenderNode(Gender selfType) {
        super(selfType, new AgeNode(), new AgeNode());
        this.id = new Random().nextInt()+1;
    }

    @Override
    public AgeNode search(Search search) {
        if (selfType==Gender.MALE)return left;
        if (selfType==Gender.FEMALE)return right;
        return null;
    }
}
