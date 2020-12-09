package aoc2020.day5;

import aoc2020.Util;

import java.io.File;
import java.util.ArrayList;

public class Day5 {
    public static void run() {
        System.out.println("Solutions day 5:");
        File file = new File("day5.txt");
        ArrayList<Integer> data = new ArrayList<>(); //keep track of the processed objects
        ArrayList<String> input = Util.readFile(file);
        for (String s: input) {
            int entry;
            s = s.replace('F', '0');
            s = s.replace('B', '1');
            s = s.replace('L','0');
            s = s.replace('R', '1');
            entry = Integer.parseInt(s, 2);
            data.add(entry);

            //process the input file into whatever we need
        }

        int min = Util.getMin(data);
        int max = Util.getMax(data);
        System.out.println("highest seat id: " + max); //highest seat number
        System.out.println("lowest seat id: "+ min); //lowest seat number
        int id = -1;

        for(int i = min; i < max; i++) { //seat ID is the only place that is still left
            if(!data.contains(i)) {
                id = i;
                break;
            }
        }

        System.out.println("your seat ID = " + id);
    }
}
