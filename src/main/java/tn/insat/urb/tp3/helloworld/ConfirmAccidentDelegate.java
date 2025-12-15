package tn.insat.urb.tp3.helloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ConfirmAccidentDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String complaintId = (String) execution.getVariable("complaintId");
        String citizenName = (String) execution.getVariable("citizenName");

        System.out.println("✅ ACCIDENT CONFIRMÉ");
        System.out.println("   ID: " + complaintId);
        System.out.println("   Citoyen: " + citizenName);
    }
}