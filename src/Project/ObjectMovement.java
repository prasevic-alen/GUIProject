package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ObjectMovement extends JPanel {
    private ZRunner runner;
    private int x = 5;
    private int y = 5;
    private int scrollHorValue, scrollVerValue;

    private int objectSize = 30; // Size of the object

    public ObjectMovement(ZRunner runner) {
        this.runner = runner;
        setPreferredSize(new Dimension(187, 227));
        setOpaque(false);	
        setVisible(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Unused
            }

            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Unused
            }
        });

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
        g.fillRect(x, y, objectSize, objectSize);
        g.setColor(Color.darkGray);
        g.drawRect(x, y, objectSize, objectSize);
    }

    private void handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (y - 5 >= 0) {
                    y -= 5;
                }
                getVerValue();

                break;
            case KeyEvent.VK_DOWN:
                if (y + objectSize + 5 <= getHeight()) {
                    y += 5;
                }
                getVerValue();
                break;
            case KeyEvent.VK_LEFT:
                if (x - 5 >= 0) {
                    x -= 5;
                }
                getHorValue();
                break;
            case KeyEvent.VK_RIGHT:
                if (x + objectSize + 5 <= getWidth()) {
                    x += 5;
                }
                getHorValue();
                break;
        }
        
    }

    private void getHorValue(){
        scrollHorValue = (int) (x / 1.63);
        if (scrollHorValue >= 89 ){
            scrollHorValue = 90;
        }
        runner.getScrollHor().setValue(scrollHorValue);
    }

    private void getVerValue(){
        scrollVerValue = (int) (y / 2.03);
        if (scrollVerValue >= 89 ){
            scrollVerValue = 90;
        }
        runner.getScrollVer().setValue(scrollVerValue);
    }

    public void horizontalMovement(int value){
        x = (int) (value * 1.63);
    }

    public void verticalMovement(int value){
        y = (int) (value *  2.03);
    }
}
