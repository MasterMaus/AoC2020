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
            //split string in the parent and childs. but first make the string as: parent,#child,#child
            s = s.replace("bags contain no other bags.","");
            s = s.replaceAll(" bags contain", ",");
            s = s.replaceAll(" bags?[,.]?", ",");
            String[] info = s.split(",");
            String parentId = info[0]; // INITIALIZE
            // retrieve existing bag or create a new one and add it in the list
            Bag parentBag = Bag.getFromList(newObjectList, parentId);
            if(parentBag == null) { //parent does not exist
                parentBag = new Bag(parentId, null);
                newObjectList.add(parentBag);
            }
            for (int i = 1; i<info.length; i++) {
                //TODO: next lines could be way more efficient
                int quantity = Character.getNumericValue(info[i].trim().charAt(0)); // get the quantity of bags
                String childId = info[i].trim().split("[0-9] ")[1]; //get the id of the child
                Bag childBag = Bag.getFromList(newObjectList, childId); //retrieve existing bag or create a new one and add it to list
                if(childBag == null) {
                    childBag = new Bag(childId, parentBag);
                    newObjectList.add(childBag);
                } else {
                    childBag.addParent(parentBag);
                }
                parentBag.addChild(childBag, quantity); //add the child to the parent bag, with its quantity
            }
        }

        String targetId = "shiny gold";
        Bag target = Bag.getFromList(newObjectList, targetId);
        HashSet<Bag> parents = target.getParents();
        HashSet<Bag> allParents = target.getAllParents();
        System.out.println("the " + targetId + " bag can fit in " + allParents.size() + " kind of bags");
        System.out.println("the " + targetId + "bag has " + target.amountOfBags() + " inside");
    }
}
