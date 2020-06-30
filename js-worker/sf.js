const {
    Client,
    logger
} = require("camunda-external-task-client-js");

const config = {
    baseUrl: "http://localhost:8080/engine-rest",
    use: logger,
};

const client = new Client(config);


client.start();
client.subscribe("salesforceService", async ({ task, taskService }) => {
    let email = task.variables.get("email");

    console.log(`Registering ${email} in Salesforce.`);
    
    await taskService.complete(task);
});