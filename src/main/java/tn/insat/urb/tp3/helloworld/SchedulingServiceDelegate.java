package tn.insat.urb.tp3.helloworld;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SchedulingServiceDelegate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("Scheduling-Service");

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String trackingId = (String) execution.getVariable("trackingId");
        String destination = (String) execution.getVariable("destination");

        LOGGER.info("📅 Scheduling Service - Tracking: " + trackingId +
                " | Destination: " + destination);

        // Calculer l'heure d'arrivée estimée
        execution.setVariable("estimatedArrival", "14:30");
        execution.setVariable("delay", "0 min");
    }
}