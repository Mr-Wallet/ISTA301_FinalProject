package model;

import java.util.HashMap;
import java.util.TreeSet;

public class RCLyricMap {

	public HashMap<String, TreeSet<String>> textMap;


	public void addWords(String wordKey, String wordValue) {

		textMap = new HashMap<String, TreeSet<String>>();

		TreeSet<String> array;

		if(! textMap.containsKey(wordKey)){
			textMap.put(wordKey, array = new TreeSet<String>());
		}

		textMap.get(wordKey).add(wordValue);

	}
	
	public void generateMap(){
		
		for(Object str : textMap.keySet())
			System.out.println(str +": "+textMap.get(str));
		
	}



}
