package inc.acme.user.mock;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VioletPyramid {

  RestTemplate restTemplate;

  public VioletPyramid() {
    this.restTemplate = new RestTemplate();
  }

  public void initiateEmailRegistration(String email) {
    System.out.println("Bot was started for email: " + email);
    new Thread(() -> doTheBotStuff(email)).start();
  }

  private void doTheBotStuff(String email) {
    // a bot is slow
    try {
      Thread.sleep(10_000l + (long) (Math.random() * 20_000l));
    } catch (InterruptedException e) {
    }

    // hand in the response message via REST to the embedded engine in this application
    String fooResourceUrl
        = "http://localhost:8081/engine-rest/message";

    String body = "{\"messageName\": \"VioletPyramid_Response\",\n" +
        "  \"correlationKeys\": {\n" +
        "    \"email\": {\n" +
        "      \"value\": \"" + email + "\",\n" +
        "      \"type\": \"String\"\n" +
        "    }\n" +
        "  },\n" +
        "  \"processVariables\": {\n" +
        "    \"primate_id\": {\n" +
        "      \"value\": \"" + Integer.toString((int) (Math.random() * 10000)) + "\",\n" +
        "      \"type\": \"String\"\n" +
        "    }" +
        "  }" +
        "}";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> request = new HttpEntity<>(body, headers);

    ResponseEntity<String> entity = restTemplate.postForEntity(fooResourceUrl, request, String.class);

    if (! entity.getStatusCode().is2xxSuccessful()) {
      System.err.println("Could not send message to Camunda.");
    }

    System.out.println("Bot successfully registered email: " + email);
  }

}
