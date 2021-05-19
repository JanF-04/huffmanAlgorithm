package de.jan.HuffmanAlgorithm;

import de.jan.HuffmanAlgorithm.Utils;

public class Node implements Cloneable {
	char key;
	int value;
	Node zero = null;
	Node one = null;
	
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
	
	public Node(Node o, Node z, int v) {
		zero = z;
		one = o;
		value = v;
	}
	
	public Node(int v, char k) {
		value = v;
		key = k;
	}
	
	public void treePrinter() {
		if (this.isSheet()) {
			System.out.println(String.valueOf(value) + ":" + String.valueOf(key));
		} else {
			System.out.println(String.valueOf(value));
			zero.treePrinter(1);
			one.treePrinter(1);
		}
	}

	public void treePrinter(int number) {
		if (this.isSheet()) {
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

	public String [] getAllPaths() {
		if (this.isSheet()) {
			return null;
		} else {
			String [] allZeroPaths = zero.getAllPaths("0");
			String [] allOnePaths = one.getAllPaths("1");
			String [] allPaths = Utils.addArrays(allOnePaths, allZeroPaths);
			return allPaths;
		}
	}

	public String [] getAllPaths(String path) {
		String [] allSubPaths;
		if (this.isSheet()) {
			allSubPaths = new String[1];
			allSubPaths[0] = path;
			return allSubPaths.clone();
		} else {
			String [] allSubZeroPaths = zero.getAllPaths(path + "0");
			String [] allSubOnePaths = one.getAllPaths(path + "1");
			allSubPaths = Utils.addArrays(allSubOnePaths, allSubZeroPaths);
			return allSubPaths;
		}
	}
	
	public boolean isSheet() {
		if (zero == null && one == null) {
			return true;
		}
		return false;
	}
}
