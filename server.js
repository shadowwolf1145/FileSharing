// server.js
//const connect = require("ngrok").connect
const Server = require("ws").Server;
const launch = require("carlo").launch;
const resolve = require("path").resolve;

const wss = new Server({port: 8080});

let turtle;

wss.on("connection",ws => {
    turtle = ws;
    ws.on("message", message => {
        console.log(`Receive message => ${message}`);
    })
    ws.send(JSON.stringify({type:"msg",text:"Connected To Riok Industries"}));
})

var App=(async () => {
    console.log("Start");
    //const url = await connect(8080);
    //console.log(url);
    const app = await launch();
    app.on('exit', () => process.exit());
    app.serveFolder(resolve(__dirname, "frontend"));
    await app.load("index.html");

    app.exposeFunction('exec',(msg)=>{
        return new Promise(r=>{
            turtle.send(msg);
            turtle.once("message", (msg)=>{
                r(msg);
            });
        });
    });

})();
