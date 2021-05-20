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
        
        HashMap<Character, String> HashMapDict = Huffman.createHashMapDict(mainNode);
        
        String stringResult = "";
        
        for (char ch : str.toCharArray()) {
        	stringResult += HashMapDict.get(ch);
        }
        
        byte [] byteArray = Utils.stringToBits(stringResult);
        
        // PRINTS THE STRING ARRAY AND BIT ARRAY
//        System.out.println(stringResult);
//        for (byte b : byteArray) {
//        	System.out.println(Integer.toBinaryString(b));
//        }
        
        CodingData result = new CodingData(mainNode, byteArray);
        
		return result;
	}
	
	public static String decode(CodingData c) {
		
		return null;
	}
	
	public static HashMap<Character, String> createHashMapDict(Node node) {
		HashMap<Character, String> hashMapDict = new HashMap<Character, String>();
		char zero = "0".charAt(0);
		char one = "1".charAt(0);
		Node subClone;
		String path;
		if (!node.isSheet()) {
			String [] allPaths = node.getAllPaths();
			for (int i = 0; i < allPaths.length; i++) {
				try {
					subClone = (Node) node.clone();
				} catch (CloneNotSupportedException CNSE) {
					System.out.println(CNSE);
					return null;
				}
				path = allPaths[i];
				// PRINTS ALL PATHS
//				System.out.println(allPaths[i]);
				for (char ch : allPaths[i].toCharArray()) {
					if (ch == zero) {
						subClone = subClone.getZero();
					} else if (ch == one) {
						subClone = subClone.getOne();
					}
				}
				// PRINT KEYS AND PATHS
//				System.out.println("Key: "+ subClone.getKey() + " | path: " + path);
				hashMapDict.put(subClone.getKey(), path);
			}
		}
		return hashMapDict;
	}
	
}
