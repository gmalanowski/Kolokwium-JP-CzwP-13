package gm.kolokwium;

import java.awt.*;

public class Oval implements Runnable {
    private int x, y, width, height;
    private int constUp, constRight;
    private boolean moving = false;
    private final DrawingPanel panel;

    public Oval(int x, int y, int width, int height, DrawingPanel panel) {
        this.panel = panel;
        panel.setFocusable(true);
        initializeOval(x, y, width, height);
    }

    private void initializeOval(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.drawOval(x, y, width, height);
        g.setColor(Color.red);
        g.fillOval(x, y, width, height);
    }

    @Override
    public void run() {
        while(moving) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            moveOval(constUp, constRight);
        }
    }

    public void moveOval(int up, int right) {
        constUp = up;
        constRight = right;
        moving = true;

        x += right;
        y += up;

        if(x < 0 || x + width > panel.getWidth()) {
            x = 0;
        }
        if(y < 0 || y + height > panel.getHeight()) {
            y = 0;
        }
        panel.repaint();
    }
}
