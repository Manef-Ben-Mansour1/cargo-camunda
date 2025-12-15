package tn.insat.urb.tp3.helloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Random;
import java.util.logging.Logger;

/**
 * 6. AdjustRouteDelegate - Ajuster l'itinéraire en cas de fort trafic
 */
public class AdjustRouteDelegate implements JavaDelegate {

    private static final Logger LOGGER = Logger.getLogger("BusTracking");
    private static final Random random = new Random();

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String routeNumber = (String) execution.getVariable("routeNumber");
        String currentStop = (String) execution.getVariable("currentStop");
        String trafficLevel = (String) execution.getVariable("trafficLevel");

        // Proposer un itinéraire alternatif
        String[] alternativeRoutes = {
                "Via Avenue Habib Bourguiba",
                "Via Rue de la Liberté",
                "Via Boulevard du 7 Novembre",
                "Via Avenue de la République"
        };

        String alternativeRoute = alternativeRoutes[random.nextInt(alternativeRoutes.length)];
        int timeSaved = random.nextInt(8) + 5; // économie de 5-12 minutes

        execution.setVariable("alternativeRoute", alternativeRoute);
        execution.setVariable("estimatedTimeSaved", timeSaved);
        execution.setVariable("routeAdjusted", true);

        LOGGER.info("==== ROUTE ADJUSTMENT ====");
        LOGGER.info("Original Route: " + routeNumber);
        LOGGER.info("Current Location: " + currentStop);
        LOGGER.info("Traffic Level: " + trafficLevel);
        LOGGER.info("Alternative Route: " + alternativeRoute);
        LOGGER.info("Estimated Time Saved: " + timeSaved + " minutes");
        LOGGER.info("Route adjustment recommended to driver");
    }
}
