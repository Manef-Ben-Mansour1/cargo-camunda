package tn.insat.urb.tp3.helloworld;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class PaymentServiceDelegate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("Payment-Service");

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String ticketId = (String) execution.getVariable("ticketId");
        String amountStr = (String) execution.getVariable("amount");
        double amount = Double.parseDouble(amountStr);

        LOGGER.info("💳 Payment Service - Ticket: " + ticketId +
                " | Amount: " + amount + " TND");

        execution.setVariable("paymentStatus", "COMPLETED");
        execution.setVariable("transactionId", "TXN_" + System.currentTimeMillis());
    }
}