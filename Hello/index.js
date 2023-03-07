// replace service_account.json with the path to the file with your service_account
var serviceAccount = require("service_account.json");
const admin = require('firebase-admin');

const app = admin.initializeApp({
    credential: admin.credential.cert(serviceAccount)
});


module.exports = async function (context) {

    const message = {
        data: {
          stage: context.bindings.name.orderStatus
        },
        token: context.bindings.name.regId
      };

    var responce = await app.messaging().send(message)

    return `Hello ${context.bindings.name}!`;
};
