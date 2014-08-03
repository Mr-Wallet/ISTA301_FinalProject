package model;

import java.util.ArrayList;

public class RCStructureGenerator {
	private static RCSongStructure songStruct;
	private static RCRhymeDictionary rhymeDict;

	public static void setSongStructure(RCSongStructure struct) {
		songStruct = struct;
	}
	
	public static void setRhymeDictionary(RCRhymeDictionary dict) {
		rhymeDict = dict;
	}
	
	public static ArrayList<String> generateSongStructure() {
		ArrayList<String> result = new ArrayList<String>();
		
		//TODO generate ArrayList of line-ending words.
		
		return result;
	}
}
