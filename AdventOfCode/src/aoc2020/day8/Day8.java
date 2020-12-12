package aoc2020.day8;

import aoc2020.utilities.CommonFunctions;
import aoc2020.utilities.InputLoader;

import java.io.File;
import java.util.ArrayList;

public class Day8 {

    static int accumulator = 0;
    public static void run() {

        boolean error = true;
        int j = 0;
        System.out.println("Solutions day 8:");
        File file = new File("day8.txt");
        ArrayList<String> input = InputLoader.asList("day8.txt");
        runInstructionSet(input);
        int res1 = accumulator;

        while (error || j==0) {
            ArrayList<String> inputCopy = new ArrayList<>(input);
            if(inputCopy.get(j).contains("nop")) {
                inputCopy.set(j, inputCopy.get(j).replace("nop", "jmp"));
                error = runInstructionSet(inputCopy);
            } else if(inputCopy.get(j).contains("jmp")) {
                inputCopy.set(j, inputCopy.get(j).replace("jmp", "nop"));
                error = runInstructionSet(inputCopy);
            } else {
            }
            j++;
        }
        
        System.out.println("the accumulator right before the program started looping stored the value " + res1);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("The program was corrupted at line " + j);
        System.out.println("The accumulator stores value " + accumulator + " at the end of the program"); //OUTPUT RESULT
    }

    public static boolean runInstructionSet(ArrayList<String> input) {
        boolean[] instructionLines = new boolean[input.size()];
        accumulator = 0;
        for (int i = 0; i < input.size() || i < 0; ) {
            if (instructionLines[i]) {
                return true;
            }
            instructionLines[i] = true;
            String[] s = input.get(i).split(" ");
            if (s[0].equals("acc")) {
                accumulator += Integer.parseInt(s[1]);
                i++;
            } else if (s[0].equals("jmp")) {
                i += Integer.parseInt(s[1]);
            } else if (s[0].equals("nop")) {
                i++;
            } else {
                System.out.println("unkown OP code, program terminates now!");
                return true;
            }
        }
        return false;
    }
}
