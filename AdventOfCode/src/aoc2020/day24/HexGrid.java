package aoc2020.day24;

import aoc2020.day17.Coordinate;

import java.util.HashSet;

public class HexGrid {

    private HashSet<Coordinate> points;
    private int[] range;

    public HexGrid() {
        points = new HashSet<>();

        range = new int[] {0,0,0,0}; //{xmin, xmax, ymin, ymax}
    }

    public void add (Coordinate c) {
        points.add(c);
        int x = c.getX();
        int y = c.getY();

        if (x < range[0]) {
            range[0] = x;
        } else if (x > range[1]) {
            range[1] = x;
        }
        if (y < range[2]) {
            range[2] = y;
        } else if (y > range[3]) {
            range[3] = y;
        }

    }

    public boolean contains(Coordinate c) {
        return points.contains(c);
    }

    public boolean remove(Coordinate c) {
        return points.remove(c);
    }

    public int size() {
        return points.size();
    }

    public boolean containsPoint (Coordinate c) {
        return points.contains(c);
    }

    public boolean containsPoint (int x, int y) {
        return containsPoint(new Coordinate(x, y));
    }

    public int neighborsAmount(int x, int y) {
        int res = 0;

        if (containsPoint(x+1, y+1)) { //ne
            res++;
        }
        if (containsPoint(x-1, y+1)) { //nw
            res++;
        }
        if (containsPoint(x+1, y-1)) { //se
            res++;
        }
        if (containsPoint(x-1, y-1)) { //sw
            res++;
        }
        if (containsPoint(x+2, y)) { //e
            res++;
        }
        if (containsPoint(x-2, y)) { //w
            res++;
        }

        return res;
    }

    public int neighborsAmount(Coordinate c) {
        return neighborsAmount(c.getX(), c.getY());
    }

    public int[] getRange() {
        return range;
    }
}
