import java.util.Random;
import java.util.Arrays;

public class Board{
    private Card[] deck;
    private Player[] players;

    // For stats
    private int leasties = 0;
    private int trials = 0;
    private int[] jacksInSeat = {0, 0, 0, 0};
    private int[] TotalNumberOfTrump = {0, 0, 0, 0};
    private int[] trumpInMiddleForLeasties = {0, 0, 0};
    

    private int[][] picksWithJack;
    private int[][] jacksForXTrump;
    private int[][][] numberOfTrumpInMiddle;
    private int[][] picksWithXTrump;


    public Board(){
        char[] suits = {'d', 'c', 'h', 's'};
        String[] names = {"9", "K", "10", "A", "J", "Q"};
        int[] values = {0, 4, 10, 11, 2, 3};
        int[] id_numbers = {2, 3, 4, 5, 6, 10, -4, -3, -2, -1, 9, 14, -4, -3, -2, -1, 7, 11, -4, -3, -2, -1, 8, 12};

        // Make the Deck
        deck = new Card[26];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 6; j++){
                deck[i*6+j] = new Card(suits[i], names[j], id_numbers[i*6+j], values[j]);
            }
        }
        deck[24] = new Card('d', "7", 13, 0);
        deck[25] = new Card('d', "8", 1, 0);

        // Make the players
        players = new Player[4];
        String[] playerNames = {"1", "2", "3", "4"};
        for (int i = 0; i < 4; i++){
            players[i] = new Player(playerNames[i]);
        }

        // Player position, number of trump, then either 0, 1, or 2 trump, with also index 3 for total hands like that
        numberOfTrumpInMiddle = new int[4][7][4];
        // Player position, then number of trump
        jacksForXTrump = new int[4][7];
        // Total number of picks they did with x trump
        picksWithXTrump = new int[4][7];
        // Player position, then number of trump they had. index 7 is total
        picksWithJack = new int[4][8];
    }

    public void shuffle(){
        Card tmp;
        Random ran = new Random();
        int randNum = 0;
        for (int i = deck.length - 1; i >0; i--){
            randNum = ran.nextInt(i + 1);
            tmp = deck[i];
            deck[i] = deck[randNum];
            deck[randNum] = tmp;
        }
    }

    public void deal(){
        for (int i = 0; i < 4; i++){
            players[i].setHand(Arrays.copyOfRange(this.deck, i*6, i*6+6));
        }
    }

    public int simulatePicking(){
        int middleTrump = 0;
        for (int i = 0; i < 2; i++){
            if (deck[24+i].getId() > 0){
                middleTrump++;
            }
        }

        for (int i = 0; i < 4; i++){
            if (players[i].canPick(i + 1)){
                TotalNumberOfTrump[i] += players[i].getNumberOfTrump();
                picksWithXTrump[i][players[i].getNumberOfTrump()]++;
                if (this.deck[24].getId() == 6 || this.deck[25].getId() == 6){
                    jacksForXTrump[i][players[i].getNumberOfTrump()]++;
                    jacksInSeat[i] += 1;
                    if (i == 3 && (players[i].getNumberOfTrump() == 2 || players[i].getNumberOfTrump() == 3)){
                        System.out.println();
                        for (int j = 0; j < 4; j++){
                            players[j].printHand();
                        }
                        this.deck[25].print();
                        this.deck[24].print();
                    }
                }
                if (players[i].doesHaveJack()){
                    picksWithJack[i][players[i].getNumberOfTrump()]++;
                    picksWithJack[i][7]++;

                }
                numberOfTrumpInMiddle[i][players[i].getNumberOfTrump()][middleTrump]++;
                numberOfTrumpInMiddle[i][players[i].getNumberOfTrump()][3]++;

                return i;
            }
        }


        // -1 for leasties
        this.leasties += 1;
        trumpInMiddleForLeasties[middleTrump]++;
        return -1;
    }

    public void playAHand(){
        this.shuffle();
        this.deal();
        this.simulatePicking();
        this.trials++;
    }

    public void simulation(int t){
        for (int i = 0; i < t; i++){
            this.playAHand();
        }
    }

    public void outputStats(){
        System.out.println("Total Hands played: " + this.trials);
        System.out.printf("Leasties Percentage: %.2f", 100.0 * this.leasties / trials);
        System.out.println("%");

        for (int i = 0; i < 4; i++){
            System.out.println();
            System.out.print("Overall pick percentage in seat " + (i + 1) + ": ");
            System.out.printf("%.2f", 100.0 * players[i].getNumberOfPicks() / trials);
            System.out.println("%");

            System.out.print("Jack  percentage in seat " + (i + 1) + ": ");
            System.out.printf("%.2f", 100.0 * jacksInSeat[i] / (players[i].getNumberOfPicks() - picksWithJack[i][7]));
            System.out.println("%");

            System.out.print("Average number of trump when picking in seat " + (i + 1) + ": "); 
            System.out.printf("%.2f\n", 1.0 * TotalNumberOfTrump[i] / players[i].getNumberOfPicks());

            int totalTrump = TotalNumberOfTrump[i];
            for (int j = 0; j < 7; j++){
                totalTrump += (numberOfTrumpInMiddle[i][j][1] + 2 * numberOfTrumpInMiddle[i][j][2]);
            }
            System.out.print("Average number of trump after picking when in seat " + (i + 1) + ": "); 
            System.out.printf("%.2f\n", 1.0 * totalTrump / players[i].getNumberOfPicks());


            System.out.print("Percentage of Hands the person in seat " + (i + 1) + " got to choose: ");
            System.out.printf("%.2f", 100.0 * players[i].getTimesAbleToPick() / trials);
            System.out.println("%");

            System.out.printf("Average # of trump in middle: %.2f\n", (1.0 * (totalTrump - TotalNumberOfTrump[i])) / players[i].getNumberOfPicks());
            for (int j = 2; j < 7; j++){
                System.out.println("For seat " + (i + 1) + " with " + j + " trump: ");
                System.out.printf("\tJack Percentage: %.2f\n", 100.0 * jacksForXTrump[i][j] / (picksWithXTrump[i][j] - picksWithJack[i][j]));
                System.out.printf("\tMiddles with 0 trump: %.2f\n", 100.0 * numberOfTrumpInMiddle[i][j][0] / numberOfTrumpInMiddle[i][j][3]);
                System.out.printf("\tMiddles with 1 trump: %.2f\n", 100.0 * numberOfTrumpInMiddle[i][j][1] / numberOfTrumpInMiddle[i][j][3]);
                System.out.printf("\tMiddles with 2 trump: %.2f\n", 100.0 * numberOfTrumpInMiddle[i][j][2] / numberOfTrumpInMiddle[i][j][3]);
            }
        }



    }

    public void printDeck(){
        for (int i = 0; i < deck.length; i++){
            deck[i].print();
        }
    }
    public void printDeck(boolean extended){
        if (extended){
            for (int i = 0; i < deck.length; i++){
                deck[i].printExtended();
            }
        }
    }

    public void printHands(){
        for (int i = 0; i < 4; i++){
            players[i].printHand();
        }
    }


}