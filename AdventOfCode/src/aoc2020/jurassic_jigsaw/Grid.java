package aoc2020.jurassic_jigsaw;

import org.omg.CORBA.CODESET_INCOMPATIBLE;

import java.util.TreeSet;

public class Grid {

    private int id;
    private TreeSet<Coordinate> points;
    private int[] range;

    public Grid(int id) {
        this.id = id;
        points = new TreeSet<>();
        range = new int[] {Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE}; //{xMin, xMax, yMin, yMax}
    }

    public TreeSet<Coordinate> getPoints() {
        return points;
    }

    public int[] getRange() {
        return range;
    }

    public TreeSet<Coordinate> getRow(int y) {
        TreeSet<Coordinate> res = new TreeSet<>();

        for (Coordinate c : points) {
            if (y == c.getY()) {
                res.add(new Coordinate(c.getX(), 0));
            } else if (c.getY() > y) {
                break;
            }
        }
        return res;
    }

    public TreeSet<Coordinate> getColumn(int x) {
        TreeSet<Coordinate> res = new TreeSet<>();

        for (Coordinate c : points) {
            if (x == c.getX()) {
                res.add(new Coordinate(0,c.getY()));
            }
        }
        return res;
    }

    public Grid rotateLeft() {
        Grid newGrid = new Grid(id);
        for (Coordinate c : points) {
            int xNew = c.getY();
            int yNew = range[1] - c.getX();
            newGrid.addCoordinate(new Coordinate(xNew, yNew));
        }
        return newGrid;
    }

    public Grid rotateRight() {
    Grid newGrid = new Grid(id);
    for (Coordinate c : points) {
            int xNew = range[3] - c.getY();
            int yNew = c.getX();
            newGrid.addCoordinate(new Coordinate(xNew, yNew));
        }
        return newGrid;
    }

    public Grid mirrorHorizontal() {
        Grid newGrid = new Grid(id);
        for (Coordinate c : points) {
            int xNew = c.getX();
            int yNew = range[3] - c.getY();
            newGrid.addCoordinate(new Coordinate(xNew, yNew));
        }
        return newGrid;
    }

    public Grid mirrorVertical() {
        Grid newGrid = new Grid(id);
        for (Coordinate c : points) {
            int xNew = range[1] - c.getX();
            int yNew = c.getY();
            newGrid.addCoordinate(new Coordinate(xNew, yNew));
        }
        return newGrid;
    }

    public Grid combineGrid(Grid grid, int offsetX, int offsetY) {
        Grid newGrid = new Grid(id);
        for(Coordinate c: getPoints()) {
            newGrid.addCoordinate((new Coordinate(c.getX(), c.getY())));
        }
        for(Coordinate c : grid.getPoints()) {
            newGrid.addCoordinate(new Coordinate(c.getX()+offsetX, c.getY()+offsetY));
        }
        return newGrid;
    }

    public void printGrid() {
        System.out.println(this);
    }

    public void addCoordinate(Coordinate c) {
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


    @Override
    public String toString() {
        String res = "";
        int x = range[0];
        int y = range[2];

        for (Coordinate c : points) {
            while (y < c.getY()) {
                res = res + "\n";
                x = range[0];
                y++;
            }
            while (x < c.getX()) {
                res = res + " ";
                x++;
            }
            res = res + c.getIdentifier();
            x++;
        }

        return res;
    }
}
