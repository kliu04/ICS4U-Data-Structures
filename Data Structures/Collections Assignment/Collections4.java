// Collections.java
// Kevin Liu
// Solves Q4 from Assignment

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class Collections4 {
    static Map<String, Car> cars = new HashMap<>(); // map mapping plates to offenses

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("1. Show all offenses belonging to a plate");
            System.out.println("2. Add offense");
            System.out.println("3. Exit");

            int choice = Integer.parseInt(input.nextLine()); // 1, 2, or 3
            if (choice == 3) {
                break;
            }
            if (choice == 1) {
                read(); // loads data from file
                String plate = input.nextLine(); // gets user inputted plate
                System.out.println(cars.get(plate));
            } else if (choice == 2) {
                String plate = input.nextLine();
                try {
                    File f = new File("advanced data structures/data/cars.txt");

                    Scanner s = new Scanner(f);
                    List<String> list = new ArrayList<>(); // list of everything in the file
                    while (s.hasNext()) {
                        list.add(s.nextLine());
                    }
                    s.close();

                    String date = input.nextLine(); // new offense data
                    String init = input.nextLine();

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).equals(plate)) { // finds the plate that the offense is under
                            list.add(i + 2, init); // adds the new data
                            list.add(i + 2, date);
                            list.set(i + 1, Integer.parseInt(list.get(i + 1)) + 1 + ""); // changes the num of offenses
                            break;

                        }
                    }
                    FileWriter write = new FileWriter(f);
                    for (String str : list) { // writes the arraylist back to the file
                        write.write(str + System.lineSeparator());
                    }
                    write.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

        input.close();
    }

    public static void read() {
        try {
            File f = new File("advanced data structures/data/cars.txt");
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                // reads offense data
                int n = Integer.parseInt(s.nextLine());
                for (int i = 0; i < n; i++) {
                    String plate = s.nextLine();
                    // plate #
                    Car c = new Car();
                    int num = Integer.parseInt(s.nextLine());
                    // num offenses
                    for (int j = 0; j < num; j++) {
                        // data for each offense
                        Date d = new Date(s.nextLine());
                        String init = s.nextLine();
                        c.add(d, init);
                    }
                    cars.put(plate, c);

                }
            }

            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}