package aoc2020.day21;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class Day21 {

    public static void run() {
        ArrayList<String> allIngredients = new ArrayList<>();
        TreeMap<String, TreeSet<String>> allergen2Ingredients = new TreeMap<>();
        TreeMap<String, String> ingredient2allergen = new TreeMap<>();
        ArrayList<String> input = InputLoader.asList("input/day21.txt");
        for (String s: input) {
            String[] data = s.split(" \\(contains ");
            TreeSet<String> ingredientList = parseIngredients(data[0], " ");
            TreeSet<String> allergenList = parseIngredients(data[1].replace(")", ""), ", ");
            allIngredients.addAll(ingredientList);

            for(String allergen : allergenList) {
                if(allergen2Ingredients.containsKey(allergen)) {
                    allergen2Ingredients.get(allergen).retainAll(ingredientList);
                    // retain all from both sets, point to new set
                } else {
                    allergen2Ingredients.put(allergen, new TreeSet<>(ingredientList));
                    //System.out.println(allergen);
                }
            }

            //one of the ingreidents in the list has to be 1 of the allergens. if allergen is in global map, retain all ingredients that are in this set too
            // else map allergen to set


        }
        for(String allergen : allergen2Ingredients.keySet()) {
            System.out.println(allergen + allergen2Ingredients.get(allergen));
            allIngredients.removeAll(allergen2Ingredients.get(allergen));
        }
        System.out.println(allIngredients.size());

        //System.out.println(allIngredients.contains("nhdjth"));
    }

    private static TreeSet<String> parseIngredients(String ingredientList, String regex) {
        TreeSet<String> res = new TreeSet<>();
        //System.out.println(ingredientList);
        for(String ingredient : ingredientList.split(regex)) {
            res.add(ingredient);
            //System.out.println(ingredient);
        }

        return res;
    }

}
