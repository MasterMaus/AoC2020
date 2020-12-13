package aoc2020.day13;


import aoc2020.utilities.CommonFunctions;
import aoc2020.utilities.InputLoader;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class Day13 {
    public static void run() {
        ArrayList<String> input = InputLoader.asList("input/day13.txt");
        //part1(input);
        part2(input);
//        int res = 0; // STORE RESULT
//        System.out.println(res); //OUTPUT RESULT
    }

    private static void part1(ArrayList<String> input) {
        int timestamp;
        String[] busses;
        TreeSet<Integer> timetable = new TreeSet<>();
        TreeMap<Integer, Integer> earliestDeparture = new TreeMap<>(); // <BUS_ID , TIME TO DEPART>
        timestamp = Integer.parseInt(input.get(0));
        busses = input.get(1).replace(",x", "").split(",");
        for(String s : busses) {
            timetable.add(Integer.parseInt(s));
        }
        for (int i : timetable) {
            earliestDeparture.put(i, i-(timestamp%i));
        }
        System.out.println(earliestDeparture.toString());
    }

    private static void part2(ArrayList<String> input) {
        String[] departureConstraints = input.get(1).split(",");
        TreeMap<Integer, Integer> offset = new TreeMap<>(); //<ID, offset>
        for (int i = 0; i< departureConstraints.length; i++) {
            if (!departureConstraints[i].equals("x")) {
                offset.put(Integer.parseInt(departureConstraints[i]), i);
            }
        }

        System.out.println(getNextCondition(offset));
    }

    private static long getNextCondition(TreeMap<Integer, Integer> offset) {
        int highestID = offset.lastKey();
        //long timestamp = highestID - offset.get(highestID);
        long timestamp = 100000000000010L;
        while (true) {
            for(int i : offset.keySet()) {
                if((timestamp + offset.get(i))%i != 0) {
                    break;
                }
                if(i==highestID) {
                    return timestamp;
                }
            }
            timestamp += highestID;
        }
    }
}
