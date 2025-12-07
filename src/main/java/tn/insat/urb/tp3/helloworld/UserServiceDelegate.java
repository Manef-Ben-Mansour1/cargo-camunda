package tn.insat.urb.tp3.helloworld;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class UserServiceDelegate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("User-Service");

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String ticketId = (String) execution.getVariable("ticketId");
        String userId = (String) execution.getVariable("userId");

        LOGGER.info("👤 User Service - Linking Ticket: " + ticketId +
                " | User: " + userId);

        execution.setVariable("ticketLinked", true);
    }
}