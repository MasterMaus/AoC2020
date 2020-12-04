package aoc2020.day4;

import aoc2020.Util;

import java.io.File;
import java.util.ArrayList;

public class Day4 {
    public static void run() {
        String entry = "";
        File file = new File("day4.txt");
        ArrayList<Passport> data = new ArrayList<>();
        ArrayList<String> input = Util.readFile(file);
        input.add(""); // add empty line to the input so that our last entry will be added in the data list
        for (String s: input) {
            if (s.isEmpty()) {
                data.add(new Passport(entry)); //todo figure out to also add last entry
                //System.out.println(entry);
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
