package aoc2020.day15;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class Day15 {
    public static void run() {
        TreeMap<Integer, int[]> numberList = new TreeMap<>(); //map <number, array of last occured indexes of the number>
        int lastNumber = 0;
        int roundNumber = 0;
        String input = InputLoader.asString("input/day15.txt");
        for (String s: input.split(",")) {
            roundNumber++;
            int[] lastOccurrances = {0,roundNumber};
            lastNumber = Integer.parseInt(s);
            numberList.put(lastNumber, lastOccurrances);
        }

        while(roundNumber < 30000000) {
            roundNumber++;
            lastNumber = nextNumber(numberList, lastNumber, roundNumber);
        }


        System.out.println(lastNumber); //OUTPUT RESULT
    }

    private static int nextNumber(TreeMap<Integer, int[]> numberList, int lastNumber, int roundNumber) {
        int newNumber = 0;
        int[] lastOccurances = numberList.get(lastNumber);
        if(lastOccurances[0] == 0) {
            newNumber = 0; //newNumber becomes 0, because the last number has been spoken once
        } else {
            newNumber = lastOccurances[1] - lastOccurances[0];
        }
        if (numberList.containsKey(newNumber)) {
            lastOccurances = numberList.get(newNumber);
            lastOccurances[0] = lastOccurances[1];
            lastOccurances[1] = roundNumber;
        } else {
            lastOccurances = new int[] {0,roundNumber};
        }

        //System.out.println(newNumber + " ----- " + Arrays.toString(lastOccurances));
        numberList.put(newNumber, lastOccurances);
        return newNumber;
    }

}
