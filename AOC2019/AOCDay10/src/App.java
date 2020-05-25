import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class App {

    public LinkedList<int[]> createListOfAsteroidLocations() throws IOException {
        LinkedList<int[]> coordinates = new LinkedList<int[]>();
        Path path = Paths.get("C:/Users/aravd.000/Desktop/PuzzleInput.txt");
        try (BufferedReader br = Files.newBufferedReader(path)){
            String line;
            int x = 0, y = 0;
            while((line = br.readLine()) != null) {
                x = 0;
                char ch;
                while(x < line.length()){
                    ch = line.charAt(x);
                    if (ch == '#') {
                        coordinates.add(new int[]{x,y});
                    }
                    x++;
                }
                y++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coordinates;
    }

    public int numOfVisibleAsteroids(LinkedList<int[]> list) {
        int greatestNumOfAsteroids = Integer.MIN_VALUE;
        for (int[] tempStation : list) {
            int numOfAsteroids = 0;
            int x1 = tempStation[0];
            int y1 = tempStation[1];
            for (int i = 0; i < list.size(); i++) {
                int x2 = list.get(i)[0];
                int y2 = list.get(i)[1];
                if (!((x2 == x1) && (y2 == y1))) {
                    boolean interference = false;
                    for (int j = 0; j < i; j++) {
                        int xValueOfRockNearby = list.get(j)[0];
                        int yValueOfRockNearby = list.get(j)[1];
                        if (x1 == xValueOfRockNearby && y1 == yValueOfRockNearby){
                            continue;
                        }
                        else if (((x2-xValueOfRockNearby) == (y2-yValueOfRockNearby)) && ((xValueOfRockNearby-x1) == (yValueOfRockNearby-y1))){
                            interference = true;
                            break;
                        }
                        else if ((x1 == xValueOfRockNearby && x1 == x2) && (Math.abs(yValueOfRockNearby-y1) < Math.abs(y2-y1))){
                            interference = true;
                            break;
                        }
                        else if ((y1 == yValueOfRockNearby && y1 == y2) && (Math.abs(xValueOfRockNearby-x1) < Math.abs(x2-x1))){
                            interference = true;
                            break;
                        }
                        else{
                            interference = false;
                        }
                    }
                    if (interference == false){
                        numOfAsteroids++;
                    }
                }
            }
            if(numOfAsteroids > greatestNumOfAsteroids)
                greatestNumOfAsteroids = numOfAsteroids;
        }
        return greatestNumOfAsteroids;
    }
    public static void main(String[] args) throws Exception {
        App obj = new App();
        System.out.println(obj.numOfVisibleAsteroids(obj.createListOfAsteroidLocations()));
    }
}
