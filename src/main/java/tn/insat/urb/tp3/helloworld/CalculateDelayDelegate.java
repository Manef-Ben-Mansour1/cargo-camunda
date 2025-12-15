package tn.insat.urb.tp3.helloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

/**
 * 4. CalculateDelayDelegate - Calculer le retard accumulé
 */
public class CalculateDelayDelegate implements JavaDelegate {

    private static final Logger LOGGER = Logger.getLogger("BusTracking");

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String busNumber = (String) execution.getVariable("busNumber");
        Integer currentDelay = (Integer) execution.getVariable("delayMinutes");
        Integer additionalDelay = (Integer) execution.getVariable("additionalDelay");
        Integer completedStops = (Integer) execution.getVariable("completedStops");
        Integer totalStops = (Integer) execution.getVariable("totalStops");

        if (currentDelay == null)
            currentDelay = 0;
        if (additionalDelay == null)
            additionalDelay = 0;
        if (completedStops == null)
            completedStops = 0;
        if (totalStops == null || totalStops == 0)
            totalStops = 1;

        // Calculer le nouveau retard total
        int newDelay = currentDelay + additionalDelay;
        execution.setVariable("delayMinutes", newDelay);

        // Calculer le statut du service
        String delayStatus;
        if (newDelay == 0) {
            delayStatus = "ON_TIME";
        } else if (newDelay <= 5) {
            delayStatus = "SLIGHT_DELAY";
        } else if (newDelay <= 15) {
            delayStatus = "MODERATE_DELAY";
        } else {
            delayStatus = "SEVERE_DELAY";
        }

        execution.setVariable("delayStatus", delayStatus);

        // Calculer le pourcentage de progression
        int progressPercentage = (completedStops * 100) / totalStops;
        execution.setVariable("progressPercentage", progressPercentage);

        LOGGER.info("==== DELAY CALCULATION ====");
        LOGGER.info("Bus: " + busNumber);
        LOGGER.info("Previous Delay: " + currentDelay + " min");
        LOGGER.info("Additional Delay: " + additionalDelay + " min");
        LOGGER.info("Total Delay: " + newDelay + " min");
        LOGGER.info("Status: " + delayStatus);
        LOGGER.info("Progress: " + completedStops + "/" + totalStops + " stops (" + progressPercentage + "%)");

        if ("SEVERE_DELAY".equals(delayStatus)) {
            LOGGER.warning("SEVERE DELAY! Passenger notification required.");
        }
    }
}
