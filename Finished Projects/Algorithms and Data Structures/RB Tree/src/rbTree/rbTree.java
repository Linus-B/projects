package rbTree;

/**
 * Implements a Red Black Tree, and 
 * tests the height of the tree with the worst case scenario input. 
 * @author lioness
 *
 */
public class rbTree {
	
	public static final int ADDED_NUMBERS = 2048; // Number of numbers added
	
	/**
	 * Inserts a node into a Red Black Tree
	 * @param T The tree
	 * @param z The node that will be added. 
	 */
	public static void rbInsert(Sentinel T, Node z) {
		Node y = T.nil;
		Node x = T.root;
		int height = 0;
		while (x != T.nil) {
			y = x;
			if (z.key < x.key) {
				x = x.left;
			}
			else {
				x = x.right;
			}
			height += 1;
			if (height > T.height) {
				T.height = height;
			}
		}
		z.p = y;
		if (y == T.nil) {
			T.root = z;
		}
		else if (z.key < y.key) {
			y.left = z;
		}
		else {
			y.right = z;
		}
		z.left = T.nil;
		z.right = T.nil;
		z.color = 'r';
		rbInsertFixup(T, z);
	}
	
	/**
	 * A node was just added to the tree, and so the properties may now be violated. 
	 * This ensures that all properties are not being violated. 
	 * @param T The tree
	 * @param z The node that was added
	 */
	public static void rbInsertFixup(Sentinel T, Node z) {
		while (z.p.color == 'r') {
			if (z.p == z.p.p.left) {
				Node y = z.p.p.right;
				if (y.color == 'r') {
					z.p.color = 'b';
					y.color = 'b';
					z.p.p.color = 'r';
					z = z.p.p;
				}
				else if (z == z.p.right) {
					z = z.p;
					leftRotate(T, z);
					
				}
				else{
					z.p.color = 'b';
					z.p.p.color = 'r';
					rightRotate(T, z.p.p);
				}
			}
			else {
				Node y = z.p.p.left;
				if (y != null) {
					if (y.color == 'r') {
						z.p.color = 'b';
						y.color = 'b';
						z.p.p.color = 'r';
						z = z.p.p;
					}
					else if (z == z.p.left) {
						z = z.p;
						rightRotate(T, z);
						
					}
					else{
						z.p.color = 'b';
						z.p.p.color = 'r';
						leftRotate(T, z.p.p);
					}
				}
				
				
			}
			T.root.color = 'b';
		}
		
	}
	
	/**
	 * Rotates the nodes leftward. 
	 * @param T The Tree
	 * @param x The parent node to be rotated on
	 */
	public static void leftRotate(Sentinel T, Node x) {
		Node y = x.right;
		x.right = y.left;
		if (y.left != T.nil) {
			y.left.p = x;
		}
		y.p = x.p;
		if (x.p == T.nil){
			T.root = y;
		}
		else if (x == x.p.left) {
			x.p.left = y;
		}
		else {
			x.p.right = y;
		}
		y.left = x;
		x.p = y;
		
	}
	
	/**
	 * Rotates the nodes rightward. 
	 * @param T The Tree
	 * @param x The parent node to be rotated on
	 */
	public static void rightRotate(Sentinel T, Node x) {
		Node y = x.left;
		x.left = y.right;
		if (y.right != T.nil) {
			y.right.p = x;
		}
		y.p = x.p;
		if (x.p == T.nil){
			T.root = y;
		}
		else if (x == x.p.right) {
			x.p.right = y;
		}
		else {
			x.p.left = y;
		}
		y.right = x;
		x.p = y;
		
	}
	
	/**
	 * Adds ADDED_NUMBERS numbers to a Red-Black Tree, and prints out the height of the tree. 
	 * @param args The command line arguments, none needed for this application. 
	 */
	public static void main(String[] args) {
		rbTree.Sentinel T = new rbTree.Sentinel();
		for (int i = 0; i < ADDED_NUMBERS; i++) {
			Node z = new Node(i, T);
			rbInsert(T, z);
		}
		System.out.println("After Inserting " + ADDED_NUMBERS + " items in the worst case order: ");
		System.out.println("The height is " + T.height);
		output(T.root, T); 	// Ensures that the addition of numbers maintained the property wherein 
							// nodes to the left are smaller and nodes to the right are larger or equal to. 
	}
	
	/**
	 * Outputs the tree in non-decreasing order. 
	 * @param x The node to be outputted
	 * @param T The tree
	 */
	public static void output(Node x, Sentinel T){
		if (x.left != T.nil) {
			output(x.left, T);
		}
		System.out.print(x.key + " ");
		if (x.right != T.nil) {
			output(x.right, T);
		}
	}
	
	/**
	 * Sentinel class which contains the final node that all leaves point to, as well as the root node. 
	 * @author lioness
	 *
	 */
	public static class Sentinel {
		public  Node nil;
		public  Node root;
		public int height = 0;
		
		
		/**
		 * Constructor class that sets the nil pointer to a node which points to itself and a color of black. 
		 */
		Sentinel(){
			nil = new Node(nil, nil, nil, 0, 'b');
			root = nil;
		}
		
		
	}
}
