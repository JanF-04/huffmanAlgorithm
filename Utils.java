package de.jan.HuffmanAlgorithm;

public class Utils {
	public static String [] addArrays(String [] array1, String [] array2) {
        if (array1 == null) {
            return array2.clone();
        } else if (array2 == null) {
            return array1.clone();
        }
        final String[] joinedArray = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
	}
	
	public static byte [] stringToBits(String stringInput) {
        int byteArrayLength = (int) Math.ceil(stringInput.length() / 8.0);
        byte [] byteArray = new byte[byteArrayLength + 1];
        int byteArrayRemainders = (stringInput.length() % 8);
        byteArray[0] = (byte)byteArrayRemainders;
        int arrayPosition = 1;
        int bytePosition = 0;
        for (char ch : stringInput.toCharArray()) {
        	if (ch == '0') {
        		byteArray[arrayPosition] |= 0 << (7-bytePosition);
        	} else if (ch == '1') {
        		byteArray[arrayPosition] |= 1 << (7-bytePosition);
        	} else {
        		System.out.println("An error occured in stringToBits");
        	}
        	bytePosition++;
        	if (bytePosition == 8) {
        		bytePosition = 0;
        		arrayPosition++;
        	}
        }
        return byteArray;
	}
}
