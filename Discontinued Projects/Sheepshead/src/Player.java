import java.util.Arrays;

public class Player {

    private String name = "";
    private int numberOfHands = 0;
    private int[] cardFrequency;
    private int timesAbleToPick = 0;
    private int numberOfPicks = 0;
    private int numberOfTrump;
    private boolean hasJack;
    private Card[] hand;

    public Player(){
        cardFrequency = new int[15];
        hand = new Card[6];

    }
    public Player(String name){
        this();
        this.name = name;
        
    }

    public void printHand(){
        System.out.println("Hand for player: " + this.name);
        for (int i = 0; i < hand.length; i++){
            hand[i].print();
        }
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setHand(Card[] newHand){
        this.numberOfTrump = 0;
        numberOfHands += 1;
        this.hasJack = false;
        for (int i = 0; i < hand.length; i++){
            if (newHand[i].getId() > 0){
                cardFrequency[newHand[i].getId()] += 1;
                this.numberOfTrump += 1;
                if (newHand[i].getId() == 6){
                    this.hasJack = true;
                }
            }
            this.hand[i] = newHand[i];
        }
        Arrays.sort(this.hand);
    }

    public boolean canPick(int position){
        this.timesAbleToPick += 1;
        // If you don't have the jack
        if (!hasJack){
            if (numberOfTrump == 6){
                // All 6 trump
                this.numberOfPicks += 1;
                return true;
            } else if (numberOfTrump == 5){
                // 5 trump Jack of Clubs
                if (hand[5].getId() > 8){
                    this.numberOfPicks += 1;
                    return true;
                // All 5 trump in last seat
                } else if (position == 4){
                    this.numberOfPicks += 1;
                    return true;
                }
            } else if (numberOfTrump == 4){
                // 4 trump queen of spades of QH QD
                if (hand[5].getId() > 11 || hand[4].getId() > 9){
                    this.numberOfPicks += 1;
                    return true;
                // 4 trump QH in last seat
                } else if (position == 4 & hand[5].getId() > 8){
                    this.numberOfPicks += 1;
                    return true;
                }
            } else if (numberOfTrump == 3){
                // 3 trump QC
                if (hand[5].getId() == 14){
                    this.numberOfPicks += 1;
                    return true;
                // 3 trump spitz in third or last
                } else if (position > 2 && hand[5].getId() == 13){
                    this.numberOfPicks += 1;
                    return true;
                }
            } else if (numberOfTrump == 2){
                // top 2
                if (hand[4].getId() == 13){
                    this.numberOfPicks += 1;
                    return true;
                // Queen of clubs 2nd in 3rd or 4th seat
                } else if (position > 2 && hand[5].getId() == 14){
                    this.numberOfPicks += 1;
                    return true;
                }
            } else {
                return false;
            }
        } else {
            if (numberOfTrump == 6){
                // 6 trump spitz
                if (hand[5].getId() > 12){
                    this.numberOfPicks += 1;
                    return true;
                }

            } else if (numberOfTrump == 5){
                // 5 trump with 2 of top 3
                if (hand[4].getId() > 11 && (position == 1 || position == 4)){
                    this.numberOfPicks += 1;
                    return true;
                }
            } else if (numberOfTrump == 4){
                // Jack of diamonds top 3
                if (hand[3].getId() == 12 && hand[0].getPoints() + hand[0].getPoints() > 13){
                    this.numberOfPicks += 1;
                    return true;
                }
            }
        }
        return false;
    }

    public int getNumberOfPicks(){
        return this.numberOfPicks;
    }

    public int getTimesAbleToPick(){
        return this.timesAbleToPick;
    }

    public int getNumberOfHands(){
        return this.numberOfHands;
    }

    public int getNumberOfTrump(){
        return this.numberOfTrump;
    }

    public boolean doesHaveJack(){
        return this.hasJack;
    }


    
}
