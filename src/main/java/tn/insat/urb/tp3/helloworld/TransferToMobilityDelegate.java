package tn.insat.urb.tp3.helloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class TransferToMobilityDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String complaintId = (String) execution.getVariable("complaintId");
        System.out.println("📨 Transfert département Mobilité: " + complaintId);
    }
}