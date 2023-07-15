// Stack.java
// Kevin Liu
// Implements "stack"

public class Stack {
    private LNode head;

    public Stack() {
        head = null;
    }

    public void push(int n) {
        LNode node = new LNode(n, head); // makes a new node pointing to the current head
        head = node; // changes the head such that the new node is first
    }

    public int pop() {
        int ans = head.getValue(); // gets the value of the head
        head = head.getNext(); // moves the head to next node
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
