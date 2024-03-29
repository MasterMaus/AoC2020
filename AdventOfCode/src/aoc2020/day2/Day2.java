package aoc2020.day2;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;

public class Day2 {
    public static void run() {
        System.out.println("Solutions day 2:");
        ArrayList<Password> passwordList = new ArrayList<>();
        ArrayList<String> input = InputLoader.asList("input/day2.txt");
        for(String i: input) {
            String[] entry = i.split("(-|: |\\s)");
            int min = Integer.parseInt(entry[0]);
            int max = Integer.parseInt(entry[1]);
            char letter = entry[2].charAt(0);
            String password = entry[3];
            Password p = new Password(password, min, max, letter);
            passwordList.add(p);
        }

        int countV1 = 0;
        int countV2 = 0;
        for (Password p : passwordList) {
            if (p.isValidPassword()) {
                countV1 ++;
            }
            if (p.isValidPasswordV2()) {
                countV2 ++;
            }
        }

        System.out.println(countV1 + " passwords satisfy the conditions of the first ruleset");
        System.out.println(countV2 + " passwords satisfy the conditions of the second ruleset");
    }
}
