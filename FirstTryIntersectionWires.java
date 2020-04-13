/*  Arav Dave
    04/12/2020
    Day 3 Puzzle 1
*/

import javax.swing.JFrame;
import java.io.*;
import java.awt.*;
import java.util.*;

public class FirstTryIntersectionWires {

    static Scanner keyboard = new Scanner(System.in);
    static char[][] wire1 = new char[20000][20000];
    static char[][] wire2 = new char[20000][20000];
    static int wire1Row = 10000, wire1Column = 10000;
    static int wire2Row = 10000, wire2Column = 10000;
    static String startString = "";

    public static void main(String[] args) throws FileNotFoundException {

        //Declaring variables so I can 
        boolean firstWire = true;
        int numOfTimesScanRuns = 0;

        Scanner readInput = new Scanner(validFile(getPath()));
        Scanner readInput2 = new Scanner(validFile(getPath()));
        readInput.useDelimiter(",");
        readInput2.useDelimiter(",|\\n");

        do {
            String move = readInput.next();
            numOfTimesScanRuns++;
            if (move.contains("\n")) {
                firstWire =  false;
                startString = move;
            }
            else {
                if (move.contains("R")) {
                    int[] newLocation = right(wire1Row, wire1Column, wire1, move);
                    wire1Row = newLocation[0];
                    wire1Column = newLocation[1];
                }
                else if (move.contains("L")) {
                    int[] newLocation = left(wire1Row, wire1Column, wire1, move);
                    wire1Row = newLocation[0];
                    wire1Column = newLocation[1];
                }
                else if (move.contains("U")) {
                    int[] newLocation = up(wire1Row, wire1Column, wire1, move);
                    wire1Row = newLocation[0];
                    wire1Column = newLocation[1];
                }
                else {
                    int[] newLocation = down(wire1Row, wire1Column, wire1, move);
                    wire1Row = newLocation[0];
                    wire1Column = newLocation[1];
                }
            }
        } while(firstWire);

        int[] lastWire1 = treatLastWire1Input(startString);
        wire1Row = lastWire1[0];
        wire1Column = lastWire1[1];

        for(int x = 1; x <= numOfTimesScanRuns; x++){
            readInput2.next();
        }
        do {
            String move = readInput2.next();      
            if (move.contains("R")) {
                int[] newLocation = right(wire2Row, wire2Column, wire2, move);
                wire2Row = newLocation[0];
                wire2Column = newLocation[1];                
            }
            else if (move.contains("L")) {
                int[] newLocation = left(wire2Row, wire2Column, wire2, move);
                wire2Row = newLocation[0];
                wire2Column = newLocation[1];
            }
            else if (move.contains("U")) {
                int[] newLocation = up(wire2Row, wire2Column, wire2, move);
                wire2Row = newLocation[0];
                wire2Column = newLocation[1];
            }
            else {
                int[] newLocation = down(wire2Row, wire2Column, wire2, move);
                wire2Row = newLocation[0];
                wire2Column = newLocation[1];
            }
        } while(readInput2.hasNext());

        ArrayList<int[]> intersectPoints = compare(wire1, wire2);
        int manhattanDistanceSmallest = 0;
        for (int[] point : intersectPoints) {
            int manhattanDistance = Math.abs(point[0]-10000) + Math.abs(point[1]-10000);
            if (manhattanDistance < manhattanDistanceSmallest)
                manhattanDistanceSmallest = manhattanDistance;
        }
        readInput.close();
        readInput2.close();
    }




    public static String getPath() {
        //Calls the file with the puzzle input
        System.out.println("Please choose the .txt file with your puzzle input.");
        FileDialog pickFile = new FileDialog(new JFrame());
        pickFile.setVisible(true);
        return(pickFile.getDirectory() + pickFile.getFile());
    }
    public static File validFile(String initialFilePath) {
        String filePath = initialFilePath;
        boolean keepTrying = true;
        do{
            //checks if the file is valid and returns a valid file
            if (filePath.equals(null) || !(filePath.contains(".txt"))){
                System.out.println("You inputed an invalid file.");

                //Asking if the user wants to try again selecting two .txt files
                System.out.println("\nDo you want to try again? Enter \"No\" if you do not. Otherwise, enter any key to try again.");
                String input = keyboard.nextLine().toLowerCase();
                if (input.contains("no")) {
                    System.exit(0);
                }
                System.out.println("\nRemember: Select one .txt files. No other file format can be read.");
                filePath = getPath();
            }
            else {
                keepTrying = false;
            }
        } while (keepTrying);

        //Returning new file object
        return(new File(filePath));
    }
    public static int[] right(int row, int column, char[][] array, String command) {
        char[][] localArray = array;
        int dist = Integer.parseInt(command.substring(command.indexOf('R')+1));
        for (int i = column+1; i <= column+dist; i++) {
            localArray[row][i] = 'O';
        }
        int[] coordinates = {row, (column+dist)};
        return coordinates;
    } 
    public static int[] left(int row, int column, char[][] array, String command) {
        char[][] localArray = array;
        int dist = Integer.parseInt(command.substring(command.indexOf('L')+1));
        for (int i = column-1; i >= column-dist; i--) {
            localArray[row][i] = 'O';
        }
        int[] coordinates = {row, (column-dist)};
        return coordinates;
    }
    public static int[] up(int row, int column, char[][] array, String command) {
        char[][] localArray = array;
        int dist = Integer.parseInt(command.substring(command.indexOf('U')+1));
        for (int i = row-1; i >= row-dist; i--) {
            localArray[i][column] = 'O';
        }
        int[] coordinates = {row-dist, column};
        return coordinates;
    }
    public static int[] down(int row, int column, char[][] array, String command) {
        char[][] localArray = array;
        int dist = Integer.parseInt(command.substring(command.indexOf('D')+1));
        for (int i = row+1; i <= row+dist; i++) {
            localArray[i][column] = 'O';
        }
        int[] coordinates = {row+dist, column};
        return coordinates;
    }
    public static ArrayList<int[]> compare(char[][] firstArray, char[][] secondArray){
        ArrayList<int[]> intersectPoints = new ArrayList<int[]>();
        for(int a = 0; a < firstArray.length; a++) {
            for(int b = 0; b < firstArray[0].length; b++) {
                if (firstArray[a][b] == 'O' && secondArray[a][b] == 'O') {
                    intersectPoints.add(new int[]{a, b});
                }
            }
        }
        return intersectPoints;
    }
    public static int[] treatLastWire1Input(String move) {
        int indexOf1stLetter = 0;
        for (int i = 0; i < move.length(); i++){
            if(Character.isUpperCase(move.charAt(i))) {
                indexOf1stLetter = i;
                break;
            }
        }
        move = move.substring((indexOf1stLetter+1));
        if (move.contains("R")) {
            int[] newLocation = right(wire1Row, wire1Column, wire1, move);
            return newLocation;
        }
        else if (move.contains("L")) {
            int[] newLocation = left(wire1Row, wire1Column, wire1, move);
            return newLocation;
        }
        else if (move.contains("U")) {
            int[] newLocation = up(wire1Row, wire1Column, wire1, move);
            return newLocation;
        }
        else {
            int[] newLocation = down(wire1Row, wire1Column, wire1, move);
            return newLocation;
        }
    }
}