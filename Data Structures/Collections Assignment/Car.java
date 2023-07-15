// Car.java
// Kevin Liu
// Stores info about each car for Q4

import java.util.ArrayList;
import java.util.List;

public class Car {
    // lists of all offenses
    private List<Date> dates = new ArrayList<>();
    private List<String> init = new ArrayList<>();

    public void add(Date d, String i) {
        dates.add(d);
        init.add(i);
    }

    @Override
    public String toString() {
        String ans = "";
        for (int i = 0; i < dates.size(); i++) {
            // loops through each offense and prints it
            ans += dates.get(i) + " " + init.get(i);
            if (i < dates.size() - 1) {
                ans += '\n';
            }
        }
        return ans;
    }

}
