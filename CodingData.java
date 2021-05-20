package de.jan.HuffmanAlgorithm;

public class CodingData {

	private Node tree;
	private byte [] compressed;
	
	public Node getTree() {
		return tree;
	}

	public byte [] getCompressed() {
		return compressed;
	}

	public CodingData(Node t, byte [] c) {
		tree = t;
		compressed = c;
	}
	
}
