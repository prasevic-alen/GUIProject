package Project; 

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;

public class AutomatorThread {
	private Boolean slider = true;
    private ScheduledExecutorService executorService;
	private ZRunner runner;
	private JSlider sliderHeat, sliderCool, pressureSlider1, pressureSlider2;
	private JToggleButton toggleCooling, toggleHeating;
	private JSpinner spinnerHeat, spinnerCool;

    public AutomatorThread(ZRunner zrunner) {
		runner = zrunner;
        executorService = Executors.newScheduledThreadPool(1);
		sliderHeat = runner.getSliderHeat();
		sliderCool = runner.getSliderCool();
		pressureSlider1 = runner.getPresSlider_1();
		pressureSlider2 = runner.getPresSlider_2();
		toggleCooling = runner.getTglStartCooling();
		toggleHeating = runner.getTglStartHeating();
		spinnerHeat = runner.getSpinnerHeat();
		spinnerCool = runner.getSpinnerCool();        
    }

    public void start() {
        executorService.scheduleAtFixedRate(this::automateProductionTask, 0, 8, TimeUnit.SECONDS);
		//System.out.println(sliderHeat.getValue());
    }

    public void terminate() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private void automateProductionTask() {
        double targetElectricity = 618;

        if (runner.getElectricity() < targetElectricity) {
            // Adjust sliders and toggles for heating
            sliderHeat.setValue(sliderHeat.getValue() + 1);
			sliderHeat.setEnabled(true);
			spinnerHeat.setEnabled(true);
            toggleHeating.setSelected(true);
            toggleHeating.setText("Stop heating");
            toggleCooling.setSelected(true);
            toggleCooling.setText("Stop cooling");
            sliderCool.setEnabled(true);
            spinnerCool.setEnabled(true);
            if (sliderCool.getValue() > 6 ) {
                sliderCool.setValue(sliderCool.getValue() - 1);
            }
            adjustPressureSliders();
            checkTemp();
        } else if (runner.getElectricity() > targetElectricity) {
            // Adjust sliders and toggles for cooling
            sliderHeat.setValue(sliderHeat.getValue() - 1);
            sliderCool.setValue(sliderCool.getValue() + 1);
			sliderCool.setEnabled(true);
			spinnerCool.setEnabled(true);
            adjustPressureSliders();
            checkTemp();
        }


    }

    private void checkTemp(){
        if (runner.getCurrentTemp() > 650 ){ 
            sliderCool.setValue(sliderCool.getValue() + 1);
            sliderHeat.setValue(sliderHeat.getValue() - 1);
        }
    }

    private void adjustPressureSliders() {
        if (slider) {
            if (pressureSlider1.getValue() <= 5){
                pressureSlider1.setValue(pressureSlider1.getValue() + 1);
                slider = false;
            }
        } else {
            if (pressureSlider2.getValue() <= 5) {
                pressureSlider2.setValue(pressureSlider2.getValue() + 1);
                slider = true;
            }
        }
    }

    public boolean isShutdown(){
        return executorService.isShutdown();
    }
}
