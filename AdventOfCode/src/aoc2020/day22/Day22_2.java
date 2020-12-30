package aoc2020.day22;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day22_2 {



        public int games = 0, subgames= 0;


        public Day22_2() {
            String input = InputLoader.asString("input/day22.txt", "-");
            String[] data = input.split("\n");
                String[] parsedData = data[0].split("-");
                ArrayList<Integer> deck1 = new ArrayList<>();
                ArrayList<Integer> deck2 = new ArrayList<>();
                for(int i = 1; i<parsedData.length; i++) {
                    deck1.add(Integer.parseInt(parsedData[i]));
                }
                parsedData = data[1].split("-");
                for(int i = 1; i<parsedData.length; i++) {
                    deck2.add(Integer.parseInt(parsedData[i]));
                }

            List<Integer> deck = game(deck1, deck2);
            long answer = 0;
            int winner = deck.remove(deck.size()-1);
            for (int i = 0; i<deck.size(); i++) {
                answer += deck.get(i) * (deck.size()-i);
            }
            System.out.println("Answer: " + answer + " Winner: " + winner);
        }

        private List<Integer> game(List<Integer> deck1, List<Integer> deck2) {
            deck1 = new ArrayList<>(deck1);
            deck2 = new ArrayList<>(deck2);
            List<int[]> rounds1 = new ArrayList<>();
            List<int[]> rounds2 = new ArrayList<>();

            while (deck1.size() > 0 && deck2.size() > 0) {
                int[] array1 = deck1.stream().mapToInt(i -> i).toArray();
                int[] array2 = deck2.stream().mapToInt(i -> i).toArray();

                for (int i = 0; i < rounds1.size(); i++){
                    int[] r1 = rounds1.get(i);
                    int[] r2 = rounds2.get(i);
                    if (Arrays.equals(r1, array1) || Arrays.equals(r2, array2)) {
                        deck1.clear();
                        deck1.add(1);
                        return deck1;
                    }
                }

                rounds1.add(array1);
                rounds2.add(array2);

                int d1 = deck1.remove(0);
                int d2 = deck2.remove(0);

                if (d1 <= deck1.size() && d2 <= deck2.size()) {
                    List<Integer> deck = game(deck1.subList(0, d1), deck2.subList(0, d2));
                    if (deck.get(deck.size()-1) == 1) {
                        deck1.add(d1);
                        deck1.add(d2);
                    } else {
                        deck2.add(d2);
                        deck2.add(d1);
                    }
                } else {
                    if (d1 > d2) {
                        deck1.add(d1);
                        deck1.add(d2);
                    } else {
                        deck2.add(d2);
                        deck2.add(d1);
                    }
                }
            }
            List<Integer> deck = deck1.size() > 0 ? deck1 : deck2;
            deck.add(deck1.size() > 0 ? 1 : 2);
            return deck;
        }
}
