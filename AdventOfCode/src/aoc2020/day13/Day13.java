package aoc2020.day13;


import aoc2020.utilities.InputLoader;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class Day13 {
    public static void run() {
        int timestamp;
        String[] busses;
        TreeSet<Integer> timetable = new TreeSet<>();
        TreeMap<Integer, Integer> earliestDeparture = new TreeMap<>(); // <BUS_ID , TIME TO DEPART>
        ArrayList<String> input = InputLoader.asList("input/day13.txt");
        timestamp = Integer.parseInt(input.get(0));
        busses = input.get(1).replace(",x", "").split(",");
        for(String s : busses) {
            timetable.add(Integer.parseInt(s));
        }
        for (int i : timetable) {
            earliestDeparture.put(i, i-(timestamp%i));
        }
        System.out.println(earliestDeparture.toString());
//        int res = 0; // STORE RESULT
//        System.out.println(res); //OUTPUT RESULT
    }
}
