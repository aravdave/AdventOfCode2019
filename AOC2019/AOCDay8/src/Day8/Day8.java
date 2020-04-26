package Day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;

public class Day8 {
    public static int checkIfCorrupted() throws IOException{
        Path path = Paths.get("C:/Users/aravd.000/Desktop/PixelData.txt");
        int answer = -1;
        try(BufferedReader br = Files.newBufferedReader(path)) {
            int ch;
            int place = 1;
            int lowestNumOfZero = Integer.MAX_VALUE;
            int numOfZero = 0;
            int numOfOne = 0;
            int numOfTwo = 0;
            while((ch = br.read()) != -1) {
                int number = Character.getNumericValue((char)ch);
                switch (number) {
                    case 0:
                        numOfZero++;
                        break;
                    case 1:
                        numOfOne++;
                        break;
                    case 2:
                        numOfTwo++;
                        break;
                }
                if (place == (25*6)) {
                    if (numOfZero < lowestNumOfZero) {
                        lowestNumOfZero = numOfZero;
                        answer = (numOfOne * numOfTwo);
                    }
                    place = 1;
                    numOfZero = 0;
                    numOfOne = 0;
                    numOfTwo = 0;
                }
                else{
                    place++;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return answer;
    }
    public static void main(String[] args) throws Exception {
        System.out.println(checkIfCorrupted());
    }
}