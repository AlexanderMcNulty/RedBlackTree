import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RedBlackTree<Key extends Comparable<Key>> {	
		private Node<Key> root;
		
		private Key type;
		
		public RedBlackTree(Key data) {
			root = new Node<>(data, true);
		}
		public RedBlackTree(Key type, Key hi) {
			this.type = type;
			root = null;
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
			
			if(root == null) {
				root = new Node<Key>(data);
				root.color = 1;
				return;
			}
			
			Node<Key> cur = root;
			
			while(true) {
				if(data.compareTo(cur.key) < 0) {
					System.out.println(data.compareTo(cur.key));
					if(cur.leftChild == null) {
						cur.leftChild = new Node<>(data);
						cur.leftChild.parent = cur;
						cur = cur.leftChild;
						System.out.println("sup\n\n");
						break;
					} else {
						cur = cur.leftChild;
					}
				} else {
					System.out.println(data.compareTo(cur.key));
					if(cur.rightChild == null) {
						cur.rightChild = new Node<>(data);
						cur.rightChild.parent = cur;
						cur = cur.rightChild;
						System.out.println("sup\n\n");
						break;
					} else {
						cur = cur.rightChild;
					}
				}
			}
			System.out.println(cur.parent);
			this.fixTree(cur);
		}
			

		public void insert(Key data){
			addNode(data);	
		}
		
		
		public void fixTree() {
			fixTree(root);
		}
		
		public void fixTree(Node<Key> cur) {
			if(cur == root) {
				cur.color = 1;
				return;
			}else if(cur.parent.color == 1 && cur.color == 0) {
				return;
			}else if(cur.parent.color == 0) {
				Node<Key> a = getAunt(cur);
				if((a == null || a.color == 1) && cur.parent != null && cur.parent.parent != null) {
					caseABCD(cur);
//					System.out.println(clearCase1(cur));
//					System.out.println(clearCase2(cur));
//					System.out.println(clearCase3(cur));
				} else if (a != null && a.color == 0 && cur.parent != null && cur.parent.parent != null /*&& cur.parent.parent != root*/) {
					a.color = 1;
					cur.parent.color = 1;
					cur.parent.parent.color = 0;
					fixTree(cur.parent.parent);
					System.out.println("good things happen");
				}
			}
		}
		
		public void caseABCD(Node<Key> cur) {
			Node<Key> g = cur.parent.parent;
			if(g == null) {
				return;
			}
			if(isLeftChild(g, cur.parent) && !(isLeftChild(cur.parent, cur))) {
				rotateLeft(cur.parent);
				fixTree(cur.leftChild);
			} else if (!(isLeftChild(g, cur.parent)) && (isLeftChild(cur.parent, cur))) {
				rotateRight(cur.parent);
				fixTree(cur.rightChild);
			} else if (!(isLeftChild(g, cur.parent)) && !(isLeftChild(cur.parent, cur))) {
				cur.parent.color = 1;
				g.color = 0;
				rotateLeft(g);
			} else if ((isLeftChild(g, cur.parent)) && (isLeftChild(cur.parent, cur))) {
				cur.parent.color = 1;
				g.color = 0;
				rotateRight(g);
			}
		}
		
		
		/*
		public boolean clearCase1(Node<Key> node) {
			Node<Key> a = getAunt(node);
			Node<Key> p = node.parent;
			Node<Key> g = getGrandparent(node);
			if(g == null || p == null ||g == root) {
				//case 1 invalid
				return false;
			} else if( p.color == 0 && g.color == 1 && a == null) {
				p.color = 1;
				g.color = 0;
				return true;
			} else  if (a == null){
				return false;
			} else if (a.color == 0 && p.color == 0 && g.color == 1) {
				a.color = 1;
				p.color = 1;
				g.color = 0;
				return true;
			}
			return false;

		}
		public boolean clearCase2(Node<Key> node) {
			if(getAunt(node) == null){
				return false;
			}
			Node<Key> p = node.parent;
			Node<Key> g = getGrandparent(node);
			if(g == null || p == null) {
				return false;
			} else if (node.color == 0 && p.color == 0 && g.color == 1) {
				if(p.leftChild == node && g.rightChild == p) {
					this.rotateRight(p);
					return true;
				} else if(p.rightChild == node && g.leftChild == p) {
					this.rotateLeft(p);
					return true;
				}
			}
			return false;
		}
		public boolean clearCase3(Node<Key> node) {
			Node<Key> p = node.parent;
			Node<Key> g = getGrandparent(node);
			if(p == null || g == null) {
				return false;
			} else if(node.color == 0 && p.color == 0 && g.color == 1) {
				if(node == p.rightChild && p == g.rightChild) {
					this.rotateLeft(g);
					p.color = 1;
					g.color = 0;
					node.color = 0;
					return true;
				} else if(node == p.leftChild && p == g.leftChild) {
					this.rotateRight(g);
					p.color = 1;
					g.color = 0;
					node.color = 0;
					return true;
				}
			}
			return false;
		}
		*/
			
		
		public Node<Key> getSibling(Node<Key> n){
			Node<Key> r = null;
			Node<Key> l = null;
			
			
			if(n == null || n.parent == null ||  (n.parent.rightChild == null &&  n.parent.leftChild == null)) {
				return null;
			}
			
			if(n.parent != null && n.parent.rightChild != null) {
				r = n.parent.rightChild;
			}
			if(n.parent != null && n.parent.leftChild != null) {
				l = n.parent.leftChild;
			}
			
			if(n.equals(r)) {
				return l;
			} else {
				return r;
			}
		}
		
		
		public Node<Key> getAunt(Node<Key> n){
			if(n == null) {
				return null;
			}
			Node<Key> p = getSibling(n.parent);
			
			//if(p == null) {
				return p;
			//}
			/*
			Node<Key> r = p.parent.rightChild;
			Node<Key> l = p.parent.leftChild;
			if(n.equals(r)) {
				return l;
			} else {
				return r;
			}
			*/
		}
		
		public Node<Key> getGrandparent(Node<Key> n){
			return n.parent.parent;
		}
		
		public void rotateLeft(Node<Key> x){
			
			Node<Key> y = x.rightChild;
			
			x.rightChild = y.leftChild;
			if(y.leftChild != null) {
				y.leftChild.parent = x;
			}
			y.parent = x.parent;
			
			if(x.parent == null) {
				root = y;
			} else if(x == x.parent.leftChild) {
				x.parent.leftChild = y;
			} else {
				x.parent.rightChild = y;
			}
			y.leftChild = x;
			x.parent = y;

		}
		
		public void rotateRight(Node<Key> x){
			Node<Key> y = x.leftChild;
			
			x.leftChild = y.rightChild;
			if(y.rightChild != null) {
				y.rightChild.parent = x;
			}
			y.parent = x.parent;

			if(x.parent == null) {
				root = y;
			} else if(x == x.parent.rightChild) {
				x.parent.rightChild = y;
			} else {
				x.parent.leftChild = y;
			}
			y.rightChild = x;
			x.parent = y;

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

