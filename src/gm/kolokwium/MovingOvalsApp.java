package gm.kolokwium;

import javax.swing.*;

public class OvalApp extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    OvalApp window = new OvalApp();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    public OvalApp() {
        this("OvalCatchApp");
    }

    public OvalApp(String name) {
        super(name);
    }
}
