package aoc2020.day17;

public class Coordinate {

    private int x, y, z;

    public Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(x)*"x".hashCode() + Integer.hashCode(y)*"y".hashCode() + Integer.hashCode(z)*"z".hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;
            return coordinate.getX() == x && coordinate.getY() == y && coordinate.getZ() == z;
        }
        return false;
    }
}
