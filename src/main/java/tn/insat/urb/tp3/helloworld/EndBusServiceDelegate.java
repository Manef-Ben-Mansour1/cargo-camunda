package tn.insat.urb.tp3.helloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * 8. EndBusServiceDelegate - Terminer le service du bus
 */
public class EndBusServiceDelegate implements JavaDelegate {

    private static final Logger LOGGER = Logger.getLogger("BusTracking");

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String busNumber = (String) execution.getVariable("busNumber");
        String trackingId = (String) execution.getVariable("trackingId");
        String startTime = (String) execution.getVariable("startTime");
        Integer totalDelay = (Integer) execution.getVariable("delayMinutes");
        Integer completedStops = (Integer) execution.getVariable("completedStops");
        Integer totalStops = (Integer) execution.getVariable("totalStops");

        if (totalDelay == null)
            totalDelay = 0;
        if (completedStops == null)
            completedStops = 0;
        if (totalStops == null)
            totalStops = 0;

        execution.setVariable("serviceStatus", "COMPLETED");
        execution.setVariable("endTime", new SimpleDateFormat("HH:mm:ss").format(new Date()));

        LOGGER.info("==== BUS SERVICE COMPLETED ====");
        LOGGER.info("Bus Number: " + busNumber);
        LOGGER.info("Tracking ID: " + trackingId);
        LOGGER.info("Start Time: " + startTime);
        LOGGER.info("End Time: " + execution.getVariable("endTime"));
        LOGGER.info("Total Delay: " + totalDelay + " minutes");
        LOGGER.info("Stops Completed: " + completedStops + "/" + totalStops);
        LOGGER.info("Service Status: COMPLETED");
        LOGGER.info("================================");
    }
}
