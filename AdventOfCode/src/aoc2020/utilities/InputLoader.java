package aoc2020.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Scanner;

public class InputLoader {

    public static ArrayList<String> asList(String filepath) {
        ArrayList<String> output = new ArrayList<>();
        File file = new File(filepath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                output.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return output;
    }

    public static String asString(String filepath) {
        String output = new String();
        File file = new File(filepath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                output.concat(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return output;
    }

}
