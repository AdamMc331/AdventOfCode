import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

public class AdventThree {
    private static final String INPUT_FILE = "input.txt";
    private static final String INPUT = readInput(INPUT_FILE);
    private static final String[] LINES = INPUT.split("\n");

    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    private static void partOne() {
        String[][] allInputs = getInputs();

        int possibleCount = 0;

        for(String[] line : allInputs) {
            int[] sides = new int[] {
                Integer.parseInt(line[0]),
                Integer.parseInt(line[1]),
                Integer.parseInt(line[2])
            };

            Arrays.sort(sides);

            if(sides[0] + sides[1] > sides[2]) {
                possibleCount++;
            }
        }

        System.out.println("There are " + possibleCount + " possible triangles in part one.");
    } 

    private static void partTwo() {
        String[][] allInputs = getInputs();

        int possibleCount = 0;

        for(int row = 0; row < allInputs.length; row += 3) {
            for(int col = 0; col < 3; col++) {
                int[] sides = new int[] {
                    Integer.parseInt(allInputs[row][col]),
                    Integer.parseInt(allInputs[row + 1][col]),
                    Integer.parseInt(allInputs[row + 2][col])
                };

                Arrays.sort(sides);
                
                if(sides[0] + sides[1] > sides[2]) {
                    possibleCount++;
                }
            }
        }

        System.out.println("There are " + possibleCount + " possible triangles in part two.");
    }

    private static String[][] getInputs() {
        String[][] allInputs = new String[LINES.length][3];
        for(int i = 0; i < LINES.length; i++) {
            allInputs[i] = LINES[i].replaceAll(" +", " ").trim().split(" ");
        }

        return allInputs;
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