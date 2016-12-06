import java.io.*;
import java.util.*;

public class AdventSix {
	private static final String INPUT_FILE = "input.txt";
    private static final String INPUT = readInput(INPUT_FILE);
    private static final String[] LINES = INPUT.split("\n");

    public static void main(String[] args) {
    	partOne();
    	partTwo();
    }

    private static void partTwo() {
    	char[][] inputs = getInputArray();

    	String result = "";

    	// Loop through each col, and make an array
    	// of letters at each row, sort and get first
    	int colCount = inputs[0].length;
    	for(int col = 0; col < colCount; col++) {
    		List<Letter> letterList = new ArrayList<>();

    		for(int row = 0; row < inputs.length; row++) {
                Letter letterObj = new Letter(inputs[row][col]);
                if(letterList.contains(letterObj)) {
                    letterObj = letterList.get(letterList.indexOf(letterObj));
                } else {
                    letterList.add(letterObj);
                }

                letterObj.count++;
    		}

    		Collections.sort(letterList);

    		result += letterList.get(letterList.size() - 1).letter;
    	}

    	System.out.println("Message is: " + result);
    }

    private static void partOne() {
    	char[][] inputs = getInputArray();

    	String result = "";

    	// Loop through each col, and make an array
    	// of letters at each row, sort and get first
    	int colCount = inputs[0].length;
    	for(int col = 0; col < colCount; col++) {
    		List<Letter> letterList = new ArrayList<>();

    		for(int row = 0; row < inputs.length; row++) {
                Letter letterObj = new Letter(inputs[row][col]);
                if(letterList.contains(letterObj)) {
                    letterObj = letterList.get(letterList.indexOf(letterObj));
                } else {
                    letterList.add(letterObj);
                }

                letterObj.count++;
    		}

    		Collections.sort(letterList);

    		result += letterList.get(0).letter;
    	}

    	System.out.println("Message is: " + result);
    }

    private static char[][] getInputArray() {
    	int rowCount = LINES.length;
    	int colCount = LINES[0].length();

    	char[][] inputs = new char[rowCount][colCount];

    	for(int row = 0; row < rowCount; row++) {
    		for(int col = 0; col < colCount; col++) {
    			inputs[row][col] = LINES[row].charAt(col);
    		}
    	}

    	return inputs;
    }

    private static class Letter implements Comparable<Letter>{
        public char letter;
        public int count;

        public Letter(char letter) {
            this.letter = letter;
            this.count = 0;
        }

        @Override
        public boolean equals(Object other) {
            return (other instanceof Letter) && (((Letter)other).letter == this.letter);
        }

        public int compareTo(Letter other) {
            int countCompare = other.count - this.count;
            return (countCompare == 0) ? Character.valueOf(this.letter).compareTo(Character.valueOf(other.letter)) : countCompare;
        }
    }

    private static String readInput(String fileName) {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            return sb.toString();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }    
}