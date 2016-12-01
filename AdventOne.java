import java.util.Set;
import java.util.HashSet;

public class AdventOne {
    private static final String INPUT = "R3, R1, R4, L4, R3, R1, R1, L3, L5, L5, L3, R1, R4, L2, L1, R3, L3, R2, R1, R1, L5, L2, L1, R2, L4, R1, L2, L4, R2, R2, L2, L4, L3, R1, R4, R3, L1, R1, L5, R4, L2, R185, L2, R4, R49, L3, L4, R5, R1, R1, L1, L1, R2, L1, L4, R4, R5, R4, L3, L5, R1, R71, L1, R1, R186, L5, L2, R5, R4, R1, L5, L2, R3, R2, R5, R5, R4, R1, R4, R2, L1, R4, L1, L4, L5, L4, R4, R5, R1, L2, L4, L1, L5, L3, L5, R2, L5, R4, L4, R3, R3, R1, R4, L1, L2, R2, L1, R4, R2, R2, R5, R2, R5, L1, R1, L4, R5, R4, R2, R4, L5, R3, R2, R5, R3, L3, L5, L4, L3, L2, L2, R3, R2, L1, L1, L5, R1, L3, R3, R4, R5, L3, L5, R1, L3, L5, L5, L2, R1, L3, L1, L3, R4, L1, R3, L2, L2, R3, R3, R4, R4, R1, L4, R1, L5";
    private static final String RIGHT = "R";
    private static final String LEFT = "L";

    private static final int NORTH = 1;
    private static final int EAST = 2;
    private static final int SOUTH = 3;
    private static final int WEST = 4;

    private static final String inputString = INPUT.replace(",", "");
    private static final String[] instructions = inputString.split(" ");

    private static int currentDirection;
    private static int currentX;
    private static int currentY;

    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partOne() {
        currentDirection = NORTH;
        currentX = 0;
        currentY = 0;

        for(String instruction : instructions) {
            String turn = instruction.substring(0, 1);
            int count = Integer.valueOf(instruction.substring(1));

            changeDirection(turn);

            switch(currentDirection) {
                case NORTH:
                    currentY += count;
                    break;
                case EAST:
                    currentX += count;
                    break;
                case SOUTH:
                    currentY -= count;
                    break;
                case WEST:
                    currentX -= count;
                    break;
            }
        }

        // The number of blocks we've moved north/south plus the number of blocks moved east/west is how far away we are.
        int totalBlocks = Math.abs(currentX) + Math.abs(currentY);
        System.out.println("Total blocks part one: " + totalBlocks);
    }

    public static void partTwo() {
        currentDirection = NORTH;
        currentX = 0;
        currentY = 0;

        Set<Point> points = new HashSet<Point>();
        Point origin = new Point(currentX, currentY);
        points.add(origin);

        for(String instruction : instructions) {
            String turn = instruction.substring(0, 1);
            int count = Integer.valueOf(instruction.substring(1));

            changeDirection(turn);

            for(int i = 0; i < count; i++) {
                switch(currentDirection) {
                    case NORTH:
                        currentY++;
                        break;
                    case EAST:
                        currentX++;
                        break;
                    case SOUTH:
                        currentY--;
                        break;
                    case WEST:
                        currentX--;
                        break;
                }

                Point point = new Point(currentX, currentY);
                if(points.contains(point)) {
                    int totalBlocks = Math.abs(point.x) + Math.abs(point.y);
                    System.out.println("Total blocks part two: " + totalBlocks);
                    return;
                } else {
                    points.add(point);
                }
            }
        }
    }

    private static void changeDirection(String turn) {
        // Move to point in right direction
        switch(turn) {
            case RIGHT:
                currentDirection = (currentDirection < 4) ? (currentDirection + 1) : NORTH;
                break;
            case LEFT:
                currentDirection = (currentDirection > 1) ? (currentDirection - 1) : WEST;
                break;
        }
    }

    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            // Just hash point string, guaranteed to be unique
            String pointString = "(" + x + "," + y + ")";
            return pointString.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return ((obj instanceof Point) && (((Point)obj).x == this.x) && (((Point)obj).y == this.y));
        }
    }
}