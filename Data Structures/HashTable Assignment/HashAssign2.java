// HashAssign2.java
// Kevin Liu
// Solves Q2 from Assignment

import javax.swing.*;

public class HashAssign2 extends JFrame {

    public HashAssign2() {
        super("Windsor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new HashAssign2Panel());
        pack();
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new HashAssign2();

    }
}
