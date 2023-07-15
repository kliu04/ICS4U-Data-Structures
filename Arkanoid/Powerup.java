// Map.java
// Kevin Liu
// handles the block layout

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.*;

public class Powerup {
    private Rectangle hitBox = new Rectangle();
    private int type;

    public static final int DELAY = 275;
    private int timer = DELAY;
    private Image image;

    public static final int ENLARGE = 1;
    public static final int SPLIT = 2;
    public static final int LASER = 3;
    public static final int SLOW = 4;
    public static final int SPEEDUP = 5;
    public static final int CATCH = 6;
    public static final int ONEUP = 7;

    public Powerup(int x, int y, int type) {
        this.type = type;
        hitBox.x = x;
        hitBox.y = y;
        hitBox.width = 50;
        hitBox.height = 20;

        if (type == ENLARGE) {
            image = new ImageIcon("Arkanoid/images/Enlarge.gif").getImage();

        }

        else if (type == SPLIT) {
            image = new ImageIcon("Arkanoid/images/Duplicate.gif").getImage();

        }

        else if (type == LASER) {
            image = new ImageIcon("Arkanoid/images/Laser.gif").getImage();

        }

        else if (type == SLOW) {
            image = new ImageIcon("Arkanoid/images/Slow.gif").getImage();

        }

        else if (type == SPEEDUP) {
            image = new ImageIcon("Arkanoid/images/Speed.gif").getImage();

        }

        else if (type == CATCH) {
            image = new ImageIcon("Arkanoid/images/Catch.gif").getImage();

        }

        else if (type == ONEUP) {
            image = new ImageIcon("Arkanoid/images/Player.gif").getImage();

        }
    }

    public void move() {
        hitBox.y += 10; // moves down
    }

    public void draw(Graphics ctx) {

        ctx.drawImage(image, hitBox.x, hitBox.y, hitBox.width, hitBox.height, null);

    }

    public boolean decay() { // when timer is 0, the powerup is removed
        timer--;

        return timer == 0;
    }

    public Rectangle getRect() {
        return hitBox;
    }

    public int getType() {
        return type;
    }
}
