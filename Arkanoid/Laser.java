// Ball.java
// Kevin Liu
// Handles the laser powerup

import java.awt.Rectangle;
import java.awt.*;

public class Laser {

    private final int vy = -20;
    private final int RADIUS = 2;
    private final int DIAMETER = 2 * RADIUS;
    private Rectangle hitBox;
    private Map map;

    public Laser(Rectangle paddle, Map map) { // sets the laser initally to the middle of the paddle
        this.hitBox = new Rectangle(paddle.x + paddle.width / 2 - RADIUS, paddle.y, DIAMETER, DIAMETER);
        this.map = map;
    }

    public boolean move() {

        for (int i = 0; i < map.getBlocks().size(); i++) {
            Block b = map.getBlocks().get(i); // block intersects laser
            if (b.getRect().intersects(hitBox)) {
                map.breakBlock(b);
                return true;
            }
        }

        hitBox.y += vy; // moves the laser particle up
        return false;
    }

    public void draw(Graphics ctx) {
        ctx.setColor(Color.RED);
        ctx.drawOval(hitBox.x, hitBox.y, DIAMETER, DIAMETER);
        ctx.setColor(Color.WHITE);
    }
}
