package tn.insat.urb.tp3.helloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * 1. StartBusServiceDelegate - Démarrer le service du bus
 */
public class StartBusServiceDelegate implements JavaDelegate {

    private static final Logger LOGGER = Logger.getLogger("BusTracking");

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String busNumber = (String) execution.getVariable("busNumber");
        String routeNumber = (String) execution.getVariable("routeNumber");
        String driverName = (String) execution.getVariable("driverName");
        String departureTime = (String) execution.getVariable("departureTime");

        // Initialiser le tracking
        String trackingId = "TRACK-" + busNumber + "-" + System.currentTimeMillis();
        execution.setVariable("trackingId", trackingId);
        execution.setVariable("serviceStatus", "ACTIVE");
        execution.setVariable("currentStop", "DEPOT");
        execution.setVariable("nextStop", "Station Centre Ville");
        execution.setVariable("passengersOnBoard", 0);
        execution.setVariable("totalStops", 15);
        execution.setVariable("completedStops", 0);
        execution.setVariable("delayMinutes", 0);
        execution.setVariable("startTime", new SimpleDateFormat("HH:mm:ss").format(new Date()));

        LOGGER.info("==== BUS SERVICE STARTED ====");
        LOGGER.info("Tracking ID: " + trackingId);
        LOGGER.info("Bus Number: " + busNumber);
        LOGGER.info("Route: " + routeNumber);
        LOGGER.info("Driver: " + driverName);
        LOGGER.info("Scheduled Departure: " + departureTime);
        LOGGER.info("Status: ACTIVE");
    }
}
