package me.matthewe.patientsearch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import me.matthewe.patientsearch.types.Node;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        new Main().start();
    }

    private Gson GSON;
    public Main() {
        GSON = new GsonBuilder().setPrettyPrinting().create(); //Init json

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


        patientGraph.print();

        for (int i : Arrays.asList(32, 19, 20, 12)) {
            Patient byId = patientGraph.getById(i);
            System.out.println(byId);
        }
        List<Node> search = patientGraph.search(new Search(Gender.FEMALE, 1));

        for (Node<?> node : search) {
            for (int patientId : node.getPatientIds()) {
                Patient byId = patientGraph.getById(patientId);
                System.err.println(byId.toString());
            }
        }
    }
}
