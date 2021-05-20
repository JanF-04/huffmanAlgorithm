package de.jan.HuffmanAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		String filename = "C:\\filename.bin";
		
	    File myObj = new File(filename);
	    if (myObj.createNewFile()) {
	    	System.out.println("File created: " + myObj.getName());
	    } else {
	    	System.out.println("File already exists.");
	    }
	      
		try (FileOutputStream fos = new FileOutputStream(filename)) {
			CodingData result = Huffman.code("Dieser Test zeigt, ob`s funktioniert.");   
			fos.write(result.getCompressed());
		}
	}
}
