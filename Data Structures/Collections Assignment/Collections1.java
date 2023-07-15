// Collections.java
// Kevin Liu
// Solves Q1 from Assignment

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Map;

public class Collections1 {
    public static void main(String[] args) {
        String fPath = "Data Structures/file.txt";
        solve(fPath); // file name argument

    }

    public static void solve(String fPath) {
        Map<String, Integer> words = new HashMap<>(); // maps each word to # occurences
        int n = 0; // total # of words
        try {
            File f = new File(fPath);
            Scanner input = new Scanner(f);
            while (input.hasNext()) {
                String s = input.next(); // each word from file
                s = s.replaceAll("[^a-zA-Z']", "").toLowerCase();
                // strips all punctuation and non letter chars
                if (s.isBlank()) { // gets rid of spaces
                    continue;
                }
                n++;
                if (words.containsKey(s)) { // repeated word
                    words.put(s, words.get(s) + 1); // increment occurences
                } else {
                    words.put(s, 1); // makes a new value at that spot
                }
            }

            input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // sorting from https://stackoverflow.com/a/19671853

        Map<String, Integer> sortedMap = words.entrySet().stream()
                .sorted(Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        for (String s : sortedMap.keySet()) {
            // each word amount` is rounded to 2 decimals, and then changed into percent
            System.out.println(s + " " + Math.round((double) words.get(s) / n * 10000) / 100.0 + "%");
        }

    }

}
