// client.js

const WebSocket = require("ws")

const url = 'ws://84bd3e5c74be.ngrok.io/'
const connection = new WebSocket(url)

connection.onopen = () => {
    connection.send("Message From Client")
}

connection.onerror = (error) => {
    console.log(`WebSocket error: ${error}`);
}

connection.onmessage = (e) => {
    console.log(e.data)
}
