package model;

/**
 * Takes a word and then generates a line of appropriate length behind it.
 */
public class RCLineGenerator {
	private static RCLyricMap lyricMap;
	
	public static void setLyricMap(RCLyricMap map) {
		lyricMap = map;
	}
	
	//TODO develop a sense of desired word count
	public static String generateLine(String lastWord, int desiredLetterCount) {
		if(lyricMap == null) {
			throw new NullPointerException("You forgot to set RCLineGenerator's lyric map");
		}
		
		String result = lastWord;	
		
		while(lyricMap.hasPreviousWord(lastWord)) {
			String newWord = lyricMap.getRandomPreviousWord(lastWord);
			if(newWord.replaceAll("\\p{Punct}", "").toLowerCase().equals("i") || newWord.toLowerCase().startsWith("i'"))
				newWord = "I" + newWord.substring(1);
			
			
			String tempWord = newWord + " " + result;
			
			if(countLetter(tempWord) < desiredLetterCount){
				result = tempWord;
			}
			else{
				if(Math.abs(countLetter(tempWord) - desiredLetterCount) < Math.abs(countLetter(result) - desiredLetterCount))
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
