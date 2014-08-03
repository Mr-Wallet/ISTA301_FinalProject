package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

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

	public static void setGeneration(File file){

		String fileName;
		HashMap<String, TreeSet<String>> setMap;
		Scanner scanner;


		scanner = null;

		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(file + " not found");
		}

		setMap = new HashMap<String, TreeSet<String>>();

		TreeSet<String> array;

		while(scanner.hasNextLine()){
			String str = scanner.nextLine();

			String[] words = str.split("\\s+");


			// Local Map

			if(! setMap.containsKey(words[0])){
				setMap.put(words[0], array = new TreeSet<String>());
			}

			setMap.get(words[0]).add(words[words.length-1]);


			//Lyric Map

			for(int i = words.length-1; i >= 2; i-- ){
				lyricMap.addWords(words[i], words[i-1]);
			}

		}

		for(Object str : setMap.keySet()) // for about Local Map
			System.out.println(str +": "+setMap.get(str));

		lyricMap.generateMap();



	}


}
