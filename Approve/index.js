import { getClient } from "durable-functions";


export default async function (context, req) {
    const client = getClient(context);
    await client.raiseEvent(req.params.orchestrationId, "Approval", true);
    context.res = {
        body: "Ok"
    };
}