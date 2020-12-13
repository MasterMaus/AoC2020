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
        TreeMap<Long, Integer> offsetMap = new TreeMap<>(); //<ID, offset>
        int start = Integer.parseInt(departureConstraints[0]);
        for (int i = 1; i< departureConstraints.length; i++) {
            if (!departureConstraints[i].equals("x")) {
                offsetMap.put(Long.parseLong(departureConstraints[i]), i);
            }
        }

        long multiple = 1;
        long increment = 1;
        for(long id : offsetMap.keySet()) {
            multiple = getNextMultiple(start, multiple, increment, id, offsetMap.get(id));
            increment *= id;
        }

        //System.out.println(getNextCondition(offset));
    }

    private static long getNextMultiple(long first, long multiple, long increment, long second, int offset) {
        //find integer x where first * x == (second * (int) y) + offset
        long x = multiple;


        long timestamp = first * x + offset;
        long y = timestamp/second;

        while (y*second != timestamp) {
            x+=increment;
            timestamp = first * x + offset;
            y = timestamp/second;
        }
        System.out.println(timestamp-offset);
        return x;
    }
}
