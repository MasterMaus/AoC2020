package aoc2020.day4;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;

public class Day4 {
    public static void run() {
        System.out.println("Solutions day 4:");
        String entry = "";
        ArrayList<Passport> data = new ArrayList<>();
        ArrayList<String> input = InputLoader.asList("day4.txt");
        input.add(""); // add empty line to the input so that our last entry will be added in the data list
        for (String s: input) {
            if (s.isEmpty()) {
                data.add(new Passport(entry));
                entry = s;
            } else {
                entry = entry.concat(s).concat(" ");
            }
        }

        int res = 0; // STORE RESULT
        for (Passport p : data) {
            if (p.isValidV2()) {
                res++;
            }
        }
        System.out.println(res);
    }
}
