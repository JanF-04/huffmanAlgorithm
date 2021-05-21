package de.jan.HuffmanAlgorithm;

import java.util.ArrayList;

public class BitArray {
    
	private ArrayList<Byte> data;
    private int length;

    public BitArray() {
        data = new ArrayList<>();
        length = 0;
    }
    
    public BitArray(boolean b) {
    	this();
        addBit(b);
    }
    
    public BitArray(BitArray ba) {
    	this.length = ba.length;
    	this.data = new ArrayList<>(ba.data);
    }
    
    // converts the binary file data to a BitArray class
    // first byte 	--> remainder
    // rest			--> data
    public BitArray(byte [] b) {
    	int remainder = b[0];
    	data = new ArrayList<>();
    	length = (b.length-1) * 8 - remainder;
    	for (int i = 1; i < b.length; i++) {
    		data.add(b[i]);
    	}
//    	this.data = new ArrayList<>(byte.sublist(1,byte.length));
    }
    
    public BitArray(BitArray ba, boolean b) {
    	this(ba);
    	addBit(b);
    }

    public void addBit(boolean value) {
        length++;
        if (length > data.size() * 8) {
            data.add((byte) 0);
        }
        setBit(length - 1, value);
    }

    public void setBit(int index, boolean value) {
        if (value)
            setBit(index);
        else
            unsetBit(index);
    }

    private void setBit(int index) {
    	if (index >= length) {
    		throw new IndexOutOfBoundsException();
    	}
        byte b = data.get(index / 8);
        b |= 1 << (7 - index % 8);
        data.set(index / 8, b);
    }

    private void unsetBit(int index) {
    	if (index >= length) {
    		throw new IndexOutOfBoundsException();
    	}
    	byte b = data.get(index / 8);
        b &= ~(1 << (7 - index % 8));
        data.set(index / 8, b);
    }
    
    public void append(BitArray ba) {
    	for (int i = 0; i < ba.length; i++) {
    		addBit(ba.getBit(i));
    	}
    }

    public boolean getBit(int index) {
        return (data.get(index / 8) & 1 << (7 - index % 8)) != 0;
    }

    public byte remainder() {
        return (byte) (8 - length % 8);
    }
    
	public byte [] toByteArray() {
		byte [] result = new byte[data.size()];
		for (int i = 0; i < data.size(); i++) {
			result[i] = data.get(i);
		}
		return result;
	}
	
	public byte [] getFinalByteArray() {
		byte [] result = new byte[data.size() + 1];
		result = Utils.addByteToArray(this.toByteArray(), this.remainder());
		return result;
	}
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < length; i++) {
            if (getBit(i)) {
                s.append("1");
            } else {
                s.append("0");
            }
        }
        s.append("]");
        return s.toString();
    }

	public int size() {
		return length;
	}
}

//public class BitArray {
//	int remainders;
//	byte [] byteArray;
//	
//	public int getRemainders() {
//		return remainders;
//	}
//
//	public byte[] getByteArray() {
//		return byteArray;
//	}
//	
//	public BitArray(String stringInput) {
//        int byteArrayLength = (int) Math.ceil(stringInput.length() / 8.0);
//        byteArray = new byte[byteArrayLength];
//        remainders = (stringInput.length() % 8);
//        int arrayPosition = 1;
//        int bytePosition = 0;
//        for (char ch : stringInput.toCharArray()) {
//        	if (ch == '0') {
//        		byteArray[arrayPosition] |= 0 << (7-bytePosition);
//        	} else if (ch == '1') {
//        		byteArray[arrayPosition] |= 1 << (7-bytePosition);
//        	} else {
//        		System.out.println("An error occured in stringToBits");
//        	}
//        	bytePosition++;
//        	if (bytePosition == 8) {
//        		bytePosition = 0;
//        		arrayPosition++;
//        	}
//        }
//	}
//	
//	public BitArray(byte[] ba, int r) {
//		byteArray = ba;
//		remainders = r;
//	}
//	
//	public BitArray(byte[] finalByteArray) {
//		remainders = (int) finalByteArray[0];
//		byteArray = Arrays.copyOfRange(finalByteArray, 1, finalByteArray.length);
//	}
//	
//	public byte [] getFinalByteArray() {
//		byte [] finalByteArray = new byte[byteArray.length + 1];
//		finalByteArray = Utils.addByteToArray(byteArray, remainders);
//		return finalByteArray;
//	}
//}	
