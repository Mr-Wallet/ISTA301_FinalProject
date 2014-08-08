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
			
			//grab a random rhymeSet for each structure; we'll fix it in the next loop if it's too small
			for (Integer i : rhymeStructure) {
				while (i >= rhymeSets.size()) {
					rhymeSets.add(rhymeDict.getRandomRhymeList());
				}
			}
			
			// generate each line
			for (Integer i : rhymeStructure) {
				while (uniqueElements(rhymeSets.get(i)) < occurencesOf(rhymeStructure, i)) {
					List<String> randomRhymeSet = rhymeDict.getRandomRhymeList();
					rhymeSets.set(i, randomRhymeSet);
				}
				List<String> currentRhymeSet = rhymeSets.get(i);

				String randomWord = currentRhymeSet.get(rnd.nextInt(currentRhymeSet.size()));
				//if the randomWord matches a previous one, and doesn't rhyme with itself, keep drawing words
				while (lastWords.contains(randomWord) && !rhymeDict.getRhymesWithItself(randomWord)) {
					randomWord = currentRhymeSet.get(rnd.nextInt(currentRhymeSet.size()));
				}

				lastWords.add(randomWord);
			}
			
			if(lse.getSongElementType() == SongElementType.CHORUS) {
				chorusElement = lse;
			}
			result.add(lse);
		}

		return result;
	}

	private static int occurencesOf(List<Integer> rhymeStructure, Integer i) {
		int result = 0;
		for(Integer j : rhymeStructure) {
			if(j.equals(i)) result++;
		}
		
		return result;
	}

	private static int uniqueElements(List<String> list) {
		int result = 0;
		for(int i = 0; i < list.size(); i++) {
			String word = list.get(i);
			if(rhymeDict.getRhymesWithItself(word)) return Integer.MAX_VALUE;
			else if(list.indexOf(word) == i) result++;
		}
		return result;
	}
}
