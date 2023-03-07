const df = require("durable-functions");
const admin = require('firebase-admin');


module.exports = df.orchestrator(function* (context) {
    console.log(context.bindings.context.input.regId)
    const regId = context.bindings.context.input.regId
    const outputs = [];
    outputs.push(yield context.df.callActivity("BackendAction", {"orderStatus": "received", "regId" : regId}));
    outputs.push(yield context.df.callActivity("BackendAction", {"orderStatus": "waiting", "regId" : regId}));
    const approved = yield context.df.waitForExternalEvent("Approval");
    outputs.push(yield context.df.callActivity("BackendAction", {"orderStatus": "last", "regId" : regId}));
    return outputs;
});