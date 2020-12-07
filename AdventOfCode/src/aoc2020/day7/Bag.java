package aoc2020.day7;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Bag {

    private ArrayList<Bag> parents = new ArrayList<>();
    private ArrayList<Bag> childs = new ArrayList<>();
    private String id;

    public Bag(String id, Bag parent) {
        this.id = id;
        if(parent != null) {
            parents.add(parent);
        }
    }

    public void addChild(Bag child) {
        childs.add(child);
    }

    public void addParent(Bag parent) {
        parents.add(parent);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Bag) {
            Bag b = (Bag) obj;
            if(b.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    static public Bag getFromList(ArrayList<Bag> list, String id) {

        for(Bag b : list) {
            if(id.equals(b.getId())) {
                return b;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Bag with ID: " + getId();
    }

    public ArrayList<Bag> getAllParents() {
        ArrayList<Bag> res = new ArrayList<Bag>();
        res.addAll(parents);
        for(Bag parent : parents) {
            res.addAll(parent.getAllParents());
        }
        return res;
    }

    public ArrayList<Bag> getParents() {
        return parents;
    }
}
