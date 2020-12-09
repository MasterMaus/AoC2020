package aoc2020.day9;

import aoc2020.Util;

import java.io.File;
import java.util.ArrayList;

public class Day9 {
    public static void run() {
        System.out.println("Solutions day 9:");
        File file = new File("day9.txt");
        ArrayList<String> input = Util.readFile(file);
        long[] data = new long[input.size()];
        for (int i = 0; i < input.size(); i++) {
            data[i] = Long.parseLong(input.get(i));
        }

        long res = 0; // STORE RESULT
        int preambleSize = 25;
        long[] preamble = new long[preambleSize];
        for( int i = preambleSize; i < data.length; i++) {
            System.arraycopy(data, i-preambleSize, preamble, 0, preambleSize);
            if(!findSum2Val(preamble, data[i])) {
                res = data[i];
            }
        }
        System.out.println(res); //OUTPUT RESULT

        long res2 = findEncryptionWeakness(data, res);
        System.out.println(res2);
    }


    private static boolean findSum2Val(long[] input, long target) { // TODO rewrite day 1 so that it can be used for this solution
        for(long i : input) {
            for(long j : input) {
                if(i+j == target) {
                    return true;
                }
            }
        }
        return false;

    }

    public static long findEncryptionWeakness(long[] input, long target) {
        long res = -1;

        for (int i = 0; i < input.length; i++) {
            long sum = input[i];
            int j = i + 1;
            while(sum < target) {
                sum += input[j];
                j++;
            }
            if (sum == target) {
                long[] set = new long[j-i];
                System.arraycopy(input, i, set, 0, j-i);
                return Util.getMin(set) + Util.getMax(set);
            }
        }

        return res;
    }
}
