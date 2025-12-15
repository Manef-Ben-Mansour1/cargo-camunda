package tn.insat.urb.tp3.helloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class AnalyzeSensorsDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String complaintId = (String) execution.getVariable("complaintId");
        String location = (String) execution.getVariable("accidentLocation");

        System.out.println("=== Analyse Capteurs ===");
        System.out.println("Réclamation: " + complaintId);
        System.out.println("Lieu: " + location);

        // Simuler analyse capteurs IoT (70% de confirmation)
        boolean sensorsConfirm = Math.random() > 0.3;

        execution.setVariable("sensorsConfirm", sensorsConfirm);

        if (sensorsConfirm) {
            System.out.println("✓ CAPTEURS CONFIRMENT → Validation automatique");
            execution.setVariable("validationSuccessful", true);
        } else {
            System.out.println("✗ CAPTEURS NE CONFIRMENT PAS → Vérification physique requise");
        }
    }
}