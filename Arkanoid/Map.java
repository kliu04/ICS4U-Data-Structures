// Map.java
// Kevin Liu
// handles the block layout

import java.util.ArrayList;
import java.awt.Point;
import java.awt.*;

public class Map {
    private ArrayList<Block> blocks = new ArrayList<Block>();

    private int[] x = new int[36]; // x-co of blocks
    private int[] y = new int[36]; // y-co of blocks
    private int[] t = new int[36]; // type of blocks

    public Map(int level) {

        if (level == 1) {
            one(); // loads level one
            make(x, y, t); // adds the block to the screen
        }

        else if (level == 2) {

            x = new int[36];
            y = new int[36];
            t = new int[36];
            two();
            make(x, y, t);
        }
    }

    private void one() {

        for (int i = 0; i <= 550; i += 50) { 
            x[i / 50] = i;
            y[i / 50] = 100;
            x[i / 50 + 12] = i;
            y[i / 50 + 12] = 120;
            x[i / 50 + 24] = i;
            y[i / 50 + 24] = 140;
            t[i / 50] = Block.NORMAL;
            t[i / 50 + 12] = Block.NORMAL;
            t[i / 50 + 24] = Block.NORMAL;
        }
    }

    private void two() {

        for (int i = 0; i < 10; i++) {
            x[i] = 0 + Block.WIDTH * i;
            y[i] = 1 + Block.HEIGHT * i;
            t[i] = Block.NORMAL;

            x[i + 10] = 0 + Block.WIDTH * i;
            y[i + 10] = 201 + Block.HEIGHT * i;
            t[i + 10] = Block.SILVER;

            x[i + 20] = 0 + Block.WIDTH * i;
            y[i + 20] = 401 + Block.HEIGHT * i;
            t[i + 20] = Block.NORMAL;

        }

        t[14] = Block.GOLDEN;

    }

    private void make(int[] x, int[] y, int[] t) {
        for (int i = 0; i < x.length; i++) {
            if (x[i] == 0 && y[i] == 0) {
                break;
            }
            blocks.add(new Block(new Point(x[i], y[i]), t[i]));
        }
    }

    public void drawBlocks(Graphics ctx) {
        for (Block b : blocks) {
            b.draw(ctx);
        }

    }

    public boolean breakBlock(Block b) {
        if (b.calcHit()) {
            blocks.remove(b);
            return true;
        }
        return false;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}
