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
	private List<Integer> rhymeStructure;
	private List<String> lastWords;
	private List<String> songLines;
	
	public LargeSongElement(SongElementType type) {
		this.type = type;
		rhymeStructure = new ArrayList<Integer>();
		lastWords = new ArrayList<String>();
		songLines = new ArrayList<String>();
	}
	
	public SongElementType getSongElementType() {
		return type;
	}
	
	public List<Integer> getRhymeStructure() {
		return rhymeStructure;
	}
	
	public void setRhymeStructure(List<Integer> rhymeStructure) {
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
}
