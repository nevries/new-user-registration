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
client.subscribe("<topic>", async ({ task, taskService }) => {
    // let myvar = task.variables.get("myvar");
    
    await taskService.complete(task);
});