package aoc2020.day2;

import aoc2020.Util;
import java.io.File;
import java.util.ArrayList;

public class Day2 {
    public static void run() {
        File file = new File("day2.txt");
        ArrayList<Password> passwordList = new ArrayList<>();
        ArrayList<String> input = Util.readFile(file);
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

        System.out.println(countV1 + "amount of passwords satisfy the conditions of the first ruleset");
        System.out.println(countV2 + "amount of passwords satisfy the conditions of the second ruleset");
    }
}
