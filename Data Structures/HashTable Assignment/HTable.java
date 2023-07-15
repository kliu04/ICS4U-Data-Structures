// HTable.java
// Kevin Liu
// Implements the "Hash Table"

import java.util.ArrayList;
import java.util.LinkedList;

public class HTable<T> {
    ArrayList<LinkedList<T>> table; // the table of values
    int items; // num items in hashtable
    double load; // how full HT is
    double maxLoad = 0.7; // max load HT is allowed to have

    public HTable() {
        fill(10); // sets up a HT with size 10
    }

    public void fill(int size) { // fills table with a given number of nulls
        items = 0;
        load = 0;
        table = new ArrayList<LinkedList<T>>();
        for (int i = 0; i < size; i++) {
            table.add(null);
        }
    }

    public void setMaxLoad(double n) { // allows user to change maxload
        if (0.1 >= n && n <= 0.8) {
            maxLoad = n;
        }
    }

    public void setLoad(double n) { // sets the load to a certain %-age
        if (0.1 >= n && n <= 0.8 && n < maxLoad) {
            int size = (int) Math.round(items / n); // using formula items/spots = load
            ArrayList<LinkedList<T>> cur = table;
            fill(size);
            for (LinkedList<T> lis : cur) {
                if (lis != null) {
                    for (T val : lis) {
                        add(val);
                    }
                }
            }
        }
    }

    public void resize() { // makes the HT 10 times the size and reindexes everything
        ArrayList<LinkedList<T>> cur = new ArrayList<LinkedList<T>>(table);
        fill(table.size() * 10); //
        for (LinkedList<T> lis : cur) {
            if (lis != null) {
                for (T val : lis) {
                    add(val);
                }
            }
        }
    }

    public T get(int hash) { // gets a object given its hash
        int spot = Math.abs(hash % table.size()); // calculate the spot
        if (table.get(spot) == null) { // HT does not contain it
            return null;
        }

        for (T val : table.get(spot)) { // can be multiple stuff in the truncated hashcode
            if (val.hashCode() == hash) { // looks for the full hashcode for a match
                return val;
            }
        }
        return null;
    }

    public double getLoad() {
        return load;
    }

    public void add(T val) {
        int spot = Math.abs(val.hashCode() % table.size()); // truncates hashcode depending on size
        if (table.get(spot) == null) { // if there is nothing at that spot
            table.set(spot, new LinkedList<T>());
        }
        table.get(spot).add(val); // adds the value to the linkedlist
        items++;

        load = (double) items / table.size(); // recalculates load, maybe need to resize
        if (load >= maxLoad) {
            resize();
        }
    }

    public void remove(T val) {
        int spot = Math.abs(val.hashCode()) % table.size(); // getsthe spot the value is at
        table.get(spot).remove(val); // removes the value from LList
        if (table.get(spot).size() == 0) { // if there is nothing left, gets rid of LList there
            table.set(spot, null);
        }

    }

    public ArrayList<T> toArray() {
        ArrayList<T> ans = new ArrayList<T>();
        for (LinkedList<T> lis : table) { // goes through every LList in the HT
            if (lis != null) { // LList has at least 1 element
                for (T val : lis) { // adds each element to ans
                    ans.add(val);
                }
            }
        }
        return ans;
    }

    @Override
    public String toString() {
        String ans = "";
        for (LinkedList<T> lis : table) {
            if (lis != null) {
                for (T val : lis) {
                    ans += val + ", ";
                }
            }
        }
        if (ans != "") {
            ans = ans.substring(0, ans.length() - 2);
        }
        return "{" + ans + "}";
    }

}
