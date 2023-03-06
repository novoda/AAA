const df = require("durable-functions");

module.exports = async function (context, req) {
    const client = df.getClient(context);
    await client.raiseEvent(instanceId, "Approval", true);
    context.res = {
        body: "Ok"
    };
}