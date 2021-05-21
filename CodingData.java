package de.jan.HuffmanAlgorithm;

public class CodingData {

	private Node tree;
	private BitArray data;
	
	public Node getTree() {
		return tree;
	}

	public BitArray getData() {
		return data;
	}

	public CodingData(Node t, BitArray d) {
		tree = t;
		data = d;
	}
	
	public CodingData(Node t, byte [] b) {
		tree = t;
		data = new BitArray(b);
	}
}
