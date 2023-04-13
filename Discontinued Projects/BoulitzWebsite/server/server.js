const accounts = [
    {
        username: "Admin",
        password: "Account123"
    },
    {
        username: "Jackson",
        password: "User123"
    }
]


const http = require('http');
const express = require('express');
const socketio = require('socket.io');

const app = express();
const clientPath = `${__dirname}/../client/`;
console.log(`Serving Static from ${clientPath}`);

app.use(express.static(clientPath));


const server = http.createServer(app);

const io = socketio(server);

io.on('connection', (sock) => {
    console.log('Someone connected');
    sock.emit("info", ['message', "Hi, you are connected"]);

    sock.on('info', (info) => {
        switch (info[0]){

            case 'message':
                io.emit('info', ['message', info[1]]);
                break;

            case 'verification':
                console.log("Information received");
                io.emit('info', ['message', `Username: ${info[1][0]} has logged in`]);
                for (i = 0; i < accounts.length; i++){
                    if (info[1][0] == accounts[i].username && info[1][1] == accounts[i].password){
                        sock.emit('info', ['verification_from_server', true]);
                    } 
                }
                break;
            default:
                console.log("We could not find that that type of info emmitted: ", info[0]);
        }
    }); 
});


server.on('error', (err) => {
    console.error('Server error:', err);
});

server.listen(8080, () => {
    console.log("Connected to 8080");
});


