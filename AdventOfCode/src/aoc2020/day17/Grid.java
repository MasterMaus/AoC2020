package aoc2020.day17;

import com.sun.org.apache.xalan.internal.lib.ExsltSets;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Grid {

    private HashSet<Coordinate> points;
    private HashMap<Integer, HashSet<Coordinate>> xMap;
    private HashMap<Integer, HashSet<Coordinate>> yMap;
    private HashMap<Integer, HashSet<Coordinate>> zMap;
    private int[] range;

    public Grid() {
        points = new HashSet<>();
        xMap = new HashMap<>();
        yMap = new HashMap<>();
        zMap = new HashMap<>();

        range = new int[]{0, 0, 0, 0, 0, 0}; //[xmin, xmax, ymin, ymax, zmin, zmax]
    }

    public void addPoint(Coordinate c) {
        points.add(c);
        int x = c.getX();
        int y = c.getY();
        int z = c.getZ();

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
        if (z < range[4]) {
            range[4] = z;
        } else if (z > range[5]) {
            range[5] = z;
        }

        if (!xMap.containsKey(x)) {
            xMap.put(x, new HashSet<>());
        }
        if (!yMap.containsKey(y)) {
            yMap.put(y, new HashSet<>());
        }
        if (!zMap.containsKey(z)) {
            zMap.put(z, new HashSet<>());
        }
        xMap.get(x).add(c);
        yMap.get(y).add(c);
        zMap.get(z).add(c);
    }

    public boolean containsPoint (int x, int y, int z) {
        // make sure that the xMap, yMap and zMap has a set for their coordinates
        if (xMap.get(x) != null && yMap.get(y) != null && zMap.get(z) != null) {
            HashSet<Coordinate> res = new HashSet<>(xMap.get(x));
            res.retainAll(yMap.get(y));
            res.retainAll(zMap.get(z));

            return (res.size() == 1);
            }
        return false;
    }

    public int[] getRange() {
        return range;
    }

    public int getAmountOfNeighbors(int x, int y, int z) {
        HashSet<Coordinate> res = new HashSet<>();
        HashSet<Coordinate> ys = new HashSet<>();
        HashSet<Coordinate> zs = new HashSet<>();

        if(xMap.get(x-1) != null) {
            res.addAll(xMap.get(x-1));
        }
        if(xMap.get(x) != null) {
            res.addAll(xMap.get(x));
        }
        if(xMap.get(x+1) != null) {
            res.addAll(xMap.get(x+1));
        }

        if(yMap.get(y-1) != null) {
            ys.addAll(yMap.get(y-1));
        }
        if(yMap.get(y) != null) {
            ys.addAll(yMap.get(y));
        }
        if(yMap.get(y+1) != null) {
            ys.addAll(yMap.get(y+1));
        }
        res.retainAll(ys);

        if(zMap.get(z-1) != null) {
            zs.addAll(zMap.get(z-1));
        }
        if(zMap.get(z) != null) {
            zs.addAll(zMap.get(z));
        }
        if(zMap.get(z+1) != null) {
            zs.addAll(zMap.get(z+1));
        }
        res.retainAll(zs);

        return res.size();
    }

    public int getGridSize() {
        return points.size();
    }

    @Override
    public String toString() {
        String res = "";
        for(Coordinate c : points) {
            res = res.concat(c.toString().concat("\n"));
        }
        return res;
    }
}
