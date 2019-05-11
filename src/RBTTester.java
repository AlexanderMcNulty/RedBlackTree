
import static org.junit.Assert.*;

import org.junit.Test;


public class RBTTester {
	
	/*
	
	@Test
    //Test the Red Black Tree
	public void testGeneric() {
		Rectangle first = new Rectangle(10,10);
		RedBlackTree<Rectangle> rbt = new RedBlackTree<>(first);
        rbt.insert(new Rectangle(1,1));
        rbt.insert(new Rectangle(1,1));
        rbt.insert(new Rectangle(1,1));
        rbt.insert(new Rectangle(1,1));
        rbt.insert(new Rectangle(1,1));
        rbt.insert(new Rectangle(1,1));
        rbt.insert(new Rectangle(1,1));
        rbt.insert(new Rectangle(1,1));
        rbt.insert(new Rectangle(1,1));
        rbt.insert(new Rectangle(1,1));
        System.out.println(makeString(rbt));
        System.out.println(makeStringDetails(rbt));
        String str=     "Color: 1, Key:D Parent: \n"+
                        "Color: 1, Key:B Parent: D\n"+
                        "Color: 1, Key:A Parent: B\n"+
                        "Color: 1, Key:C Parent: B\n"+
                        "Color: 1, Key:F Parent: D\n"+
                        "Color: 1, Key:E Parent: F\n"+
                        "Color: 0, Key:H Parent: F\n"+
                        "Color: 1, Key:G Parent: H\n"+
                        "Color: 1, Key:I Parent: H\n"+
                        "Color: 0, Key:J Parent: I\n";
		assertEquals(str, makeStringDetails(rbt));
            
    }
    
	@Test
    //Test the Red Black Tree
	public void testStrings() {
		RedBlackTree<String> rbt = new RedBlackTree<>("First");
        rbt.insert("A");
        rbt.insert("B");
        rbt.insert("C");
        rbt.insert("D");
        rbt.insert("E");
        rbt.insert("F");
        rbt.insert("G");
        rbt.insert("H");
        rbt.insert("I");
        rbt.insert("J");
        System.out.println(makeString(rbt));
        System.out.println(makeStringDetails(rbt));
        assertEquals("DBACFEHGIJ", makeString(rbt));
        String str=     "Color: 1, Key:D Parent: \n"+
                        "Color: 1, Key:B Parent: D\n"+
                        "Color: 1, Key:A Parent: B\n"+
                        "Color: 1, Key:C Parent: B\n"+
                        "Color: 1, Key:F Parent: D\n"+
                        "Color: 1, Key:E Parent: F\n"+
                        "Color: 0, Key:H Parent: F\n"+
                        "Color: 1, Key:G Parent: H\n"+
                        "Color: 1, Key:I Parent: H\n"+
                        "Color: 0, Key:J Parent: I\n";
		assertEquals(str, makeStringDetails(rbt));
    }
    */
    
	
			
	@Test
	public void manualTest() {
		RedBlackTree<String> rbt = new RedBlackTree<>("BB");
		rbt.insert("B");
		rbt.insert("A");
        rbt.insert("C");
        rbt.insert("D");
        rbt.insert("E");
        rbt.insert("FE");
        rbt.insert("FA");
        rbt.insert("G");
        rbt.insert("H");
        rbt.insert("I");
        rbt.insert("J");
        makeStringDetails(rbt);

	}
	
	
    //add tester for spell checker
    
    public static String makeString(RedBlackTree t)
    {
       class MyVisitor implements Visitor<String> {
          String result = "";
          public void visit(Node n)
          {
             result = result + n.key;
             System.out.println(n.key);
          }
       };
       MyVisitor v = new MyVisitor();
       t.preOrderVisit(v);
       return v.result;
    }

    // color, parents key, this key
    public static String makeStringDetails(RedBlackTree t) {
    	{
    	       class MyVisitor implements Visitor<String> {
    	          String result = "";
    	          public void visit(Node n)
    	          {
    	        	  if(!(n.key).equals("")) {
    	        		  System.out.println("Key: "+n.key);
    	        		  //result = result +"Color: "+n.color+", Key:"+n.key+" Parent: "+n.parent.key+"\n";
    	        	  }
    	          }
    	       };
    	       MyVisitor v = new MyVisitor();
    	       printAll(t,v);
    	       System.out.println("hi\n\n");
    	       t.rotateLeft(t.getRoot());
    	       printAll(t,v);
    	       System.out.println("bad\n\n");
    	       t.rotateLeft(t.getRoot());
    	       printAll(t,v);
    	       System.out.println("bad\n\n");
    	       t.rotateRight(t.getRoot());
    	       t.rotateRight(t.getRoot());
    	       printAll(t,v);
    	       System.out.println("bad");
    	       return v.result;
    	 }
    }
    
    public static void printAll(RedBlackTree t, Visitor v) {
       t.preOrderVisit(v);
       System.out.println("\n\n");
       t.inOrderVisit(v);
       System.out.println("\n\n");
       t.postOrderVisit(v);
    }
    
    
 }
  
