package tn.insat.urb.tp3.helloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SaveComplaintDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String complaintId = (String) execution.getVariable("complaintId");

        // Simuler échec aléatoire (10%)
        boolean saveFailed = Math.random() < 0.1;

        if (saveFailed) {
            System.out.println("❌ Échec sauvegarde: " + complaintId);
            execution.setVariable("saveFailed", true);
        } else {
            System.out.println("✓ Sauvegarde réussie: " + complaintId);
            execution.setVariable("saveFailed", false);
        }
    }
}