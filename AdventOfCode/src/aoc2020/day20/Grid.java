package aoc2020.day20;

import aoc2020.day17.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Grid {

    private HashSet<Coordinate> points;
    private HashMap<Integer, HashSet<Coordinate>> xMap;
    private HashMap<Integer, HashSet<Coordinate>> yMap;
    private int[] range;
    private ArrayList sides;
    private int id;

    public Grid() {
        points = new HashSet<>();
        xMap = new HashMap<>();
        yMap = new HashMap<>();

        id = 0;

        sides = new ArrayList<>();

        range = new int[]{0, 0, 0, 0}; //[xmin, xmax, ymin, ymax, zmin, zmax]
    }

    public void addId(int id) {
        this.id = id;
    }

    public void addPoint(Coordinate c) {
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

        if (!xMap.containsKey(x)) {
            xMap.put(x, new HashSet<>());
        }
        if (!yMap.containsKey(y)) {
            yMap.put(y, new HashSet<>());
        }
        xMap.get(x).add(c);
        yMap.get(y).add(c);
    }

    public boolean containsPoint (int x, int y, int z) {
        // make sure that the xMap, yMap and zMap has a set for their coordinates
        if (xMap.get(x) != null && yMap.get(y) != null) {
            HashSet<Coordinate> res = new HashSet<>(xMap.get(x));
            res.retainAll(yMap.get(y));

            return (res.size() == 1);
            }
        return false;
    }

    public int[] getRange() {
        return range;
    }

    public int getGridSize() {
        return points.size();
    }

    public TreeSet<Integer> getColumn(int x) {
        TreeSet<Integer> res = new TreeSet<>();
        for (Coordinate c : xMap.get(x)) {
            res.add(c.getY());
        }
        return res;
    }

    public TreeSet<Integer> getRow(int y) {
        TreeSet<Integer> res = new TreeSet<>();
        for (Coordinate c : yMap.get(y)) {
            res.add(c.getX());
        }
        return res;
    }


    @Override
    public String toString() {
        String res = "";
//        for(Coordinate c : points) {
//            res = res.concat(c.toString().concat("\n"));
//        }
        return res + id;
    }

    public void addSide(Grid neighbor) {
        sides.add(neighbor);
    }

    public ArrayList<Grid> getSides() {
        return sides;
    }

    public int getId() {
        return id;
    }
}
