package de.jan.HuffmanAlgorithm;

public class CodingData {

	private Node tree;
	private String compressed;
	
	public Node getTree() {
		return tree;
	}

	public String getCompressed() {
		return compressed;
	}

	public CodingData(Node t, String c) {
		tree = t;
		compressed = c;
	}
	
}
