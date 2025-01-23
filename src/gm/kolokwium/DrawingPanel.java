package gm.kolokwium;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DrawingPanel extends JPanel {
    private final List<Oval> ovalsList;
    private Point startPoint;
    private final int unitSize = 50;
    private Image buffer;

    public DrawingPanel() {
        ovalsList = new CopyOnWriteArrayList<>();
        setFocusable(true);
        setupKeysBindings();
        setupMouseListeners();
    }

    private void setupMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startPoint = e.getPoint();
                createOval(startPoint);
            }
        });
    }

    private void createOval(Point startPoint) {
        if (startPoint != null) {
            int x = startPoint.x;
            int y = startPoint.y;

            Oval oval = new Oval(x, y, unitSize, unitSize, this);
            ovalsList.add(oval);
            new Thread(oval).start();
            repaint();
        }
    }

    private void setupKeysBindings() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!ovalsList.isEmpty()) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_DOWN -> moveCurrentOval(2);
                        case KeyEvent.VK_UP -> moveCurrentOval(-2);
                        case KeyEvent.VK_LEFT -> moveCurrentOval(-1);
                        case KeyEvent.VK_RIGHT -> moveCurrentOval(1);
                    }
                }
            }
        });
    }

    private void moveCurrentOval(int delta) {
        Oval currentOval = ovalsList.getLast();
        if(currentOval != null) {
            if(delta == 1) {
                currentOval.moveOval(0, unitSize);
            }
            else if(delta == -1) {
                currentOval.moveOval(0, -unitSize);
            }
            else if(delta == 2) {
                currentOval.moveOval(unitSize, 0);
            }
            else if(delta == -2) {
                currentOval.moveOval(-unitSize, 0);
            }
        }
    }

    public List<Oval> getOvalsList() {
        return ovalsList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(buffer == null) {
            buffer = createImage(getWidth(), getHeight());
        }

        Graphics bufferGraphics = buffer.getGraphics();
        bufferGraphics.setColor(getBackground());
        bufferGraphics.fillRect(0, 0, getWidth(), getHeight());

        for (Oval oval : ovalsList) {
            oval.draw(bufferGraphics);
        }

        g.drawImage(buffer, 0, 0, this);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        buffer = null;
    }
}
