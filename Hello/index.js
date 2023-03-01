module.exports = async function (context) {
    //Thread.sleep(100000)
    await sleep(10000)
    return `Hello ${context.bindings.name}!`;
};

function sleep (time) {
    return new Promise((resolve) => setTimeout(resolve, time));
  }