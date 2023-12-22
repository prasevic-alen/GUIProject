package Project;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
// import java.awt.*;

public class FirePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    //private Color lightBlue = new Color(0,0,182,155);

	private final Fire fire;

    public FirePanel(int width, int height) {
        this.fire = new Fire(width, height, this);
        this.setPreferredSize(new Dimension(width, height));
    }

    public void startFireAnimation(int newSpeed) {
        int fireDelay = (20 - newSpeed) * 2 ; 
        fire.updateFire(fireDelay);
        //System.out.println("Fire delay: " + fireDelay);
    }

    public void stopFireAnimation() {
       fire.updateFire(0);
    }

    public Fire getFire() {
        return fire;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.setColor(lightBlue);
        // g.fillRect(0, 0, 186, 100);
        g.drawImage(fire.getBufferedImage(), 0, 0, null);
    }
}

