const { Interface } = require("readline");
export enum Direction {FORWARD,UP,DOWN}
export enum Side {LEFT,RIGHT}
export class Turtle {
    ws:WebSocket;
    constructor(ws:WebSocket){
        this.ws = ws;
    }
    excec<T>(commmand:string):Promise<T>{
        return new Promise(r => {
            this.ws.send(JSON.stringify(
                {
                    type:"eval",
                    function:commmand
                }
            ))
            this.ws.once("message",(resp:string)=>{
                r(JSON.parse(resp));
            }) 
        })
    }
    async forward():Promise<Boolean>{
        return await this.excec<boolean>("turtle.forward()");
    }
    async back():Promise<Boolean>{
        return await this.excec<boolean>("turtle.back()");
    }
    async up():Promise<Boolean>{
        return await this.excec<boolean>("turtle.up()");
    }
    async down():Promise<Boolean>{
        return await this.excec<boolean>("turtle.down()");
    }
    async turnLeft():Promise<Boolean>{
        return await this.excec<boolean>("turtle.turnLeft()");
    }
    async turnRight():Promise<Boolean>{
        return await this.excec<boolean>("turtle.turnRight()");
    }
    async pHW():Promise<Boolean>{
        return await this.excec<boolean>(`print("Hello World")`);
    }
    async dig(direction:Direction, side:Side=Side.RIGHT):Promise<Boolean>{
        let functionToCall: string;
        switch(direction){
            case Direction.FORWARD:{
                functionToCall = "dig";
                break;
            }
            case Direction.UP:{
                functionToCall = "digUp";
                break;
            }
            case Direction.DOWN:{
                functionToCall = "digDown";
                break;
            }
        }
        return await this.excec<boolean>(`turtle.${functionToCall}("${side === Side.LEFT  ? "left":"right"}")`);
    }
    async place(direction:Direction):Promise<Boolean>{
        let functionToCall: string;
        switch(direction){
            case Direction.FORWARD:{
                functionToCall = "place";
                break;
            }
            case Direction.UP:{
                functionToCall = "placeUp";
                break;
            }
            case Direction.DOWN:{
                functionToCall = "placeDown";
                break;
            }
        }
        return await this.excec<boolean>(`turtle.${functionToCall}()`);
    }
}
