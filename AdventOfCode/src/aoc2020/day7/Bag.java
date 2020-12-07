package aoc2020.day7;

import java.util.HashSet;
import java.util.Set;

public class Bag {

    private HashSet<Bag> parents = new HashSet<>();
    private HashSet<Bag> childs = new HashSet<>();
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

    static public Bag getFromList(HashSet<Bag> list, String id) {

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

    public HashSet<Bag> getAllParents() {
        HashSet<Bag> res = new HashSet<Bag>();
        res.addAll(parents);
        for(Bag parent : parents) {
            res.addAll(parent.getAllParents());
        }
        return res;
    }

    public HashSet<Bag> getParents() {
        return parents;
    }

    public HashSet<Bag> getChilds() {
        return childs;
    }

    public HashSet<Bag> getAllsChilds() {
        HashSet<Bag> res = new HashSet<Bag>();
        res.addAll(childs);
        for(Bag child : childs) {
            res.addAll(child.getAllParents());
        }
        return res;
    }
}
