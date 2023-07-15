// ArkanoidPanel.java
// Kevin Liu
// Main class that handles the game + events

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

public class ArkanoidPanel extends JPanel implements ActionListener, MouseListener, KeyListener {
    private Timer timer;
    private boolean[] keys = new boolean[200];
    public static final int SPACE = 32;

    public static double difficulty = 1.0; // affects speed of balls
    private static final int MENU = 0;
    private static final int END = 3;
    public static final int GAMEOVER = 4;
    private int screen = MENU; // what level the player is on

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    private int score = 0;
    private int lives = 3;

    Player player = new Player();
    Map map = new Map(screen);

    ArrayList<Ball> balls = new ArrayList<Ball>();
    ArrayList<Powerup> powerups = new ArrayList<Powerup>(); // powerups not in play (falling)
    ArrayList<Powerup> used = new ArrayList<Powerup>(); // powerups currently in play
    ArrayList<Laser> lasers = new ArrayList<Laser>();
    boolean laser = false; // player currently can shoot

    Font font = null;

    public ArkanoidPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        addMouseListener(this);
        addKeyListener(this);

        timer = new Timer(20, this);
        timer.start();

        balls.add(new Ball(player.getRect(), map));
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, ArkanoidPanel.class.getResourceAsStream("Roboto-Regular.ttf"))
                    .deriveFont(24f);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void move() {

        if (lives == 0) {
            screen = GAMEOVER;
            return;
        }

        if (screen == 1 && map.getBlocks().size() == 0) {
            reset();
            return;
        }

        if (screen == 2 && map.getBlocks().size() == 1) { // gold block can't be destroyed
            reset();
            return;
        }
        player.move(keys);
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);

            if (b.getResting()) { // ball is resting on platform
                if (keys[SPACE]) {
                    b.launch();
                }

            }
            if (b.move(player.getRect())) { // true when there is a collision between a block and player
                Random rand = new Random();

                if (rand.nextInt(4) == 0) {
                    int type = rand.nextInt(7) + 1;
                    powerups.add(new Powerup(b.getRect().x, b.getRect().y, type));
                }
                score += 10;

            }

            if (b.die()) { // ball falls off the screen
                balls.remove(b);

            }
        }

        for (int i = 0; i < lasers.size(); i++) {
            Laser l = lasers.get(i);
            if (l.move()) { // true when laser object hits a block
                lasers.remove(l);
            }
        }

        if (balls.size() == 0) { // all balls fallen off
            lives--;
            score = Math.max(0, score - 100); // prevent score from going below 0
            player = new Player(); // resets position of ball and player
            balls.add(new Ball(player.getRect(), map));
            used.clear();
            laser = false;
            lasers.clear();
        }

        for (int j = 0; j < powerups.size(); j++) {
            Powerup p = powerups.get(j);
            if (player.getPowerup(p)) {
                used.add(p);
                powerups.remove(p);
                break;
            }
        }
        for (Powerup p : powerups) {
            p.move();

        }

        for (int j = 0; j < used.size(); j++) {
            Powerup p = used.get(j);

            applyPowerup(p);

            if (p.decay()) { // some powerups have a timer where they will become inactive
                used.remove(p);
                int count = 0;
                for (Powerup pp : used) {
                    if (pp.getType() == p.getType()) {
                        count++;
                    }
                }

                if (count == 0) {
                    removePowerup(p);
                }
            }

            if (p.getType() == Powerup.SPLIT || p.getType() == Powerup.ONEUP) {
                used.remove(p);
            }

        }

    }

    public void applyPowerup(Powerup powerup) {
        if (powerup.getType() == Powerup.ENLARGE) {
            player.enlarge(200);
        }

        if (powerup.getType() == Powerup.SPLIT) {
            Ball n = new Ball(player.getRect(), map);
            n.launch();
            balls.add(n);
            n = new Ball(player.getRect(), map);
            n.launch();
            balls.add(n);
        }

        if (powerup.getType() == Powerup.LASER) {
            laser = true;
        }

        else if (powerup.getType() == Powerup.SLOW) {
            for (Ball b : balls) {
                b.slow(0.6);
            }
        } else if (powerup.getType() == Powerup.SPEEDUP) {
            player.speedup(20);
        }

        else if (powerup.getType() == Powerup.CATCH) {
            for (Ball b : balls) { // allows the ball to be caught by the paddle
                b.setCaught(true);
            }

        }

        else if (powerup.getType() == Powerup.ONEUP) {
            lives++;
        }
    }

    public void removePowerup(Powerup powerup) {
        if (powerup.getType() == Powerup.ENLARGE) {
            player.enlarge(100);
        }

        if (powerup.getType() == Powerup.LASER) {
            laser = false;
        }

        if (powerup.getType() == Powerup.SLOW) {
            for (Ball b : balls) {
                b.slow(1);
            }
        }

        if (powerup.getType() == Powerup.SPEEDUP) {
            player.speedup(12);
        }

        if (powerup.getType() == Powerup.CATCH) {
            for (Ball b : balls) {
                b.setCaught(false);
                if (b.getResting()) {
                    b.launch();
                }
            }
        }
    }

    public void drawGame(Graphics ctx) {
        ctx.setColor(Color.BLACK);
        ctx.fillRect(0, 0, getWidth(), getHeight());
        ctx.setColor(Color.WHITE);
        player.draw(ctx);
        for (Ball b : balls) {
            b.draw(ctx);
        }
        map.drawBlocks(ctx);

        for (Powerup p : powerups) {
            p.draw(ctx);
        }

        for (Laser l : lasers) {
            l.draw(ctx);
        }

        for (int i = 0; i < lives; i++) {
            Image image = new ImageIcon("Arkanoid/images/EnergyBall.png").getImage();
            ctx.drawImage(image, 60 + 20 * i, 5, 15, 15, null);
        }

        ctx.setFont(font);
        ctx.drawString(score + "", 5, 20);

    }

    @Override
    public void paint(Graphics ctx) {

        if (screen == MENU) {
            Image image = new ImageIcon("Arkanoid/images/Menu.png").getImage();
            ctx.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        } else if (screen == END) {
            Image image = new ImageIcon("Arkanoid/images/Victory.png").getImage();
            ctx.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        } else if (screen == GAMEOVER) {
            Image image = new ImageIcon("Arkanoid/images/GameOver.png").getImage();
            ctx.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        } else {
            drawGame(ctx);

        }

    }

    public void reset() { // resets all variables
        screen++;
        map = new Map(screen);
        player = new Player();
        balls.clear();
        used.clear();
        powerups.clear();
        lasers.clear();
        balls.add(new Ball(player.getRect(), map));
        laser = false;
        difficulty += 0.1;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (keys[SPACE] && laser) {
            lasers.add(new Laser(player.getRect(), map));
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (screen == MENU) {
            reset();
        }

        else if (screen == END) {
            System.exit(0);
        }

        if (screen == GAMEOVER) {
            screen = 0;
            reset();
            lives = 3;
            score = 0;
            difficulty = 1;

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
