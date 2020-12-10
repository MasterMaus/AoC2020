package aoc2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Util {

    public static ArrayList<String> readFile(File file) {
        ArrayList<String> res = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                res.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }

    public static long getMax(long[] input) {
        long res = input[0];
        for(long l : input) {
            if (l>res) {
                res = l;
            }
        }
        return res;
    }

    public static long getMin(long[] input) {
        long res = input[0];
        for(long l : input) {
            if (l<res) {
                res = l;
            }
        }
        return res;
    }

    public static int getMax(int[] input) {
        int res = input[0];
        for(int i : input) {
            if (i>res) {
                res = i;
            }
        }
        return res;
    }

    public static int getMin(int[] input) {
        int res = input[0];
        for(int i : input) {
            if (i<res) {
                res = i;
            }
        }
        return res;
    }

    public static int getMax(Collection<Integer> input) {
        return Collections.max(input);
    }

    public static int getMin(Collection<Integer> input) {
        return Collections.min(input);
    }

}
