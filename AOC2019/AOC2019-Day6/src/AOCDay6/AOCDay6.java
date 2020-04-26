package AOCDay6;
import java.io.*;
import java.util.*;
import java.nio.file.*;

public class AOCDay6 {
    public AOCDay6() throws IOException {
        Map<String, List<String>> orbits = new HashMap<>();
        List<String> input = new ArrayList<>();

        //Creating an ArrayList with orbit info for every planet
        Path path = Paths.get("C:/Users/aravd.000/Desktop/PuzzleInput.txt");
        try(BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while((line = br.readLine()) != null) {
                input.add(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        //Filling a HashMap with planets and the planets that orbit them
        for(String orbitInfo : input) {
            String[] whatIsGettingOrbitedByWhat = orbitInfo.split("\\)");
            if(orbits.containsKey(whatIsGettingOrbitedByWhat[0])) {
                List<String> planetsThatOrbit = orbits.get(whatIsGettingOrbitedByWhat[0]);
                planetsThatOrbit.add(whatIsGettingOrbitedByWhat[1]);
                orbits.put(whatIsGettingOrbitedByWhat[0], planetsThatOrbit);
            }
            else {
                List<String> planetsThatOrbit = new ArrayList<>();
                planetsThatOrbit.add(whatIsGettingOrbitedByWhat[1]);
                orbits.put(whatIsGettingOrbitedByWhat[0], planetsThatOrbit);
            }
        }

        int totalOrbits = getTotalOrbits("COM", orbits, 0);
        System.out.println("Part 1: " + totalOrbits);

        //Part 2
        List<String> pathToYou = pathToPerson("YOU", orbits);
        List<String> pathToSanta = pathToPerson("SAN", orbits);
        
        while(pathToYou.get(0).equals(pathToSanta.get(0))) {
            pathToSanta.remove(0);
            pathToYou.remove(0);
        }
        
        System.out.println("Part 2: " + (pathToYou.size()+pathToSanta.size()));

}

    public int getTotalOrbits(String planet, Map<String, List<String>> planetsWithOrbitingPlanets, int numberOfOrbiting) {
        int totalOrbits = numberOfOrbiting;
        if(planetsWithOrbitingPlanets.containsKey(planet)) {
            for(String orbiting : planetsWithOrbitingPlanets.get(planet)) {
                totalOrbits += getTotalOrbits(orbiting, planetsWithOrbitingPlanets, numberOfOrbiting+1);
            }
        }
        return totalOrbits;
    }

    public List<String> pathToPerson(String toObject, Map<String, List<String>> planetsWithOrbitingPlanets) {
        List<String> path = new ArrayList<>();
        if(!toObject.equals("COM")) {
            for(String orbitCenter : planetsWithOrbitingPlanets.keySet()) {
                if(planetsWithOrbitingPlanets.get(orbitCenter).contains(toObject)) {
                    path = pathToPerson(orbitCenter, planetsWithOrbitingPlanets);
                    path.add(orbitCenter);
                }
            }
        }
        return path;
    }

    public static void main(String[] args) throws Exception {
        new AOCDay6();
    }
}