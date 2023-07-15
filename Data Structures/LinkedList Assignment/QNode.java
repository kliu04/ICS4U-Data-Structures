// QNode.java
// Kevin Liu
// Each node in the Queue

public class QNode {
    private int value;
    private QNode next;
    private QNode prev;

    // the same thing as LNode but with a prev value
    public QNode(QNode p, int v, QNode n) {
        prev = p;
        value = v;
        next = n;
    }

    public QNode getNext() {
        return next;
    }

    public QNode getPrev() {
        return prev;
    }

    public void setNext(QNode n) {
        next = n;
    }

    public void setPrev(QNode n) {
        prev = n;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
