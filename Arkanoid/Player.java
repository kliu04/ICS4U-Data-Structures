// Player.java
// Kevin Liu
// handles the block layout

import java.awt.*;
import javax.swing.ImageIcon;

public class Player {

    public final int LEFT = 65;
    public final int RIGHT = 68;
    public final int SPACE = 32;

    private int speed = 12;

    private Rectangle hitBox = new Rectangle();

    public Player() {
        hitBox.width = 100;
        hitBox.height = 25;
        hitBox.x = ArkanoidPanel.WIDTH / 2 - hitBox.width / 2; // sets initial position to center
        hitBox.y = ArkanoidPanel.HEIGHT - 60;

    }

    public void move(boolean[] keys) {
        if (keys[LEFT] && hitBox.x > 0) {
            hitBox.x -= speed;
        }
        if (keys[RIGHT] && hitBox.x + hitBox.width < ArkanoidPanel.WIDTH) {
            hitBox.x += speed;
        }

    }

    public void enlarge(int width) {
        hitBox.width = width;

    }

    public void speedup(int speed) {
        this.speed = speed;
    }

    public boolean getPowerup(Powerup powerup) {

        return hitBox.intersects(powerup.getRect());

    }

    public void draw(Graphics ctx) {

        Image image = new ImageIcon("Arkanoid/images/VausSpacecraft.png").getImage();
        ctx.drawImage(image, hitBox.x, hitBox.y, hitBox.width, hitBox.height, null);
        // ctx.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    public Rectangle getRect() {
        return hitBox;
    }

}
