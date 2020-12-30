package aoc2020.day24;

import aoc2020.day17.Coordinate;
import aoc2020.utilities.InputLoader;

import java.util.*;

public class Day24 {

    public static void run() {
       ArrayList<String> input = InputLoader.asList("input/day24.txt");
       HexGrid flippedTiles = new HexGrid();
       for (String s : input) {
           String xDif = s.replaceAll("(ne)|(se)", "1,");
           xDif = xDif.replaceAll("(nw)|(sw)", "-1,");
           xDif = xDif.replaceAll("(e)", "2,");
           xDif = xDif.replaceAll("w", "-2,");
           String yDif = s.replaceAll("(ne)|(nw)", "1,");
           yDif = yDif.replaceAll("(sw)|(se)", "-1,");
           yDif = yDif.replaceAll("e|w", "");
           int x=0, y=0;
           for (String d : xDif.split(",")) {
               x += Integer.parseInt(d);
           }
           for (String d : yDif.split(",")) {
               y += Integer.parseInt(d);
           }
           Coordinate c = new Coordinate(x, y);
           if(!flippedTiles.remove(c)) {
               flippedTiles.add(c);
           }
       }

       System.out.println(flippedTiles.size());
       for (int i = 0; i<100; i++) {
           flippedTiles = nextDay(flippedTiles);
       }
       System.out.println(flippedTiles.size());
    }

    private static HexGrid nextDay(HexGrid today) {
        HexGrid tomorrow = new HexGrid();

        int[] range = today.getRange();
        for(int x = range[0]-1; x<=range[1]+1; x++) {
            for(int y = range[2]-1; y<=range[3]+1; y++) {
                int neighbors = today.neighborsAmount(x,y);
                if(today.containsPoint(x,y)) { // tile is flipped, stay flipped if
                    if (neighbors == 1 || neighbors == 2) {
                        tomorrow.add(new Coordinate(x,y));
                    }
                } else {
                    if (neighbors == 2) {
                        tomorrow.add(new Coordinate(x,y));
                    }
                }
            }
        }
        return tomorrow;
    }


}
