package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class ValveRotation extends JPanel {

    private double rotationAngle = 0.0;
    private Image valveImage;

    public ValveRotation() {
        // Load the valve image from the resources folder
        try {
            valveImage = new ImageIcon(getClass().getResource("resources/valve.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rotateValve(double angle) {
        rotationAngle = angle;
        repaint();
    }

    public void rotateValveLeft() {
        rotateValve(rotationAngle + Math.PI / 4); // Rotate left by 45 degrees
    }

    public void rotateValveRight() {
        rotateValve(rotationAngle - Math.PI / 4); // Rotate right by 45 degrees
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform oldTransform = g2d.getTransform();
        g2d.rotate(rotationAngle, getWidth() / 2.0, getHeight() / 2.0);

        // Draw the valve using the loaded image
        drawValve(g2d);

        g2d.setTransform(oldTransform);
    }

    private void drawValve(Graphics2D g) {
        int valveWidth = 50;
        int valveHeight = 50;
        int x = getWidth() / 2 - valveWidth / 2;
        int y = getHeight() / 2 - valveHeight / 2;

        if (valveImage != null) {
            g.drawImage(valveImage, x, y, valveWidth, valveHeight, this);
        } else {
            // Draw a placeholder rectangle if the image couldn't be loaded
            g.setColor(Color.BLUE);
            g.fillRect(x, y, valveWidth, valveHeight);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Valve Rotation");
        ValveRotation valveRotation = new ValveRotation();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(valveRotation);
        frame.pack();
        frame.setVisible(true);

        // Example: Rotate the valve to a specific angle
        valveRotation.rotateValveRight(); // Rotate to 45 degrees
    }
}
