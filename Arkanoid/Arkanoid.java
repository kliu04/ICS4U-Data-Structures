// Arkanoid.java
// Kevin Liu
// Sets up the game panel

import javax.swing.*;

public class Arkanoid extends JFrame {

    public Arkanoid() {
        super("Arkanoid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new ArkanoidPanel());
        pack();
        setVisible(true);
        setResizable(false);

    }

    public static void main(String[] args) {
        new Arkanoid();
    }
}
