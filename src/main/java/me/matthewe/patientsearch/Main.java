package me.matthewe.patientsearch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
        List<Long> search = patientGraph.search(new Search(Gender.MALE, 30));
        for (Patient patient : search) {
            System.out.println(patient);

        }
    }
}
