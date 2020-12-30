package aoc2020.day23;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

public class Day23 {

    public static void run() {
        int[] input = {5,9,8,1,6,2,7,3,4};
        //int[] input = {3,8,9,1,2,5,4,6,7};
        int cupPointer = input[0];
        TreeMap<Integer, Cup> cups = parseInput(input);


        for(int i = 1; i<=10000000; i++) {
            //System.out.print("round " + i + ": ");
            cupPointer = nextRound(cups, cupPointer);
            //printCups(cups);
        }



        printCups(cups);

        //System.out.println(cups.get(1));
        //System.out.println(cups.get(1).getLeftCup());
        //System.out.println(cups.get(1).getLeftCup().getLeftCup());
//        System.out.println(cups.get(4).getRightCup().getRightCup().getRightCup());
    }

    private static void printCups(TreeMap<Integer, Cup> cups) {
        Cup out = cups.get(1);
        for(int i = 0; i<9; i++) {
            System.out.print(out.getId() + ", ");
            out = out.getRightCup();
        }
        System.out.println("");
    }

    private static int nextRound(TreeMap<Integer, Cup> cups, int cupPointer) {
        ArrayList<Cup> replacement = new ArrayList<>();
        Cup currentCup = cups.get(cupPointer);
        for (int i = 0; i<3; i++) {
            Cup c = currentCup.getRightCup();
            c.removeCup();
            replacement.add(0, c);
        }
        Cup destination = null;
        for (int i = 1; i<10; i++) {
            int destinationPointer = cupPointer-i;
            if (destinationPointer < 1) {
                destinationPointer += 1000000;
            }
            destination = cups.get(destinationPointer);
            if (destination.isInCircle()) {
                break;
            }
        }

        while(!replacement.isEmpty()) {
            replacement.remove(0).placeCup(destination);
        }

        return currentCup.getRightCup().getId();
    }

    private static TreeMap<Integer,Cup> parseInput(int[] input) {
        TreeMap<Integer, Cup> res = new TreeMap<>();
        Cup lastCup = null;
        for (int i : input) {
            res.put(i, new Cup(i, lastCup));
            lastCup = res.get(i);
        }

        for(int i = 10; i<=1000000; i++) {
            res.put(i, new Cup(i, lastCup));
            lastCup = res.get(i);
        }

        System.out.println(res.size());
        return res;
    }
}
