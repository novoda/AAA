import { getClient } from "durable-functions";


/**
 * HTTP function that approves 'something'.
 */
export default async function (context, req) {
    const client = getClient(context);
    await client.raiseEvent(req.params.orchestrationId, "Approval", true);
    context.res = {
        body: "Ok"
    };
}