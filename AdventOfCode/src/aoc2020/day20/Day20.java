package aoc2020.day20;

import aoc2020.utilities.CommonFunctions;
import aoc2020.utilities.InputLoader;
import aoc2020.day17.Coordinate;
import aoc2020.day20.Grid;

import java.util.*;

public class Day20 {
    public static void run() {
        TreeMap<Integer, Grid> images = new TreeMap<>();
        ArrayList<String> input = InputLoader.asList("input/day20.txt");
        Grid image = new Grid();
        int id = 0;
        int y = 0;
        for (String s: input) {
            if (s.matches("Tile [0-9]{4}:")) {
                id = Integer.parseInt(s.substring(5,9));
                image.addId(id);
            } else if (s.isEmpty()) {
                images.put(id, image);
                image = new Grid();
                y=0;
            } else if (s.matches("[.|#]+")) {
                char[] row = s.toCharArray();
                for (int x = 0; x < row.length; x++) {
                    if (row[x] == '#') {
                        image.addPoint(new Coordinate(x,y));
                    }
                }
                y++;
            } else {
                System.out.println("inputfile does not match protocol");
            }
            //process the input file into whatever we need
        }

        //System.out.println(images.keySet().size());
        part2(images);

        for (Grid g : images.values()) {
            if (g.getSides().size() == 3) {
                System.out.println(g.getId());
            }
        }

        int res = 0; // STORE RESULT

    }


    private static void part2(TreeMap<Integer, Grid> images) {
        for (Grid image : images.values()) {
            int i = image.getId();
            TreeSet<Integer> up = image.getRow(0);
            TreeSet<Integer> down = image.getRow(9);
            TreeSet<Integer> left = image.getColumn(0);
            TreeSet<Integer> right = image.getColumn(9);
            for (Grid neighbor : images.values()) {
                if (neighbor.getId() > i) {

                    TreeSet<Integer> nUp = neighbor.getRow(0);
                    TreeSet<Integer> nDown = neighbor.getRow(9);
                    TreeSet<Integer> nLeft = neighbor.getColumn(0);
                    TreeSet<Integer> nRight = neighbor.getColumn(9);

                    //up
                    if (up.equals(nUp)) {
                        combineGrids(image, neighbor);
                    } else if (up.equals(nDown)) {
                        combineGrids(image, neighbor);
                    } else if (up.equals(nLeft)) {
                        combineGrids(image, neighbor);
                    } else if (up.equals(nRight)) {
                        combineGrids(image, neighbor);
                    } else if (down.equals(nUp)) {
                        combineGrids(image, neighbor);
                    } else if (down.equals(nDown)) {
                        combineGrids(image, neighbor);
                    } else if (down.equals(nLeft)) {
                        combineGrids(image, neighbor);
                    } else if (down.equals(nRight)) {
                        combineGrids(image, neighbor);
                    } else if (left.equals(nUp)) {
                        combineGrids(image, neighbor);
                    } else if (left.equals(nDown)) {
                        combineGrids(image, neighbor);
                    } else if (left.equals(nLeft)) {
                        combineGrids(image, neighbor);
                    } else if (left.equals(nRight)) {
                        combineGrids(image, neighbor);
                    } else if (right.equals(nUp)) {
                        combineGrids(image, neighbor);
                    } else if (right.equals(nDown)) {
                        combineGrids(image, neighbor);
                    } else if (right.equals(nLeft)) {
                        combineGrids(image, neighbor);
                    } else if (right.equals(nRight)) {
                        combineGrids(image, neighbor);
                    } else {
                        up = invert(up);
                        down = invert(down);
                        right = invert(right);
                        left = invert(left);
                        if (up.equals(nUp)) {
                            combineGrids(image, neighbor);
                        } else if (up.equals(nDown)) {
                            combineGrids(image, neighbor);
                        } else if (up.equals(nLeft)) {
                            combineGrids(image, neighbor);
                        } else if (up.equals(nRight)) {
                            combineGrids(image, neighbor);
                        } else if (down.equals(nUp)) {
                            combineGrids(image, neighbor);
                        } else if (down.equals(nDown)) {
                            combineGrids(image, neighbor);
                        } else if (down.equals(nLeft)) {
                            combineGrids(image, neighbor);
                        } else if (down.equals(nRight)) {
                            combineGrids(image, neighbor);
                        } else if (left.equals(nUp)) {
                            combineGrids(image, neighbor);
                        } else if (left.equals(nDown)) {
                            combineGrids(image, neighbor);
                        } else if (left.equals(nLeft)) {
                            combineGrids(image, neighbor);
                        } else if (left.equals(nRight)) {
                            combineGrids(image, neighbor);
                        } else if (right.equals(nUp)) {
                            combineGrids(image, neighbor);
                        } else if (right.equals(nDown)) {
                            combineGrids(image, neighbor);
                        } else if (right.equals(nLeft)) {
                            combineGrids(image, neighbor);
                        } else if (right.equals(nRight)) {
                            combineGrids(image, neighbor);
                        }
                    }
                }
            }
        }

    }

    private static TreeSet<Integer> invert(TreeSet<Integer> target) {
        TreeSet<Integer> res = new TreeSet<>();
        for (int i : target) {
            res.add((i-9)*-1);
        }
        return res;
    }

    private static void combineGrids(Grid image, Grid neighbor) {
        image.addSide(neighbor);
        neighbor.addSide(image);
    }
}
