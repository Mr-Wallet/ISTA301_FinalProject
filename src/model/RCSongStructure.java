package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is in charge of tracking word length of lines, rhyming structure
 * over the entire song, and ideally, if we have time, the generation and
 * repetition of choruses.
 * 
 */
public class RCSongStructure {

	private List<List<SongElementType>> songSchemes = new ArrayList<List<SongElementType>>();
	private List<List<Integer>> verseSchemes = new ArrayList<List<Integer>>();
	private List<List<Integer>> chorusSchemes = new ArrayList<List<Integer>>();
	private List<List<Integer>> bridgeSchemes = new ArrayList<List<Integer>>();

	private List<SongElementType> currentSong;
	private LargeSongElement currentElement;
	private List<Integer> currentScheme;
	
	public RCSongStructure() {
		songSchemes = new ArrayList<List<SongElementType>>();
		verseSchemes = new ArrayList<List<Integer>>();
		chorusSchemes = new ArrayList<List<Integer>>();		
		bridgeSchemes = new ArrayList<List<Integer>>();		
	}

	/**
	 * This should be called every time a new song file is started.
	 */
	public void startNewSong() {
		currentSong = new ArrayList<SongElementType>();
		songSchemes.add(currentSong);
	}

	/**
	 * A verse is a collection of lines that have rhyming structures. Call this
	 * method to mark that a new verse is starting.
	 */
	public void startNewVerse() {
		currentSong.add(SongElementType.VERSE);

		currentElement = new LargeSongElement(SongElementType.VERSE);
		currentScheme = currentElement.getRhymeStructure();
		verseSchemes.add(currentScheme);
	}

	/**
	 * A break is a collection of lines that have rhyming structures. Breaks are
	 * like verses, but appearing more or less uniquely in the song. Call this
	 * method to mark that a new break is starting.
	 */
	public void startNewBreak() {
		currentSong.add(SongElementType.BREAK);

		currentElement = new LargeSongElement(SongElementType.BREAK);
		currentScheme = currentElement.getRhymeStructure();
		verseSchemes.add(currentScheme);
	}
	
	/**
	 * A bridge is a special verse that precedes a chorus; it is usually shorter than other verses.
	 */
	public void startNewBridge() {
		currentSong.add(SongElementType.BRIDGE);

		currentElement = new LargeSongElement(SongElementType.BRIDGE);
		currentScheme = currentElement.getRhymeStructure();
		bridgeSchemes.add(currentScheme);
	}
	
	/**
	 * An intro is a verse that only appears at the very beginning of the song.
	 * Intros are considered bridges for structure purposes because they are similarly short.
	 */
	public void startNewIntro() {
		currentSong.add(SongElementType.INTRO);

		currentElement = new LargeSongElement(SongElementType.INTRO);
		currentScheme = currentElement.getRhymeStructure();
		bridgeSchemes.add(currentScheme);
	}

	/**
	 * An outro is a verse that only appears at the very end of the song.
	 * outros are considered bridges for structure purposes because they are similarly short.
	 */
	public void startNewOutro() {
		currentSong.add(SongElementType.OUTRO);

		currentElement = new LargeSongElement(SongElementType.OUTRO);
		currentScheme = currentElement.getRhymeStructure();
		bridgeSchemes.add(currentScheme);
	}

	/**
	 * For now, choruses are just verses that are identical whenever they
	 * appear. Call this method to mark that a new instance of the chorus is
	 * starting.
	 */
	public void startNewChorus() {
		currentSong.add(SongElementType.CHORUS);

		currentElement = new LargeSongElement(SongElementType.CHORUS);
		currentScheme = currentElement.getRhymeStructure();
		chorusSchemes.add(currentScheme);
	}

	/**
	 * This should be called every single line to build up the current large
	 * song element.
	 * 
	 * @param rhymeIndex
	 *            a number corresponding to the rhyme; start at 0 and count up
	 *            whenever a new word is encountered that doesn't rhyme with a
	 *            previous word in the same verse/chorus.
	 */
	public void addLine(int rhymeIndex) {
		currentScheme.add(rhymeIndex);
	}

	/**
	 * Song structures are a list of large elements (verses, choruses, breaks)
	 * that contain both their type and their rhyme structure.
	 * 
	 * @Return A list of LargeSongElements that can be used to generate line-ending rhyming groups.
	 */
	public List<LargeSongElement> generateRandomSongStructure() {
		List<LargeSongElement> result = new ArrayList<LargeSongElement>();
		Random rnd = new Random();
		
		List<SongElementType> overallForm = songSchemes.get(rnd.nextInt(songSchemes.size()));
		
		LargeSongElement songVerse = new LargeSongElement(SongElementType.VERSE);
		songVerse.setRhymeStructure(verseSchemes.get(rnd.nextInt(verseSchemes.size())));
		
		LargeSongElement songChorus = new LargeSongElement(SongElementType.CHORUS);
		songChorus.setRhymeStructure(chorusSchemes.get(rnd.nextInt(chorusSchemes.size())));

		LargeSongElement songBreak = new LargeSongElement(SongElementType.BREAK);
		songBreak.setRhymeStructure(verseSchemes.get(rnd.nextInt(verseSchemes.size())));

		LargeSongElement songBridge = new LargeSongElement(SongElementType.BRIDGE);
		songBreak.setRhymeStructure(bridgeSchemes.get(rnd.nextInt(bridgeSchemes.size())));
		
		LargeSongElement songIntro = new LargeSongElement(SongElementType.INTRO);
		songBreak.setRhymeStructure(bridgeSchemes.get(rnd.nextInt(bridgeSchemes.size())));
		
		LargeSongElement songOutro = new LargeSongElement(SongElementType.OUTRO);
		songBreak.setRhymeStructure(bridgeSchemes.get(rnd.nextInt(bridgeSchemes.size())));
		
		for(SongElementType elType : overallForm) {
			switch(elType) {
				case VERSE:
					result.add(songVerse);
					break;
				case CHORUS:
					result.add(songChorus);
					break;
				case BREAK:
					result.add(songBreak);
					break;
				case BRIDGE:
					result.add(songBridge);
					break;
				case INTRO:
					result.add(songIntro);
					break;
				case OUTRO:
					result.add(songOutro);
					break;
			}
		}
		
		return result;
	}
}
