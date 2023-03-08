import admin from 'firebase-admin';

const app = admin.initializeApp({
  // being set by GOOGLE_APPLICATION_CREDENTIALS
  credential: admin.credential.applicationDefault()
});

/**
 * This represent some backend logic. For each task, push an update to the client. 
 */
export default async function (context) {
  const message = {
    data: {
      stage: context.bindings.name.orderStatus
    },
    token: context.bindings.name.regId
  };
  var responce = await app.messaging().send(message)
  return `Push ID: ${responce}!`;
};
