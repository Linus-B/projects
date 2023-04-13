import java.util.ArrayList;
import java.util.Comparator;

public class Node implements Comparator<Node>{
    private char value;
    private ArrayList<Node> nodeList;


    Node(){
        this.value = '*';
        this.nodeList = new ArrayList<Node>();
    }

    Node(char value){
        this.value = value;
        this.nodeList = new ArrayList<Node>();
    }

    @Override
    public int compare(Node o1, Node o2) {
        return o1.getValue() - o2.getValue();
    }

    public char getValue() {
        return value;
    }
    public void setValue(char value) {
        this.value = value;
    }
    public int getSize(){
        return this.nodeList.size();
    }
    public Node getNode(int pos) {
        // Redundancy to make sure no out of bounds errors
        if (pos < this.nodeList.size()){
            return this.nodeList.get(pos);
        }
        return null;
    }

    public void addNode(Node next) {
        this.nodeList.add(next);
    }

    private void printRecur(String substr){
        if (this.value == '*'){
            System.out.println(substr);
        } else {
            for (int i = 0; i < this.nodeList.size(); i++){
                nodeList.get(i).printRecur(substr + this.value);
            }
        }

    }

    public void print(){
        for (int i = 0; i < this.nodeList.size(); i++){
            nodeList.get(i).printRecur("");
        }
    }

}
