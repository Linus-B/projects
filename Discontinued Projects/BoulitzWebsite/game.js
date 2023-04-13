
function getRandomInteger(min, max){
    return Math.floor(Math.random() * (max - min) + min);
}
// Card Class
class Card{
    constructor (suit, rank, points){
        this.suit = suit;
        this.rank = rank; // Hierarchy - 0 to 13 are trump suit, 14-16 are fail of whatever suit. 
        this.points = points;
        this.playable = false;
    }
}

// Deck of Cards
class Deck{
    constructor(gametype){
        this.deck = Card[26];
        points = [3, 0, 3, 3, 3, 2, 2, 2, 2, 11, 10, 4, 0, 0];
        suits = ['c', 's', 'd'];
        fail_points = [11, 10, 4, 0];
        switch(gametype){
            case sheepshead:
                for (var i = 0; i < 14; i++){
                    this.deck[i] = new Card('t', i, points[i])
                }
                for (var i = 0; i < 12; i++){
                    this.deck[14 + i] = new Card(suits[i % 3], 13 + (i % 4), fail_points[i % 4]);
                }
        }
        this.deck.shuffle();
    }

    shuffle(){
        for (var i = 0; i < this.deck.length * 2; i++){
            var rint = getRandomInteger(0, this.deck.length - 1);
            var tmp = this.deck[i % this.deck.length];
            this.deck[i % this.deck.length] = this.deck[rint];
            this.deck[rint] = tmp;
        }
    }
}

// Keeps track of player info
class Player{
    constructor (hand, tricks, id){
        this.hand = hand; // Array of Cards
        this.tricks = tricks; // Array of Cards 
        this.id = id; 
        this.picking_team = false; // Defines which team you're on
        this.chopping = false;
        this.sortHand();

        // For stats
        this.score = 0;
        this.scoreInBuck = 0;
        this.scoreInDoubleBuck = 0;
        this.picks = 0;
        this.pickWins = 0;
        this.chops = 0;
        this.jacks = 0;
        this.chopWins = 0;
        this.cardNumbers = Card[16] // I'll bundle the fail together. 
        this.leasties = 0;
        this.leastiesWon = 0;
        this.tricksTaken = 0;
    }

    sortHand(){
        this.hand.sort((a, b) => b.rank - a.rank)
    }

    remove(){
        
    }

    updateStatsStart(){
        for (var i = 0; i < this.hand.length; i++){
            cardNumbers[this.hand[i]] += 1;
        }
    }

    updateStatsEnd(scoreChange, leasties, buck){
        this.score += scoreChange;
        switch(buck){
            case 1:
                break;
            case 2:
                scoreInBuck += scoreChange;
                break;
            case 4: 
                scoreInDoubleBuck += scoreChange;
                break;
        }
        if (leasties){
            this.leasties += 1;
            if (scoreChange > 0){
                this.leastiesWon += 1;
            }
        }
        this.tricksTaken += this.tricks.length;
        if (this.tricks % 4 == 2){
            this.picks += 1;
            if (scoreChange > 0){
                this.pickWins += 1;
            }
        }
        if (this.chopping){
            this.chops += 1;
            if (scoreChange > 0){
                this.chopWins += 1;
            }
        }
    }
}

class Board{
    constructor(){
        this.lead = null;
        this.winner = null; // id of player with winning card
        this.played = Card[0];
    }

    addCard(){ // Returns true if trick is complete

    }
}

class Game{
    constructor(){
        this.handsOfBuck = 0;
        this.buck = 1;
        this.players = Player[0];
        this.deck = new Deck(sheepshead);
        this.board = new Board;
        this.turn_order = [0];
        this.turn = "";
    }

    addPlayer(player){
        this.players.push(player);
        this.turn_order.push(player.id);
    }

    currentPlayer(){
        var location;
        for (var i = 0; i < this.players.length; i++){
            if (this.players[i].id == this.turn){
                location = i;
            }
        }
        return location;
    }
    incrementTurn(){
        this.turn = this.players[(currentPlayer() + 1) % this.players.length];
    }

    play(index){// Plays the card at a certain index
        this.board.addCard(this.players[this.currentPlayer()].hand[index]); // Add card to board
        

    }
}



















const EventEmitter = require('events');
const emitter = new EventEmitter();

// register a listener
emitter.on('messageLogged', e => {
    console.log('Listener called', e);
});

// Raise an event - need a listener
emitter.emit('messageLogged', { id: 1, url: 'http://' });

const http = require('http');

const server = http.createServer((req, res) => {
    if (req.url === '/') {
        res.write('Hello World');
        res.end();
    }
});
server.on('connection', (socket) => {
    console.log('New Connection');
})
server.listen(3000);

console.log('Listening on port 3000...');
