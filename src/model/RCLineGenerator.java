package model;

import java.util.Random;

/**
 * Takes a word and then generates a line of appropriate length behind it.
 */
public class RCLineGenerator {
	private static RCLyricMap lyricMap;
	private static final int MIN_WORD_COUNT = 4;
	private static final int MAX_WORD_COUNT = 13;
	
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
		while(wordCount(result) < desiredWordCount && lyricMap.hasPreviousWord(lastWord)) {
			String newWord = lyricMap.getRandomPreviousWord(lastWord);
			result = newWord + " " + result;
			lastWord = newWord;
		}
		return result;
	}
	
	private static int wordCount(String s) {
		return s.isEmpty() ? 0 : s.split("\\s+").length;
	}
}
