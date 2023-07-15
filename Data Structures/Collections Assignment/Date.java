// Date.java
// Kevin Liu
// Stores the date of offense

public class Date {
    private String month;
    private int day;
    private String time;

    public Date(String line) {
        String[] s = line.split(" ");
        month = s[0];
        day = Integer.parseInt(s[1]);
        time = s[2];
    }

    @Override
    public String toString() { // makes it easy to display the date
        return month + " " + day + " " + time;
    }

}
