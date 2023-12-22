package Project;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.Timer;

public class CoolingGauge extends JPanel {
    private ZRunner runner;
    private static final int GAUGE_WIDTH = 186;
    private static final int GAUGE_HEIGHT = 40;
    private int cooling;

    public CoolingGauge(ZRunner runner) {
        this.runner = runner;

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCoolValue();
                repaint();
            }
        });

        timer.start();
    }

    private void updateCoolValue() {
        cooling = runner.getSliderCool().getValue();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 2;
        int y = 2;
        int width = GAUGE_WIDTH;
        int filledWidth = (int) (cooling * 18.6);

        g.setColor(new Color(250, 12, 80));
        g.fillRect(x, y, width, GAUGE_HEIGHT);

        g.setColor(new Color(50, 237, 178));
        g.fillRect(x, y, filledWidth, GAUGE_HEIGHT);

        g.setColor(Color.BLACK);
        g.drawString("Current cooling level is: " + cooling + ".", 10 , 25 );
    }
}
