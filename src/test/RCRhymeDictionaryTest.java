package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.RCRhymeDictionary;

import org.junit.Test;

public class RCRhymeDictionaryTest {

	@Test
	public void testSingleSet() {
		RCRhymeDictionary rd = new RCRhymeDictionary();
	
		
		ArrayList<String> al = new ArrayList<String>(); 
		
		al.add("blue");
		al.add("clue");
		al.add("due");
		
		rd.addWords(al);
		
		assertTrue(rd.getRandomRhymeList().contains("blue"));
		assertTrue(rd.getRandomRhymeList().contains("clue"));
		assertTrue(rd.getRandomRhymeList().contains("due"));
		
		assertEquals(1,rd.getSize());
		assertEquals(3,rd.getSizeGivenRhymeList(0));
		
		System.out.println(rd.toString());
     	
	}
	
	@Test
	public void testTwoSet() {
		RCRhymeDictionary rd = new RCRhymeDictionary();
		
		ArrayList<String> al = new ArrayList<String>();
		ArrayList<String> al2 = new ArrayList<String>();
		
		al.add("blue");
		al.add("clue");
		al.add("due");
		
		rd.addWords(al);
		
		al2.add("shoe");
		al2.add("drew");
		al2.add("clue");
		
		rd.addWords(al2);
		
		assertEquals(1,rd.getSize());
		assertEquals(6,rd.getSizeGivenRhymeList(0));
		
		assertTrue(rd.getRandomRhymeList().contains("blue"));
    	assertTrue(rd.getRandomRhymeList().contains("clue"));
		assertTrue(rd.getRandomRhymeList().contains("due"));
		assertTrue(rd.getRandomRhymeList().contains("shoe"));
		assertTrue(rd.getRandomRhymeList().contains("drew"));
		
		System.out.println(rd.toString());
	}
	
	@Test
	public void testTwoSetAndOneDifferent() {
		RCRhymeDictionary rd = new RCRhymeDictionary();
		
		ArrayList<String> al = new ArrayList<String>();
		
		
		al.add("blue");
		al.add("clue");
		al.add("due");
		
		rd.addWords(al);
		
		ArrayList<String> al2 = new ArrayList<String>();
		
		al2.add("shoe");
		al2.add("drew");
		al2.add("clue");
		
		rd.addWords(al2);
		
        ArrayList<String> al3 = new ArrayList<String>();
		
		al3.add("black");
		al3.add("pac");
		al3.add("lack");
		
		rd.addWords(al3);
		
		
		assertEquals(2,rd.getSize());
		assertEquals(6,rd.getSizeGivenRhymeList(0));
		assertEquals(3,rd.getSizeGivenRhymeList(1));
		
		assertTrue(rd.getRhymeList(0).contains("blue"));
    	assertTrue(rd.getRhymeList(0).contains("clue"));
		assertTrue(rd.getRhymeList(0).contains("due"));
		assertTrue(rd.getRhymeList(0).contains("shoe"));
		assertTrue(rd.getRhymeList(0).contains("drew"));
		
		assertTrue(rd.getRhymeList(1).contains("black"));
		assertTrue(rd.getRhymeList(1).contains("pac"));
		assertTrue(rd.getRhymeList(1).contains("lack"));
		
		System.out.println(rd.toString());
		
	}
	
	@Test
	public void testTwoSetMerged() {
		RCRhymeDictionary rd = new RCRhymeDictionary();
		
		ArrayList<String> al = new ArrayList<String>();
		
		al.add("blue");
		al.add("clue");
		al.add("due");
		
		rd.addWords(al);
		
		ArrayList<String> al2 = new ArrayList<String>();
		
		al2.add("shoe");
		al2.add("drew");
		al2.add("blew");
		
		rd.addWords(al2);
		
        ArrayList<String> al3 = new ArrayList<String>();
		
		al3.add("blue");
		al3.add("shoe");
		al3.add("knew");
		
		rd.addWords(al3);
		
		System.out.println(rd.toString());
		
		
		assertEquals(1,rd.getSize());
		assertEquals(9,rd.getSizeGivenRhymeList(0));
		
		assertTrue(rd.getRhymeList(0).contains("blue"));
    	assertTrue(rd.getRhymeList(0).contains("clue"));
		assertTrue(rd.getRhymeList(0).contains("due"));
		assertTrue(rd.getRhymeList(0).contains("shoe"));
		assertTrue(rd.getRhymeList(0).contains("drew"));
		assertTrue(rd.getRhymeList(0).contains("blew"));
		assertTrue(rd.getRhymeList(0).contains("knew"));
		
	}
	
	@Test
	public void testThreeSetMergedAndOneDifferent() {
		RCRhymeDictionary rd = new RCRhymeDictionary();
		
		ArrayList<String> al = new ArrayList<String>();
		
		al.add("blue");
		al.add("clue");
		al.add("due");
		
		rd.addWords(al);
		
		ArrayList<String> al2 = new ArrayList<String>();
		
		al2.add("shoe");
		al2.add("drew");
		al2.add("blew");
		
		rd.addWords(al2);
		
        ArrayList<String> al3 = new ArrayList<String>();
		
		al3.add("black");
		al3.add("rap");
		al3.add("crack");
		
		rd.addWords(al3);
		
		ArrayList<String> al4 = new ArrayList<String>();

		al4.add("true");
		al4.add("shampoo");
		al4.add("deja vu");

		rd.addWords(al4);
		
		ArrayList<String> al5 = new ArrayList<String>();

		al5.add("moo");
		al5.add("blue");
		al5.add("shoe");
		al5.add("true");

		rd.addWords(al5);

		System.out.println(rd.toString());
		
		
		assertEquals(2,rd.getSize());
		assertEquals(13,rd.getSizeGivenRhymeList(0));
		assertEquals(3,rd.getSizeGivenRhymeList(1));
		
		assertTrue(rd.getRhymeList(0).contains("blue"));
    	assertTrue(rd.getRhymeList(0).contains("clue"));
		assertTrue(rd.getRhymeList(0).contains("due"));
		assertTrue(rd.getRhymeList(0).contains("shoe"));
		assertTrue(rd.getRhymeList(0).contains("drew"));
		assertTrue(rd.getRhymeList(0).contains("blew"));
		assertTrue(rd.getRhymeList(0).contains("true"));
		assertTrue(rd.getRhymeList(0).contains("shampoo"));
		assertTrue(rd.getRhymeList(0).contains("deja vu"));
		assertTrue(rd.getRhymeList(0).contains("moo"));
		
		assertTrue(rd.getRhymeList(1).contains("black"));
		assertTrue(rd.getRhymeList(1).contains("rap"));
		assertTrue(rd.getRhymeList(1).contains("crack"));
		
	}


}
