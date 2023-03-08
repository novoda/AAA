import { orchestrator } from "durable-functions";

/**
* This orchestrator starts with the Push notification ID as regID.
*/
export default orchestrator(function* (context) {
    const regId = context.bindings.context.input.regId
    const outputs = [];

    /**
     * We received the order.
     * The backend action is processing the order
     */
    outputs.push(yield context.df.callActivity("BackendAction", { "orderStatus": "received", "regId": regId }));

    /**
     * We are waiting for the order to be processed
     */
    outputs.push(yield context.df.callActivity("BackendAction", { "orderStatus": "processing", "regId": regId }));

    /**
     * The flow requires a user action for approval
     */
    const approved = yield context.df.waitForExternalEvent("Approval");

    
    /**
     * Finally the order has been processed
     */
    outputs.push(yield context.df.callActivity("BackendAction", { "orderStatus": "finished", "regId": regId }));

    return outputs;
});