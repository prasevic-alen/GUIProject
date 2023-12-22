package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BlueCirclePanel extends JPanel {
    private int x;
    private int y;
    private int circleRadius = 15;

    public BlueCirclePanel(){
        setPreferredSize(new Dimension(187, 227));
        setOpaque(false);	
        setVisible(true);

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.cyan);
        g.drawArc(x + circleRadius/2, y + circleRadius/2, 2 * circleRadius, 2 * circleRadius, 0, 360);
        g.setColor(Color.blue);
        g.fillOval(x +  circleRadius/2, y + circleRadius/2, 2 * circleRadius, 2 * circleRadius); // Draw a blue circle
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y; 
    }
}