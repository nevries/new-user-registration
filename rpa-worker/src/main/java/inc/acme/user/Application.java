package inc.acme.user;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.impl.EngineClient;
import org.camunda.bpm.client.impl.ExternalTaskClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Bean
  ExternalTaskClient externalTaskClient() {
    ExternalTaskClient client = ExternalTaskClient.create()
        .baseUrl("http://localhost:8080/engine-rest")
        .asyncResponseTimeout(1000)
        .lockDuration(90_000)
        .build();

    return client;
  }

  // little hack until we have a solution for
  // https://github.com/camunda/camunda-external-task-client-java/issues/25
  @Bean
  EngineClient engineClient(@Autowired ExternalTaskClient externalTaskClient) {
    return ((ExternalTaskClientImpl) externalTaskClient).getTopicSubscriptionManager().getEngineClient();
  }

}