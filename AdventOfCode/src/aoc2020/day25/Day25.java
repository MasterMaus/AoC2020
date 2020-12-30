package aoc2020.day25;

import aoc2020.day17.Coordinate;
import aoc2020.day24.HexGrid;
import aoc2020.utilities.InputLoader;

import java.util.ArrayList;

public class Day25 {

    public static void run() {
       long subject = 7;
       long divisor = 20201227;
       long cardKey = 2069194; //13207740
       long doorKey = 16426071; //8229037
       long value = 1;
       int loop = 0;
       while (value != doorKey) {
           value *= subject;
           value %= divisor;
           loop++;
       }
       System.out.println(loop);
       System.out.println(value + " = " + doorKey);

       long encryptionKey = 1;
       for (int i = 0; i<loop; i++) {
           encryptionKey *= cardKey;
           encryptionKey %= divisor;
       }

        System.out.println(encryptionKey);
    }


}
