package cs146S19.McNulty.project4;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;


public class RBTTester {
	RedBlackTree<String> poem;
	RedBlackTree<String> dic; 
	
	
	@Test
    //Test the Red Black Tree
	public void testGeneric() {
		Rectangle first = new Rectangle(11,1);
		RedBlackTree<Rectangle> rbt = new RedBlackTree<>(first);
        rbt.insert(new Rectangle(1,1));
        rbt.insert(new Rectangle(2,1));
        rbt.insert(new Rectangle(3,1));
        rbt.insert(new Rectangle(4,1));
        rbt.insert(new Rectangle(5,1));
        rbt.insert(new Rectangle(6,1));
        rbt.insert(new Rectangle(7,1));
        rbt.insert(new Rectangle(8,1));
        rbt.insert(new Rectangle(9,1));
        rbt.insert(new Rectangle(10,1));
        //System.out.println(makeString(rbt));
        System.out.println(makeStringDetails(rbt));
        String str=			
			"Color: 1, Key:4 Parent: \n"+
			"Color: 1, Key:2 Parent: 4\n"+
			"Color: 1, Key:1 Parent: 2\n"+
			"Color: 1, Key:3 Parent: 2\n"+
			"Color: 1, Key:6 Parent: 4\n"+
			"Color: 1, Key:5 Parent: 6\n"+
			"Color: 0, Key:8 Parent: 6\n"+
			"Color: 1, Key:7 Parent: 8\n"+
			"Color: 1, Key:10 Parent: 8\n"+
			"Color: 0, Key:9 Parent: 10\n"+
			"Color: 0, Key:11 Parent: 10\n";
        		
        		
		assertEquals(str, makeStringDetails(rbt));        
    }
    
	private long startTime; //startTime is used to hold the system time at the start of a time test
	private long endTime; // endTime is used to hold the system time at the end of a time test

	
	@Before
	public void createTrees() throws IOException {
		System.out.println("\n\n\n--Starting New Test--\n");
		startTime = System.currentTimeMillis();
		poem = new RedBlackTree<>("", "");
	     String file ="notitle.txt";		      
	     BufferedReader reader = new BufferedReader(new FileReader(file));
	     while(true){
	    	 String str = reader.readLine();
	    	 
	    	 if(poem.lookup(str) != null) {
	    		 continue;
	    	 }

	    	 if(str == null) {
	    		 break;
	    	 }
	    	 poem.addNode(str);
	     }
	     reader.close();
	     endTime = System.currentTimeMillis();
	     System.out.println("Time to create Poem Tree: "+ (endTime-startTime));
	     
		startTime = System.currentTimeMillis();
	     dic = new RedBlackTree<>("", "");
	     file ="dictionary.txt";
	     reader = new BufferedReader(new FileReader(file));
	     while(true){
	    	 String str = reader.readLine();
	    	 if(str == null) {
	    		 break;
	    	 }
	    	 dic.addNode(str);
	     }
	     reader.close();
	     endTime = System.currentTimeMillis();
	     System.out.println("Time to create Dictionary Tree: "+ (endTime-startTime));
	}
    
	@Test
	public void createAndTestDictionary() {
			

		     System.out.println("\nCheck dictionary for a word.");
		     System.out.print("Enter a word: ");
		     String q = enterSearch(); 
		     
		     System.out.println("\nNode Key Value: " + dic.lookup(q)+"");
		     System.out.println("Node Sibling Value:  " + dic.getSibling(dic.lookup(q))+"");
		     System.out.println("Node Aunt Value: " + dic.getAunt(dic.lookup(q))+"\n\n");

		
	}
	
	@Test
	public void createAndTestPoem() throws IOException {


		     System.out.println("\nCheck poem for a word.");
		     System.out.print("Enter a word: ");
		     String q = enterSearch(); 
		     
		     System.out.println("\nNode Key Value: " + poem.lookup(q)+"");
		     System.out.println("Node Sibling Value:  " + poem.getSibling(poem.lookup(q))+"");
		     System.out.println("Node Aunt Value: " + poem.getAunt(poem.lookup(q))+"\n\n");

	}
	
	@Test
	public void validatePoem() {
		startTime = System.currentTimeMillis();
		ArrayList<String> keys = poem.getKeys();
		for(String k : keys) {
			System.out.println("Still good");
			assertTrue(dic.lookup(k) != null);
		}

		endTime = System.currentTimeMillis();
		System.out.println("Time to compare Dictionary with Poem: "+ (endTime-startTime));
	}
	
	
	
	@Test
    //Test the Red Black Tree
	public void testStrings() {
	     System.out.println("\n\nResults of Teachers Test: \n");
	     
		RedBlackTree<String> rbt = new RedBlackTree<>("First", "gi");
        rbt.insert("D");
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");
        assertEquals("DBACFEHGIJ", makeString(rbt));
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
    
    public static String makeString(RedBlackTree t)
    {
       class MyVisitor implements Visitor {
          String result = "";
          public void visit(Node n)
          {
             result = result + n.key;
          }
       };
       MyVisitor v = new MyVisitor();
       t.preOrderVisit(v);
       return v.result;
    }

    public static String makeStringDetails(RedBlackTree t) {
    	{
    	       class MyVisitor implements Visitor {
    	          String result = "";
    	          public void visit(Node n)
    	          {
    	        	  if(!(n.key).equals("") && n.parent!= null) {
    	        		  result = result +"Color: "+n.color+", Key:"+n.key+" Parent: "+n.parent.key+"\n";
    	        	  }
    	        	  else if(!(n.key).equals("")) {
    	        		  result = result +"Color: "+n.color+", Key:"+n.key+ " Parent: " + "\n";
    	        	  }
    	          }

    	          public String collectResult() {
    	        	  String toReturn = result;
    	        	  result = "";
    	        	  return toReturn;
    	          }
    	          
    	       };
    	       MyVisitor v = new MyVisitor();
    	       t.preOrderVisit(v);
    	       return v.result;
    	 }
    }

	
    
    public static void printAll(RedBlackTree t) {
    	class MyVisitor implements Visitor {
	          String result = "";
	          public void visit(Node n)
	          {
	        	  if(!(n.key).equals("") && n.parent!= null) {
	        		  result = result +"Color: "+n.color+", Key:"+n.key+" Parent: "+n.parent.key+"\n";
	        	  }
	        	  else if(!(n.key).equals("")) {
	        		  result = result +"Color: "+n.color+", Key:"+n.key+ " Parent: " + "\n";
	        	  }
	          }
	          
	          public String collectResult() {
	        	  String toReturn = result;
	        	  result = "";
	        	  return toReturn;
	          }
	          
	       };
	       MyVisitor v = new MyVisitor();
       t.preOrderVisit(v);
       System.out.println(v.collectResult()+"\n\n");
     //  t.inOrderVisit(v);
       //System.out.println(v.collectResult()+"\n\n");
     //  t.postOrderVisit(v);
       //System.out.println(v.collectResult()+"bad\n\n");
    }
    
    public static String enterSearch() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String name = reader.readLine();
			return name;
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
    }

    

	/*	
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
*/
    
 }
  
