package inc.acme.user.adapter;

import inc.acme.user.mock.VioletPyramid;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartVioletPyramid implements JavaDelegate {
  @Autowired
  VioletPyramid violetPyramid;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    violetPyramid.initiateEmailRegistration(execution.getVariable("email").toString());
  }
}
