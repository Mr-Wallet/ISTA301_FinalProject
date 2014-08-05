package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RCLyricMap {

	private HashMap<String, ArrayList<String>> textMap;
	private Random rnd; // used to get random words

	public void addWords(String wordKey, String wordValue) {

		textMap = new HashMap<String, ArrayList<String>>();
		rnd = new Random();

		ArrayList<String> array;

		if (!textMap.containsKey(wordKey)) {
			textMap.put(wordKey, array = new ArrayList<String>());
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
		return textMap.containsKey(word);
	}

	/**
	 * 
	 * @param word
	 *            the key to look up
	 * @return a random word from the value list
	 */
	public String getRandomPreviousWord(String word) {
		if (!textMap.containsKey(word))
			throw new IllegalArgumentException(
					"You must check hasPreviousWord() before trying to get a random one.");
		ArrayList<String> wordList = textMap.get(word);
		return wordList.get(rnd.nextInt(wordList.size()));
	}

}
