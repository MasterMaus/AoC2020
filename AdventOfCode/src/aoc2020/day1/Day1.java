package aoc2020.day1;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;

public class Day1 {

    public static void run() {
        System.out.println("Solutions day 1:");
        int[] sortedExpenses = new int[2021];
        ArrayList<String> input = InputLoader.asList("day1.txt");
        for(String s : input){
            int i = Integer.parseInt(s);
            sortedExpenses[i] = i;
        }
        int target = 2020;
        int a, b;
        findSum2Val(sortedExpenses, target);
        findSum3Val(sortedExpenses, target);
    }

    private static void findSum2Val(int[] input, int target) { //4ms
        for(int a : input) {
            if(input[a] != 0) {
                if (input[target - a] == target - a) {
                    System.out.println("sum = " + a + " + " + (target-a) + ", its product = "+ a * (target-a));
                    return;
                }
            }
        }
    }

    private static void findSum3Val(int[] input, int target) { //11ms
        for(int a : input) {
            if(input[a] != 0){
                for(int b : input) {
                    if(input[b] != 0) {
                        if (input[target - a] == target - a) {
                            System.out.println("sum = " + a + " + " + b + " + " + (target-a-b) + ", its product = "+ a*b*(target-a-b));
                            return;
                        }
                    }
                }
            }
        }
    }
}
