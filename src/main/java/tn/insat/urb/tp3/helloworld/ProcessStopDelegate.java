package tn.insat.urb.tp3.helloworld;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Random;
import java.util.logging.Logger;

/**
 * 7. ProcessStopDelegate - Traiter un arrêt de bus
 */
public class ProcessStopDelegate implements JavaDelegate {

    private static final Logger LOGGER = Logger.getLogger("BusTracking");
    private static final Random random = new Random();

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String busNumber = (String) execution.getVariable("busNumber");
        String currentStop = (String) execution.getVariable("currentStop");
        Integer passengersOnBoard = (Integer) execution.getVariable("passengersOnBoard");
        Integer completedStops = (Integer) execution.getVariable("completedStops");

        if (passengersOnBoard == null)
            passengersOnBoard = 0;
        if (completedStops == null)
            completedStops = 0;

        // Simuler l'embarquement/débarquement
        int passengersBoarding = random.nextInt(8) + 1;
        int passengersAlighting = Math.min(passengersOnBoard, random.nextInt(5));

        int newPassengerCount = passengersOnBoard + passengersBoarding - passengersAlighting;
        int stopDuration = 1 + (passengersBoarding + passengersAlighting) / 2; // 1-5 minutes

        execution.setVariable("passengersOnBoard", newPassengerCount);
        execution.setVariable("passengersBoarding", passengersBoarding);
        execution.setVariable("passengersAlighting", passengersAlighting);
        execution.setVariable("stopDuration", stopDuration);
        execution.setVariable("completedStops", completedStops + 1);

        // Définir le prochain arrêt
        String[] nextStops = {
                "Station Centre Ville", "Station Menzah", "Station Bardo",
                "Station Lafayette", "Station Passage", "Station République",
                "Station Tunis Marine", "Station Carthage", "DEPOT"
        };
        int nextStopIndex = Math.min(completedStops + 1, nextStops.length - 1);
        execution.setVariable("nextStop", nextStops[nextStopIndex]);
        execution.setVariable("currentStop", currentStop);

        LOGGER.info("==== STOP PROCESSING ====");
        LOGGER.info("Bus: " + busNumber);
        LOGGER.info("Stop: " + currentStop);
        LOGGER.info("Boarding: " + passengersBoarding + " passengers");
        LOGGER.info("Alighting: " + passengersAlighting + " passengers");
        LOGGER.info("On Board: " + newPassengerCount + " passengers");
        LOGGER.info("Stop Duration: " + stopDuration + " minutes");
        LOGGER.info("Next Stop: " + nextStops[nextStopIndex]);
        LOGGER.info("Completed: " + (completedStops + 1) + " stops");
    }
}
