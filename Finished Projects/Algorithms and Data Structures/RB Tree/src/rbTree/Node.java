package rbTree;

/**
 * Node Class with pointers to 2 children and a parent. 
 * @author lioness
 *
 */
public class Node {
	public  Node left;
	public Node right;
	public  Node p;
	public  int key;
	public  char color;
	
	/**
	 * Constructor that sets all values to a base value. 
	 * @param T The Tree
	 */
	Node(rbTree.Sentinel T){
		this.left = T.nil;
		this.right = T.nil;
		this.p = T.nil;
		this.key = 0;
		this.color = 'b';
	}
	
	/**
	 * Constructor that sets all values to the given values 
	 * @param left The left child node
	 * @param right The right child node
	 * @param p The parent node
	 * @param key The value to be sorted
	 * @param color The color of the node
	 */
	Node(Node left, Node right, Node p, int key, char color){
		this.left = left;
		this.right = right;
		this.p = p;
		this.key = key;
		this.color = color;
	}
	/**
	 * Constructor that sets the key to some number, and sets all other pointers to nil. 
	 * @param key
	 * @param t
	 */
	Node(int key, rbTree.Sentinel T){
		this.key = key;
		this.left = T.nil;
		this.right = T.nil;
		this.p = T.nil;
	}

}
