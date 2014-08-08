package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Used in the generation of songs LargeSongElement combines rhyming structures
 * with syntactic information of what type of song element (verse, chorus, etc)
 * the rhyming structure is used for.
 */
public class LargeSongElement {
	private SongElementType type;
	private List<RhymeLengthTuple> rhymeStructure;
	private List<String> lastWords;
	private List<String> songLines;
	
	public String toString() {
		return type + " // " + rhymeStructure + " // " + lastWords;
	}

	public LargeSongElement(SongElementType type) {
		this.type = type;
		rhymeStructure = new ArrayList<RhymeLengthTuple>();
		lastWords = new ArrayList<String>();
		songLines = new ArrayList<String>();
	}

	public SongElementType getSongElementType() {
		return type;
	}

	public List<RhymeLengthTuple> getRhymeStructure() {
		return rhymeStructure;
	}

	public void setRhymeStructure(List<RhymeLengthTuple> rhymeStructure) {
		this.rhymeStructure = rhymeStructure;
	}

	public List<String> getLastWords() {
		return lastWords;
	}

	public List<String> getLines() {
		return songLines;
	}

	public void addLastWord(String word) {
		lastWords.add(word);
	}

	public void addLine(String line) {
		songLines.add(line);
	}

	public String getSongElementTypeAsString() {
		String result = "[";
		switch (type) {
			case BREAK:
				result += "BREAK";
				break;
			case BRIDGE:
				result += "BRIDGE";
				break;
			case CHORUS:
				result += "CHORUS";
				break;
			case INTRO:
				result += "INTRO";
				break;
			case OUTRO:
				result += "OUTRO";
				break;
			case VERSE:
				result += "VERSE";
		}
		result += "]";
		
		return result;
	}

	public LargeSongElement getSchemeCopy() {
		LargeSongElement result = new LargeSongElement(getSongElementType());
		List<RhymeLengthTuple> copyList = new ArrayList<RhymeLengthTuple>();
		for(RhymeLengthTuple t : getRhymeStructure()) {
			copyList.add(new RhymeLengthTuple(t.getRhymeType(), t.getLineLength()));
		}
		result.setRhymeStructure(copyList);
				
		return result;
	}

	public List<Integer> getLineLengths() {
		List<Integer> result = new ArrayList<Integer>();
		for(RhymeLengthTuple t : rhymeStructure) {
			result.add(t.getLineLength());
		}
		return result;
	}
}
