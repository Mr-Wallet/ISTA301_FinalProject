package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * IMPORTANT: Keys should be lowercase and should never include punctuation!
 */
public class RCLyricMap {

	private HashMap<String, ArrayList<String>> textMap;
	private Random rnd; // used to get random words

	public RCLyricMap() {
		textMap = new HashMap<String, ArrayList<String>>();
		rnd = new Random();

	}
	
	public void addWords(String wordKey, String wordValue) {
		wordKey = wordKey.toLowerCase().replaceAll("\\p{Punct}", "");
		if (!textMap.containsKey(wordKey)) {
			textMap.put(wordKey, new ArrayList<String>());
		}

		textMap.get(wordKey).add(wordValue);

	}

	public void printMap() {

		for (Object str : textMap.keySet())
			System.out.println(str + ": " + textMap.get(str));

	}

	/**
	 * 
	 * @return true if the chain can continue from the argument word, or false
	 *         otherwise
	 */
	public boolean hasPreviousWord(String word) {
		return textMap.containsKey(word.toLowerCase().replaceAll("\\p{Punct}", ""));
	}

	/**
	 * 
	 * @param word
	 *            the key to look up
	 * @return a random word from the value list
	 */
	public String getRandomPreviousWord(String word) {
		word = word.toLowerCase().replaceAll("\\p{Punct}", "");
		if (!textMap.containsKey(word))
			throw new IllegalArgumentException(
					"You must check hasPreviousWord() before trying to get a random one.");
		ArrayList<String> wordList = textMap.get(word);
		return wordList.get(rnd.nextInt(wordList.size()));
	}

}
