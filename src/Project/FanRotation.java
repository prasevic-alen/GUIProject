package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FanRotation extends JPanel {

    private double rotationAngle;
    private List<ImageInfo> images = new ArrayList<>();
    private Timer timer;
    private int speed;

    public FanRotation(double rotationAngle) {
        this.rotationAngle = rotationAngle;

        initTimer();
    }

    private void initTimer() {
        int interval = 10; // default interval
        if (speed > 0) {
            interval = 10; // adjust interval based on speed
        }

        timer = new Timer(interval, e -> {
            setRotation(rotationAngle + 0.03 * speed);
            repaint();
        });
        timer.start();
    }

    public void setRotation(double rotationAngle) {
        this.rotationAngle = rotationAngle;
        repaint();  // Repaint
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        timer.stop();
        initTimer();
    }

    public void addImage(String imagePath, int x, int y, int width, int height) {
        Image image = loadImage(imagePath);
        images.add(new ImageInfo(image, x, y, width, height));
    }

    public void startRotation() {
        timer.start();
    }

    public void stopRotation() {
        timer.stop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform oldTransform = g2d.getTransform();

        for (ImageInfo imageInfo : images) {
            g2d.rotate(rotationAngle, imageInfo.getX() + imageInfo.getWidth() / 2.0,
                    imageInfo.getY() + imageInfo.getHeight() / 2.0);

            drawFan(g2d, imageInfo.getX(), imageInfo.getY(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImage());

            g2d.setTransform(oldTransform);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    private void drawFan(Graphics2D g, int x, int y, int width, int height, Image image) {
        g.drawImage(image, x, y, width, height, this);
    }

    private Image loadImage(String imagePath) {
        try {
            URL resourceUrl = getClass().getResource(imagePath);
            if (resourceUrl != null) {
                return new ImageIcon(resourceUrl).getImage();
            } else {
                System.err.println("Resource not found: " + imagePath);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class ImageInfo {
        private final Image image;
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public ImageInfo(Image image, int x, int y, int width, int height) {
            this.image = image;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public Image getImage() {
            return image;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
