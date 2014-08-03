package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class RCFileWriter {

	/**
	 * Writes an output song to the disk.
	 * @param fileName The output file to write to
	 * @param outputSong A list of song lines
	 */
	public static void writeSong(String fileName, List<String> outputSong) {
		PrintWriter writer = null;
		try {
			
			writer = new PrintWriter(new File(fileName));
			for(String line : outputSong) {
				writer.println(line);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Could not write output to disk.");
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
		
	}

}
