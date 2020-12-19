package aoc2020.day19;

import aoc2020.utilities.InputLoader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class Day19 {
    public static int i = 0;

    public static void run() {
        TreeMap<Integer, Rule> ruleSet = new TreeMap<>();
        TreeMap<Integer, Rule> solvedRuleSet = new TreeMap<>();
        ArrayList<String> messages = new ArrayList<>();
        ArrayList<String> input = InputLoader.asList("input/day19.txt");
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            if (s.isEmpty()) {
                messages = new ArrayList<>(input.subList(i+1, input.size()));
                break;
            }
            String[] rule = s.split(": ");
            int id = Integer.parseInt(rule[0]);
            String rules = rule[1];
            if(ruleSet.containsKey(id)) {
                ruleSet.get(id).setRules(rules);
            } else {
                ruleSet.put(id, new Rule(id, rules, ruleSet));
            }
            if(ruleSet.get(id).isSolved()) {
                solvedRuleSet.put(id, ruleSet.get(id));
            }
        }

        Rule start = ruleSet.get(0);
        solve(start);
        start.setMessages();
        HashSet<String> validMessages = start.getMessages();
        int res = 0;
        HashSet<String> invalidMessages = new HashSet<>();

        for(String message : messages) {
            if(validMessages.contains(message)) {
                res++;
            } else {
                if (message.length() <= 24) {
                    invalidMessages.add(message);
                }
            }
        }

        messages.removeAll(invalidMessages);

        System.out.println(i);
        System.out.println("THERE ARE " + res + " valid messages");
        System.out.println("THERE ARE A MAXIMUM OF " + messages.size() + " valid messages");
    }

    private static int solve(Rule rule) {
        ArrayList<Rule> replacementsList = rule.getReplacementList();
        int res = replacementsList.size();
        for (Rule replacement : replacementsList) {
            solve(replacement);
            rule.replaceRulesInSequences(replacement);
        }
        return res;
    }


}
