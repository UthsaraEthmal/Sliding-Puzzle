import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.mainMenu();
    }
    public  static void mainMenu(){
        boolean quit = false;
        System.out.println("\t'Welcome to The Sliding Puzzles Game'");
        int option;
        Scanner sc = new Scanner(System.in);
        while (!quit) {
            try {
                System.out.println("\nSelect a benchmark file.\n");
                System.out.println("1. Sample puzzle");
                System.out.println("2. puzzle_10.txt");
                System.out.println("3. puzzle_20.txt");
                System.out.println("4. puzzle_40.txt");
                System.out.println("5. puzzle_80.txt");
                System.out.println("6. puzzle_160.txt");
                System.out.println("7. puzzle_320.txt");
                System.out.println("8. puzzle_640.txt");
                System.out.println("9. puzzle_1280.txt");
                System.out.println("10. puzzle_2560.txt");
                System.out.println("11. For Mass puzzles");
                System.out.println("To exit press 0");
                System.out.print("Please enter the number of the file you want to use: ");

                option = sc.nextInt();

                String filename = ""; // Initialize filename with an empty string

                switch (option) {
                    case 1:
                        filename = "file.txt";
                        printSamplePuzzle("file.txt");
                        System.out.println("\nThe shortest path to the sample puzzle :\n");
                        break;

                    case 2:
                        filename = "puzzle_10.txt";
                        System.out.println("\nThe shortest path to the puzzle_10 \n");
                        break;

                    case 3:
                        filename = "puzzle_20.txt";
                        System.out.println("\nThe shortest path to the puzzle_20 \n");
                        break;

                    case 4:
                        filename = "puzzle_40.txt";
                        System.out.println("\nThe shortest path to the puzzle_40 \n");
                        break;

                    case 5:
                        filename = "puzzle_80.txt";
                        System.out.println("\nThe shortest path to the puzzle_80 \n");
                        break;

                    case 6:
                        filename = "puzzle_160.txt";
                        System.out.println("\nThe shortest path to the puzzle_160 \n");
                        break;

                    case 7:
                        filename = "puzzle_320.txt";
                        System.out.println("\nThe shortest path to the puzzle_320 \n");
                        break;

                    case 8:
                        filename = "puzzle_640.txt";
                        System.out.println("\nThe shortest path to the puzzle_640 \n");
                        break;

                    case 9:
                        filename = "puzzle_1280.txt";
                        System.out.println("\nThe shortest path to the puzzle_1280 \n");
                        break;

                    case 10:
                        filename = "puzzle_2560.txt";
                        System.out.println("\nThe shortest path to the puzzle_2560 \n");
                        break;

                    case 11:
                        handleMassPuzzles();
                        break;

                    case 0:
                        quit = true;
                        System.out.println("Thank You...!");
                        break;

                    default:
                        System.err.println("Invalid option. Using default file 'maze15_5.txt puzzle'.");
                        filename = "maze15_5.txt"; // Default file
                        printSamplePuzzle("maze15_5.txt");
                        System.out.println("\nThe shortest path to the puzzle :\n");
                }

                if (!quit && option != 11) {
                    if (option != 0) {
                        PuzzleGame pathFinder = new PuzzleGame(filename);
                        pathFinder.findPath(filename);
                    }
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                sc.next(); // Clear the scanner buffer
            }
        }
    }
    public static void handleMassPuzzles(){
        boolean quit = false;
        System.out.println("\t' The Maze Sliding Puzzles Game'");
        int option;
        Scanner sc = new Scanner(System.in);
        while (!quit) {
            try {
                System.out.println("\nSelect a maze file.\n");
                System.out.println("1. maze10_1.txt");
                System.out.println("2. maze15_1.txt");
                System.out.println("3. maze20_1.txt");
                System.out.println("4. maze25_1.txt");
                System.out.println("5. maze30_1.txt");
                System.out.println("Back To Main Menu press 0");
                System.out.print("Please enter an option: ");

                option = sc.nextInt();

                String filename = ""; // Initialize filename with an empty string

                switch (option) {
                    case 1:
                        filename = "maze10_1.txt";
                        System.out.println("\nThe shortest path to the  maze puzzle 10_1 :\n");
                        break;
                    case 2:
                        filename = "maze15_1.txt";
                        System.out.println("\nThe shortest path to the maze puzzle 15_1 \n");
                        break;
                    case 3:
                        filename = "maze20_1.txt";
                        System.out.println("\nThe shortest path to the maze puzzle 20_1 \n");
                        break;
                    case 4:
                        filename = "maze25_1.txt";
                        System.out.println("\nThe shortest path to the maze puzzle 25_1 \n");
                        break;
                    case 5:
                        filename = "maze30_1.txt";
                        System.out.println("\nThe shortest path to the maze puzzle 30_1 \n");
                        break;


                    case 0:
                        quit = true;
                        System.out.println("Thank You...!\nThe main menu ...");
                        mainMenu();
                        break;
                    default:
                        System.err.println("Invalid option. Using default file 'maze30_5.txt puzzle'.");
                        filename = "maze30_5.txt"; // Default file
                        printSamplePuzzle("maze30_5.txt");
                        System.out.println("\nThe shortest path to the puzzle :\n");
                }

                if (!quit) {

                    PuzzleGame pathFinder = new PuzzleGame(filename);
                    pathFinder.findPath(filename);
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                sc.next(); // Clear the scanner buffer
            }
        }
    }


    private static void printSamplePuzzle(String filename) {
        System.out.println("The puzzle : \n");
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line); // Print the line

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }
}
