package aoc2020.day14;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;
import java.util.TreeMap;

public class Day14 {
    public static void run() {
        long sum = 0;
        int count = 0;
        String mask = "";
        long mask1 = 0; //mask that sets the 1's
        long mask0 = 0; //inverted mask that sets the 0's
        TreeMap<Integer, Long> memory = new TreeMap<>();
        ArrayList<String> input = InputLoader.asList("input/day14.txt");
        for (String s: input) {
            if(s.contains("mask")) {
                mask = s.replace("mask = ", "");
                mask1 = Long.parseLong(mask.replace('X', '0'),2);
                mask0 = Long.parseLong(mask.replace('X', '1'),2);
                mask0 = ~mask0;
//                System.out.println(Long.toBinaryString(mask1));
//                System.out.println(Long.toBinaryString(mask0));
            } else {
                String data[] = s.split("] = ");
                long newValue = Long.parseLong(data[1]); // parse the long from input file
//                System.out.println(mask);
//                System.out.println(Long.toBinaryString(newValue));
                //do bitwise operations
                newValue = newValue | mask1; //set all the 1's from the mask
                newValue = ~newValue; //invert
                newValue = newValue | mask0; //set all the 0's from the mask as a 1 in the inverted set
                newValue = ~newValue; //invert back

                // keep track of all information
                memory.put(Integer.parseInt(data[0].replace("mem[", "")), newValue);
//                System.out.println(Long.toBinaryString(newValue));
            }
            //process the input file into whatever we need
        }
        for (long l : memory.values()) {
            sum += l;
        }
        System.out.println(sum);
    }
}
