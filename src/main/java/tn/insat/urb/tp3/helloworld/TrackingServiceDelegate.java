package tn.insat.urb.tp3.helloworld;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class TrackingServiceDelegate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("Tracking-Service");

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String deviceId = (String) execution.getVariable("deviceId");
        String gpsData = (String) execution.getVariable("gpsData");

        LOGGER.info("📍 Tracking Service - Device: " + deviceId +
                " | GPS Data: " + gpsData);

        // Simuler l'enregistrement de la position
        execution.setVariable("trackingId", "TRACK_" + System.currentTimeMillis());
        execution.setVariable("trackingStatus", "TRACKED");
    }
}