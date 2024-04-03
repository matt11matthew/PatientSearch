package me.matthewe.patientsearch;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import me.matthewe.patientsearch.types.Node;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        new Main().start();
    }

    private Gson GSON;
    public Main() {
        GSON = new GsonBuilder().setPrettyPrinting().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {

                return fieldAttributes.getName().equalsIgnoreCase("table");
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        }).create(); //Init json

    }
    private PatientGraph patientGraph;
    public void start() throws FileNotFoundException {
        patientGraph =new PatientGraph();
        JsonReader jsonReader = new JsonReader(new FileReader("src/main/resources/patients.json"));
        JsonArray array = GSON.fromJson(jsonReader, JsonArray.class);

        long time = System.currentTimeMillis();
        for (JsonElement element : array) {
            Patient patient = Patient.fromJson(element);
            patientGraph.insert(patient);



        }
        patientGraph.print();


        Search search = new Search(Gender.FEMALE,70,true,true,true,System.currentTimeMillis(), true,true,SearchType.STRICT);
        for (Patient patient : patientGraph.search(search)) {
            System.out.println(patient);
        }


        System.out.println((System.currentTimeMillis()-time)+"ms"); //RUNTIME
//        String json = GSON.toJson(patientGraph);
//        System.out.println(json);


    }


}
