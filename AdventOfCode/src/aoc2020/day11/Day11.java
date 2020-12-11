package aoc2020.day11;

import aoc2020.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Day11 {
    public static void run() {
        File file = new File("day11.txt");
        //HashMap<int[], Chair> hall = new HashMap<>(); //keep track of the processed objects
        HashSet<Chair> seats = new HashSet<Chair>();
        ArrayList<String> input = Util.readFile(file);
        for (int y = 0; y < input.size(); y++) {
            String s = input.get(y);
            for (int x = 0; x < s.length(); x++) {
                int[] loc = {x, y};
                if (s.charAt(x) == 'L') {
                    seats.add(new Chair(loc, seats));
                }
                //process the input file into whatever we need
            }
        }

        while (simulationRound(seats) != 0) {
        }

        int res = 0;
        for(Chair c : seats) {
            if (c.isOccupied()) {
                res++;
            }
        }
        System.out.println(res);

//        for (int i = 0; i < 10; i++) {
//            System.out.println(i + ": " + simulationRound(seats));
//
//        }
    }

    private static int simulationRound(HashSet<Chair> seats) {
        HashSet<Chair> changeList = new HashSet<>();
        for(Chair c : seats) {
            if(c.isOccupied()) { //someone is sitting in the seat, swap occupation if there are 4 or more neighbors
                if(c.neighborsOccupied() >= 4) {
                    changeList.add(c);
                }
            } else { // no one is sitting in the seat, swap occupation state if there are all its neighbors are empty seats as well
                if(c.neighborsOccupied() == 0) {
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
