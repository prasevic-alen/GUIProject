package Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    private ZRunner runControl;

    public MenuBar(ZRunner runControl) {
        this.runControl = runControl;
        createFileMenu();
    }

    private void createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem restartItem = new JMenuItem("Restart Simulation");
        JMenuItem exitItem = new JMenuItem("Exit Simulation");
        JMenuItem showAbout = new JMenuItem("Show about");

        showAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                    null, 
                    "This is a simulation of a power plant.\nYou can play with all used functions.\n Created by Alen Prasevic 2023.",
                    "About", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        restartItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Do you want to restart the simulation?",
                        "Restart Simulation",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    // Restart the simulation
                    runControl.restartSimulation();
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Do you want to exit the simulation?",
                    "Exit Simulation",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                runControl.exitSimulation();
            }
        }
    });

        fileMenu.add(showAbout);
        fileMenu.add(restartItem);
        fileMenu.add(exitItem);
        
        add(fileMenu);
    }

}
