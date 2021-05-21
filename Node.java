package de.jan.HuffmanAlgorithm;

import java.io.Serializable;
import java.util.ArrayList;

public class Node implements Serializable {
	
	private static final long serialVersionUID = 5209108916202433809L;
	private char key;
	private int value;
	private Node zero = null;
	private Node one = null;
	
	public Node(Node o, Node z, int v) {
		zero = z;
		one = o;
		value = v;
	}
	
	public Node(int v, char k) {
		value = v;
		key = k;
	}
		
	public int getValue() {
		return value;
	}
	
	public char getKey() {
		return key;
	}
	
	public Node getZero() {
		return zero;
	}

	public Node getOne() {
		return one;
	}
	
	public void treePrinter() {
		if (this.isLeaf()) {
			System.out.println(String.valueOf(value) + ":" + String.valueOf(key));
		} else {
			System.out.println(String.valueOf(value));
			zero.treePrinter(1);
			one.treePrinter(1);
		}
	}

	public void treePrinter(int number) {
		if (this.isLeaf()) {
			for (int i = 0; i < number; i++) {
				System.out.print("-");
			}
			System.out.println(String.valueOf(value) + ":" + String.valueOf(key));
		} else {
			for (int i = 0; i < number; i++) {
				System.out.print("-");
			}
			System.out.println(String.valueOf(value));
			number++;
			zero.treePrinter(number);
			one.treePrinter(number);
		}
	}

	public ArrayList<BitArray> getAllPaths() {
		if (this.isLeaf()) {
			return null;
		} else {
			ArrayList<BitArray> allPaths = new ArrayList<>();
			allPaths.addAll(zero.getAllPaths(new BitArray(false)));
			allPaths.addAll(one.getAllPaths(new BitArray(true)));
			return allPaths;
		}
	}

	public ArrayList<BitArray> getAllPaths(BitArray path) {
		ArrayList<BitArray> allSubPaths = new ArrayList<>();
		if (this.isLeaf()) {
			allSubPaths.add(path);
			return allSubPaths;
		} else {
			allSubPaths.addAll(zero.getAllPaths(new BitArray(path, false)));
			allSubPaths.addAll(one.getAllPaths(new BitArray(path, true)));
			return allSubPaths;
		}
	}
	
	public boolean isLeaf() {
		if (zero == null && one == null) {
			return true;
		}
		return false;
	}
}
