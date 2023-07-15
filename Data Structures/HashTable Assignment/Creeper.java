// Creeper.java
// Kevin Liu
// Implements the "Creeper" object, used to keep track of each creeper

import java.awt.Color;
import java.awt.Point;

public class Creeper {
    private int x, y, LH, HS, EB;
    private int num = 0;

    public Creeper(String data) {
        String[] split = data.split(" "); // takes in the parameters from the raw string
        x = Integer.parseInt(split[0]);
        y = Integer.parseInt(split[1]);
        LH = Integer.parseInt(split[2]);
        HS = Integer.parseInt(split[3]);
        EB = Integer.parseInt(split[4]);
        num++;
    }

    public void add(Creeper c) {
        LH = (num * LH + c.LH) / (num + 1); // calculates the average LH, HS, EB
        HS = (num * HS + c.HS) / (num + 1); // keeps track of number of elements so that the total amount can be found
        EB = (num * EB + c.EB) / (num + 1);
        num++;
    }

    public Point getCoords() { // returns the point the creeper is stored at
        return new Point(x, y);
    }

    public Color getColor() {
        // maps the -100 -> 100 scale to 0 -> 255 linearly
        final double m = 1.275; // slope - calculated from the formula delta y / delta x (255/200)
        final double b = 127.5; // y-int - calculated from subbing in (100, 255) into above
        // converts each parameter into either r, g, or b value for the color
        return new Color((int) Math.round(LH * m + b), (int) Math.round(HS * m + b), (int) Math.round(EB * m + b));
    }

    @Override
    public int hashCode() { // arbitrary co-primes multipled by the co-ordinate making a unique-ish hashcode
        return x * 1000 + y * 777;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d) {LH - %d, HS - %d, EB- %d}", x, y, LH, HS, EB);
    }
}
