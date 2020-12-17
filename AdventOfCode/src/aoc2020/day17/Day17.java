package aoc2020.day17;

import aoc2020.utilities.CommonFunctions;
import aoc2020.utilities.InputLoader;

import java.util.ArrayList;
import java.util.HashSet;


public class Day17 {

    public static void run() {

        Grid grid = new Grid();

        ArrayList<String> input = InputLoader.asList("input/day17.txt");

        for (int y = 0; y < input.size(); y++) {
            String row = input.get(y);
            for (int x = 0; x < row.length(); x++) {
                if(row.charAt(x) == '#') {
                    grid.addPoint(new Coordinate(x, y, 0));
                }
            }
        }
        for(int i = 0; i<6; i++) {
            grid = nextCycle(grid);
        }
        System.out.println(grid.getGridSize());

    }

    private static Grid nextCycle(Grid grid) {
        Grid res = new Grid();

        int[] range = grid.getRange();

        for (int x = range[0]-1; x <= range[1]+1; x++) {
            for (int y = range[2]-1; y <= range[3]+1; y++) {
                for (int z = range[4]-1; z <= range[5]+1; z++) {
                    boolean isActive = grid.containsPoint(x, y, z);
                    int neighbors = grid.getAmountOfNeighbors(x,y,z); // it counts itself with neighbors if it was active
                    if (isActive) {
                        if(neighbors == 3 || neighbors == 4) {
                            res.addPoint(new Coordinate(x,y,z));
                        }
                    } else {
                        if(neighbors == 3) {
                            res.addPoint(new Coordinate(x,y,z));
                        }
                    }
                }
            }
        }
        return res;
    }

}