package aoc2020.day9;

import aoc2020.Util;

import java.io.File;
import java.util.ArrayList;

public class Day9 {
    public static void run() {
        System.out.println("Solutions day 9:");
        File file = new File("day9.txt");
        ArrayList<String/*NewObject*/> newObjectList = new ArrayList<>(); //keep track of the processed objects
        ArrayList<String> input = Util.readFile(file);
        for (String s: input) {
            //process the input file into whatever we need
        }
        int res = 0; // STORE RESULT
        System.out.println(res); //OUTPUT RESULT
    }
}
