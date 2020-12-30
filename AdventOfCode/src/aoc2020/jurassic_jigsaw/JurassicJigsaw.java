package aoc2020.jurassic_jigsaw;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;
import java.util.TreeSet;

public class JurassicJigsaw {

    public static void run(String inputPath) {
        ArrayList<Grid> images = parseImages(inputPath);
        Grid image = pasteGrids(images);



    }

    private static Grid pasteGrids(ArrayList<Grid> images) {  //todo something goes wrong. lets try to figure out if matching goes correct, and why the pasting goes boom
        Grid result = new Grid(0); //id 0 --> full grid
        ArrayList<Grid> rows = new ArrayList<>();
        while (!images.isEmpty()) {
            Grid row = new Grid(1); //id 1 --> row on grid
            row = row.combineGrid(images.remove(0),0,0);
            TreeSet<Coordinate> edge = row.getColumn(row.getRange()[1]);
            Grid nextGrid = searchMatchingGrid(images, edge);
            while (nextGrid != null) {
                row = row.combineGrid(nextGrid,row.getRange()[1]-1,0);
                edge = row.getColumn(row.getRange()[1]);
                nextGrid = searchMatchingGrid(images, edge);
            }
            row = row.mirrorVertical();
            edge = row.getColumn(row.getRange()[1]);
            nextGrid = searchMatchingGrid(images, edge);
            while (nextGrid != null) {
                row = row.combineGrid(nextGrid,row.getRange()[1]-1,0);
                edge = row.getColumn(row.getRange()[1]);
                nextGrid = searchMatchingGrid(images, edge);
            }
        }

        // paste x rows together to get the whole picture

        return result;
    }

    private static Grid searchMatchingGrid(ArrayList<Grid> images, TreeSet<Coordinate> reference) {
        for (int i = 0; i<images.size(); i++) {
            Grid image = images.get(i);
            for (int r = 0; r<4; r++) {
                if (image.getColumn(image.getRange()[0]).equals(reference)) {
                    return images.remove(i);
                } else if (image.getColumn(image.getRange()[1]).equals(reference)) {
                    image = image.mirrorVertical();
                    return images.remove(i);
                }
                image = image.rotateLeft();
            }

        }


        return null;
    }

    private static ArrayList<Grid> parseImages(String inputPath) {
        ArrayList<String> input = InputLoader.asList(inputPath);
        ArrayList<Grid> images = new ArrayList<>();
        Grid image = new Grid(0);
        int y = 0;
        for (String s : input) {
            if (s.matches("[.|#]+")) {
                char [] row = s.toCharArray();
                for (int x = 0; x < row.length; x++) {
                    if(row[x] == '#') {
                        image.addCoordinate(new Coordinate(x,y));
                    }
                }
                y++;
            } else if (s.matches("Tile [0-9]{4}:")) {
                String id = s.substring(5,9);
                image = new Grid(Integer.parseInt(id));
                images.add(image);
                y=0; // set the y coordinate back to 0 for a new grid;
            } else if (s.isEmpty()) {
                //empty line --> next line should be the new identifier for grid
            } else {
                System.out.println("inputfile does not match protocol");
                return null;
            }
        }
        return images;
    }

}
