// Queue.java
// Kevin Liu
// Implements the "Queue"

public class Queue {
    private QNode head;
    private QNode tail;

    public Queue() {
        head = null;
        tail = null;
    }

    public void add(int n) { // add to front
        QNode node = new QNode(null, n, head);
        // head of the queue, so no previous
        // next must connect to head
        if (head == null) { // empty queue
            head = node;
            tail = node;
            return;
        }
        head.setPrev(node); // old head needs to connect backwards to the new head
        head = node; // moves the head
    }

    public void enqueue(int n) { // add to back
        // back of the queue, so no next
        // back must connect to tail
        QNode node = new QNode(tail, n, null);
        if (tail == null) { // empty queue
            head = node;
            tail = node;
            return;
        }
        tail.setNext(node); // old tail needs to connect forwards to the new tail
        tail = node; // moves tail

    }

    public int dequeue() {
        int ans = head.getValue(); // same as stack
        head = head.getNext();
        head.setPrev(null); // new head has no previous
        return ans;
    }

    public void delete(int n) {
        QNode cur = head; // loops through queue head to tail
        do {
            if (cur.getValue() == n) { // found value to delete
                delete(cur);
            }
            cur = cur.getNext();
        } while (cur != null); // reaches past the tail
    }

    public void delete(QNode node) {
        QNode prev = node.getPrev();
        QNode next = node.getNext();
        if (prev == null && next == null) { // last node in queue, clears head and tail
            head = null;
            tail = null;
        } else if (prev == null) { // head of queue being deleted
            head = next;
            next.setPrev(null);
        } else if (next == null) { // tail of queue being deleted
            tail = prev;
            prev.setNext(null);
        } else { // middle node
            prev.setNext(next); // connects prev with next and next with prev, skipping over this node
            next.setPrev(prev);
        }
        return;
    }

    public void deleteAt(int index) {
        QNode cur = head;
        int counter = 0;

        while (counter != index) { // loops through the queue looking for index
            cur = cur.getNext();
            counter++;
        }

        delete(cur);
    }

    public QNode get(int value) {
        QNode cur = head;
        while (value != cur.getValue()) { // loops through every node in queue looking for value
            cur = cur.getNext();
            if (cur == null) {
                return null;
            }
        }
        return cur;
    }

    @Override
    public String toString() {
        String ans = "";
        QNode temp = head;
        while (temp != null) {
            ans += temp + ", ";
            temp = temp.getNext();
        }
        if (ans != "") {
            ans = ans.substring(0, ans.length() - 2);
        }
        return "<" + ans + ">";
    }

    public String reverse() {
        String ans = "";
        QNode temp = tail; // same as toString but start at tail
        while (temp != null) {
            ans += temp + ", ";
            temp = temp.getPrev();
        }
        if (ans != "") {
            ans = ans.substring(0, ans.length() - 2);
        }
        return "<" + ans + ">";
    }
}
