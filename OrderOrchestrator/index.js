const df = require("durable-functions");
const admin = require('firebase-admin');

var serviceAccount = require("/home/charroch/aaaa-279a7-firebase-adminsdk-zef6e-6fbefd8c6b.json");

const app = admin.initializeApp({
    credential: admin.credential.cert(serviceAccount)
});

module.exports = df.orchestrator(function* (context) {
    const outputs = [];
    outputs.push(yield context.df.callActivity("Hello", "Tokyo"));
    outputs.push(yield context.df.callActivity("Hello", "Seattle"));
    outputs.push(yield context.df.callActivity("Hello", "London"));
    return outputs;
});