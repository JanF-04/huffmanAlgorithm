package de.jan.HuffmanAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Huffman {

	public static CodingData code(String str) {
		
		HashMap<Character, Integer> charCounterMap = new HashMap<Character, Integer>();
		ArrayList<Node> charNodes = new ArrayList<Node>();
		
		for (char ch: str.toCharArray()) {
			int charCounter;
			
			try {
				charCounter = charCounterMap.get(ch);
				charCounter++;
			} catch (NullPointerException NPE) {
				charCounter = 1;
			}
			
			charCounterMap.put(ch, charCounter);
		}
		
		// PRINT HASHMAP WITH FOR LOOP AND CREATES NODES
//        System.out.println("For Loop:");
        for (Entry<Character, Integer> me : charCounterMap.entrySet()) {
//          System.out.println("Key: "+ me.getKey() + " | Value: " + me.getValue().toString());
          charNodes.add(new Node(me.getValue(), me.getKey()));
        }
//        System.out.println("-------------------------------------");
        
        while (charNodes.size() > 1) {
        	Node firstElement = null;
        	Node secondElement = null;
        	int firstValue = Integer.MAX_VALUE;
        	int secondValue = Integer.MAX_VALUE;
        	for (Node n : charNodes) {
        		int val = n.getValue();
        		if (val < secondValue) {
        			if (val < firstValue) {
        				secondElement = firstElement;
        				firstElement = n;
        				secondValue = firstValue;
        				firstValue = val;
        			} else {
        				secondElement = n;
        				secondValue = val;
        			}
        		}
        	}
        	Node newNode = new Node(firstElement, secondElement, firstValue + secondValue);
        	charNodes.remove(firstElement);
        	charNodes.remove(secondElement);
        	charNodes.add(newNode);
        }
        
        Node mainNode = charNodes.get(0);
  
//		System.out.println("CHARACTER COUNT:");
//        System.out.println(mainNode.getValue());
//        System.out.println("-------------------------------------");
//        System.out.println("TREE PRINT:");
//        mainNode.treePrinter();
//        System.out.println("-------------------------------------");
        
        HashMap<Character, BitArray> HashMapDict = Huffman.createHashMapDict(mainNode);
        
        BitArray finalBitArray = new BitArray();
        
        for (char ch : str.toCharArray()) {
        	finalBitArray.append(HashMapDict.get(ch));
        }
        
        CodingData result = new CodingData(mainNode, finalBitArray);
        
		return result;
	}
	
	public static String decode(CodingData c) {
		BitArray compressedBitArray = c.getData();
		String result = "";
		Node tree = c.getTree();
		Node temp = tree;
		for (int i = 0; i < compressedBitArray.size(); i++) {
//			System.out.print(i);
//			System.out.print(" - ");
//			System.out.println(compressedBitArray.getBit(i));
			if (compressedBitArray.getBit(i) == false) {
				temp = temp.getZero();
			} else if (compressedBitArray.getBit(i) == true) {
				temp = temp.getOne();
			}
			if (temp.isLeaf()) {
//				System.out.println(temp.getKey());
				result += temp.getKey();
				temp = tree;
			}
		}
//		System.out.println(result);
		return result;
	}
	
	public static HashMap<Character, BitArray> createHashMapDict(Node root) {
		HashMap<Character, BitArray> hashMapDict = new HashMap<Character, BitArray>();
		Node temp;
		BitArray path;
		if (!root.isLeaf()) {
			ArrayList<BitArray> allPaths = root.getAllPaths();
			for (int i = 0; i < allPaths.size(); i++) {
				temp = root;
				path = allPaths.get(i);
				// PRINTS ALL PATHS
//				System.out.println(allPaths[i]);
				for (int j = 0; j < path.size(); j++) {
					if (path.getBit(j)) {
						temp = temp.getOne();
					} else {
						temp = temp.getZero();
					}
				}
				// PRINT KEYS AND PATHS
//				System.out.println("Key: "+ subClone.getKey() + " | path: " + path);
				hashMapDict.put(temp.getKey(), path);
			}
		}
		return hashMapDict;
	}
	
}
