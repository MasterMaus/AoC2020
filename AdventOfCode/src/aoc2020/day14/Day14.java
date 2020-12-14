package aoc2020.day14;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;
import java.util.TreeMap;

public class Day14 {
    public static void run() {


        ArrayList<String> input = InputLoader.asList("input/day14.txt");
//        TreeMap<Long, Long> part1 = part1(input);
//        System.out.println(sumOfValues(part1));
        TreeMap<Long, Long> part2 = part2(input);
        System.out.println(sumOfValues(part2));

    }

    private static TreeMap<Long, Long> part1(ArrayList<String> input) {
        String mask = "";
        long mask1 = 0; //mask that sets the 1's
        long mask0 = 0; //inverted mask that sets the 0's
        TreeMap<Long, Long> memory = new TreeMap<>();

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
                memory.put(Long.parseLong(data[0].replace("mem[", "")), newValue);
//                System.out.println(Long.toBinaryString(newValue));
            }
        }

        return memory;
    }

    private static TreeMap<Long, Long> part2(ArrayList<String> input) {
        TreeMap<Long, Long> memory = new TreeMap<>(); // <memory location, actual data>

        String maskX = "";
        long mask = 0;
                for (String s: input) {
            if(s.contains("mask")) {
                maskX = s.replace("mask = ", "");
                mask = Long.parseLong(maskX.replace("X", "1"),2);
            } else {
                String data[] = s.split("] = ");
                long val = Long.parseLong(data[1]);
                long newMem = Long.parseLong(data[0].replace("mem[", "")); // parse the memory pointer from input
                newMem |= mask;
                ArrayList<Long> mems = getAllPossibleValues(maskX, newMem);

                for (long m : mems) {
                    memory.put(m, val);
                }
                // keep track of all information
//                System.out.println(Long.toBinaryString(newValue));
            }
        }

        return memory;
    }

    private static long sumOfValues(TreeMap<Long, Long> memory) {
        long sum = 0;


        for (long l : memory.values()) {
            sum += l;
        }
        return sum;
    }

    private static ArrayList<Long> getAllPossibleValues(String mask, long val) {
        ArrayList<Long> res = new ArrayList<>();
        int index = mask.length() - mask.indexOf('X')-1;
        long val1 = val ^ ((long)1<<index); // CAST 1 to long, because 1 will be an integer. not only will bit 2^33 - 2^36 never flip this way.
        // bit 2^32 is the sign bit, and this will flip the sign bit of the long for some reason
        mask = mask.replaceFirst("X", "2");
        if (mask.indexOf('X') >= 0) {
            res.addAll(getAllPossibleValues(mask, val));
            res.addAll(getAllPossibleValues(mask, val1));
        } else {
            res.add(val);
            res.add(val1);
        }
        return res;
    }
}
