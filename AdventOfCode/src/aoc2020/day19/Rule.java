package aoc2020.day19;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class Rule {

    public int getId() {
        return id;
    }

    private int id;
    private HashSet<ArrayList<Rule>> ruleSequences;

    public HashSet<String> getMessages() {
        return messages;
    }

    private HashSet<String> messages;
    private TreeMap<Integer, Rule> ruleSet;

    public boolean isSolved() {
        return solved;
    }

    private boolean solved;

    public Rule (int id, TreeMap<Integer, Rule> ruleSet) {
        this.id = id;
        this.ruleSet = ruleSet;
        ruleSequences = new HashSet<>();
        messages = new HashSet<>();
        solved = false;
    }
    public Rule (int id, String rules, TreeMap<Integer, Rule> ruleSet) {
        this.id = id;
        this.ruleSet = ruleSet;
        ruleSequences = new HashSet<>();
        messages = new HashSet<>();
        setRules(rules);
    }

    private static ArrayList<Integer> parseIntsFromString(String target, String regex) {
        ArrayList<Integer> res = new ArrayList<>();
        String[] ints = target.split(regex);
        for (String s : ints) {
            res.add(Integer.parseInt(s));
        }
        return res;
    }

    public HashSet<ArrayList<Rule>> getRuleSequences() {
        return ruleSequences;
    }

    @Override
    public String toString() {
        if (id == 95) return "b"; //HARDCODED... in real dataset we use 95
        if (id == 104) return "a"; //HARDCODED... in real dataset we use 104
        return Integer.toString(id);
    }
        public void setRules(String rules) {
        if (rules.contains("a")) {
            messages.add("a");
            solved = true;
        } else if (rules.contains("b")) {
            messages.add("b");
            solved = true;
        } else {
            String[] ruleString = rules.split(" \\| ");
            for (String s : ruleString) {
                ArrayList<Rule> someRuleSequence = new ArrayList<>();
                for (int i : parseIntsFromString(s, " ")) {
                    Rule rule;
                    if(ruleSet.containsKey(i)) {
                        rule = ruleSet.get(i);
                    } else {
                        rule = new Rule(i, ruleSet);
                        ruleSet.put(i, rule);
                    }
                    someRuleSequence.add(rule);
                }
                ruleSequences.add(someRuleSequence);
            }
            solved = false;
        }
    }

    public void replaceRulesInSequences(Rule rule) {
        HashSet<ArrayList<Rule>> newRuleSequences = new HashSet<>();
        for(ArrayList<Rule> ruleSequence : ruleSequences) {
            if (ruleSequence.contains(rule)) {
                for(ArrayList<Rule> replacementSequence : rule.getRuleSequences()) {
                    ArrayList<Rule> newRuleSequence = new ArrayList<>(ruleSequence);// i thnk something goes wrong here
                    int index = newRuleSequence.indexOf(rule);
                    newRuleSequence.remove(index);
                    newRuleSequence.addAll(index, replacementSequence);
                    newRuleSequences.add(newRuleSequence);
                }
            } else { // this rulesequence does not contain the rule that needs to be replaced
                newRuleSequences.add(ruleSequence);
            }
        }
        ruleSequences = newRuleSequences;


    }

    public ArrayList<Rule> getReplacementList() {
        ArrayList<Rule> replacementsList = new ArrayList<>(); //TRICKY
        for(ArrayList<Rule> seq : getRuleSequences()) {
            for (Rule r : seq) {
                if(!r.isSolved()) {
                    replacementsList.add(r);
                }
            }
        }
        return replacementsList;
    }

    public void setMessages() {
        for (ArrayList<Rule> seq: ruleSequences) {
            String message = "";
            for (Rule r : seq) {
                message = message + r.toString();
            }
            messages.add(message);
        }
    }

}
