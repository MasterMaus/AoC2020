package aoc2020.day16;

import aoc2020.utilities.CommonFunctions;
import aoc2020.utilities.InputLoader;

import java.util.*;

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
        String[] ticket;
        ArrayList<String> ticketFields = new ArrayList<>(input.subList(0, fieldSize));
        ticket = input.get(fieldSize+2).split(",");
        ArrayList<String> nearbyTickets = new ArrayList<>(input.subList(fieldSize + 5,input.size()));
        part1(nearbyTickets, ticketFields);
        part2(ticketFields, nearbyTickets, ticket, "departure");
    }

    private static void part1(ArrayList<String> nearbyTickets, ArrayList<String> ticketFields) {
        //TODO DONT HARDCODE THE MIN AND THE MAX VALUES
        int min = 25;
        int max = 973;
        int res = 0;
        ArrayList<String> invalidTickets = new ArrayList<>();
        for (String s : nearbyTickets) {
            String[] fields = s.split(",");
            for(String field : fields) {
                int val = Integer.parseInt(field);
                if (!CommonFunctions.inRange(val, min, max)) {
                    res += val;
                    invalidTickets.add(s);
                }
            }
        }
        nearbyTickets.removeAll(invalidTickets);
        System.out.println("The ticket scanning error rate = " + res);
    }

    private static void part2(ArrayList<String> fields, ArrayList<String> nearbyTickets, String[] ticket, String regex) {
        TreeMap<String, ArrayList<Integer>> validFields = new TreeMap<>();
        for (int i = 0; i < 20; i++) {
            String field = fields.get(i);
            String[] info = field.split("(: )|( or )|(-)");
            int[] min = {Integer.parseInt(info[1]), Integer.parseInt(info[3])};
            int[] max = {Integer.parseInt(info[2]), Integer.parseInt(info[4])};
            validFields.put(info[0], getValidFields(min, max, fields.size(), nearbyTickets));
        }

        TreeMap<String, Integer> fieldMap = filter(validFields);
        long res = 1;
        for(String s : fieldMap.keySet()) {
            if (s.contains(regex)) {
                res *= Long.parseLong(ticket[fieldMap.get(s)]);
            }
        }
        System.out.println("Multiplying the 6 values from the fields containing \"departure\" results in: " + res);
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
                if(!CommonFunctions.inRange(val, min[0], max[0]) && !CommonFunctions.inRange(val,min[1], max[1])) {
                    invalidFields.add(i);
                }
            }
            validFields.removeAll(invalidFields);
        }
        return validFields;
    }

    private static TreeMap<String, Integer> filter(TreeMap<String, ArrayList<Integer>> validFields) {
        TreeMap<String, Integer> res = new TreeMap<>(); // create a map that contains the field name, mapped to the correct index of the inputfile
        ArrayList<String> keySet = new ArrayList<> (validFields.keySet()); //ArrayList containing all field names
        for (int j = 0; j< validFields.size(); j++) { //iterate through the keySet, and if the key has only 1 valid index, place that key in the result map. start iterating again
            ArrayList<Integer> fields = validFields.get(keySet.get(j));
            if(fields.size() == 1) {
                int index = fields.get(0);
                res.put(keySet.get(j), index);
                for (ArrayList<Integer> i : validFields.values()) {
                    i.remove((Object) index); // The index is taken by one of the fields, and therefore it cannot be valid anymore for any of the other fields
                }
                j = -1; // start iterating through the loop again. The for loop adds 1 at the end of the loop, and we want to start at the begin (0)
            }
        }
        return res;
    }
}
