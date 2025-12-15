package tn.insat.urb.tp3.helloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

/**
 * ContinueNormalRouteDelegate - tâche neutre pour continuer l'itinéraire normal.
 */
public class ContinueNormalRouteDelegate implements JavaDelegate {

    private static final Logger LOGGER = Logger.getLogger("BusTracking");

    @Override
    public void execute(DelegateExecution execution) {
        LOGGER.info("Continuing on normal route without adjustment.");
        execution.setVariable("routeAdjusted", false);
    }
}


