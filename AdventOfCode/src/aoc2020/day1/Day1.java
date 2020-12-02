package aoc2020.day1;

import aoc2020.Util;

import java.io.File;
import java.util.ArrayList;

public class Day1 {

    public static void run() {
        ArrayList<Integer> expenses = new ArrayList<>();
        File input = new File("day1.txt");
        for(String i : Util.readFile(input)){
            expenses.add(Integer.parseInt(i));
        }
        int[] sum = findSum2Val(expenses, 2020);
        System.out.println("sum = " + sum[0] + " + " + sum [1] + ", its product = "+ sum[0] * sum[1]);
        sum = findSum3Val(expenses, 2020);
        System.out.println("sum = " + sum[0] + " + " + sum [1] + " + " + sum [2] + ", its product = "+ sum[0] * sum[1] * sum[2]);
    }

    private static int[] findSum2Val(ArrayList<Integer> input, int target) {
        for(int i : input) {
            for(int j : input) {
               if(i+j == target) {
                   return new int[]{i, j};
               }
            }
        }
        return null;

    }

    private static int[] findSum3Val(ArrayList<Integer> input, int target) {
        for(int i : input) {
            for(int j : input) {
                for (int k : input) {
                    if(i+j+k == target) {
                        return new int[]{i, j, k};
                    }
                }
            }
        }
        return null;
    }
}
