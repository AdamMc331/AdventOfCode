import java.io.*;
import java.util.*;

public class AdventFour {
	private static final String INPUT_FILE = "input.txt";
    private static final String INPUT = readInput(INPUT_FILE);
    private static final String[] LINES = INPUT.split("\n");

    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    private static void partOne() {
        int total = 0;

        Encryption[] encryptions = getEncryptions();

        for(Encryption encryption : encryptions) {
            List<Letter> letterList = encryption.getLetters();

            Collections.sort(letterList);
            List<Letter> topFive = letterList.subList(0, 5);
            String topFiveString = "";
            for(Letter letter : topFive) {
                topFiveString += letter.letter;
            }

            if(topFiveString.equals(encryption.checkSum)) {
                total += encryption.sector;
            }
        }

        System.out.println("Total is: " + total);
    }

    private static void partTwo() {
        int total = 0;

        Encryption[] encryptions = getEncryptions();

        for(Encryption encryption : encryptions) {
            String result = "";
            for(char c : encryption.letters.toCharArray()) {
                char tmp = c;
                if(tmp == '-') {
                    result += ' ';
                } else {
                    for(int i = 0; i < encryption.sector; i++) {
                        tmp = getNext(tmp);
                    }
                    result += tmp;
                }
            }

            if(result.equals("northpole object storage")) {
                System.out.println("North pole: " + encryption.sector);
                return;
            }
        }
    }

    private static Encryption[] getEncryptions() {
        Encryption[] result = new Encryption[LINES.length];

        for(int i = 0; i < LINES.length; i++) {
            result[i] = new Encryption(LINES[i]);
        }

        return result;
    }

    private static char getNext(char ch) {
        return (ch < 'z') ? ((char)(ch + 1)) : 'a';
    }

    private static class Encryption {
        public String letters;
        public int sector;
        public String checkSum;

        public Encryption(String line) {
            int lastHyphen = line.lastIndexOf('-');
            this.letters = line.substring(0, lastHyphen);

            String sectorChecksum = line.substring(lastHyphen + 1);
            int openBracket = sectorChecksum.indexOf('[');
            this.sector = Integer.parseInt(sectorChecksum.substring(0, openBracket));
            this.checkSum = sectorChecksum.substring(openBracket + 1, sectorChecksum.length() - 1);
        }

        public List<Letter> getLetters() {
            List<Letter> letterList = new ArrayList<Letter>();

            // Don't use dashes
            for(char letter : letters.replace("-", "").toCharArray()) {
                Letter letterObj = new Letter(letter);
                if(letterList.contains(letterObj)) {
                    letterObj = letterList.get(letterList.indexOf(letterObj));
                } else {
                    letterList.add(letterObj);
                }

                letterObj.count++;
            }

            return letterList;
        }
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