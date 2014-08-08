package model;

/**
 * RhymeString is a line ending that rhymes with other RhymeStrings.
 *
 */
public class RhymeString {
	private String value;
	private boolean rhymesWithItself;
	
	public RhymeString(String value) {
		this.value = value;
		rhymesWithItself = false;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setRhymesWithItself(boolean rhymesWithItself) {
		this.rhymesWithItself = rhymesWithItself;
	}
	
	public boolean getRhymesWithItself() {
		return rhymesWithItself;
	}
	
	public int hashCode() {
		return value.hashCode();
	}
	
	public boolean equals(Object other) {
		if(!(other instanceof RhymeString))
			return false;
		
		return value.equals(((RhymeString) other).getValue());
	}
}
