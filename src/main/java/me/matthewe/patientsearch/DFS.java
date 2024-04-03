package me.matthewe.patientsearch;

// Import other necessary packages and classes

import me.matthewe.patientsearch.nodes.CardiovascularRiskFactorsNode;
import me.matthewe.patientsearch.nodes.MedicalHistoryNode;
import me.matthewe.patientsearch.nodes.PhysicalExamNode;
import me.matthewe.patientsearch.nodes.SymptomsOnsetNode;
import me.matthewe.patientsearch.types.AgeNode;
import me.matthewe.patientsearch.types.GenderNode;
import me.matthewe.patientsearch.types.Node;

import java.util.HashSet;
import java.util.Set;

public class DFS {

    private PatientGraph patientGraph;



    /**
     * Perform a Depth-First Search on the binary tree structure and collect matches.
     * 
     * @param root The root node of the binary tree.
     * @param searchCriteria The search criteria to match against.
     * @return A list of matched patient IDs.
     */
    public Set<Integer> findMatches(Node root, Search searchCriteria) {
        this.patientGraph = (PatientGraph) root;
        // Create a set to hold unique matched patient IDs
        Set<Integer> matchedPatients = new HashSet<>();

        //START DOWN STRICT PATHS
        GenderNode path = searchCriteria.getGender() == Gender.MALE ? (GenderNode) root.getLeft() : (GenderNode) root.getRight();
        AgeNode ageNode = searchCriteria.getAge() <= 65 ? path.getLeft() : path.getRight();

        //go LEFT IF TRUE

        if (searchCriteria.isCardiovascularRiskFactors()){
            CardiovascularRiskFactorsNode left = ageNode.getLeft();
            if (searchCriteria.isMedsPriorToArrival()) {
                matchedPatients.addAll(left.getLeft().getPatientIds());
            } else {
                matchedPatients.addAll(left.getRight().getPatientIds());
            }
        } else {
            if (searchCriteria.isMedicalHistory()){
                SymptomsOnsetNode right = ageNode.getRight();

                //GO BOTH DIRECTIONS


                //GO LEFT
                if (searchCriteria.isMedicalHistory()){ //NOT COMMON ONLY HAPPENS IF IS WITHIN ONE DAY
                    //TIME IS HANDLED IN INSERTION PROCESS
                    matchedPatients.addAll(right.getLeft().getPatientIds());
                }

                //GO RIGHT
                PhysicalExamNode right1 = right.getRight();
               if (searchCriteria.isPhysicalExamKillipClass()&&searchCriteria.isPhysicalExamKillipClass()){

               }
                if (searchCriteria.isPhysicalExamHeartRate()){
                    matchedPatients.addAll(right1.getLeft().getPatientIds());
                }
                if (searchCriteria.isPhysicalExamKillipClass()){
                    matchedPatients.addAll(right1.getRight().getPatientIds());
                }



            }
        }

        return matchedPatients;
    }



}
