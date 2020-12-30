package aoc2020.day23;

public class Cup {

    private int id;
    private Cup left;
    private Cup right;
    boolean inCircle;



    public Cup(int id, Cup destination) {
        this.id = id;
        if (destination == null) {
            left = this;
            right = this;
            inCircle = true;
        } else {
            placeCup(destination);
        }
    }


    public int getId() {
        return id;
    }

    public Cup getRightCup() {
        return right;
    }

    public Cup getLeftCup() {
        return left;
    }

    public void setLeftCup(Cup leftCup) {
        left = leftCup;
        if (leftCup.getRightCup() != this) {
            leftCup.setRightCup(this);
        }
    }

    public void setRightCup(Cup rightCup) {
        right = rightCup;
        if (rightCup.getLeftCup() != this) {
            rightCup.setLeftCup(this);
        }
    }

    public void placeCup(Cup destination) {
        setRightCup(destination.getRightCup());
        setLeftCup(destination);
        inCircle = true;
    }

    public void removeCup() {
        left.setRightCup(right);
        left = null;
        right = null;
        inCircle = false;
    }

    public boolean isInCircle() {
        return inCircle;
    }

    @Override
    public String toString() {
        return "cup label: " + id;
    }
}
