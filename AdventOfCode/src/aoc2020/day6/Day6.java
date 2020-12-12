package aoc2020.day6;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;

public class Day6 {
    public static void run() {
        System.out.println("Solutions day 6:");
        String entry = "";
        int uniqueAnswers = 0;
        int sameAnswers = 0;
        ArrayList<String> input = InputLoader.asList("input/day6.txt");
        input.add(""); // add empty line to the input so that our last entry will be added in the data list
        for (String s: input) {
            if (s.isEmpty()) {
                uniqueAnswers += calculateUniqueAnswers(entry);
                sameAnswers += calculateSameAnswers(entry);
                entry = s;
            } else {
                entry = entry.concat(s).concat(" ");
            }
        }
        int res = 0; // STORE RESULT
        System.out.println("sum of unique answers per group: " + uniqueAnswers); //OUTPUT RESULT
        System.out.println("sum of common answers per group: " + sameAnswers);
    }

    public static int calculateUniqueAnswers(String s) {
        int res = 0;
        for(char c = 'a'; c<='z'; c++) {
            if(s.indexOf(c) >= 0) {
                res++;
            }
        }
        return res;
    }

    public static int calculateSameAnswers(String s) {
        int res = 0;
        String[] group = s.split(" ");
        for(char c = 'a'; c<='z'; c++) {
            boolean charStillCommon = true;
            for (String q : group) {
                if(q.indexOf(c) < 0) {
                    charStillCommon = false;
                    break;
                }
            }
            if (charStillCommon) {
                res ++;
            }
        }
        return res;
    }
}
