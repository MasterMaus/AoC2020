package aoc2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Password> passwordList = new ArrayList<>();

        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                String info[] = temp.split("(-|: |\\s)");
                int min = Integer.parseInt(info[0]);
                int max = Integer.parseInt(info[1]);
                char letter = info[2].charAt(0);
                String password = info[3];
                Password entry = new Password(password, min, max, letter);
                passwordList.add(entry);
                //process the input file here
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int count = 0;
        for (Password i : passwordList) {
            if (i.isValidPasswordV2()) {
                count++;
            }
        }
        System.out.println(count);


    }
}
