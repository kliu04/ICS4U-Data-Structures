// LNode.java
// Kevin Liu
// Each node in the LList

public class LNode {
    private int value;
    private LNode next;

    public LNode(int v, LNode n) {
        value = v;
        next = n;
    }

    // getters and setters
    public LNode getNext() {
        return next;
    }

    public int getValue() {
        return value;
    }

    public void setNext(LNode n) {
        next = n;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
