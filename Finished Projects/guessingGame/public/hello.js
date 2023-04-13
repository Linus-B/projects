/* hello.js
 * Created April 8th
 * Linus Bendel-Stenzel
 */
let ran = 0;
let moves = 0;

$(startup);


// startup registers the doSomething function to be called
// when the element with id=dosomething is clicked.
function startup() {
    updateInfo();
    callServer();
    console.log("Answer: " + ran);
    $("#butt").click( doSomething);
}

function doSomething(){
    if ($(this).text() == "Start Game"){
        $("#yourGuesses").text("");
        $(this).text("Submit Guess");
        $("#instructions").text("Enter a guess between 1-100, and click the submit guess button");
    } else {
        let num = parseInt($("#guessInput").val());
        guess(num);
    }
}

// Checking the guess
function guess(number){
    moves++;
    if (ran == number){
        gameWon();
    } else if (ran < number){
        $("#info").text("Your number is too big!")
        $("#yourGuesses").append("<p>" + number + ": Too big</p>")
    } else {
        $("#info").text("Your number is too small :(")
        $("#yourGuesses").append("<p>" + number + ": Too small</p>")
    }
}

// Stuff to do if you win the game
function gameWon(){
    $("#butt").text("Start Game");
    $("#info").text("You won!")
    $("#instructions").text("You won in " + moves + " moves! Press the button to play again.");
    storeInfo(moves)
    moves = 0;
    callServer();
}

function storeInfo(guesses){
    // If it's empty, add to it
    if (sessionStorage.length < 1){
        sessionStorage.setItem("gamesPlayed", 1);
        sessionStorage.setItem("average", guesses);
        sessionStorage.setItem("best", guesses);
    } else {
        // Grab the data, turn into numbers, then calculate the new average
        let gp = parseInt(sessionStorage.getItem("gamesPlayed"));
        let avg = parseFloat(sessionStorage.getItem("average"));
        let bst = parseInt(sessionStorage.getItem("best"));
        sessionStorage.setItem("gamesPlayed", gp + 1);
        let math = (avg * gp * 1.0 + guesses) / (gp + 1);
        //console.log(math);
        sessionStorage.setItem("average", math);
        if (guesses < bst){
            sessionStorage.setItem("best", guesses);
        }
    }
    updateInfo();
}

function updateInfo(){
    if (sessionStorage.length > 1){
        let gp = parseInt(sessionStorage.getItem("gamesPlayed"));
        let avg = parseFloat(sessionStorage.getItem("average"));
        let bst = parseInt(sessionStorage.getItem("best"));
        $("#GamesPlayed").text(gp);
        $("#avgGuesses").text(avg.toFixed(2));
        $("#bestGuesses").text(bst);
    }
}



function callServer() {
    let xhr = new XMLHttpRequest();
    xhr.addEventListener( "load", getRandom);
    // Port 3002 is what it's listening on, and infoj is the get
    xhr.open( "GET", "http://45.79.221.107:3050/random");
    xhr.send();   
}


// takes json with stuff. 
function getRandom() {
   let conversionObject = JSON.parse( this.response);
   ran = conversionObject["number"];
   console.log("random: " + ran);
}
