package tn.insat.urb.tp3.helloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.util.Date;

public class LogErrorDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String complaintId = (String) execution.getVariable("complaintId");
        System.out.println("📋 Log erreur: " + complaintId + " - " + new Date());
    }
}