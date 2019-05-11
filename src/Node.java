public class Node<Key extends Comparable<Key>> { //changed to static 
	
	  Key key;  		  
	  Node<Key> parent;
	  Node<Key> leftChild;
	  Node<Key> rightChild;
	  boolean isRed;
	  boolean isRoot;
	  int color;
	  
	  public Node(Key data, boolean val){
		  this.key = data;
		  isRoot = val; 
		  leftChild = null;
		  rightChild = null;
		  parent = null;
	  }
	  public Node(Key data){
		  this.key = data;
		  isRoot = false; 
		  leftChild = null;
		  rightChild = null;
		  parent = null;
	  }		
	  
	  public int compareTo(Node<Key> n){ 	//this < that  <0
	 		return key.compareTo(n.key);  	//this > that  >0
	  }
	  
	  
	  public boolean isLeaf(){
		  if (isRoot && this.leftChild == null && this.rightChild == null) return true;
		  if (isRoot) return false;
		  if (this.leftChild == null && this.rightChild == null){
			  return true;
		  }
		  return false;
	  }
	  
	  @Override
	  public String toString() {
		  return String.valueOf(key);
	  }
	  
}
