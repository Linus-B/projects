/* server.js
 * Linus Bendel-Stenzel
 */
var express = require("express");
var app = express();

// File directory is public/
app.use(express.static("public"));


// Registers an endpoint named 'infoj' to call getInfoJson
app.get("/random", getRandomNumber);
// Called when a request is sent to server.ip:port/infoj
// As an example, we create an object, stringify it, and
// send it back to the caller.
function getRandomNumber( req, res) {
    let num = Math.floor(Math.random() * 100 + 1);
   
    res.send(JSON.stringify({"number": num}));
}


// Listen on port 3050
app.listen(3050);

