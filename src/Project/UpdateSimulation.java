package Project;

import java.awt.Color;

import javax.swing.*;

public class UpdateSimulation {
	private ZRunner runner;
    private String runningStatus;
    //private JProgressBar heatingBar, coolingBar, pressureBar;
    private JSlider sliderHeat, sliderCool;
    private JLabel lblElectricity, lblTurbineTemperature, lblBoilerTemperature, lblBoilerPressure, lblRunningStatus;
    private double heating, cooling, pressure, temperature, electricity;
    private JPanel statusDisplay;


    public UpdateSimulation(ZRunner runner) {
        this.runner = runner;
        

    }

    public void setVariables(){
        this.heating = runner.getHeatingBar().getValue();
        this.cooling = runner.getCoolingBar().getValue();
        this.pressure = runner.getPressureBar().getValue() / 10;

        this.sliderHeat = runner.getSliderHeat();
        this.sliderCool = runner.getSliderCool();

        this.lblElectricity = runner.getLblElectricity();
        this.lblTurbineTemperature = runner.getLblTurbineTemperature();
        this.lblBoilerTemperature = runner.getLblBoilerTemperature();
        this.lblBoilerPressure = runner.getLblBoilerPressure();
        this.lblRunningStatus = runner.getLblRunningStatus();
        this.statusDisplay = runner.getStatusDisplay();
    }



    public void startSimulation() { 
            // Update the temperature and pressure values
            temperature += heating * 0.11 - cooling * 0.11;
            if (temperature < 20) {
                temperature = 20;
            }

            pressure += heating * 0.07 - cooling * 0.06 + pressure ;
            if ( pressure < 0 ) {
                pressure = 0;
            }
            // Update the electricity and status values
            if (120 <= temperature && temperature < 480 && pressure > 3) {
                statusDisplay.setBackground(Color.GREEN);
                //System.out.println("Print: " + temperature + " " + pressure + " " + heating);
                electricity = temperature * 0.43 * pressure * 0.14;
                runningStatus = "Running";
            } else if (480 <= temperature && temperature < 680 && pressure > 3) {
                electricity = temperature * 0.43 * pressure * 0.14;
                runningStatus = "Emergency";
                statusDisplay.setBackground(Color.ORANGE);
            } else if (680 <= temperature && temperature < 780 && pressure > 5) {
                runningStatus = "Critical, reducing heat";
                sliderHeat.setValue(sliderHeat.getValue() - 3 );
                sliderCool.setValue(sliderCool.getValue() + 3 );
                statusDisplay.setBackground(Color.RED);
            } else if (780 <= temperature && pressure > 3) {
                runningStatus = "Shutting down production.";
                sliderHeat.setValue(0);
                for (int i=5; i>0; i--) {
                    sliderCool.setValue(i);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
  
                    }
                }
                JOptionPane.showMessageDialog(runner.getParent(), "The power plant is overheating,\n Stopping heater, \nand increasing cooling", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                electricity = 0.0;
                runningStatus = "Idle";
            }

            // Update the progress bars and labels
            lblElectricity.setText("Elec prod: \t" + String.format("%.1f", electricity) + "MW.");
            lblTurbineTemperature.setText("Turbine temp: \t" + String.format("%.1f", temperature) + "C");
            lblBoilerTemperature.setText("Boiler temp: \t" + String.format("%.1f", temperature * 0.93) + "C");
            lblBoilerPressure.setText("Boiler pressure: \t" + String.format("%.1f", pressure) + "bar");
            lblRunningStatus.setText("Status: " + runningStatus + ".");

        }

            public String getRunningStatus() {
        return runningStatus;
    }

    public double getCooling() {
        return cooling;
    }

    public double getPressure() {
        return pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getElectricity() {
        return electricity;
    }
}
