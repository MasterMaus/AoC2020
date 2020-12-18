package aoc2020.day18;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;
import java.util.Arrays;

public class Day18 {
    public static void run() {
        long sum = 0;
        ArrayList<String> input = InputLoader.asList("input/day18.txt");
        for (String s: input) {
            System.out.println(calculate(s));
            sum += calculateV2(s);
        }
        System.out.println(sum);

    }

    private static long calculate(String expression) {
        while (expression.contains("(")) {
            int i = expression.indexOf('(');
            int count = 1;
            int j = i + 1;
            for(; j < expression.length(); j++) {
                char c = expression.charAt(j);
                if (c == '(') {
                    count ++;
                } else if (c == ')') {
                    count --;
                    if (count == 0) {
                        break;
                    }
                }
            }
            String subExpression = expression.substring(i+1,j);

            expression = expression.replace("("+ subExpression + ")",Long.toString(calculate(subExpression)));
        }
        String[] values = expression.split("( \\+ )|( \\* )");
        String[] operators = expression.split("[0-9]+");
        long res = Long.parseLong(values[0]);
        for(int i = 1; i<values.length; i++) {
            if(operators[i].contains("+")) {
                res += Long.parseLong(values[i]);
            } else if (operators[i].contains("*")) {
                res *= Long.parseLong(values[i]);
            }
        }

        return res;
    }

    private static long calculateV2(String expression) {
        long res = 1;
        while (expression.contains("(")) {
            int i = expression.indexOf('(');
            int count = 1;
            int j = i + 1;
            for(; j < expression.length(); j++) {
                char c = expression.charAt(j);
                if (c == '(') {
                    count ++;
                } else if (c == ')') {
                    count --;
                    if (count == 0) {
                        break;
                    }
                }
            }
            String subExpression = expression.substring(i+1,j);

            expression = expression.replace("("+ subExpression + ")",Long.toString(calculateV2(subExpression)));
        }
        String[] sums = expression.split(" \\* ");
        for (String s : sums) {
            String[] values = s.split(" \\+ ");
            long sum = 0;
            for (String v : values) {
                sum += Long.parseLong(v);
            }
            res *= sum;
        }
        return res;
    }
}
