package model;

import java.io.File;

/**
 * RhymeCopy uses a corpus of annotated rhyming lyrics to generate its own lyrics in the same style.
 * Created as a final project for ISTA 301, Summer 2014.
 * 
 * @author Nicolas Azevedo Costa and Jordan Wallet
 *
 */

public class RhymeCopy {

	/**
	 * 
	 * @param args Expects a single argument, the directory to read lyric files from.
	 */
	public static void main(String[] args) {
		if(!validDirectory(args)) {
			System.out.println("The argument should be a directory with annotated lyric files.");
			System.exit(1);
		}

		RCRhymeDictionary rhymeDict = new RCRhymeDictionary();
		RCLyricMap lyricMap = new RCLyricMap();
		RCSongStructure songStruct = new RCSongStructure();
		
		//TODO iterate over the files in the directory and have RCFileReader handle them all
		
		//TODO generate a song structure
		
		RCLineGenerator.setLyricMap(lyricMap);
		//TODO generate all the necessary lines based on the song structure
		
		//TODO use RCFileWriter to write the final song to an output file
	}

	private static boolean validDirectory(String[] args) {
		if(args.length < 1 || args[0].length() < 1)
			return false;
		
		File dir = new File(args[0]);
		if(dir.isDirectory())
			return false;

		return true;
	}
}
