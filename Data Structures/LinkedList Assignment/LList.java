// LList.java
// Kevin Liu
// Custom version of Linked List

public class LList {
    private LNode head; // head of LL

    public LList() {
        head = null;
    }

    public void add(int n) { // add to front
        LNode node = new LNode(n, head);
        head = node;
    }

    public void sortedInsert(int n) {
        // two nodes, one in front and one behind

        if (head == null) { // empty LList
            LNode node = new LNode(n, head);
            head = node;
            return;
        }

        LNode A = head;
        LNode B = A;

        if (A.getNext() == null) { // one sized LList
            if (n >= B.getValue()) {
                LNode node = new LNode(n, B);
                head = node;
            } else {
                LNode node = new LNode(n, null);
                B.setNext(node);

            }
            return;
        }

        if (n > A.getValue()) { // insert at front of LList
            LNode node = new LNode(n, head);
            head = node;
            return;
        }

        do {
            A = A.getNext(); // A moves first
            if (A == null || B.getValue() >= n && A.getValue() <= n) { // the value is between A and B
                // A is null at the end of the list
                LNode node = new LNode(n, A); // adds the new node in order
                B.setNext(node); // connects prev node with this one
                break;
            }
            B = A; // B follows A if no match
        } while (A != null);
    }

    public void removeDuplicates() {
        final LNode init = head;
        LNode left = head;
        LNode right = head;

        if (init.getNext() == null) { // linked list size 1, impossible to have duplicate
            return;
        }

        // uses the head as a pivot
        // all values "left" of head are guaranteed to have no duplicates
        while (right != null) { // while not all values of LL have been checked
            boolean valid = true; // assumes there is no duplicate
            while (left != init) {
                if (left.getValue() == right.getValue()) { // duplicate found
                    valid = false; // moves on to next
                    break;
                }
                left = left.getNext(); // goes through every value guaranteed to have no duplicates
            }
            if (valid) { // if there is no duplicate, adds it to "left"
                this.add(right.getValue());
            }
            right = right.getNext(); // check each value "right" of the LL
            left = head;
        }

        init.setNext(null); // gets rid of right
        this.reverse();
        head = head.getNext();
    }

    public void reverse() {
        LNode a = head;

        if (a.getNext() == null) { // case where there is only one element
            return; // don't need to do anything
        }
        LNode b = a.getNext(); // case where there are two elements
        if (b.getNext() == null) { // switch the next and previous
            head = b;
            b.setNext(a);
            a.setNext(null);
            return; // exit before going out of bounds
        }
        LNode temp = b.getNext();
        a.setNext(null); // head becomes the last
        while (temp != null) {
            b.setNext(a); // the "next" node switches direction
            a = b; // every node moves up one
            b = temp;
            head = temp;
            temp = temp.getNext();
        }
        b.setNext(a); // sets the first node's next

    }

    public LList clone() {
        LList ans = new LList();
        LNode cur = head;
        while (cur != null) { // goes through every value and adds it to new LL
            ans.add(cur.getValue());
            cur = cur.getNext();
        }
        return ans;
    }

    @Override
    public String toString() {
        String ans = "";
        LNode temp = head;
        while (temp != null) {
            ans += temp + ", ";
            temp = temp.getNext();
        }
        if (ans != "") {
            ans = ans.substring(0, ans.length() - 2);
        }
        return "<" + ans + ">";
    }
}
