package aoc2020.day11;

import aoc2020.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class Day11 {
    public static void run() {
        File file = new File("day11_2.txt");
        //HashMap<int[], Chair> hall = new HashMap<>(); //keep track of the processed objects
        HashSet<Chair> seats = new HashSet<>();
        ArrayList<String> input = Util.readFile(file);
        int[] grid = {input.get(0).length(), input.size()};
        for (int y = 0; y < input.size(); y++) {
            String s = input.get(y);
            for (int x = 0; x < s.length(); x++) {
                int[] loc = {x, y};
                if (s.charAt(x) == 'L') {
                    seats.add(new Chair(loc, seats, grid, false));
                } else if (s.charAt(x) == '#') {
                    seats.add(new Chair(loc, seats, grid, true));
                }
                //process the input file into whatever we need
            }
        }


        int i = 0;
        while (simulationRoundV2(seats) != 0) {
            i++;
            int res = 0;
            for(Chair c : seats) {
//            System.out.println(c);
//            System.out.println(c.getChairsInSight().size());
                if (c.isOccupied()) {
                    res++;
                }
            }
            System.out.println(res);

            if(i==2) {
                for(Chair c : seats) {
                    //System.out.println(c);
                }
                break;
            }

        }

        Chair test = Chair.getChairWithLocation(new int[] {6,0}, seats);
        HashSet<Chair> testSet = test.getChairsInSight();
        for (Chair c : testSet) {
            System.out.println(c);
        }

//        int res = 0;
//        for(Chair c : seats) {
//            if (c.isOccupied()) {
//                res++;
//            }
//        }
//        System.out.println("ANTWOORD: " + res);
    }

    private static int simulationRoundV1(HashSet<Chair> seats) {
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

    private static int simulationRoundV2(HashSet<Chair> seats) {
        HashSet<Chair> changeList = new HashSet<>();
        for(Chair c: seats) {
            HashSet<Chair> adjacent = c.getChairsInSight();
            if(c.isOccupied()) { //someone is sitting, swap occupation on rule
                if(Chair.adjacentOccupied(adjacent) >= 5) {
                    changeList.add(c);
                }

            } else { //chair is empty, swap occupation state if all neighbor seats are empty as well
                if (Chair.adjacentOccupied(adjacent) == 0) {
                    changeList.add(c);
                }
            }
        }

        for(Chair c : changeList) {
            c.swapOccupation();
        }
        return changeList.size();
    }
}
