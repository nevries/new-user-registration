const {
    Client,
    logger
} = require("camunda-external-task-client-js");

const config = {
    baseUrl: "http://localhost:8080/rest",
    use: logger,
};

const client = new Client(config);


client.start();
client.subscribe("emailService", async ({ task, taskService }) => {
    let email = task.variables.get("email");
    let subject = task.variables.get("subject");

    console.log(`Sending email to ${email} with subject: ${subject}`);
    
    await taskService.complete(task);
});