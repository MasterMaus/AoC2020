package aoc2020.day11;

import aoc2020.day7.Bag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Chair {

    int location[];
    HashSet<Chair> neighborChairs = new HashSet<>();
    boolean occupied;

    public Chair(int[] location, HashMap<int[], Chair> hall) {
        this.location = location;
        occupied = false;
        for (int[] neighborsLoc : getNeighborLocations(location)) {
            Chair neighbor = hall.get(neighborsLoc);
            if(neighbor != null) {
                addNeighbor(neighbor); // add neighbor to the set of neighbors
                neighbor.addNeighbor((this)); // add this object to its set of neighbors
            }
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
    }

    public void swapOccupation() {
        occupied = !occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public int neighborsOccupied() {
        int res = 0;
        System.out.println("amount of neighbors: " + neighborChairs.size());
        for(Chair neighbor : neighborChairs) {
            if (neighbor.isOccupied()) {
                res ++;
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

    public static ArrayList<int[]> getNeighborLocations(int[] loc) {
        ArrayList<int[]> res = new ArrayList<>();
        for(int x = loc[0]-1; x<=loc[0]+1; x++) {
            for(int y = loc[1]-1; y<=loc[1]+1; y++) {
                int[] neighborLoc = {x, y};
                if (!Arrays.equals(loc, neighborLoc)) {
                    res.add(neighborLoc);
                }
            }
        }
        return res;
    }

}
