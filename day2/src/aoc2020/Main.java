package aoc2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String info = scanner.nextLine();
                System.out.println(info.split("-")[1]);

                //process the input file here
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
