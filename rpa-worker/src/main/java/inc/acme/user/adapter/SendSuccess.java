package inc.acme.user.adapter;

import org.camunda.bpm.client.impl.EngineClient;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendSuccess implements JavaDelegate {
  @Autowired
  EngineClient engineClient;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String primateId = execution.getVariable("primate_id").toString();

    // TODO: use public API as soon as https://jira.camunda.com/browse/CAM-11909 is done
    engineClient.complete(execution.getProcessBusinessKey(), Variables.putValue("primate_id", primateId), null);

    System.out.println("VioletPyramidService done for email " + execution.getVariable("email").toString() + " returning primate_id " + primateId);
  }
}
