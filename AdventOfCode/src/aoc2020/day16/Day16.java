package aoc2020.day16;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day16 {
    public static void run() {
        ArrayList<String/*NewObject*/> newObjectList = new ArrayList<>(); //keep track of the processed objects
        ArrayList<String> input = InputLoader.asList("input/day16.txt");
        int fieldSize = 0;
        for (int i = 0; i<input.size(); i++) {
            String s = input.get(i);
            if (s.equals("")) {
                fieldSize = i;
                break;
            }
            //process the input file into whatever we need
        }
        String ticket;
        ArrayList<String> ticketFields = new ArrayList<>(input.subList(0, fieldSize));
        ticket = input.get(fieldSize+2);
        ArrayList<String> nearbyTickets = new ArrayList<>(input.subList(fieldSize + 5,input.size()));
        part1(nearbyTickets);

        part2(ticketFields, nearbyTickets, ticket);

       //System.out.println(nearbyTickets.toString()); //OUTPUT RESULT
    }

    private static void part1(ArrayList<String> nearbyTickets) {
        int min = 25;
        int max = 973;
        int res = 0;
        ArrayList<String> invalidTickets = new ArrayList<>();
        for (String s : nearbyTickets) {
            String[] fields = s.split(",");
            for(String field : fields) {
                int val = Integer.parseInt(field);
                if (!inRange(val, min, max)) {
                    res += val;
                    invalidTickets.add(s);
                }
            }
        }
        nearbyTickets.removeAll(invalidTickets);
        System.out.println(res);
    }

    //todo place this function in utilities maybe?
    private static boolean inRange(int val, int min, int max) {
        if(val >= min && val <= max) {
            return true;
        } else {
            return false;
        }
    }

    private static void part2(ArrayList<String> fields, ArrayList<String> nearbyTickets, String ticket) {
        ArrayList<Integer> validFields = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String field = fields.get(i);
            String[] info = field.split("(: )|( or )|(-)");
            int[] min = {Integer.parseInt(info[1]), Integer.parseInt(info[3])};
            int[] max = {Integer.parseInt(info[2]), Integer.parseInt(info[4])};
            System.out.println(info[0] + " = " + getValidFields(min, max, fields.size(), nearbyTickets));
        }

    }

    private static ArrayList<Integer> getValidFields(int[] min, int[] max, int fieldAmount, ArrayList<String> input) {
        ArrayList<Integer> validFields = new ArrayList<>();

        for(int i = 0; i < fieldAmount; i++) {
            validFields.add(i);
        }

        for (String s : input) {
            String[] fields = s.split(",");
            ArrayList<Integer> invalidFields = new ArrayList<>();
            for (int i : validFields) {
                int val = Integer.parseInt(fields[i]);
                if(!inRange(val, min[0], max[0]) && !inRange(val,min[1], max[1])) {
                    invalidFields.add(i);
                }
            }
            validFields.removeAll(invalidFields);
        }
        return validFields;
    }
}
