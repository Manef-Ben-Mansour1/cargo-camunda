package tn.insat.urb.tp3.helloworld;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class NotificationServiceDelegate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("Notification-Service");

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String userId = (String) execution.getVariable("userId");
        String message = "Votre bus arrive à " + execution.getVariable("estimatedArrival");

        LOGGER.info("🔔 Notification Service - User: " + userId +
                " | Message: " + message);

        execution.setVariable("notificationSent", true);
    }
}