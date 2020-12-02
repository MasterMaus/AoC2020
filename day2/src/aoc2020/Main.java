package aoc2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                String info[] = temp.split("(-|:|\\s)");
                System.out.println(Arrays.toString(info));
                break;
                //process the input file here
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
