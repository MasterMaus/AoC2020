package aoc2020.day17;

public class Coordinate {

    private int x, y, z, w;

    public Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 0;
    }

    public Coordinate(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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

    public int getW() {
        return w;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(x)*"x".hashCode() + Integer.hashCode(y)*"y".hashCode() + Integer.hashCode(z)*"z".hashCode() + Integer.hashCode(w)*"w".hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;
            return coordinate.getX() == x && coordinate.getY() == y && coordinate.getZ() == z && coordinate.getW() == w;
        }
        return false;
    }
}
