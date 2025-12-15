package tn.insat.urb.tp3.helloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

/**
 * 3. CheckTrafficConditionsDelegate - Vérifier les conditions de trafic
 */
public class CheckTrafficConditionsDelegate implements JavaDelegate {

    private static final Logger LOGGER = Logger.getLogger("BusTracking");
    private static final Random random = new Random();

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String routeNumber = (String) execution.getVariable("routeNumber");
        String nextStop = (String) execution.getVariable("nextStop");

        // Simuler les conditions de trafic : parfois normal, parfois dense
        boolean isHeavyTraffic = random.nextDouble() < 0.5; // 50% de chances d'être dense

        String trafficLevel;
        int baseTimeMinutes = 8;
        int additionalDelay;

        if (isHeavyTraffic) {
            trafficLevel = "HEAVY";
            additionalDelay = random.nextInt(11) + 5; // 5-15 minutes de retard
        } else {
            trafficLevel = "NORMAL";
            additionalDelay = 0; // pas de retard
        }

        int estimatedTimeToNextStop = baseTimeMinutes + additionalDelay;

        execution.setVariable("trafficLevel", trafficLevel);
        execution.setVariable("additionalDelay", additionalDelay);
        execution.setVariable("estimatedTimeToNextStop", estimatedTimeToNextStop);
        execution.setVariable("trafficCheckTime", new SimpleDateFormat("HH:mm:ss").format(new Date()));

        // Déterminer si le trafic cause un retard significatif
        boolean heavyTraffic = isHeavyTraffic;
        execution.setVariable("heavyTraffic", heavyTraffic);

        LOGGER.info("==== TRAFFIC CONDITIONS CHECK ====");
        LOGGER.info("Route: " + routeNumber);
        LOGGER.info("Next Stop: " + nextStop);
        LOGGER.info("Traffic Level: " + trafficLevel);
        LOGGER.info("Base Time: " + baseTimeMinutes + " min");
        LOGGER.info("Additional Delay: " + additionalDelay + " min");
        LOGGER.info("Estimated Time to Next Stop: " + estimatedTimeToNextStop + " min");

        if (heavyTraffic) {
            LOGGER.warning("HEAVY TRAFFIC DETECTED! Significant delays expected.");
        }
    }
}
