package model;

import java.util.Random;

/**
 * Takes a word and then generates a line of appropriate length behind it.
 */
public class RCLineGenerator {
	static RCLyricMap lyricMap;
	static int defaultWordCount = 7;
	
	public static void setLyricMap(RCLyricMap map) {
		lyricMap = map;
	}
	
	public static String generateLine(String lastWord) {
		int desiredWordCount = defaultWordCount - 1 + (new Random()).nextInt(2);
		return generateLine(lastWord, desiredWordCount);
	}
	
	public static String generateLine(String lastWord, int desiredWordCount) {
		if(lyricMap == null) {
			throw new NullPointerException("You forgot to set RCLineGenerator's lyric map");
		}
		String result = lastWord;
		while(wordCount(result) < desiredWordCount) {
			//TODO ask lyricmap for another word
		}
		return result;
	}
	
	private static int wordCount(String s) {
		return s.isEmpty() ? 0 : s.split("\\s+").length;
	}
}
