package aoc2020.day10;

import aoc2020.utilities.CommonFunctions;
import aoc2020.utilities.InputLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Day10 {
    public static void run() {
        System.out.println("Solutions day 10:");
        TreeSet<Integer> data = new TreeSet<>(); //keep track of the processed objects
        ArrayList<String> input = InputLoader.asList("day10.txt");
        for (String s: input) {
            data.add(Integer.parseInt(s));
        }
        data.add(0);
        int totalJolts = CommonFunctions.getMax(data)+3;
        HashMap<Integer, Long> combinations = new HashMap<>();

        for (int i : data) {
            calculateCombinations(combinations, i);
        }
        System.out.println("SOLUTION = " + combinations.get(totalJolts-3));
    }

    private static void part1(HashSet<Integer> data) {
        int dif1 = 0;
        int dif3 = 1;
        int currentJolts = 0;

        for(int i = 0; i<data.size(); i++) {
            if(data.contains(currentJolts + 1)) {
                currentJolts ++;
                dif1 ++;
            } else if (data.contains(currentJolts + 2)) {
                currentJolts += 2;
            } else if (data.contains(currentJolts + 3)) {
                currentJolts += 3;
                dif3 ++;
            } else {
                System.out.println("no next adapter possible");
                return;
            }
        }
        currentJolts +=3;
        System.out.println(dif1 * dif3); //OUTPUT RESULT
    }

    private static void calculateCombinations(HashMap<Integer, Long> combinations, int node) {
        if(node == 0) {
            combinations.put(0, (long) 1);
        } else {
            long val = 0;
            if(combinations.containsKey(node-1)) {
                val += combinations.get(node-1);
            }
            if(combinations.containsKey(node-2)) {
                val += combinations.get(node-2);
            }
            if(combinations.containsKey(node-3)) {
                val += combinations.get(node-3);
            }
            combinations.put(node, val);
        }
    }
}

