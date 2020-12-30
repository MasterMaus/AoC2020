package aoc2020.day22;

import aoc2020.utilities.InputLoader;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Day22 {

    public static void run() {
        TreeMap<String, ArrayList<Integer>> hands = parseHands("input/day22.txt");

        //part 1
        //String winner = playCombat(copyHands(hands));
//        System.out.println(getScore(hands.get(winner)));

        //part2
        playRecursiveCombat(copyHands(hands));
        System.out.println("test");
    }

    public static String playCombat(TreeMap<String, ArrayList<Integer>> hands) {
        String winner;
        while (hands.size() > 1) {
            nextRound(hands);
        }
        winner = hands.firstKey();
        System.out.println(getScore(hands.get(winner)));

        return winner;
    }

    public static String playRecursiveCombat(TreeMap<String, ArrayList<Integer>> hands) {
        TreeSet<Integer> previousHands = new TreeSet<>();

        while (hands.size() > 1) {

            // if the hand is already in hashCode, terminate and give win to the first player;
            int hash = hands.hashCode();
            if (previousHands.contains(hash)) {
                System.out.println("repeat");
                return hands.firstKey();
            }
            previousHands.add(hash);

            boolean newSubGame = true;
            for (ArrayList<Integer> hand : hands.values()) {
                if (hand.get(0) >= hand.size()) {
                    newSubGame = false; // dont start new subgame and break out of for loop (it doesnt matter anymore what the rest of the cards are)
                    break;
                }
            }

            if(newSubGame) {
                TreeMap<String, Integer> wager = new TreeMap<>();
                for(String player : hands.keySet()) {
                    wager.put(player, hands.get(player).remove(0));
                }
                //System.out.println("new game");
                String subWinner = playRecursiveCombat(copyHands(hands));
                ArrayList<Integer> hand = hands.get(subWinner);
                hand.add(wager.remove(subWinner));
                ArrayList<Integer> rest = new ArrayList<>(wager.values());
                rest.sort(Comparator.reverseOrder());
                hand.addAll(rest);

            } else {
                ArrayList<String> lostPlayers = new ArrayList<>();
                for(String player : hands.keySet()) {
                    if (hands.get(player).isEmpty()) {
                        lostPlayers.add(player);
                        //System.out.println("bingo");
                    }
                }
                for(String player : lostPlayers) {
                    hands.remove(player);
                }
                nextRound(hands);
               // System.out.println("nextRound");
            }
        }

        //play normal round or start new recursive combat,

        System.out.println(getScore(hands.get(hands.firstKey())));
        return hands.firstKey();
    }

    public static TreeMap<String, ArrayList<Integer>> parseHands(String filepath) {
        TreeMap<String, ArrayList<Integer>> hands = new TreeMap<>();
        String input = InputLoader.asString(filepath, "-");
        String[] data = input.split("\n");
        for(String s : data) {
            String[] parsedData = s.split("-");
            String userName = parsedData[0].replace(":", "");
            ArrayList<Integer> hand = new ArrayList<>();
            for(int i = 1; i<parsedData.length; i++) {
                hand.add(Integer.parseInt(parsedData[i]));
            }
            hands.put(userName, hand);
        }
        return hands;
    }

    private static int getScore(ArrayList<Integer> values) {
        int score = 0;
        for (int i = 0; i<values.size(); i++) {
            score += values.get(i)*(values.size()-i);
        }
        return score;
    }

    public static void nextRound(TreeMap<String, ArrayList<Integer>> hands) {
        int currentHigh = 0;
        String currentWinner = "";
        ArrayList<Integer> turn = new ArrayList<>();
        for (String player : hands.keySet()) {
            ArrayList<Integer> hand = hands.get(player);
            int value = hand.remove(0);
            turn.add(value);
            if (value > currentHigh) {
                currentHigh = value;
                currentWinner = player;
            }
        }

        turn.sort(Comparator.reverseOrder());
        hands.get(currentWinner).addAll(turn);
        ArrayList<String> lostPlayers = new ArrayList<>();
        for(String player : hands.keySet()) {
            if (hands.get(player).isEmpty()) {
                lostPlayers.add(player);
            }
        }
        for(String player : lostPlayers) {
            hands.remove(player);
        }
    }


    public static TreeMap<String,ArrayList<Integer>> copyHands(TreeMap<String, ArrayList<Integer>> originalHands) {
        TreeMap<String, ArrayList<Integer>> res = new TreeMap<>();

        for (String player : originalHands.keySet()) {
            ArrayList<Integer> hand = new ArrayList<>(originalHands.get(player));
            res.put(player, hand);
        }
        return res;
    }
}
