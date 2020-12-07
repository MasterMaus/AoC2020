package aoc2020.day7;

import aoc2020.Util;

import java.io.File;
import java.util.ArrayList;

public class Day7 {
    public static void run() {
        System.out.println("Solutions day 7:");
        File file = new File("day7_2.txt");
        ArrayList<Bag> newObjectList = new ArrayList<>(); //keep track of the processed objects
        ArrayList<String> input = Util.readFile(file);
        for (String s: input) {
            s = s.replaceAll(" bags contain ([0-9] )*", ",");
            s = s.replace(",no other bags.","");
            s = s.replaceAll(" bags?[,.]( [0-9] +)?", ",");
            String[] info = s.split(",");
            String parentId = info[0]; // INITIALIZE
            Bag parentBag = Bag.getFromList(newObjectList, parentId);
            if(parentBag == null) { //parent does not exist
                parentBag = new Bag(parentId, null);
                newObjectList.add(parentBag);
            }
            for (int i = 1; i<info.length; i++) {
                String childId = info[i];
                Bag childBag = Bag.getFromList(newObjectList, childId);
                if(childBag == null) {
                    childBag = new Bag(childId, parentBag);
                    newObjectList.add(childBag);
                } else {
                    childBag.addParent(parentBag);
                }
                parentBag.addChild(childBag);
            }
            //process the input file into whatever we need
        }

        Bag target = Bag.getFromList(newObjectList, "shiny gold");
        ArrayList<Bag> parents = target.getParents();
        ArrayList<Bag> allParents = target.getAllParents();
        for(int i = 0; i < allParents.size(); i++) {
            Bag parent = allParents.get(i);
            int duplicate = allParents.lastIndexOf(parent);
            while (duplicate != i) {
                System.out.println("removing " + parent);
                allParents.remove(i);
                duplicate = allParents.indexOf(parent);
            }
        }
        System.out.println(allParents.size());
        for(Bag p : allParents) {
            System.out.println(p);
        }

    }
}
