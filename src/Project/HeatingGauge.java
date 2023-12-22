package Project;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.Timer;

public class HeatingGauge extends JPanel {
    private ZRunner runner;
    private static final int GAUGE_WIDTH = 186;
    private static final int GAUGE_HEIGHT = 40;
    private int heating;
    private Color lightBlue = new Color(0,0,182,155);


    public HeatingGauge(ZRunner runner) {
        this.runner = runner;

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHeatingValue();
                repaint();
            }
        });

        timer.start();
    }

    private void updateHeatingValue() {
        heating = runner.getSliderHeat().getValue();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 2;
        int y = 2;
        int width = GAUGE_WIDTH;
        int filledWidth = (int) (heating * 18.6);

        g.setColor(lightBlue);
        g.fillRect(x, y, width, GAUGE_HEIGHT);

        g.setColor(Color.RED);
        g.fillRect(x, y, filledWidth, GAUGE_HEIGHT);

        g.setColor(Color.BLACK);
        g.drawString("Current heating level is: " + heating + ".", 10 , 25 );
    }
}
