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
        moving = true;
        while(moving) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                moving = false;
                e.printStackTrace();
            }
            moveOval(constUp, constRight);
        }
    }

    public void moveOval(int up, int right) {
        constUp = up;
        constRight = right;

        x += right;
        y += up;

        if (x < 0) {
            x = panel.getWidth() - width;
        } else if (x + width > panel.getWidth()) {
            x = 0;
        }

        if (y < 0) {
            y = panel.getHeight() - height;
        } else if (y + height > panel.getHeight()) {
            y = 0;
        }

        checkCollision();

        panel.repaint();
    }

    private void checkCollision() {
        for(Oval other : panel.getOvalsList()) {
            if(other != this && isColliding(other)) {
                constUp = -constUp;
                constRight = -constRight;
            }
        }
    }

    private boolean isColliding(Oval other) {
        int dx = (x + width / 2) - (other.x + other.width / 2);
        int dy = (y + height / 2) - (other.y + other.height / 2);
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        return distance < (width + other.width) / 2;
    }
}
