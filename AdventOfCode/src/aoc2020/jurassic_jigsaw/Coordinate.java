package aoc2020.jurassic_jigsaw;

public class Coordinate implements Comparable<Coordinate> {

    private int x, y;
    private char identifier;


    public Coordinate(int x, int y) {
         this(x, y, '#');
    }

    public Coordinate(int x, int y, char identifier) {
        this.x = x;
        this.y = y;
        this.identifier = identifier;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setIdentifier(char identifier) {
        this.identifier = identifier;
    }

    public char getIdentifier() {
        return identifier;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(x)*"x".hashCode() + Integer.hashCode(y)*"y".hashCode();
    }

    @Override
    public String toString() {
        return Character.toString(identifier);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;
            return compareTo(coordinate) == 0;
        }
        return false;    }

    @Override
    public int compareTo(Coordinate c) {
        if (y < c.getY() ) {
            return -1;
        } else if (y == c.getY()) {
            if (x < c.getX()) {
                return -1;
            } else if ( x == c.getX()) {
                return 0;
            } else {
                return 1;
            }
        } else { //y > c.getY
            return 1;
        }
    }
}
