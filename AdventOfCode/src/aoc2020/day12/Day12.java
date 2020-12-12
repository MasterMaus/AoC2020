package aoc2020.day12;

import aoc2020.Util;

import java.io.File;
import java.util.ArrayList;

public class Day12 {
    public static void run() {
        File file = new File("day12.txt");
        ArrayList<String> input = Util.readFile(file);
        System.out.print("solution part 1: ");
        part1(input);
        System.out.print("solution part 2: ");
        part2(input);

    }

    private static void part1 (ArrayList<String> input) {
        int x = 0;
        int y = 0;
        int direction = 0;
        for (String s: input) {
            char action = s.charAt(0);
            int val = Integer.parseInt(s.substring(1));
            if (action == 'F') { //go forward
                if (direction == 0) {
                    x += val;
                } else if (direction == 90) {
                    y += val;
                } else if (direction == 180) {
                    x -= val;
                } else if (direction == 270) {
                    y -= val;
                }
            } else if (action == 'N') { // move north (+ on y-axis)
                y += val;
            } else if (action == 'S') { // move south (- on y-axis)
                y -= val;
            } else if (action == 'E') { // move east (+ on x-axis)
                x += val;
            } else if (action == 'W') { // move west (- on x-axis)
                x -= val;
            } else if (action == 'L') { // turn the ship to the left (add with direction, then mod)
                direction = (direction+val)%360;
            } else if (action == 'R') { // turn the ship to the right (substract from direction, then mod)
                direction = (direction-val)%360;
                if(direction<0) {
                    direction += 360;
                }
            }
        }
        System.out.println(Util.getManhattanDistance(x,y)); //OUTPUT RESULT
    }

    private static void part2(ArrayList<String> input) {
        int waypoint[] = {10, 1};
        int ship[] = {0,0};

        for (String s: input) {
            char action = s.charAt(0);
            int val = Integer.parseInt(s.substring(1));
            if (action == 'F') { //move ship val times with the waypoint
                ship[0] = ship[0] + val*waypoint[0];
                ship[1] = ship[1] + val*waypoint[1];
            } else if (action == 'N') { // move waypoint north (+ on y-axis)
                waypoint[1] += val;
            } else if (action == 'S') { // move waypoint south (- on y-axis)
                waypoint[1] -= val;
            } else if (action == 'E') { // move waypoint east (+ on x-axis)
                waypoint[0] += val;
            } else if (action == 'W') { // move waypoint west (- on x-axis)
                waypoint[0] -= val;
            } else if (action == 'L') { // turn the ship to the left (add with direction, then mod)
                int x, y;
                if (val == 0) {
                    x = waypoint[0];
                    y = waypoint[1];
                } else if (val == 90) {
                    x = waypoint[1] * -1;
                    y = waypoint[0];
                } else if (val == 180) {
                    x = waypoint[0] * -1;
                    y = waypoint[1] * -1;
                } else if (val == 270) {
                    x = waypoint[1];
                    y = waypoint[0] * -1;
                } else {
                    x = waypoint[0];
                    y = waypoint[1];
                    System.out.println("rotation unknown");
                }
                waypoint[0] = x;
                waypoint[1] = y;
            } else if (action == 'R') { // turn the ship to the right (substract from direction, then mod)
                int x, y;
                if (val == 0) {
                    x = waypoint[0];
                    y = waypoint[1];
                } else if (val == 90) {
                    x = waypoint[1];
                    y = waypoint[0] * -1;
                } else if (val == 180) {
                    x = waypoint[0] * -1;
                    y = waypoint[1] * -1;
                } else if (val == 270) {
                    x = waypoint[1] * -1;
                    y = waypoint[0];
                } else {
                    x = waypoint[0];
                    y = waypoint[1];
                    System.out.println("rotation unknown");
                }
                waypoint[0] = x;
                waypoint[1] = y;
            }
        }

        System.out.println(Util.getManhattanDistance(ship[0], ship[1]));

    }
}
