import java.io.*;
import java.util.*;

public class PuzzleGame {
    private char[][]  puzzle;
    private boolean[][] checkVisitedNodes;
    private List<Node> nodes;
    private Node visitedNode;
    private Node adjacentNode;

    public PuzzleGame(String filename) {
        nodes = new ArrayList<>();
        readTextFile(filename); // Read the puzzle from the specified filename
    }


    public void findPath(String filename) {
        readTextFile(filename); // Read the puzzle from the specified filename
        startIndexOfS(); //function to find the 'S' which is the start point

        long startTime = System.nanoTime();

        int visitedRow = visitedNode.getRowIndex();
        int visitedCol = visitedNode.getColIndex();

        while (!nodes.isEmpty()) {
            visitedNode = nodes.get(0);
            visitedRow = visitedNode.getRowIndex();
            visitedCol = visitedNode.getColIndex();
            nodes.remove(0);

            if ( puzzle[visitedRow][visitedCol] == 'F') {// checking the target 'F'
                break;
            } else {//Directions
                move(-1, 0, "left");
                move(1, 0, "right");
                move(0, 1, "down");
                move(0, -1, "up");

            }

        }
        long currentEndTime = System.nanoTime();
        double difTime = currentEndTime - startTime;
        double nanoDifTime = (double) difTime / 1_000_000.0;

        shortestPathPrint();//This method used to print the shortest path  'S' to 'F'
        System.out.println("\nPerformance time = " + nanoDifTime + " microseconds");
    }

    private void readTextFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            puzzle = new char[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                puzzle[i] = lines.get(i).toCharArray();
            }
            checkVisitedNodes = new boolean[ puzzle.length][ puzzle[0].length];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startIndexOfS() {//Searching for the Starting Index
        for (int i = 0; i <  puzzle.length; i++) {
            for (int j = 0; j <  puzzle[i].length; j++) {
                if ( puzzle[i][j] == 'S') {
                    visitedNode = new Node(i, j);
                    nodes.add(visitedNode);
                    return;
                }
            }
        }
        System.out.println("Start node 'S' not found ");
    }

    private void move(int x, int y, String direction) {
        int row = visitedNode.getRowIndex();
        int col = visitedNode.getColIndex();

        while (true) {
            row += y;
            col += x;

            if (row >= 0 && col >= 0 && row <  puzzle.length && col <  puzzle[0].length &&  puzzle[row][col] != '0') {//checking sliding boundaries
                if (! checkVisitedNodes[row][col]) {
                    if ( puzzle[row][col] == 'F') {
                        adjacentNode = new Node(row, col);
                        nodes.add(0, adjacentNode);
                        checkVisitedNodes[row][col] = true;
                        adjacentNode.setDirection(direction);
                        adjacentNode.setPrevious(visitedNode);
                        return;
                    } else {
                        if ((row + y < 0 || col + x < 0) || (row + y >=  puzzle.length || col + x >=  puzzle[row].length)//checking sliding boundaries
                                || ( puzzle[row + y][col + x] == '0')) {
                            adjacentNode = new Node(row, col);
                            nodes.add(adjacentNode);
                            checkVisitedNodes[row][col] = true;
                            adjacentNode.setDirection(direction);
                            adjacentNode.setPrevious(visitedNode);
                            return;
                        }
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }
    private void shortestPathPrint() {
        Node current = visitedNode;
        List<String> path = new ArrayList<>();
        while (current != null) {
            String direction = current.getDirection();

            if (direction == null) {       // If direction is null, change it to "Start at"
                direction = "Start at";
                path.add(direction + " (" + (current.getColIndex() + 1) + "," + (current.getRowIndex() + 1) + ")");
            } else {
                path.add("Move " + direction + " to (" + (current.getColIndex() + 1) + "," + (current.getRowIndex() + 1) + ")");
            }
            current = current.getPrevious();
        }


        for (int i = path.size() -1; i >= 0; i--) {//printing the number of steps
            System.out.println((path.size() - i) + ". " + path.get(i));
        }
        System.out.println((path.size() + 1) + ". Done!");//the last step


    }


}
