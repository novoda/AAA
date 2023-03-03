const df = require("durable-functions");

module.exports = async function (context, req) {
    const client = df.getClient(context);
    const regId = req.params.registrationId

    const instanceId = await client.startNew("orderOrchestrator", undefined, {regId});

    context.log(`Started orchestration with ID = '${instanceId}'.`);

    return client.createCheckStatusResponse(context.bindingData.req, instanceId);
};