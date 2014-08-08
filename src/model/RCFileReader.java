package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * This class builds up the data structures using input files. First its setters
 * must be called to give it references to the data objects; then it is given
 * individual File objects to insert information into those objects.
 * 
 */
public class RCFileReader {

	static RCRhymeDictionary rhymeDict;
	static RCLyricMap lyricMap;
	static RCSongStructure songStruc;

	public static void setRhymeDictionary(RCRhymeDictionary dict) {
		rhymeDict = dict;
	}

	public static void setLyricMap(RCLyricMap map) {
		lyricMap = map;
	}

	public static void setSongStructure(RCSongStructure song) {
		songStruc = song;
	}

	public static void setGeneration(File file) {

		HashMap<String, ArrayList<String>> setMap;
		setMap = new HashMap<String, ArrayList<String>>();
		List<String> rhymeStructureSymbols = new ArrayList<String>();

		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(file + " not found");
		}

		songStruc.startNewSong();

		while (scanner.hasNextLine()) {
			String str = scanner.nextLine();
			if (str.isEmpty())
				continue;

			String[] words = str.toLowerCase().split("\\s+");

			// Stuff for the Rhyming Dictionary later
			if (!setMap.containsKey(words[1])) {
				setMap.put(words[1], new ArrayList<String>());
			}

			setMap.get(words[1]).add(words[words.length - 1]);

			
			
			// Lyric Map
			
			for (int i = words.length - 1; i >= 3; i--) {
				//keys do not have punctuation, values do
			
				lyricMap.addWords(words[i], words[i - 1]);
			}

			// Song Structure			
			switch (words[0].toLowerCase()) { // requires Java 7
			case "verse":
				songStruc.startNewVerse();
				rhymeStructureSymbols = new ArrayList<String>();
				break;
			case "chorus":
				songStruc.startNewChorus();
				rhymeStructureSymbols = new ArrayList<String>();
				break;
			case "break":
				songStruc.startNewBreak();
				rhymeStructureSymbols = new ArrayList<String>();
				break;
			case "bridge":
				songStruc.startNewBridge();
				rhymeStructureSymbols = new ArrayList<String>();
				break;
			case "intro":
				songStruc.startNewIntro();
				rhymeStructureSymbols = new ArrayList<String>();
				break;
			case "outro":
				songStruc.startNewOutro();
				rhymeStructureSymbols = new ArrayList<String>();
				break;
			}
			
			if(!rhymeStructureSymbols.contains(words[1])) {
				rhymeStructureSymbols.add(words[1]);
			}
			songStruc.addLine(rhymeStructureSymbols.indexOf(words[1]), getLyricLineLength(str));
		}

		
		//add what we've built up to the rhyming dictionary
		for (String str : setMap.keySet()) {
			rhymeDict.addWords(setMap.get(str));
		}

	}

	/**
	 * @param str a full line of an annotated lyric file
	 * @return the number of non-punctuation non-whitespace characters in the actual un-annotated lyrics
	 */
	private static int getLyricLineLength(String str) {
		return str.substring(2).toLowerCase().replaceAll("\\p{Punct}", "").replaceAll("\\s+", "").length();
	}
}
