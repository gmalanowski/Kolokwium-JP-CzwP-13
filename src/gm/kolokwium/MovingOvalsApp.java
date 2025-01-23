package gm.kolokwium;

import javax.swing.*;

public class MovingOvalsApp extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    MovingOvalsApp window = new MovingOvalsApp();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    public MovingOvalsApp() {
        this("OvalCatchApp");
    }

    public MovingOvalsApp(String name) {
        super(name);
        buildFrame();
    }

    protected void buildFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel);

        drawingPanel.requestFocusInWindow();
        setVisible(true);
    }
}
