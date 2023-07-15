
// import java.util.HashSet;
import java.util.ArrayList;
// import java.util.HashSet;
import java.util.List;
// import java.util.Set;
import java.util.TreeSet;

public class Sets {
    public static void main(String[] args) {
        // add/delete O(1)
        // HashSet<Integer> nums = new HashSet<Integer>();
        // Set<Integer> nums = new HashSet<>();
        // nums.add(321);
        // nums.add(325);
        // nums.add(1);
        // nums.add(3521);
        // nums.add(321);
        // System.out.println(nums);

        // add/delete O(N)
        // Set<Integer> nums = new TreeSet<>();
        // nums.add(321);
        // nums.add(325);
        // nums.add(1);
        // nums.add(3521);
        // nums.add(321);
        // System.out.println(nums);

        // Set<Student> group = new HashSet<>();
        // group.add(new Student("Athavan", 12));
        // group.add(new Student("Jugraj", 86));
        // group.add(new Student("Arthur", 21));

        // System.out.println(group);

        // Set<Student> group = new TreeSet<>();
        // group.add(new Student("Athavan", 12));
        // group.add(new Student("Jugraj", 86));
        // group.add(new Student("Arthur", 21));
        // group.add(new Student("Arthur", 48));

        // System.out.println(group);

        List<Integer> nums = new ArrayList<>();
        nums.add(123);
        nums.add(123);
        nums.add(123);
        nums.add(123);
        nums.add(456);
        nums = new ArrayList<>(new TreeSet<>(nums));
        System.out.println(nums);

    }
}

class Student implements Comparable<Student> {
    private String name;
    private int mark;

    public Student(String n, int m) {
        name = n;
        mark = m;
    }

    @Override
    public String toString() {
        return name + " " + mark;
    }

    @Override
    public int compareTo(Student other) {
        if (name.equals(other.name)) {
            return mark - other.mark;
        }
        return name.compareTo(other.name);
    }

}
