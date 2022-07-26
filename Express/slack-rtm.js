/***************************************
 * TMT Main Class
 *
 * @author Jhvictor4
 ***************************************/
"use strict"

const SlackClient = require("slack-client")
const RtmClient = SlackClient.RtmClient

/**
 * Configuration
 */

const token = "SECRET CODE"
const rtm = new RtmClient(token)

rtm.start({
    logLevel: "error"
});

rtm.on("message", (message) => {
    // TODO Handle Message As-You-Wish
    console.log(message.channel)
    console.log(message.text)
});
