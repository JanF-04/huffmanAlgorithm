package de.jan.HuffmanAlgorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

// TODO: create an optimized tree file
// TODO: input stream --> doesnt work

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		while (true) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			System.out.println("Do you want to exit / code / encode? ( exit / c / e ):");
			String ce = sc.nextLine();
			if (ce.toUpperCase().equals("C")) {
				
				System.out.println("Please enter the string you want to code:");
				String code = sc.nextLine();
				System.out.println("Please enter the filename for the tree and binary File:");
				String filename = sc.nextLine();
				String dir = "huffmanAlgorithmFiles\\" + filename + "\\";
				String binFilename =  dir + filename + ".bin";
				String treeFilename = dir + filename + ".tree";
				
				CodingData result = Huffman.code(code);
				
				System.out.println("");
				
				//CREATE THE DIRECTORY
				File dirFile = new File(dir);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
					System.out.println("[INFO] Directory created: " + dir);
				} else {
					System.out.println("[INFO] Directory already exists.");
				}
				
				//SAVE BINARY FILES
			    File binFile = new File(binFilename);
			    if (binFile.createNewFile()) {
			    	System.out.println("[INFO] File created: " + binFile.getName());
			    } else {
			    	System.out.println("[INFO] File already exists.");
			    }
			      
				try {
					FileOutputStream binOutputStream = new FileOutputStream(binFilename);
//					System.out.println(result.getData());
					binOutputStream.write(result.getData().getFinalByteArray());
					binOutputStream.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				//SAVE TREE FILE
				File treeFile = new File(treeFilename);
			    if (treeFile.createNewFile()) {
			    	System.out.println("[INFO] File created: " + treeFile.getName());
			    } else {
			    	System.out.println("[INFO] File already exists.");
			    }
			      
				try {
					FileOutputStream treeOutputStream = new FileOutputStream(treeFilename);
					ObjectOutputStream treeObjectOut = new ObjectOutputStream(treeOutputStream);
					treeObjectOut.writeObject(result.getTree());
					treeObjectOut.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
//				System.out.println(result.getData().remainder());
//				result.getTree().treePrinter();
//				System.out.println(result.getData());
			} else if (ce.toUpperCase().equals("E")) {
				
				System.out.println("Please enter the filename for the tree and binary File:");
				String filename = sc.nextLine();
				String dir = "huffmanAlgorithmFiles\\" + filename + "\\";
				String binFilename =  dir + filename + ".bin";
				String treeFilename = dir + filename + ".tree";
				
				byte[] data;
				Node root;
				
				//GET BINARY FILES
				try {   
					FileInputStream binInputStream = new FileInputStream(binFilename);
		            long fileSize = new File(binFilename).length();
		            data = new byte[(int) fileSize];
		            binInputStream.read(data);
		            binInputStream.close();
				} catch (Exception ex) {
					data = new byte[0];
					ex.printStackTrace();
				}
				
				//GET TREE FILE
				try {
					FileInputStream treeInputStream = new FileInputStream(treeFilename);
					ObjectInputStream treeObjectIn = new ObjectInputStream(treeInputStream);
					root = (Node) treeObjectIn.readObject();
					treeObjectIn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					root = new Node(0,'0');
				}
				try {
					CodingData input = new CodingData(root, data);
					Huffman.decode(null);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			} else if (ce.toUpperCase().equals("EXIT")) {
				break;
			}
			System.out.println("\n-------------------------------------\n");
		}
	}
}
