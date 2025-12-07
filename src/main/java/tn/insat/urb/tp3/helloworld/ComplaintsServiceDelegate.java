package tn.insat.urb.tp3.helloworld;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ComplaintsServiceDelegate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("Complaints-Service");

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String complaintId = (String) execution.getVariable("complaintId");
        String userId = (String) execution.getVariable("userId");

        LOGGER.info("📝 Complaints Service - Complaint: " + complaintId +
                " | User: " + userId);

        execution.setVariable("complaintAssociated", true);
    }
}