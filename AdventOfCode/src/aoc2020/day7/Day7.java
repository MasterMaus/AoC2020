package aoc2020.day7;

import aoc2020.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class Day7 {
    public static void run() {
        System.out.println("Solutions day 7:");
        File file = new File("day7.txt");
        HashSet<Bag> newObjectList = new HashSet<>(); //keep track of the processed objects
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
        HashSet<Bag> parents = target.getParents();
        HashSet<Bag> allParents = target.getAllParents();
        System.out.println("the shiny gold bag can fit in " + allParents.size() + " kind of bags");

        HashSet<Bag> allChilds = target.getAllsChilds();
        System.out.println("the shiny gold bag can fit " + allChilds.size() + " different kind of bags");
    }
}
