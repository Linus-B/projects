

public class Card implements Comparable<Card>{
    private int id;
    private char suit;
    private String name;
    private int pointValue;

    public Card(char suit, String name, int id, int pointValue){
        this.suit = suit;
        this.name = name;
        this.id = id;
        this.pointValue = pointValue;
    }

    @Override
    public int compareTo(Card other){
        return id - other.getId();
    }

    public void print(){
        System.out.println(name + suit);
    }

    public void printExtended(){
        System.out.println(name + suit + " " + pointValue + " " + id);
    }


    public int getId(){
        return this.id;
    }

    public int getPoints(){
        return this.pointValue;
    }
    
    
}
