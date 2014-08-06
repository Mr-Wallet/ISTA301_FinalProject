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

	/**
	 * 
	 * @return a list of LargeSongElement which are aware of their final words and their element type.
	 */
	public static List<LargeSongElement> generateSongSkeleton() {
		ArrayList<LargeSongElement> result = new ArrayList<LargeSongElement>();
		Random rnd = new Random();

		List<LargeSongElement> songStructure = songStruct
				.generateRandomSongStructure();
		
		LargeSongElement chorusElement = null;

		//now generate each verse, inserting choruses as needed
		for (LargeSongElement lse : songStructure) {
			List<List<String>> rhymeSets = new ArrayList<List<String>>();
			List<Integer> rhymeStructure = lse.getRhymeStructure();
			List<String> lastWords = lse.getLastWords();
			
			if(lse.getSongElementType() == SongElementType.CHORUS && chorusElement != null) {
				result.add(chorusElement);
				continue;
			}

			// generate each line
			for (Integer i : rhymeStructure) {
				while (i >= rhymeSets.size()) {
					rhymeSets.add(rhymeDict.getRandomRhymeList());
				}
				List<String> currentRhymeSet = rhymeSets.get(i);

				String randomWord = currentRhymeSet.get(rnd.nextInt(currentRhymeSet.size()));
				//TODO make the system aware of when repetition is OK
				//while (lastWords.contains(randomWord))
				//	currentRhymeSet.get(rnd.nextInt(currentRhymeSet.size()));

				lastWords.add(randomWord);
			}
			
			if(lse.getSongElementType() == SongElementType.CHORUS) {
				chorusElement = lse;
			}
			result.add(lse);
		}

		return result;
	}
}
