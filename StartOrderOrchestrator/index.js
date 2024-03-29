﻿import { getClient } from "durable-functions";

/**
 * Start the orchestration with the GCM Registration ID.
 */
export default async function (context, req) {
    const client = getClient(context);
    const regId = req.params.registrationId
    const instanceId = await client.startNew("orderOrchestrator", undefined, {regId});
    context.log(`Started orchestration with ID = '${instanceId}'.`);
    return client.createCheckStatusResponse(context.bindingData.req, instanceId);
};