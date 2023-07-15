// HashAssign2Panel.java
// Kevin Liu
// The Panel needed for GFX interface

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;

public class HashAssign2Panel extends JPanel implements MouseListener, ActionListener {
    HTable<Creeper> table = new HTable<Creeper>();
    File file;
    Scanner scanner;
    Timer timer;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    ArrayList<Creeper> points = new ArrayList<Creeper>();
    // points is all the creepers that need to be drawn

    public HashAssign2Panel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        addMouseListener(this);

        timer = new Timer(20, this);
        timer.start();
        try {
            // adds every creeper to the HT
            file = new File("advanced data structures/data/creeper.txt");
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Creeper creeper = new Creeper(scanner.nextLine());
                if (table.get(creeper.hashCode()) != null) {
                    // merges two creepers together if in same spot
                    table.get(creeper.hashCode()).add(creeper);
                } else {
                    table.add(creeper);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }

    }

    @Override
    public void paint(Graphics ctx) {
        Image image = new ImageIcon("advanced data structures/data/windsor.png").getImage();
        ctx.drawImage(image, 0, 0, null); // draws the map of windsor
        // BG image
        for (Creeper c : points) {
            // draws every creeper
            Point p = c.getCoords();
            Color col = c.getColor();
            ctx.setColor(col);
            ctx.drawLine(p.x, p.y, p.x, p.y);

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        Point click = e.getPoint();
        int code;
        points.clear();
        final int RADIUS = 10;
        for (int x = -RADIUS; x <= RADIUS; x++) { // left to right
            for (int y = -RADIUS; y <= RADIUS; y++) { // top to bottom
                if (x * x + y * y <= RADIUS * RADIUS) { // finds all lattice points using the formula for a circle
                    code = (click.x + x) * 1000 + (click.y + y) * 777; // hashcode for that point (arbitrary)
                    if (table.get(code) != null) {
                        points.add(table.get(code)); // adds that point to be drawn later

                    }

                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
