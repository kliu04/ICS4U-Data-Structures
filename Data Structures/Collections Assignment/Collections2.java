// Collections.java
// Kevin Liu
// Solves Q2 from Assignment

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Collections2 {
    public static void main(String[] args) {
        solve(args);
    }

    public static void solve(String[] args) {
        Set<String> dict = new HashSet<>();
        // the dictionary
        try {
            // read every word from dict
            File f = new File("advanced data structures/data/dictionary.txt");
            Scanner input = new Scanner(f);
            while (input.hasNext()) {
                String s = input.next();
                dict.add(s);
            }

            input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String s : args) {
            // runs thru every argument
            if (dict.contains(s)) {
                // if the word is in the file
                System.out.println(s + " is spelt correctly.");
            } else {
                System.out.println(s + " is spelt incorrectly.");
            }

        }
    }
}
