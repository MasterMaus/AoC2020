package aoc2020.day11;

import aoc2020.day7.Bag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Chair {

    private int location[];
    private HashSet<Chair> neighborChairs;
    private boolean occupied;

    public Chair(int[] location, HashSet<Chair> seats) {
        this.location = location;
        occupied = false;
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

    public int amountOfNeighbors() {
        return neighborChairs.size();
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

    private static Chair getChairWithLocation(int[] loc, HashSet<Chair> seats) {
        Chair res = null;
        for(Chair c : seats) {
            if (Arrays.equals(loc, c.getLocation())) {
                return c;
            }
        }
        return res;
    }

}
