package cribbage;

public class Main {

	// Points in a cribbage hand
	public static final double TRIALS = 1000000;
	public static double totalScore = 0;
	public static double totalFifteens = 0;
	public static double totalFlushes = 0;
	public static double totalNobbs = 0;
	public static double totalPairs = 0;
	public static double totalRuns = 0;
	public static double[] Graph = {};
	public static Card cut = new Card();
	
	public static void main(String Args[]) {
		Graph = new double[30];
		Deck cardDeck = new Deck();
		//cardDeck.output();
		
		
		for (int i = 0; i < TRIALS; i++) {
			cardDeck.shuffle();
			
			int score = findBestScore(cardDeck);
			totalScore += score;
			Graph[score] += 1;
			//System.out.println(score);
		}
		System.out.println("Number of 0 point hands: " + Graph[0]);
		System.out.println("Number of 29 point hands: " + Graph[29]);
		System.out.println("Average Score: " + totalScore / TRIALS);
		System.out.println("Average Fifteens: " + totalFifteens / TRIALS);
		System.out.println("Average Flushes: " + totalFlushes / TRIALS);
		System.out.println("Average Nobbs: " + totalNobbs / TRIALS);
		System.out.println("Average Pairs: " + totalPairs / TRIALS);
		System.out.println("Average Runs: " + totalRuns / TRIALS);
		
		
		graphOutput(Graph);
		
	}
	
	
	public static void graphOutput(double[] graph) {
		double maximum = 0;
		for (int i = 0; i < 30; i++) {
			if (Graph[i] > maximum) {
				maximum = Graph[i];
			}
		}
		/*double factor = 1;
		while (maximum > 120) {
			maximum /= 2;
			factor *= 2;
		}*/
		for (int i = 0; i < 30; i++) {
			System.out.print(i + "\t" + (int)Graph[i] + "\t" + 100 * Graph[i]/TRIALS + "% :\t");
			//for (int j = 0; j < Graph[i] / factor; j++) {
			//	System.out.print("#");
			//}
			System.out.println("");
		}
		
	}
	
	public static int findBestScore(Deck deck) {
		int score = 0;
		int bestScore = -1;
		int indexOne = 0, indexTwo = 0;
		int element = 0;
		Card[] smallerHand = new Card[4];
		for (int i = 0; i < 4; i++) {
			smallerHand[i] = new Card();
		}
		
		for (int i = 0; i < 5; i++) {
				score = 0;
				element = 0;
				
				for (int k = 0; k < 5; k++) {
					if (k != i) {
						smallerHand[element] = deck.deck[k];
						element += 1;
					}
				}
				//System.out.println("smallerHand: ");
				//output(smallerHand, 4);
				score = score(smallerHand, 4, 4, false);
				//System.out.println("score: " + score);
				// Check if there is a new high score
				if (score > bestScore) {
					bestScore = score;
					indexOne = i;
				}
		}
		//System.out.println("Index One is: " + indexOne);
		//System.out.println("Index Two is: " + indexTwo);
		Card[] anotherOne = new Card[5];
		for (int i = 0; i < 5; i++) {
			anotherOne[i] = new Card();
		}
		element = 0;
		for (int i = 0; i < 5; i++) {
			if (i != indexOne) {
				anotherOne[element] = deck.deck[i];
				element += 1;
			}
			if (i == 4) {
				anotherOne[i] = deck.deck[12];
				cut = anotherOne[i];
			}
		}

		//System.out.println("anotherOne: ");
		//output(anotherOne, 5);
		bestScore = score(anotherOne, 5, 4, true);
		/*if (bestScore == 25 || bestScore == 28 || bestScore == 19) {
			System.out.println("anotherOne: ");
			output(anotherOne, 5);
			System.out.println(bestScore);
		}*/
		//System.out.println(bestScore);
		
		return bestScore;
	}

	
	public static int score(Card[] hand, int size, int flushNumberNeeded, Boolean crib) {
		int score = 0;
		// Find Flush
		int flushPartialScore = flush(hand, size, flushNumberNeeded, crib);
		//System.out.println("Flush: " + flushPartialScore);
		if (crib) totalFlushes += flushPartialScore;
		score += flushPartialScore;
		// Find Nobbs
		if (crib) {
			int nobbsPartialScore = nobbs(hand, size);
			//System.out.println("Nobbs: " + nobbsPartialScore);
			totalNobbs += nobbsPartialScore;
			score += nobbsPartialScore;
		}
		sort(hand, size);
		// Find 15's
		int fifteenPartialScore = fifteens(0, hand, 0, size, 0);
		//System.out.println("Fifteens: " + fifteenPartialScore);
		if (crib) totalFifteens += fifteenPartialScore;
		score += fifteenPartialScore;
		// Find Pairs
		int pairsPartialScore = pairs(hand, size);
		//System.out.println("Pairs: " + pairsPartialScore);
		if (crib) totalPairs += pairsPartialScore;
		score += pairsPartialScore;
		// Find Runs
		int runsPartialScore = runs(hand, size);
		//System.out.println("Runs: " + runsPartialScore);
		if (crib) totalRuns += runsPartialScore;
		score += runsPartialScore;
		
		return score;
	}
	
	
	
	public static void sort(Card[] hand, int size) {
		int lowest = 5;
		Card tmp = new Card();
		for (int j = 0; j < size; j++) {
			lowest = size - 1;
			for (int i = size - 1; i >= j; i--) {
				if (hand[i].type <= hand[lowest].type) {
					lowest = i;
				}
			}
			tmp = hand[lowest];
			hand[lowest] = hand[j];
			hand[j] = tmp;
		}
	}
	
	public static void output(Card[] hand, int number) {
		for (int i = 0; i < number; i++) {
			System.out.print(hand[i].type);
			System.out.println(hand[i].suit);
		}
	}
	
	public static int nobbs(Card[] hand, int size) {
		int score = 0;
		for (int i = 0; i < size - 1; i++) {
			if (hand[i].type == 11 && hand[i].suit == cut.suit && hand[i] != cut) {
				score += 1;
			}
			
		}
		
		return score;
	}
	
	public static int flush(Card[] hand, int size, int flushNumberNeeded, Boolean crib) {
		int score = 0;
		int flushNumber = 1;
		
		for (int i = 0; i < 3; i++) {// Hard coded first 4 cards, so may need to change. 
			if (hand[i].suit == hand[i + 1].suit) {
				flushNumber += 1; 
			}
		}
		
		
		if (flushNumber >= flushNumberNeeded) {
			score += flushNumber;
			if (crib) {
				if (hand[size - 1].suit == hand[0].suit) {
					score += 1;
				}
			}
		}
		
		return score;
	}
	
	public static int fifteens(int currentScore, Card[] hand, int index, int size, int score) {
		int additiveScore = 0;
		for (; index < size; index ++) {
			additiveScore = currentScore + hand[index].value;
			if (additiveScore < 15) {
				score = fifteens(additiveScore, hand, index + 1, size, score);
			}
			else if (additiveScore > 15) {
				
			}
			else {
				score += 2;
			}
		}
		return score;
	}
	

	public static int pairs(Card[] hand, int size) {
		int score = 0;
		for (int i = 0; i < size - 1; i++) {
			int k = i + 1;
			while (hand[k].type == hand[i].type) {
				score += 2;
				k += 1;
				if (k >= size) {
					break;
				}
			}
		}
		return score;
	}
	
	
	
	public static int runs(Card[] hand, int size) {
		int score = 0;
		int runLength = 1;
		int factor = 1;
		int tmpFactor = 1;
		for (int i = 0; i < size - 1; i++) {
			while ((hand[i + 1].type == hand[i].type + 1) || (hand[i + 1].type == hand[i].type)) {
				runLength += 1;
				if (hand[i + 1].type == hand[i].type) {
					tmpFactor += 1;
					if (i + 2 < size) {
						if (hand[i + 2].type == hand[i].type) {
							tmpFactor += 1;
							i ++;
							if (i + 3 < size) {
								if (hand[i + 3].type == hand[i].type) {
									tmpFactor += 1;
									i++;
								}
							}
						}
					}
					runLength --;
				}
				factor *= tmpFactor;
				tmpFactor = 1;
				i++;
				if (i >= size - 1) {
					break;
				}
			}
			if (runLength == 2) {
				runLength = 1;
			}
		}
		if (runLength >= 3) {
			score = runLength * factor;
		}
		return score;
	}
}


