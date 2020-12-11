package aoc2020.day11;

import aoc2020.day7.Bag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Chair {

    private int[] location;
    private int[] grid;

    private HashSet<Chair> allChairs;
    private HashSet<Chair> neighborChairs;
    private boolean occupied;

    public Chair(int[] location, HashSet<Chair> seats, int[] grid, boolean occupied) {
        this.location = location;
        this.grid = grid;
        this.occupied = occupied;
        allChairs = seats;
        neighborChairs = getNeighbors(location, seats);
        for(Chair c : neighborChairs) {
            c.addNeighbor(this);
        }

    }

    public int[] getLocation() {
        return location;
    }

    public boolean hasNeighbor(Chair neighbor) {
        return neighborChairs.contains(neighbor);
    }

    public void addNeighbor(Chair chair) {
        neighborChairs.add(chair);
        if(!chair.hasNeighbor(this)) {
            chair.addNeighbor(this);
        }
    }

    public void swapOccupation() {
        occupied = !occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public static int adjacentOccupied(HashSet<Chair> data) {
        int res = 0;

        for(Chair chair : data) {
            if (chair.isOccupied()) {
                res++;
            }
        }

        return res;
    }

    public int neighborsOccupied() {
        int res = 0;
        //System.out.println("amount of neighbors: " + neighborChairs.size());
        for(Chair neighbor : neighborChairs) {
            if (neighbor.isOccupied()) {
                res ++;
            }
        }
        return res;
    }

    public HashSet<Chair> getChairsInSight() {
        HashSet<Chair> res = new HashSet<>();
        for (int direction = 0; direction < 8; direction++) {
            Chair c = getChairInSight(direction);
            if (c!=null) {
                res.add(c);
            }
        }
        return res;
    }

    public Chair getChairInSight(int direction) {
        int y = location[1];
        int x = location[0];
        int max_x = grid[0];
        int max_y = grid[1];
        Chair res = null;
        if (direction == 0) { //UP
            for (int i = 0; i < y; i++) {
                res = getChairWithLocation(new int[]{x, i}, allChairs);
                if (res != null) {
                    return res;
                }
            }
        } else if (direction == 1) { //DOWN
            for (int i = max_y; i > y; i--) {
                res = getChairWithLocation(new int[]{x, i}, allChairs);
                if (res != null) {
                    return res;               }
            }
        } else if (direction == 2) { //LEFT
            for (int i = 0; i < x; i++) {
                res = getChairWithLocation(new int[]{i, y}, allChairs);
                if (res != null) {
                    return res;
                }
            }
        } else if (direction == 3) {//RIGHT
            for (int i = max_x; i > x; i--) {
                res = getChairWithLocation(new int[]{i, y}, allChairs);
                if (res != null) {
                    return res;
                }
            }
        } else if (direction == 4) {//UP-LEFT
            int x2 = x - 1;
            int y2 = y - 1;
            while (x2 >= 0 && y2 >= 0) {
                res = getChairWithLocation(new int[]{x2, y2}, allChairs);
                if (res != null) {
                    return res;
                }
                x2--;
                y2--;
            }
        } else if (direction == 5) {//UP-RIGHT
            int x2 = x + 1;
            int y2 = y - 1;
            while (x2 <= max_x && y2 >= 0) {
                res = getChairWithLocation(new int[]{x2, y2}, allChairs);
                if (res != null) {
                    return res;
                }
                x2++;
                y2--;
            }
        } else if (direction == 6) { //DOWN-LEFT
            int x2 = x - 1;
            int y2 = y + 1;
            while (x2 >= 0 && y2 <= max_y) {
                res = getChairWithLocation(new int[]{x2, y2}, allChairs);
                if (res != null) {
                    return res;
                }
                x2--;
                y2++;
            }
        } else if (direction == 7) { //DOWN-RIGHT
            int x2 = x + 1;
            int y2 = y + 1;
            while (x2 <= max_x && y2 <= max_y) {
                res = getChairWithLocation(new int[]{x2, y2}, allChairs);
                if (res != null) {
                    return res;
                }
                x2++;
                y2++;
            }
        }
        return res;
    }



    @Override
    public int hashCode() {
        return location.hashCode(); // hashcode is the hashcode of the location + the hashcode of a constant
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Chair) {
            Chair c = (Chair) obj;
            if(c.getLocation().equals(location)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (occupied) {
            return "chair at position " + Arrays.toString(location) + " is occupied";
        } else {
            return "chair at position " + Arrays.toString(location) + " is free";
        }
    }

    public static HashSet<Chair> getNeighbors(int[] loc, HashSet<Chair> seats) {
        HashSet<Chair> res = new HashSet<>();
        for(int x = loc[0]-1; x<=loc[0]+1; x++) {
            for(int y = loc[1]-1; y<=loc[1]+1; y++) {
                int[] neighborLoc = {x, y};
                if (!Arrays.equals(loc, neighborLoc)) {
                    Chair c = getChairWithLocation(neighborLoc, seats);
                    if(c!=null) {
                        res.add(c);
                    }
                }
            }
        }
        return res;
    }

    public static Chair getChairWithLocation(int[] loc, HashSet<Chair> seats) {
        for(Chair c : seats) {
            if (Arrays.equals(loc, c.getLocation())) {
                return c;
            }
        }
        return null;
    }

}
