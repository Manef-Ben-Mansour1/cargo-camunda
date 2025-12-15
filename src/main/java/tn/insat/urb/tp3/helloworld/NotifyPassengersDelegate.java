package tn.insat.urb.tp3.helloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * 5. NotifyPassengersDelegate - Notifier les passagers du retard
 */
public class NotifyPassengersDelegate implements JavaDelegate {

    private static final Logger LOGGER = Logger.getLogger("BusTracking");

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String busNumber = (String) execution.getVariable("busNumber");
        String routeNumber = (String) execution.getVariable("routeNumber");
        Integer delayMinutes = (Integer) execution.getVariable("delayMinutes");
        String nextStop = (String) execution.getVariable("nextStop");
        String trafficLevel = (String) execution.getVariable("trafficLevel");
        Integer estimatedTime = (Integer) execution.getVariable("estimatedTimeToNextStop");

        if (delayMinutes == null)
            delayMinutes = 0;
        if (estimatedTime == null)
            estimatedTime = 0;

        String notificationMessage = "Bus " + busNumber + " (Route " + routeNumber + ") is delayed by "
                + delayMinutes + " minutes due to " + trafficLevel + " traffic. "
                + "Estimated arrival at " + nextStop + ": " + estimatedTime + " minutes.";

        execution.setVariable("lastNotification", notificationMessage);
        execution.setVariable("notificationTime", new SimpleDateFormat("HH:mm:ss").format(new Date()));

        LOGGER.info("==== PASSENGER NOTIFICATION ====");
        LOGGER.info("Notification Sent:");
        LOGGER.info("   " + notificationMessage);
        LOGGER.info("Sent at: " + execution.getVariable("notificationTime"));
    }
}
