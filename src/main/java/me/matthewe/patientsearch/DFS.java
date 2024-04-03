package me.matthewe.patientsearch;

// Import other necessary packages and classes

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
        GenderNode path =null;
        if (searchCriteria.getGender()==Gender.MALE){
            path= (GenderNode) root.getLeft();
        } else {
            path= (GenderNode) root.getRight();
        }

        AgeNode ageNode =null;
        if (searchCriteria.getAge()<=65){
            ageNode=path.getLeft();
        } else {
            ageNode=path.getRight();

        }
        //START DFS


        // Start DFS from the root

        return matchedPatients;
    }

//    private void findMatchesHelper(Node currentNode, Search searchCriteria, Set<Integer> matchedPatients, boolean isLeft) {
//        if (currentNode == null) {
//            // Base case: if the node is null, return.
//            return;
//        }
//
//        // Check if current node's patient IDs match the search criteria
//        if (currentNode.isLeaf()) {
//            if (isLeft){
//            }
//
//        }
//        if (matchesCriteria(currentNode, searchCriteria)) {
//            if (currentNode.isLeaf()){
//                for (Object patientId : currentNode.getPatientIds()) {
//
////                    System.out.println(patientId);
//                    matchedPatients.add((Integer) patientId);
//                }
//
//            }
//        }
//
//        // Recursively search in the left subtree
//        findMatchesHelper(currentNode.getLeft(), searchCriteria, matchedPatients);
//        // Recursively search in the right subtree
//        findMatchesHelper(currentNode.getRight(), searchCriteria, matchedPatients);
//    }

//    private boolean matchesCriteria(Node node, Search searchCriteria, boolean direction) {
//        // Check the search criteria against the node's patientIds
//        // This pseudo-method needs to be implemented based on Search criteria
//        // For example, you may need access to a map of patientId -> Patient object
//        // to retrieve details for each patient ID and then match against the criteria.
//
//        // Below is a simplified version. You need to implement the logic based on Search criteria.
//        if (node.matches(searchCriteria))return true;
////        if (node instanceof GenderNode) {
////            GenderNode genderNode = (GenderNode) node;
////            if (genderNode.getGender()==Gender.MALE&&searchCriteria.getGender()==Gender.MALE)return true;
////            if (genderNode.getGender()==Gender.FEMALE&&searchCriteria.getGender()==Gender.FEMALE)return true;
////        }
////        if (node instanceof AgeNode) {
////            AgeNode ageNode = (AgeNode) node;
////            if (ageNode.isOver65()&&searchCriteria.getAge()>65){
////                return true;
////            }
////            if (!ageNode.isOver65()&&searchCriteria.getAge()<=65){
////                return true;
////            }
////        }
//        return false;
//    }

    // This method needs to be implemented to actually retrieve the patient details.
    private Patient getPatientById(int patientId) {
        // Retrieve the Patient object based on patient ID.
        // Placeholder for compilation.
        return patientGraph.getById(patientId);
    }
}
