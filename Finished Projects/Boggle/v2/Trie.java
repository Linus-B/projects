

public class Trie {
    private Node root;

    public Node getRoot(){
        return this.root;
    }

    public void setRoot(Node root){
        this.root = root;
    }

    Trie(){
        this.root = new Node();
    }

    Trie(Node rootNode){
        this.root = rootNode;
    }

    // Checks if there is a word that starts with this next letter. 
    // If not, returns -1. If there is, returns that position in the nodeList
    // of that node.
    private int startsWith(Node currNode, char letter){
        if (currNode == null || currNode.getSize() < 1){
            return -1;
        }
        for (int i = 0; i < currNode.getSize(); i++){
            if(currNode.getNode(i).getValue() == letter){
                return i;
            }
        }
        return -1;
    }

    public boolean isPrefix(String word){
        int pos = -1;
        Node currNode = this.root;
        for (int i = 0; i < word.length(); i++){
            pos = startsWith(currNode, word.charAt(i));
            if (pos == -1){
                return false;
            } else {
                currNode = currNode.getNode(pos);
            }
        }
        return true;
    }

    // returns false if unsuccessful, true if successful
    public boolean insert(String word){
        int length = word.length();
        Node currentlyChecking = this.root;
        int position = -1;
        for (int i = 0; i < length; i++){
            position = startsWith(currentlyChecking, word.charAt(i));
            // If it does not currently exist
            if (position < 0){
                Node tmp = new Node(word.charAt(i));
                if (i + 1 == word.length()){
                    tmp.addNode(new Node('*'));
                }
                currentlyChecking.addNode(tmp);
                currentlyChecking = tmp;

            } else {
                if (i + 1 == word.length()){
                    currentlyChecking.addNode(new Node('*'));
                } else {
                    currentlyChecking = currentlyChecking.getNode(position);
                }
            }
            if (currentlyChecking == null){
                return false;
            }
        }
        return true;
    }

    public boolean doesExist(String word){
        int length = word.length();
        int position = -1;
        Node currentlyChecking = this.root;
        for (int i = 0; i < length; i++){
            position = startsWith(currentlyChecking, word.charAt(i));
            // If it does not currently exist
            if (position < 0){
                return false;
            } else {
                currentlyChecking = currentlyChecking.getNode(position);
            }
            if (currentlyChecking == null){
                return false;
            }
        }
        for (int i = 0; i < currentlyChecking.getSize(); i++){
            if (currentlyChecking.getNode(i).getValue() == '*'){
                return true;
            }
        }
        return false;

    }

    // TODO: test this for sure
    public void print(){
        this.root.print();
    }
}
