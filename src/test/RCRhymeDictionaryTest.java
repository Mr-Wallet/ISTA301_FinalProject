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
     	
	}
	
	@Test
	public void testTwoSetMerged() {
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
	//	assertEquals(5,rd.getSizeGivenRhymeList(1));
		
	//	assertTrue(rd.getRandomRhymeList().contains("blue"));
	//	assertTrue(rd.getRandomRhymeList().contains("clue"));
	//	assertTrue(rd.getRandomRhymeList().contains("due"));
	//	assertTrue(rd.getRandomRhymeList().contains("shoe"));
	//	assertTrue(rd.getRandomRhymeList().contains("drew"));
		
		
     	
	}

}
