# AAA

AAA is a Azure Durable Function example in which the state of the orchestrator is shared with an Android client through push notification. This is a companion code for the blog post:

# Prerequisites

To run this tutorial:

1. Install Visual Studio Code.
2. Install the Azure Functions VS Code extension
3. Make sure you have the latest version of the [Azure Functions Core Tools](https://learn.microsoft.com/en-us/azure/azure-functions/functions-run-local?tabs=v4%2Clinux%2Ccsharp%2Cportal%2Cbash). 
4. Install version 10.x or 12.x of Node.js installed.
5. Android Studio and toolchain
6. On Hello.index.js make sure replace service_account.json with the correct path to YOUR service account file

# For the Android App

The Android app is located in the DurableTestApp folder.
This app needs to be run in a emulator in the same machine as the Azure Functions is running because it is
pointing twards the localhost (10.0.2.2) in Android for an emulator from Android Studio (other emulators may use a diferent address to access the localhost)

1. Install Android Studio
2. Choose open File -> Open -> *Select the DurableTestApp Folder*
3. Run