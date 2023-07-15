// HashAssign1.java
// Kevin Liu
// Solves Q1 from Assignment

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashAssign1 {
    static HTable<String> words = new HTable<String>();

    public static void main(String[] args) {

        Scanner keys = new Scanner(System.in);
        String letters = keys.nextLine(); // gets user input
        keys.close();

        try {
            File dict = new File("advanced data structures/data/dictionary.txt");
            Scanner read = new Scanner(dict); // reads every word from dict
            while (read.hasNextLine()) {
                words.add(read.nextLine());
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }

        perms("", letters); // finds all perms of the letters

    }

    private static void perms(String has, String left) {
        // same function as previous assignment to find all perms
        if (left.length() == 0) {
            verify(has); // new permuation of letters
        }

        else {
            for (int i = 0; i < left.length(); i++) {
                perms(has + left.charAt(i), left.substring(0, i) + left.substring(i + 1));
            }
        }

    }

    public static void verify(String check) {
        // sees if the permuation is a valid word
        String ans = words.get(check.hashCode());
        if (ans != null) {
            System.out.println(ans);
        }
    }

}
