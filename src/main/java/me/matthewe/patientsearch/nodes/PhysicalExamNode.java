package me.matthewe.patientsearch.nodes;

import me.matthewe.patientsearch.Patient;
import me.matthewe.patientsearch.Search;
import me.matthewe.patientsearch.types.Node;

public class PhysicalExamNode  extends Node<PhysicalExamNode.HeartRateNode, PhysicalExamNode.KillpClassNode> {

    public PhysicalExamNode() {
        left=new HeartRateNode();
        right=new KillpClassNode();
    }
    @Override
    public boolean matches( Search search, boolean isLeft) {
        return true;
    }

    @Override
    public void insert(Patient patient) {
        left.insert(patient);
        right.insert(patient);

    }
    public class  KillpClassNode extends Node {
        @Override
        public boolean matches( Search search, boolean isLeft) {
            return true;
        }

        @Override
        public void insert(Patient patient) {
            if( patient.getPhysicalExam().isKillipClass()){

                patientIds.add(patient.getPatientNumber());
            }
        }
    }
    public class  HeartRateNode extends Node {
        @Override
        public boolean matches( Search search, boolean isLeft) {
            return true;
        }

        @Override
        public void insert(Patient patient) {
            if (patient.getPhysicalExam().isHeartRate()){

                patientIds.add(patient.getPatientNumber());
            }
        }
    }
}
