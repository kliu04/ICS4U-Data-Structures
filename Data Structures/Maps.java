
// import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Maps {
    public static void main(String[] args) {
        // Map<String, String> phoneBook = new HashMap<>();
        // phoneBook.put("Massey", "(519)-969-2530");
        // phoneBook.put("McKenzie", "(519)-867-5309");

        // System.out.println(phoneBook.keySet());
        // System.out.println(phoneBook.values());
        // System.out.println(phoneBook);

        // contains key O(1)
        // contains value O(n)

        Map<String, String> phoneBook = new TreeMap<>();
        phoneBook.put("Massey", "(519)-969-2530");
        phoneBook.put("McKenzie", "(519)-867-5309");

        System.out.println(phoneBook.keySet());
        System.out.println(phoneBook.values());
        System.out.println(phoneBook);

        for (String name : phoneBook.keySet()) {
            System.out.println(phoneBook.get(name));
        }

        // contains key O(log n)
        // contains value O(n)
    }
}
