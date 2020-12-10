package aoc2020.day10;

import aoc2020.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class Day10 {
    public static void run() {
        System.out.println("Solutions day 10:");
        File file = new File("day10.txt");
        HashSet<Integer> data = new HashSet<>(); //keep track of the processed objects
        ArrayList<String> input = Util.readFile(file);
        for (String s: input) {
            data.add(Integer.parseInt(s));
        }
        int totalJolts = Util.getMax(data)+3;

        // TODO recursively calculate all ways from node 0 to node end

    }

    private static void part1(HashSet<Integer> data) {
        int dif1 = 0;
        int dif3 = 1;
        int currentJolts = 0;
        long combinations = 1;

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
}

