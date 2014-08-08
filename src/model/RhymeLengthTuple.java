package model;

/**
 * stores a number representing a rhyme type, and a number representing line length	
 *
 */
public class RhymeLengthTuple {
	private int rhymeType;
	private int lineLength;
	
	public RhymeLengthTuple(int rhymeType, int lineLength) {
		this.rhymeType = rhymeType;
		this.lineLength = lineLength;
	}
	
	public int getRhymeType() {
		return rhymeType;
	}
	public void setRhymeType(int rhymeType) {
		this.rhymeType = rhymeType;
	}
	public int getLineLength() {
		return lineLength;
	}
	public void setLineLength(int lineLength) {
		this.lineLength = lineLength;
	}
	
	public String toString() {
		return "<" + rhymeType + "," + lineLength + ">";
	}
	
}
