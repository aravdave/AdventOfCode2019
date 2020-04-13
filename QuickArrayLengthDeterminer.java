import java.io.*;
import java.util.*;

public class QuickArrayLengthDeterminer {

    public static int rightNum = 0;
    public static int leftNum = 0;
    public static int upNum = 0;
    public static int downNum = 0;
    public static boolean keepGoing = true;
    public static void main(String[] args) throws FileNotFoundException {
        File puzzleInput = new File("IntersectingWiresProgram.txt");
        Scanner fileReader = new Scanner(puzzleInput);
      

        fileReader.useDelimiter(",");
        do {
            String move = fileReader.next();
            if (move.contains("\n")) {
                System.out.println("End");
                keepGoing = false;
            }
            else {
                if (move.contains("R")) {
                    int dist = Integer.parseInt(move.substring(move.indexOf('R')+1));
                    rightNum += dist;
                }
                else if (move.contains("L")) {
                    int dist = Integer.parseInt(move.substring(move.indexOf('L')+1));
                    leftNum += dist;
                }
                else if (move.contains("U")) {
                    int dist = Integer.parseInt(move.substring(move.indexOf('U')+1));
                    upNum += dist;
                }
                else {
                    int dist = Integer.parseInt(move.substring(move.indexOf('D')+1));
                    downNum += dist;
                }
            }
        } while(keepGoing);
        System.out.println("Total distance the wire travels to the right:" + rightNum);
        System.out.println("Total distance the wire travels to the left:" + leftNum);
        System.out.println("Total distance the wire travels to the down:" + downNum);
        System.out.println("Total distance the wire travels to the up:" + upNum);
        fileReader.close();
    }
}