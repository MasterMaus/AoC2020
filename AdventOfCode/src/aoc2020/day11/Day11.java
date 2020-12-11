package aoc2020.day11;

import aoc2020.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day11 {
    public static void run() {
        File file = new File("day11_2.txt");
        HashMap<int[], Chair> hall = new HashMap<>(); //keep track of the processed objects
        HashSet<Chair> seats;
        ArrayList<String> input = Util.readFile(file);
        for (int y = 0; y < input.size(); y++) {
            String s = input.get(y);
            for (int x = 0; x < s.length(); x++) {
                int[] loc = {x, y};
                if (s.charAt(x) == 'L') {
                    hall.put(loc, new Chair(loc, hall));
                } else if (s.charAt(x) == '.') {
                    hall.put(loc, null); // place a null to location if the location is a floor
                }
                //process the input file into whatever we need
            }
        }
        seats = new HashSet<>(hall.values());
        seats.remove(null);
        int res = 0;
//        for (int i = 0; i < 50; i++) {
//            //System.out.println(i + ": " + simulationRound(seats));
//        }
    }

    private static int simulationRound(HashSet<Chair> seats) {
        HashSet<Chair> changeList = new HashSet<>();
        for(Chair c : seats) {
            if(c.isOccupied()) { //someone is sitting in the seat, swap occupation if there are 4 or more neighbors
                if(c.neighborsOccupied() >= 4) {
                    changeList.add(c);
                }
            } else { // no one is sitting in the seat, swap occupation state if there are not yet 4 or more neighbors
                if(c.neighborsOccupied() < 4) {
                    changeList.add(c);
                }
            }
        }

        for (Chair c : changeList) {
            c.swapOccupation();
        }

        return changeList.size();
    }
}
