package aoc2020.day3;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;

public class Day3 {
    public static void run() {
        System.out.println("Solutions day 3:");
        ArrayList<ArrayList<Boolean>> forest = new ArrayList<>(); //keep track of the processed objects
        ArrayList<String> input = InputLoader.asList("input/day3.txt");
        for (String s: input) {
            ArrayList<Boolean> entry = new ArrayList<>();
            for(char c : s.toCharArray()) {
                if (c == '.') {
                    entry.add(false);
                } else {
                    entry.add(true);
                }
            }
            forest.add(entry);
        }

        int res = 1; //MULTIPLIER FLOWS OVER WHEN USING INTEGERS
        int[][] slopes = {{1,1},{3,1},{5,1},{7,1},{1,2}};
        for(int[] s : slopes) {
            int hits = 0;
            int right = s[0];
            int down = s[1];
            for (int i = 0; i < forest.size(); i += (down)) {
                ArrayList<Boolean> entry = forest.get(i);
                if (entry.get(((i / down) * right) % entry.size())) {
                    hits++;
                }
            }
            res *= hits;
        }

        System.out.println(res);
    }
}
