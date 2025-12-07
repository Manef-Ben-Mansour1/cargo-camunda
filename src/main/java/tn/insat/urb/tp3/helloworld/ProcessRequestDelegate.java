package tn.insat.urb.tp3.helloworld;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ProcessRequestDelegate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger("Hello-Greetings");

    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Hey! " + execution.getVariable("salutation")
                + " " + execution.getVariable("nom") + "!");
    }

}