
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
        makeStringDetails(rbt);
        System.out.println("\n\nYO! B\n\n");

        rbt.insert("A");
        makeStringDetails(rbt);
        System.out.println("\n\nYO! 1A\n\n");

        rbt.insert("C");
        makeStringDetails(rbt);
        System.out.println("\n\nYO! C\n\n");

        rbt.insert("D");
        makeStringDetails(rbt);
        System.out.println("\n\nYO! D\n\n");

        rbt.insert("E");
        makeStringDetails(rbt);
        System.out.println("\n\nYO! E\n\n");        

        rbt.insert("FE");
        makeStringDetails(rbt);
        System.out.println("\n\nYO! FE\n\n");

        rbt.insert("FA");
        makeStringDetails(rbt);
        System.out.println("\n\nYO! FA\n\n");

        rbt.insert("G");
        makeStringDetails(rbt);
        System.out.println("\n\nYO! G\n\n");

        rbt.insert("H");
        makeStringDetails(rbt);
        System.out.println("\n\nYO! H\n\n");

        rbt.insert("I");
        makeStringDetails(rbt);
        System.out.println("\n\nYO! I\n\n");

        rbt.insert("J");
        makeStringDetails(rbt);
        System.out.println("\n\nYO! J\n\n");


        String q = enterSearch(); 
        System.out.println("lookup!! " + rbt.lookup(q)+"\n\n");
        System.out.println("sibling!! " + rbt.getSibling(rbt.lookup(q))+"\n\n");
        System.out.println("aunt!! " + rbt.getAunt(rbt.lookup(q))+"\n\n");
        
	}
	
	
    //add tester for spell checker
    
    public static String makeString(RedBlackTree t)
    {
       class MyVisitor implements Visitor<String> {
          String result = "";
          public void visit(Node n)
          {
             result = result + n.key;
             System.out.println(n.key +" "+ n.color +" "+ n.parent +" "+ n.rightChild +" "+ n.leftChild);
          }
       };
       MyVisitor v = new MyVisitor();
       t.preOrderVisit(v);
       return v.result;
    }

    
    
    public static class MyVisitor implements Visitor<String> {
        String result = "";
        public void visit(Node n)
        {
      	  if(!(n.key).equals("")) {
      		  if(n.parent == null) {
      			  result = result +"Key:"+n.key+"    Color: "+n.color + "\n";
      		  } else {
      			  result = result + "Key:" +n.key+"     Parent: "+n.parent.key +" Color: "+n.color + "\n";
      		  }
      		  
      	  }
        }
        public String collectResult() {
      	  String tmp = result;
      	  result = "";
      	  return tmp;
        }
     };
    
    // color, parents key, this key
    public static String makeStringDetails(RedBlackTree t) {
    	

	       MyVisitor v = new MyVisitor();
	       printAll(t,v);
	       
	       return v.result;
	 }

    
    public static void printAll(RedBlackTree t, MyVisitor v) {
       t.preOrderVisit(v);
       System.out.println(v.collectResult()+"\n\n");
       t.inOrderVisit(v);
       System.out.println(v.collectResult()+"\n\n");
       t.postOrderVisit(v);
       System.out.println(v.collectResult()+"bad\n\n");
    }
    
    public static String enterSearch() {
		try {
			BufferedReader reader =
                   new BufferedReader(new InputStreamReader(System.in));
			String name = reader.readLine();
			System.out.println(name);
			return name;
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
    }
 }
  
