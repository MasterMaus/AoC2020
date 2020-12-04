package aoc2020.day4;

import aoc2020.Util;

import java.io.File;
import java.util.ArrayList;

public class Day4 {
    public static void run() {
        String entry = "";
        File file = new File("day4.txt");
        ArrayList<Passport> passports = new ArrayList<>();
        ArrayList<String> data = new ArrayList<>();
        ArrayList<String> input = Util.readFile(file);
        for (String s: input) {
            if (s.isEmpty()) {
                data.add(entry);
                passports.add(new Passport(entry));
                //System.out.println(entry);
                entry = s;
            } else {
                entry = entry.concat(s).concat(" ");
            }
            //process the input file into whatever we need
        }

        int res = 0; // STORE RESULT
        for (Passport p : passports) {
            if (p.isValidV2()) {
                res++;
            }
        }
        System.out.println(res);
    }
}
