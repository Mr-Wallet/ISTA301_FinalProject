package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * RhymeCopy uses a corpus of annotated rhyming lyrics to generate its own
 * lyrics in the same style. Created as a final project for ISTA 301, Summer
 * 2014.
 * 
 * @author Nicolas Azevedo Costa and Jordan Wallet
 * 
 */

public class RhymeCopy {
	private static final String OUTPUT_FILE_PATH = "output.txt";

	/**
	 * 
	 * @param args
	 *            Expects a single argument, the directory to read lyric files
	 *            from.
	 */
	public static void main(String[] args) {
		if (!validDirectory(args)) {
			System.out.println(args[0]);
			System.out
					.println("The argument should be a directory of annotated lyric files.");
			System.exit(1);
		}

		RCRhymeDictionary rhymeDict = new RCRhymeDictionary();
		RCLyricMap lyricMap = new RCLyricMap();
		RCSongStructure songStruct = new RCSongStructure();

		RCFileReader.setRhymeDictionary(rhymeDict);
		RCFileReader.setLyricMap(lyricMap);
		RCFileReader.setSongStructure(songStruct);

		File[] lyricFiles = new File(args[0]).listFiles();
		for (File file : lyricFiles) {
			if (file.isDirectory())
				continue;
			RCFileReader.setGeneration(file);
		}

		RCStructureGenerator.setRhymeDictionary(rhymeDict);
		RCStructureGenerator.setSongStructure(songStruct);
		List<LargeSongElement> songElements = null;
		try {
			songElements = RCStructureGenerator
					.generateSongSkeleton();
		} catch (Exception e) {
		}

		RCLineGenerator.setLyricMap(lyricMap);
		for (LargeSongElement el : songElements) {
			List<String> lines = el.getLines();
			if (!lines.isEmpty()) // repetition, probably chorus
				continue;

			List<String> lastWords = el.getLastWords();
			List<Integer> lineLengths = el.getLineLengths();
			for (int i = 0; i < lastWords.size(); i++) {
				lines.add(RCLineGenerator.generateLine(lastWords.get(i), lineLengths.get(i)));
			}
		}

		List<String> outputSong = new ArrayList<String>();
		for (LargeSongElement el : songElements) {
			outputSong.add(el.getSongElementTypeAsString());
			outputSong.addAll(el.getLines());
			outputSong.add("");
		}
		
		RCFileWriter.writeSong(OUTPUT_FILE_PATH, outputSong);
	}

	private static boolean validDirectory(String[] args) {
		if (args.length < 1 || args[0].length() < 1)
			return false;

		File dir = new File(args[0]);
		if (!dir.isDirectory())
			return false;

		return true;
	}
}
