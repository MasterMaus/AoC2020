package aoc2020.day6;

import aoc2020.Util;

import java.io.File;
import java.util.ArrayList;

public class Day6 {
    public static void run() {
        File file = new File("day6.txt");
        ArrayList<String/*NewObject*/> newObjectList = new ArrayList<>(); //keep track of the processed objects
        ArrayList<String> input = Util.readFile(file);
        for (String s: input) {
            //process the input file into whatever we need
        }
        int res = 0; // STORE RESULT
        System.out.println(res); //OUTPUT RESULT
    }
}
