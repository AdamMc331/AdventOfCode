import java.io.BufferedReader;
import java.io.FileReader;

public class AdventTwo {
    private static final String INPUT = readInput();
    private static final String[] LINES = INPUT.split("\n");
    private static final char UP = 'U';
    private static final char DOWN = 'D';
    private static final char LEFT = 'L';
    private static final char RIGHT = 'R';

    private static final char[][] KEYPAD_ONE = new char[][] {
        {'1', '2', '3'},
        {'4', '5', '6'},
        {'7', '8', '9'}
    };

    private static final char[][] KEYPAD_TWO = new char[][] {
        {' ', ' ', '1', ' ', ' '},
        {' ', '2', '3', '4', ' '},
        {'5', '6', '7', '8', '9'},
        {' ', 'A', 'B', 'C', ' '},
        {' ', ' ', 'D', ' ', ' '}
    };

    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    private static void partOne() {
        // Initialize
        int row = 1;
        int col = 1;

        String code = "";

        for(String line : LINES) {
            for(char ch : line.toCharArray()) {
                switch(ch) {
                    case UP:
                        if(row > 0) {
                            row--;
                        }
                        break;
                    case DOWN:
                        if(row < 2) {
                            row++;
                        }
                        break;
                    case LEFT:
                        if(col > 0) {
                            col--;
                        }
                        break;
                    case RIGHT:
                        if(col < 2) {
                            col++;
                        }
                        break;
                }
            }

            code += KEYPAD_ONE[row][col];
        }

        System.out.println("Code part one: " + code);
    }

    private static void partTwo() {
        int row = 2;
        int col = 0;

        String code = "";

        for(String line : LINES) {
            for(char ch : line.toCharArray()) {
                switch(ch) {
                    case UP:
                        if((row > 0) && (KEYPAD_TWO[row - 1][col] != ' ')) {
                            row--;
                        }
                        break;
                    case DOWN:
                        if((row < 4) && (KEYPAD_TWO[row + 1][col] != ' ')) {
                            row++;
                        }
                        break;
                    case LEFT:
                        if((col > 0) && (KEYPAD_TWO[row][col - 1] != ' ')) {
                            col--;
                        }
                        break;
                    case RIGHT:
                        if((col < 4) && (KEYPAD_TWO[row][col + 1] != ' ')) {
                            col++;
                        }
                        break;
                }
            }

            code += KEYPAD_TWO[row][col];
        }

        System.out.println("Code part two: " + code);
    }

    private static String readInput() {
        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
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