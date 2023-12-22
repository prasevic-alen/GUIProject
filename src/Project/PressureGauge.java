package Project;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PressureGauge extends JPanel {
    private ZRunner runner;
    private static final int GAUGE_WIDTH = 186;
    private static final int GAUGE_HEIGHT = 40;
    private double pressureValue;

    public PressureGauge(ZRunner runner) {
        this.runner = runner;

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePressure();
                repaint();
            }
        });

        timer.start();
    }

    private void updatePressure() {
        pressureValue = runner.getBoilerPressure();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 2;
        int y = 2;
        int width = GAUGE_WIDTH;
        int filledWidth = (int) (pressureValue * 6.9);

        g.setColor(new Color(45, 99, 247));
        g.fillRect(x, y, width, GAUGE_HEIGHT);

        g.setColor(Color.RED);
        g.fillRect(x, y, filledWidth, GAUGE_HEIGHT);

        g.setColor(Color.BLACK);
        g.drawString("Pressure: " + String.format("%.2f", pressureValue) + "bar", 10 , 25 );
        //System.out.println("X " + x + ", Y " + y + ", filledWidth " + filledWidth + ", GAUGE_HEIGHT " + GAUGE_HEIGHT + ", Pressure " + pressureValue);
    }
}
