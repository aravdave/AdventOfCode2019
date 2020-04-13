//Replica of Boris Kurikin's Solution on GitHub
//I wrote this to practice and to learn more efficient techniques.
//I rather not complete this puzzle with a 20,000 by 20,000 2D array

import java.io.*;
import java.util.*;

public class IntersectionWires {
    //Creating a BufferReader object
    public static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static int[] getMove (char c) {
        switch(c) {
            case 'R' : return new int[] {1, 0};
            case 'L' : return new int[] {-1, 0};
            case 'U' : return new int[] {0, 1};
            case 'D' : return new int[] {0, -1};
        }
        return null;
    }

    public static void main(String[] args) throws IOException {

        //Creating HashMap and String array
        HashMap<String, Integer> wire1 = new HashMap<String, Integer>();
        String[] puzzle = read.readLine().split(",");

        //Creating integers for determing the closest intersection and the shortest wire
        int closestIntersection = Integer.MAX_VALUE;
        int shortestWire = Integer.MAX_VALUE;

        int x = 0; 
        int y = 0; 
        int d = 0;

        for (int a = 0; a < puzzle.length; a++){
            int[] move = getMove(puzzle[a].charAt(0));
            int distance = Integer.parseInt(puzzle[a].substring(1));
            for (int b = 1; b <= distance; b++) {
                int newX = x + move[0];
                int newY = y + move[1];
                wire1.put(newX + "-" + newY, ++d);
                x = newX;
                y = newY;
            }            
        }
        puzzle = read.readLine().split(",");
        x = y = d = 0;
        for (int c = 0; c < puzzle.length; c++){
            int[] move = getMove(puzzle[c].charAt(0));
            int distance = Integer.parseInt(puzzle[c].substring(1));
            for (int i = 1; i <= distance; i++){
                int newX = x + move[0];
                int newY = y + move[1];
                d++;

                if(wire1.containsKey(newX + "-" + newY)) {
                    closestIntersection = Math.min(closestIntersection, (int) Math.abs(newX) + (int) Math.abs(newY));
                    shortestWire = Math.min(shortestWire, wire1.get(newX + "-" + newY) + d);
                }
                x = newX;
                y = newY;
            }
        }
        System.out.println(closestIntersection);
        System.out.println(shortestWire);;
    }
}