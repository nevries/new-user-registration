package inc.acme.user;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.spring.boot.starter.event.ProcessApplicationStartedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ExternalTaskWorker {
  @Autowired
  RuntimeService runtimeService;

  @Autowired
  ExternalTaskClient client;

  public ExternalTaskWorker() {
    System.out.println("Create worker bean.");
  }

  @EventListener
  public void onPostDeploy(ProcessApplicationStartedEvent event) {

    System.out.println("ProcessApplicationStartedEvent triggered.");

    // subscribe to the topic
    client.subscribe("violetPyramidService")
        .handler((externalTask, externalTaskService) -> {

          String email = externalTask.getVariable("email");

          runtimeService.startProcessInstanceByMessage("newEmailForPrimate", externalTask.getId(),
              Variables.putValue("email", email));

          System.out.println("Process for e-mail " + email + " started with external task id=" + externalTask.getId());

        }).open();

    System.out.println("External task handler registered.");
  }
}
