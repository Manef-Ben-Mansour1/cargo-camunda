package tn.insat.urb.tp3.helloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * 2. UpdateGPSLocationDelegate - Mettre à jour la position GPS
 */
public class UpdateGPSLocationDelegate implements JavaDelegate {

    private static final Logger LOGGER = Logger.getLogger("BusTracking");

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String trackingId = (String) execution.getVariable("trackingId");
        String busNumber = (String) execution.getVariable("busNumber");

        // Simuler des coordonnées GPS (Tunis)
        double baseLat = 36.8065;
        double baseLon = 10.1815;

        // Ajouter une variation pour simuler le mouvement
        double latitude = baseLat + (Math.random() * 0.01);
        double longitude = baseLon + (Math.random() * 0.01);
        double speed = 30 + (Math.random() * 40); // 30-70 km/h

        execution.setVariable("currentLatitude", latitude);
        execution.setVariable("currentLongitude", longitude);
        execution.setVariable("currentSpeed", speed);
        execution.setVariable("lastGPSUpdate", new SimpleDateFormat("HH:mm:ss").format(new Date()));

        LOGGER.info("---- GPS UPDATE ----");
        LOGGER.info("Tracking ID: " + trackingId);
        LOGGER.info("Bus: " + busNumber);
        LOGGER.info("Position: (" + String.format("%.4f", latitude) + ", " + String.format("%.4f", longitude) + ")");
        LOGGER.info("Speed: " + String.format("%.1f", speed) + " km/h");
        LOGGER.info("Time: " + execution.getVariable("lastGPSUpdate"));
    }
}
