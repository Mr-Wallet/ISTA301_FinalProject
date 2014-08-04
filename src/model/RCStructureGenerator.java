package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		Random rnd = new Random();
		
		//TODO use RCSongStructure in a meaningful way
		
		for(int i = 0; i < 8; i++) {
			List<String> rs = rhymeDict.getRandomRhymeList();
			result.add(rs.get(rnd.nextInt(rs.size())));
			result.add(rs.get(rnd.nextInt(rs.size())));
		}
		
		return result;
	}
}
