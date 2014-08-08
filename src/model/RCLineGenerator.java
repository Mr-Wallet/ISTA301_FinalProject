package model;

import java.util.Random;

/**
 * Takes a word and then generates a line of appropriate length behind it.
 */
public class RCLineGenerator {
	private static RCLyricMap lyricMap;
	private static final int MIN_WORD_COUNT = 4;
	private static final int MAX_WORD_COUNT = 13;
	
	private static int varTemp = 85;
	
	public static void setLyricMap(RCLyricMap map) {
		lyricMap = map;
	}
	
	public static String generateLine(String lastWord) {
		int desiredWordCount = MIN_WORD_COUNT + (new Random()).nextInt(MAX_WORD_COUNT - MIN_WORD_COUNT);
		return generateLine(lastWord, desiredWordCount);
	}
	
	//TODO develop a sense of desired word count
	public static String generateLine(String lastWord, int desiredWordCount) {
		if(lyricMap == null) {
			throw new NullPointerException("You forgot to set RCLineGenerator's lyric map");
		}
		
		String result = lastWord;	
		
		while(lyricMap.hasPreviousWord(lastWord)) {
			String newWord = lyricMap.getRandomPreviousWord(lastWord);
			if(newWord.replaceAll("\\p{Punct}", "").toLowerCase().equals("i") || newWord.toLowerCase().startsWith("i'"))
				newWord = "I" + newWord.substring(1);
			
			
			String tempWord = newWord + " " + result;
			
			if(countLetter(tempWord) < varTemp){
				result = tempWord;
			}
			else{
				if(Math.abs(countLetter(tempWord) - varTemp) < Math.abs(countLetter(result) - varTemp))
					result = tempWord;
				break;
			}
	
			lastWord = newWord;
		}
		
		
		return result.substring(0,1).toUpperCase() + result.substring(1);
	}
	
	/*private static int wordCount(String s) {
		return s.isEmpty() ? 0 : s.split("\\s+").length;
	} */
	
	private static int countLetter(String s){
		
		String[] words = s.replaceAll("\\p{Punct}", "").split("\\s+");
		int numLetters = 0;
		
		for(int i=0; i< words.length; i++){
			numLetters += words[i].length();
		}
		
		return numLetters;
		
	}
	
}
