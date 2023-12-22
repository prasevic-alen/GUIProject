package Project;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
// Idea from https://stackoverflow.com/questions/54000139/how-doom-fire-was-done-re-implementation-of-a-fire-animation-that-results-in/54011677#54011677

public class Fire {
    private final int width;
    private final int height;
    private final BufferedImage bufferedImage;
    private final FirePanel firePanel;
    private Timer timer;
   
    public Fire(int width, int height, FirePanel firePanel) {
        this.width = width;
        this.height = height;
        this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.firePanel = firePanel;
        initializeFire();
    }
    
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
    
    public void updateFire(int interval) {
        TimerTask task = new TimerTask() {
            final Random rnd = new Random();
 
            Color getColorAtIndex(final int index) {
                if (index < 0) {
                    return colors.get(0);
                }
                    return colors.get(index);
                }
    
            int getIndexOfColor(final int rgb) {
                for (int x = 0; x < colors.size(); x++) {
                    if (colors.get(x).getRGB() == rgb) {
                        return x;
                    }
                }
                throw new RuntimeException("Color not found in the list!");
            }
    
            @Override
            public void run() {
                for (int x = 0; x < width; x++) {
                    for (int y = 1; y < height; y++) {
                        final int new_index = getIndexOfColor(getRGBAtCoordinates(x, y)) - rnd.nextInt(2);
                        final int new_x = (x - rnd.nextInt(3)) + 1;
                        colorize(getColorAtIndex(new_index), new_x, y - 1);
                    }
                }
                firePanel.repaint();  // Repaint the FirePanel
            }
        };
    
        if (interval > 0) {
            if (timer != null) {
                timer.cancel();  // Cancel the previous timer if any
            }
            timer = new Timer();
            timer.schedule(task, 0, interval);
        } else {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }
    }

    private void initializeFire() {
        final Color black = colors.get(0);
        final Color white = colors.get(colors.size() - 1);

        for (int y = 0; y < height; y++) {
            final Color clr = y < (height - 1) ? black : white;
            for (int x = 0; x < width; x++) {
                colorize(clr, x, y);
            }
        }
    }

    private void colorize(final Color color, final int x, final int y) {
        if ((x >= 0) && (x < width) && (y >= 0) && (y < height)) {
            bufferedImage.setRGB(x, y, color.getRGB());
        }
    }

    private int getRGBAtCoordinates(final int x, final int y) {
        return bufferedImage.getRGB(x, y);
    }

    private static final List<Color> colors = new ArrayList<>();

    static {
        colors.add(new Color(50, 140, 237));
        colors.add(new Color(0, 0, 0)); // black
        colors.add(new Color(7, 7, 7));
        colors.add(new Color(47, 15, 7));
        colors.add(new Color(71, 15, 7));
        colors.add(new Color(87, 23, 7));
        colors.add(new Color(103, 31, 7));
        colors.add(new Color(119, 31, 7));
        colors.add(new Color(143, 39, 7));
        colors.add(new Color(159, 47, 7));
        colors.add(new Color(175, 63, 7));
        colors.add(new Color(191, 71, 7));
        colors.add(new Color(199, 71, 7));
        colors.add(new Color(223, 79, 7));
        colors.add(new Color(223, 87, 7));
        colors.add(new Color(223, 87, 7));
        colors.add(new Color(215, 95, 7));
        colors.add(new Color(215, 103, 15));
        colors.add(new Color(207, 111, 15));
        colors.add(new Color(207, 119, 15));
        colors.add(new Color(207, 127, 15));
        colors.add(new Color(207, 135, 23));
        colors.add(new Color(199, 135, 23));
        colors.add(new Color(199, 143, 23));
        colors.add(new Color(199, 151, 31));
        colors.add(new Color(191, 159, 31));
        colors.add(new Color(191, 159, 31));
        colors.add(new Color(191, 167, 39));
        colors.add(new Color(191, 167, 39));
        colors.add(new Color(191, 175, 47));
        colors.add(new Color(183, 175, 47));
        colors.add(new Color(183, 183, 47));
        colors.add(new Color(183, 183, 55));
        colors.add(new Color(207, 207, 111));
        colors.add(new Color(223, 223, 159));
        colors.add(new Color(239, 239, 199));
        colors.add(new Color(255, 255, 255)); // white
    }

}
