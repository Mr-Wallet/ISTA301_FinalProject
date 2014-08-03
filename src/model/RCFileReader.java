package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class RCFileReader {

	private String fileName;
	private HashMap<String, TreeSet<String>> setMap;
	Scanner scanner;
	RCRhymeDictionary rhymeDict;
	RCLyricMap lyricMap;
	RCSongStructure songStruc;

	public RCFileReader(String fileName, RCRhymeDictionary rhymeDict, RCLyricMap lyricMap, RCSongStructure songStruc){

		this.fileName = fileName;
		this.rhymeDict = rhymeDict;
		this.lyricMap = lyricMap;
		this.songStruc = songStruc;

		scanner = null;

		try {
			scanner = new Scanner(new File(this.fileName));
		} catch (FileNotFoundException e) {
			System.out.println(this.fileName + " not found");
		}

	}


	private void setGeneration(){

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
