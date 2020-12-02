package aoc2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> expenses = new ArrayList<>();

        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextInt()) {
                expenses.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(int i : expenses) {
            for(int j : expenses) {
                if (i + j == 2020) {
                    System.out.println(i * j);
                }
                for(int k : expenses) {
                    if (i + j + k == 2020) {
                        System.out.println(i * j * k);
                    }
                }
            }
        }
        System.out.println(expenses.get(0));
    }

    public int findSum(int total, ArrayList<Integer> expenses) {
        return -1;
    }
}
