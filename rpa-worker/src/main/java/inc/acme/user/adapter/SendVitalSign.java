package inc.acme.user.adapter;

import org.camunda.bpm.client.impl.EngineClient;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class SendVitalSign implements JavaDelegate {
  @Autowired
  EngineClient engineClient;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    engineClient.extendLock(execution.getProcessBusinessKey(), 90_000);
  }
}
