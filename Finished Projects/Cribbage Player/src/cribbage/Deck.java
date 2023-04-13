package cribbage;

import java.util.Random;

public class Deck {

	public Card[] deck;
	
	Deck(){
		deck = new Card[52];
		
		
		for (int i = 0; i < 52; i++) {
			
			deck[i] = new Card();
			// Suit of cards
			if (i < 13) {
				deck[i].suit = 'c';
			}
			else if (i < 26) {
				deck[i].suit = 'd';
			}
			else if (i < 39){
				deck[i].suit = 'h';
			}
			else {
				deck[i].suit = 's';
			}
			
			// Value of Card
			if (i % 13 < 10) {
				deck[i].value = i % 13 + 1;
			}
			else {
				deck[i].value = 10;
			}
			
			// The Face Card
			deck[i].type = i % 13 + 1;
		}
		shuffle();
	}
	
	
	public void shuffle() {
		Card tmp = new Card();
		Random r = new Random();
		for (int i = 0; i < 52; i++) {
			int randomNumber = r.nextInt(52);
			tmp = deck[randomNumber];
			deck[randomNumber] = deck[i];
			deck[i] = tmp;
		}
	}
	
	public void output() {
		for (int i = 0; i < 52; i++) {
			System.out.print(deck[i].type);
			System.out.println(deck[i].suit);
		}
	}
}
