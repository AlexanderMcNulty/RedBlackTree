import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RedBlackTree<Key extends Comparable<Key>> {	
		private Node<Key> root;
		
		
		public RedBlackTree(Key data) {
			root = new Node<>(data, true);
		}
		
		public Node<Key> getRoot(){
			return root;
		}
		
		 public boolean isLeaf(Node<Key> n){
			  if (n.equals(root) && n.leftChild == null && n.rightChild == null) return true;
			  if (n.equals(root)) return false;
			  if (n.leftChild == null && n.rightChild == null){
				  return true;
			  }
			  return false;
		  }

		public void visit(Node<Key> n){
			System.out.println(n.key);
		}
		
		public void printTree(){  //preorder: visit, go left, go right
			Node<Key> currentNode = root;	
			printTree(currentNode);
		}
		
		public void printTree(Node<Key> node){
			System.out.print(node.key);
			if (node.isLeaf()){
				return;
			}
			printTree(node.leftChild);
			printTree(node.rightChild);
		}
		
		
		// place a new node in the RB tree with data the parameter and color it red. 
		public void addNode(Key data){  	//this < that  <0.  this > that  >0
			Node<Key> cur = root;
			
			while(true) {
				if(data.compareTo(cur.key) < 0) {
					System.out.println(data.compareTo(cur.key));
					if(cur.leftChild == null) {
						cur.leftChild = new Node<>(data);
						cur.leftChild.parent = cur;
						System.out.println("\n\n");
						return;
					} else {
						cur = cur.leftChild;
					}
				} else {
					System.out.println(data.compareTo(cur.key));
					if(cur.rightChild == null) {
						cur.rightChild = new Node<>(data);
						cur.rightChild.parent = cur;
						System.out.println("\n\n");
						return;
					} else {
						cur = cur.rightChild;
					}
				}
			}
		}
			

		public void insert(Key data){
			addNode(data);	
		}
		
		
		
		public Node<Key> lookup(Key data){
			System.out.println(data + " dog");
			Node<Key> cur = root;

			while(true) {
				if(data.compareTo(cur.key) < 0) {
					System.out.println(data.compareTo(cur.key));
					if(cur.leftChild == null) {
						return null;
					} else if(cur.leftChild.key.equals(data)) {
						System.out.println("\n\n");
						return cur.leftChild;
					} else {
						cur = cur.leftChild;
					}
				} else {
					System.out.println(data.compareTo(cur.key));
					if(cur.rightChild == null) {
						return null;
					} else if(cur.rightChild.key.equals(data)) {
						System.out.println("\n\n");
						return cur.rightChild;
					} else {
						cur = cur.rightChild;
					}
				}
			}
		}
	 	
		public void clearCase1(Node<Key> node) {
			//Node<Key> uncle = node.parent.parent.
			//if(node.color == "RED") {
			//	x
			//}
		}
		
		public Node<Key> getSibling(Node<Key> n){
			Node<Key> r = null;
			Node<Key> l = null;
			
			
			if(n.parent == null) {
				return null;
			}
			if(n.parent.rightChild != null) {
				r = n.parent.rightChild;
			}
			if(n.parent.leftChild != null) {
				l = n.parent.leftChild;
			}
			
			if(n.equals(r)) {
				return l;
			} else {
				return r;
			}
		}
		
		
		public Node<Key> getAunt(Node<Key> n){
			Node<Key> p = getSibling(n.parent);
			Node<Key> r = p.parent.rightChild;
			Node<Key> l = p.parent.leftChild;
			if(n.equals(r)) {
				return l;
			} else {
				return r;
			}
		}
		
		public Node<Key> getGrandparent(Node<Key> n){
			return n.parent.parent;
		}
		
		public void rotateLeft(Node<Key> n){
			
			Node<Key> c = n.rightChild;

			c.parent = n.parent;	
			n.parent = c;
			n.rightChild = c.leftChild;
			c.leftChild = n;
			if(n.equals(root)) {
				root = c;
			}
			
		}
		
		public void rotateRight(Node<Key> n){
			Node<Key> c = n.leftChild;

			c.parent = n.parent;	
			n.parent = c;
			n.leftChild = c.rightChild;
			c.rightChild = n;
			if(n.equals(root)) {
				root = c;
			}

		}

		
		
		public void fixTree(Node<Key> current) {
			//
		}
		
		public boolean isEmpty(Node<Key> n){
			if (n.key == null){
				return true;
			}
			return false;
		}
		 
		public boolean isLeftChild(Node<Key> parent, Node<Key> child)
		{
			if (child.compareTo(parent) < 0 ) {//child is less than parent
				return true;
			}
			return false;
		}

		public void preOrderVisit(Visitor<Key> v) {
			
		   	preOrderVisit(root, v);

		}
		 
		 
		private void preOrderVisit(Node<Key> n, Visitor<Key> v) {

		  	if (n == null) {
		  		return;
		  	}
		  	v.visit(n);
		  	preOrderVisit(n.leftChild, v);
		  	preOrderVisit(n.rightChild, v);
		}	
		
		public void inOrderVisit(Visitor<Key> v) {
			
		   	inOrderVisit(root, v);

		}
		 
		 
		private void inOrderVisit(Node<Key> n, Visitor<Key> v) {

		  	if (n == null) {
		  		return;
		  	}
		  	inOrderVisit(n.leftChild, v);
		  	v.visit(n);
		  	inOrderVisit(n.rightChild, v);
		}	

		
		public void postOrderVisit(Visitor<Key> v) {
			
			postOrderVisit(root, v);

		}
		 
		private void postOrderVisit(Node<Key> n, Visitor<Key> v) {

		  	if (n == null) {
		  		return;
		  	}
		  	postOrderVisit(n.leftChild, v);
		  	postOrderVisit(n.rightChild, v);
		  	v.visit(n);
		}	
		
	}

