package tn.insat.urb.tp3.helloworld;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class AccidentServiceDelegate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("Accident-Service");

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String incidentId = (String) execution.getVariable("incidentId");
        String location = (String) execution.getVariable("location");

        LOGGER.info("⚠️  Accident Service - Incident: " + incidentId +
                " | Location: " + location);

        execution.setVariable("incidentPublished", true);
    }
}