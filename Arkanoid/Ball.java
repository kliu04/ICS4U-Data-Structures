// Ball.java
// Kevin Liu
// Handles the ball and collisions

import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;

public class Ball {
    private double vx = 0, vy = 0;
    private final int RADIUS = 10;
    private final int DIAMETER = 2 * RADIUS;
    private boolean resting = true;
    private boolean caught = false;
    private double speed = 1;
    private Rectangle hitBox = new Rectangle();
    Map map;
    private Random rand = new Random();

    public Ball(Rectangle paddle, Map map) {

        hitBox.x = paddle.x + paddle.width / 2 - RADIUS;
        hitBox.y = paddle.y - DIAMETER;
        hitBox.width = DIAMETER;
        hitBox.height = DIAMETER;
        this.map = map;

    }

    public void launch() {
        resting = false;
        vy = -rand.nextInt(5) - 8; // random y velocity going up
        if (rand.nextBoolean()) { // random left or right direction
            vx = rand.nextInt(5) + 8;

        }

        else {
            vx = -rand.nextInt(5) - 8;
        }

        hitBox.x += vx * speed * ArkanoidPanel.difficulty;
        hitBox.y += vy * speed * ArkanoidPanel.difficulty;
    }

    private double adjustAlpha(double theta) { // randomizes the resultant angle
        double alpha = theta;
        final double EPSILON = Math.PI / 12; // 15 degree off horizontal
        if (rand.nextBoolean()) {
            alpha += rand.nextDouble(EPSILON);
        } else {
            alpha -= rand.nextDouble(EPSILON);
        }

        alpha = Math.max(EPSILON, alpha); // prevents the ball from travelling too close to the horizontal

        return alpha;

    }

    public boolean move(Rectangle paddle) {
        hitBox.x += vx * speed * ArkanoidPanel.difficulty; // move ball
        hitBox.y += vy * speed * ArkanoidPanel.difficulty;
        double theta = Math.atan2(Math.abs(vy), Math.abs(vx)); // fixes theta such that it is always positive
        double alpha = adjustAlpha(theta);
        double v = Math.sqrt(vx * vx + vy * vy); // pythagorean theorem to find total velocity
        if (resting) {
            hitBox.x = paddle.x + paddle.width / 2 - RADIUS;
            hitBox.y = paddle.y - DIAMETER;
            return false;
        }

        if (hitBox.x < 0) { // hits left wall

            if (vy > 0) // ball approaching from down
            {
                vx = v * Math.cos(alpha);
                vy = v * Math.sin(alpha);

            }

            else {
                vx = v * Math.cos(alpha);
                vy = -v * Math.sin(alpha);
            }

        }
        if (hitBox.x + RADIUS * 2 > ArkanoidPanel.WIDTH) { // hits right wall

            if (vy > 0) // ball approaching from down
            {
                vx = -v * Math.cos(alpha);
                vy = v * Math.sin(alpha);

            }

            else {
                vx = -v * Math.cos(alpha);
                vy = -v * Math.sin(alpha);
            }

        }

        if (hitBox.y < 0) { // hits top

            if (vx < 0) // ball approaching from left
            {
                vx = -v * Math.sin(alpha);
                vy = v * Math.cos(alpha);
            }

            else {
                vx = v * Math.sin(alpha);
                vy = v * Math.cos(alpha);
            }
        }

        if (paddle.intersects(hitBox) && hitBox.y <= paddle.y) { // hits paddle
            if (caught) { // fixes ball to paddle if powerup is applied
                resting = true;
                return false;
            }

            if (vx < 0) // Ball approaching from left
            {
                vx = -v * Math.sin(alpha);
                vy = -v * Math.cos(alpha);
            }

            else {
                vx = v * Math.sin(alpha);
                vy = -v * Math.cos(alpha);
            }
        }

        for (int i = 0; i < map.getBlocks().size(); i++) {
            Block b = map.getBlocks().get(i);
            Rectangle r = b.getRect();

            Rectangle intersection = r.intersection(hitBox); // the intersection between
            if (intersection.width >= 0 && intersection.height >= 0) {
                new SoundEffect("Arkanoid/images/bounce.wav").play(); // plays sound effect

                int ty = hitBox.y;
                hitBox.y -= vy;
                if (!getRect().intersects(r)) {
                    vy *= -1;
                }
                hitBox.y = ty;

                int tx = hitBox.x;
                hitBox.x -= vx;
                if (!getRect().intersects(r)) {
                    vx *= -1;
                }
                hitBox.x = tx;

                return map.breakBlock(b);

            }

        }

        return false;

    }

    public void slow(double speed) {

        this.speed = speed;

    }

    public Rectangle getRect() {
        return hitBox;
    }

    public boolean die() { // ball reaches bottom of screen
        return hitBox.y > ArkanoidPanel.HEIGHT;
    }

    public boolean getResting() {
        return resting;
    }

    public void setCaught(boolean b) {
        caught = b;
    }

    public void draw(Graphics ctx) {
        Image image = new ImageIcon("Arkanoid/images/EnergyBall.png").getImage();
        ctx.drawImage(image, hitBox.x, hitBox.y, hitBox.width, hitBox.height, null);
    }
}
