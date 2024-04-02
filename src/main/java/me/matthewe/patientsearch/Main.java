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

        for (JsonElement element : array) {
            Patient patient = Patient.fromJson(element);
            patientGraph.insert(patient);

        }
        long time = System.currentTimeMillis();
        patientGraph.print();


        HashSet<Integer> dfs = patientGraph.dfs(new Search(Gender.FEMALE, 40));
        System.out.println((System.currentTimeMillis())-time+"ms");
        if ((dfs == null) || dfs.isEmpty()){
            System.err.println("No results found");
            return;
        }
        for (int node : dfs) {
            Patient byId = patientGraph.getById(node);
            System.out.println(byId.toString());
        }

        String json = GSON.toJson(patientGraph);
        System.out.println(json);
    }
}
