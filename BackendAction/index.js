const admin = require('firebase-admin');

const app = admin.initializeApp({
  // being set by GOOGLE_APPLICATION_CREDENTIALS
    credential: admin.credential.applicationDefault()
});

module.exports = async function (context) {
    const message = {
        data: {
          stage: context.bindings.name.orderStatus
        },
        token: context.bindings.name.regId
      };

    var responce = await app.messaging().send(message)
    return `got ${context.bindings.name}!`;
};
