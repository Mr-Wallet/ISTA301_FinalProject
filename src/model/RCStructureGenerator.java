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
	 * @return a list of LargeSongElement which are aware of their final words
	 *         and their element type. 
	 */
	public static List<LargeSongElement> generateSongSkeleton() throws Exception {
		ArrayList<LargeSongElement> result = new ArrayList<LargeSongElement>();
		Random rnd = new Random();

		List<LargeSongElement> songStructure = songStruct
				.generateRandomSongStructure();

		LargeSongElement chorusElement = null;
		LargeSongElement bridgeElement = null;

		// now generate each verse, inserting choruses and bridges as needed
		for (LargeSongElement lse : songStructure) {
			List<List<String>> rhymeSets = new ArrayList<List<String>>();
			List<RhymeLengthTuple> rhymeStructure = lse.getRhymeStructure();
			List<String> lastWords = lse.getLastWords();

			if (lse.getSongElementType() == SongElementType.CHORUS
					&& chorusElement != null) {
				result.add(chorusElement);
				continue;
			}

			if (lse.getSongElementType() == SongElementType.BRIDGE
					&& bridgeElement != null) {
				result.add(bridgeElement);
				continue;
			}

			// grab a random rhymeSet for each structure; we'll fix it in the
			// next loop if it's too small
			for (RhymeLengthTuple t : rhymeStructure) {
				int i = t.getRhymeType();
				while (i >= rhymeSets.size()) {
					rhymeSets.add(rhymeDict.getRandomRhymeList());
				}
			}

			// generate each line
			List<List<String>> setsAlreadyUsed = new ArrayList<List<String>>();
			for (RhymeLengthTuple t : rhymeStructure) {
				int i = t.getRhymeType();
				int giveUp = 0;
				if (firstOccurenceOfRhymeType(rhymeStructure, t)) {
					while (setsAlreadyUsed.contains(rhymeSets.get(i))
							|| uniqueElements(rhymeSets.get(i)) < occurencesOf(
									rhymeStructure, i)) {
						List<String> randomRhymeSet = rhymeDict
								.getRandomRhymeList();
						rhymeSets.set(i, randomRhymeSet);
						giveUp++;
						if (giveUp > 100) {
							break; // give up
						}
					}
				}
				List<String> currentRhymeSet = rhymeSets.get(i);
				setsAlreadyUsed.add(currentRhymeSet);

				String randomWord = currentRhymeSet.get(rnd
						.nextInt(currentRhymeSet.size()));
				// if the randomWord matches a previous one, and doesn't rhyme
				// with itself, keep drawing words
				while (lastWords.contains(randomWord)
						&& !rhymeDict.getRhymesWithItself(randomWord)) {
					randomWord = currentRhymeSet.get(rnd
							.nextInt(currentRhymeSet.size()));
				}

				lastWords.add(randomWord);
			}

			if (lse.getSongElementType() == SongElementType.CHORUS) {
				chorusElement = lse;
			}
			if (lse.getSongElementType() == SongElementType.BRIDGE) {
				bridgeElement = lse;
			}
			result.add(lse);
		}

		return result;
	}

	/**
	 * precondition: the list contains the tuple
	 * @return true if the given tuple is the first time that the given rhyme type occurs in the given structure, and false otherwise. "Is" meaning ==, not equals().
	 */
	private static boolean firstOccurenceOfRhymeType(List<RhymeLengthTuple> rhymeStructure, RhymeLengthTuple t) throws Exception {
		for(RhymeLengthTuple u : rhymeStructure) {
			if(t == u) return true;
			if(t.getRhymeType() == u.getRhymeType()) return false;
		}
		throw new Exception("could not find the tuple in the list");
	}

	private static int occurencesOf(List<RhymeLengthTuple> rhymeStructure,
			Integer i) {
		int result = 0;
		for (RhymeLengthTuple t : rhymeStructure) {
			Integer j = t.getRhymeType();
			if (j.equals(i))
				result++;
		}

		return result;
	}

	private static int uniqueElements(List<String> list) {
		int result = 0;
		for (int i = 0; i < list.size(); i++) {
			String word = list.get(i);
			if (rhymeDict.getRhymesWithItself(word))
				return Integer.MAX_VALUE;
			else if (list.indexOf(word) == i)
				result++;
		}
		return result;
	}
}
