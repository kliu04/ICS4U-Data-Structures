// Block.java
// Kevin Liu
// Handles the blocks

import java.awt.Point;
import java.awt.*;
import java.util.Random;

import javax.swing.ImageIcon;

public class Block {
    private Rectangle hitBox = new Rectangle();
    public static final int WIDTH = 50;
    public static final int HEIGHT = 20;
    private Image image = null;

    public static final int NORMAL = 1; // number of hits to break
    public static final int SILVER = 3;
    public static final int GOLDEN = 0;
    private int type;

    public Block(Point p, int type) {

        hitBox.x = p.x;
        hitBox.y = p.y;
        hitBox.width = WIDTH;
        hitBox.height = HEIGHT;

        this.type = type;

        if (type == SILVER) { // loads image based off block
            image = new ImageIcon("Arkanoid/images/SilverWall.png").getImage();

        }

        else if (type == GOLDEN) {
            image = new ImageIcon("Arkanoid/images/GoldWall.png").getImage();
        }

        else {
            Random rand = new Random();
            int choice = rand.nextInt(4); // 4 different colours for regular blocks for variety

            switch (choice) {
                case 0:
                    image = new ImageIcon("Arkanoid/images/GreenWall.png").getImage();
                    break;
                case 1:
                    image = new ImageIcon("Arkanoid/images/LightBlueWall.png").getImage();
                    break;
                case 2:
                    image = new ImageIcon("Arkanoid/images/PinkWall.png").getImage();
                    break;
                case 3:
                    image = new ImageIcon("Arkanoid/images/YellowWall.png").getImage();
                    break;
            }
        }
    }

    public void draw(Graphics ctx) {

        ctx.drawImage(image, hitBox.x, hitBox.y, hitBox.width, hitBox.height, null);
    }

    public Rectangle getRect() {
        return hitBox;
    }

    public boolean calcHit() {
        type--;
        return type == 0;
    }
}
