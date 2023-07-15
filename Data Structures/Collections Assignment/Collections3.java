// Collections.java
// Kevin Liu
// Solves Q3 from Assignment

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Collections3 {
    public static void main(String[] args) {
        Map<String, TreeSet<String>> map = new HashMap<>();
        try {

            Scanner scan = new Scanner(new BufferedReader(new FileReader("advanced data structures/data/picks.txt")));
            while (scan.hasNextLine()) {
                // gets every student
                String[] s = scan.nextLine().split(",");
                // reverses first and last name
                String name = s[1] + " " + s[0];
                String movie = s[2];
                if (map.containsKey(movie)) {
                    // there are already students watching movie
                    map.get(movie).add(name);
                } else {
                    // new movie
                    map.put(movie, new TreeSet<String>());
                    map.get(movie).add(name);
                }
            }

            scan.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            File f = new File("movies.txt");
            f.createNewFile();
            // make new file to write to

            FileWriter write = new FileWriter(f);

            for (String s : map.keySet()) {
                // for each movie
                TreeSet<String> set = map.get(s);
                // sorts the students based off last name
                String[] arr = set.toArray(new String[set.size()]);
                // array of all students watching
                for (int i = 0; i < arr.length; i++) {
                    // reverse first and last name now that array is sorted
                    String[] name = arr[i].toString().split(" ");
                    String first = name[1];
                    String l = name[0];
                    arr[i] = first + " " + l;
                }
                // writes movie and students to file
                write.write(s + " " + Arrays.toString(arr) + System.lineSeparator());

            }
            write.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
