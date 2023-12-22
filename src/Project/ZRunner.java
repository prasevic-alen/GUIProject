package Project;

import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;
import java.util.Objects;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.event.*;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class ZRunner extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, mainDisplay;
	private FirePanel firePanel;
	private FanRotation fanRotation;
	private ValveRotation valveRotation1, valveRotation2;
	private Integer valvePriv1, valvePriv2;
	private JCheckBox checkBox1, checkBox2;
	private JLabel lblHeatLevel, lblFanLevel, lblPress_1, lblPress_2, lblMainStatusUpdate;
	private JLabel lblElectricity, lblBoilerPressure, lblBoilerTemperature, lblTurbineTemperature, lblRunningStatus;
	private JButton upButton, downButton, btnControl;
	private JSlider presSlider_2, presSlider_1, sliderHeat, sliderCool;
	private JSpinner spinnerHeat, spinnerCool;
	private JScrollBar scrollHor, scrollVer;
	private JToggleButton tglStartHeating, tglStartCooling, tglbtnActivateKeyControl;
	private JProgressBar heatingBar, pressureBar, coolingBar;
	private JComboBox<String> comboBox;
	private Timer timer;
	private AutomatorThread automatorThread;
	private ObjectMovement box;
	private JPanel panel, randomDisplay, statusDisplay;
	private JTextField xField, yField;
	private BlueCirclePanel circle;
	private UpdateSimulation simulation;
	private PressureGauge pressureGauge;
	private CoolingGauge coolingGauge;
	private HeatingGauge heatingGauge;
	private MenuBar menuBar;
	private Boolean pressureSliderBoolean = true;
	private JLabel lblText1, lblText2, lblText3;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZRunner frame = new ZRunner();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public ZRunner() {
		setTitle("Alen Prasevic - GUI Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setMinimumSize(new Dimension(1000,600));
		setMaximumSize(new Dimension(1000,600));
		// Add needed classes for the display
		presSlider_1 = new JSlider(0, 10, 0);
		presSlider_2 = new JSlider(0, 10, 0);
		sliderHeat = new JSlider(0, 10, 0);
		sliderCool = new JSlider(0, 10, 0);
		// presSlider_1.setValue(0);
		// sliderHeat.setValue(0);
		// sliderCool.setValue(0);
		// presSlider_2.setValue(0);
		initializeComponents();
		
		setJMenuBar(menuBar);

		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel displayPanelHeading = new JPanel();
		contentPane.add(displayPanelHeading);
		displayPanelHeading.setLayout(new BoxLayout(displayPanelHeading, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("Display panel");
		displayPanelHeading.add(lblNewLabel_1);
		
		JPanel displayPanel = new JPanel();
		displayPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(displayPanel);
		displayPanel.setLayout(new GridLayout(1, 5, 0, 0));
		
		JPanel heatingDisplay = new JPanel();
		// heatingDisplay.setBackground(Color.LIGHT_GRAY);
		displayPanel.add(heatingDisplay);
		heatingDisplay.setLayout(null);

		firePanel.setBounds(6, 160, 186, 101);
		heatingDisplay.add(firePanel);
		heatingBar = new JProgressBar();
		heatingBar.setBorder(BorderFactory.createRaisedBevelBorder());
		heatingBar.setToolTipText("temperatureBar");
		heatingBar.setStringPainted(true);
		heatingBar.setBounds(6, 30, 186, 20);
		heatingDisplay.add(heatingBar);

		heatingGauge.setBounds(6, 65,186,45); 
		heatingDisplay.add(heatingGauge);
		
		// Start of the cooling display
		JPanel coolingDisplay = new JPanel();
		// coolingDisplay.setBackground(Color.LIGHT_GRAY);
		displayPanel.add(coolingDisplay);
		coolingDisplay.setLayout(null);
		
		JEditorPane fanPane = new JEditorPane();
		
		// Adding Fans to the display
		fanPane.setBounds(6, 160, 186, 100);
		fanRotation.setBounds(6, 130, 186, 101);
		coolingDisplay.add(fanRotation);
		
		coolingBar = new JProgressBar();
		coolingBar.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		coolingBar.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		coolingBar.setToolTipText("coolingBar");
		coolingBar.setBounds(6, 30, 186, 20);
		coolingDisplay.add(coolingBar);

		coolingGauge.setBounds(6, 65,186,45); 
		coolingDisplay.add(coolingGauge);
		

		JPanel pressureDisplay = new JPanel();
		// pressureDisplay.setBackground(Color.LIGHT_GRAY);
		displayPanel.add(pressureDisplay);
		pressureDisplay.setLayout(null);
		valveRotation1.setBounds(45, 180, 50, 50);
		pressureDisplay.add(valveRotation1);
		valveRotation2.setBounds(105, 180, 50, 50);
		pressureDisplay.add(valveRotation2);
		
		pressureBar = new JProgressBar();
		pressureBar.setBorder(BorderFactory.createLineBorder(Color.black));
		pressureBar.setToolTipText("temperatureBar");
		pressureBar.setStringPainted(true);
		pressureBar.setBounds(6, 30, 186, 20);
		pressureDisplay.add(pressureBar);

		pressureGauge.setBounds(6, 65,186,45);
		pressureDisplay.add(pressureGauge);

		// Generating Random Display
		randomDisplay = new JPanel();	
		randomDisplay.setBorder(BorderFactory.createLoweredBevelBorder());
		randomDisplay.setBorder(UIManager.getBorder("List.sourceListFocusedSelectionBackgroundPainter"));
		displayPanel.add(randomDisplay);
		randomDisplay.setLayout(null);
		
		JLabel lblRandomDsiplay = new JLabel("Random Display");
		lblRandomDsiplay.setHorizontalAlignment(SwingConstants.CENTER);
		lblRandomDsiplay.setBounds(6, 6, 187, 16);
		randomDisplay.add(lblRandomDsiplay);

		panel = new JPanel();
		panel.setBounds(6, 28, 187, 227);
		panel.setBorder(UIManager.getBorder("List.sourceListFocusedSelectionBackgroundPainter"));
		randomDisplay.add(panel);

		box = new ObjectMovement(this);
		
		panel.add(box);

		//Generating Status Display
		statusDisplay = new JPanel();
		statusDisplay.setBackground(Color.LIGHT_GRAY);
		statusDisplay.setBorder(BorderFactory.createLoweredBevelBorder());
		displayPanel.add(statusDisplay);
		statusDisplay.setLayout(null);
		
		JLabel lblProdTitle = new JLabel("Power Plant status");
		lblProdTitle.setBounds(10, 10, 179, 19);
		lblProdTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdTitle.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		statusDisplay.add(lblProdTitle);
		
		lblElectricity = new JLabel("Elec prod:     " + String.format("%.1f", simulation.getElectricity()) + "MW.");
		lblElectricity.setBounds(10, 50, 179, 16);
		lblElectricity.setHorizontalAlignment(SwingConstants.LEFT);
		lblElectricity.setToolTipText("Current electicity production.");
		statusDisplay.add(lblElectricity);
		
		lblTurbineTemperature = new JLabel("Turbine temp: \t" + String.format("%.1f", simulation.getTemperature()) + "C");
		lblTurbineTemperature.setBounds(10, 80, 179, 16);
		lblTurbineTemperature.setHorizontalAlignment(SwingConstants.LEFT);
		lblTurbineTemperature.setToolTipText("Current Temperature in the turbine.");
		statusDisplay.add(lblTurbineTemperature);
		
		lblBoilerTemperature = new JLabel("Boiler temp: \t" + String.format("%.1f", simulation.getTemperature() * 0.93) + "C");
		lblBoilerTemperature.setBounds(10, 110, 179, 16);
		lblBoilerTemperature.setHorizontalAlignment(SwingConstants.LEFT);
		lblBoilerTemperature.setToolTipText("Current Temperature in the boiler.");
		statusDisplay.add(lblBoilerTemperature);
		
		lblBoilerPressure = new JLabel("Boiler pressure: \t" + String.format("%.1f", simulation.getPressure()) + "bar");
		lblBoilerPressure.setBounds(10, 140, 179, 16);
		lblBoilerPressure.setToolTipText("Current Temperature in the boiler.");
		lblBoilerPressure.setHorizontalAlignment(SwingConstants.LEFT);
		statusDisplay.add(lblBoilerPressure);
		
		lblRunningStatus = new JLabel("Status:    " + simulation.getRunningStatus() + ".");
		lblRunningStatus.setBounds(10, 170, 179, 16);
		statusDisplay.add(lblRunningStatus);
		
		JPanel controlPanelHeading = new JPanel();
		contentPane.add(controlPanelHeading);
		controlPanelHeading.setLayout(new BoxLayout(controlPanelHeading, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Control Panel");
		controlPanelHeading.add(lblNewLabel);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(controlPanel);
		controlPanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JPanel heatingControl = new JPanel();
		// heatingControl.setBackground(new Color(255, 255, 230));
		controlPanel.add(heatingControl);
		heatingControl.setLayout(null);
		
		JLabel lblHeatControls = new JLabel("Heating controls");
		lblHeatControls.setBounds(10, 0, 189, 16);
		lblHeatControls.setToolTipText("start heating");
		lblHeatControls.setHorizontalAlignment(SwingConstants.CENTER);
		heatingControl.add(lblHeatControls);
		
		// sliderHeat = new JSlider(0, 10, 0);
		sliderHeat.setBounds(10, 33, 56, 190);
		sliderHeat.setMajorTickSpacing(2);
		sliderHeat.setBackground(Color.LIGHT_GRAY);
		sliderHeat.setPaintTicks(true);
		sliderHeat.setBorder(new BevelBorder(BevelBorder.LOWERED));
		sliderHeat.setPaintLabels(true);
		sliderHeat.setEnabled(false);
		sliderHeat.setOrientation(SwingConstants.VERTICAL);
		heatingControl.add(sliderHeat);
		
		JLabel lblCurrentHeat = new JLabel("Current heat level");
		lblCurrentHeat.setBounds(72, 38, 117, 16);
		lblCurrentHeat.setHorizontalAlignment(SwingConstants.CENTER);
		heatingControl.add(lblCurrentHeat);
		
		lblHeatLevel = new JLabel("0");
		lblHeatLevel.setBorder(new BevelBorder(BevelBorder.RAISED));
		lblHeatLevel.setBounds(72, 69, 117, 25);
		lblHeatLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHeatLevel.setLabelFor(lblCurrentHeat);
		lblHeatLevel.setHorizontalAlignment(SwingConstants.CENTER);
		heatingControl.add(lblHeatLevel);
		
		spinnerHeat = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerHeat.setBorder(new BevelBorder(BevelBorder.LOWERED));
		spinnerHeat.setBounds(72, 127, 87, 32);
		spinnerHeat.setEnabled(false);
		spinnerHeat.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		heatingControl.add(spinnerHeat);
		
		tglStartHeating = new JToggleButton("Start heating");
		tglStartHeating.setBounds(10, 228, 179, 29);
		tglStartHeating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectionButtonPressed(e);
			}
		});

		heatingControl.add(tglStartHeating);

		sliderHeat.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				heatChange(e);
			}
		});
		spinnerHeat.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				heatChange(e);
			}
		});

		// Starting cooling control Panel
		JPanel coolingControl = new JPanel();
		// coolingControl.setBackground(new Color(255, 255, 240));
		controlPanel.add(coolingControl);
		coolingControl.setLayout(null);
		
		tglStartCooling = new JToggleButton("Start cooling");
		tglStartCooling.setBounds(10, 228, 179, 29);
		coolingControl.add(tglStartCooling);
		
		JLabel lblCoolingControls = new JLabel("Cooling controls");
		lblCoolingControls.setBounds(10, 0, 179, 16);
		lblCoolingControls.setToolTipText("Start cooling");
		lblCoolingControls.setHorizontalAlignment(SwingConstants.CENTER);
		coolingControl.add(lblCoolingControls);
		
		// sliderCool = new JSlider();
		sliderCool.setBounds(10, 33, 56, 190);
		sliderCool.setValue(0);
		sliderCool.setEnabled(false);
		sliderCool.setPaintTicks(true);
		sliderCool.setPaintLabels(true);
		sliderCool.setOrientation(SwingConstants.VERTICAL);
		sliderCool.setMaximum(10);
		sliderCool.setMajorTickSpacing(2);
		sliderCool.setBorder(new BevelBorder(BevelBorder.LOWERED));
		sliderCool.setBackground(Color.LIGHT_GRAY);
		coolingControl.add(sliderCool);
		
		JLabel lblCurrentFan = new JLabel("Current cooling");
		lblCurrentFan.setBounds(72, 38, 117, 16);
		lblCurrentFan.setHorizontalAlignment(SwingConstants.CENTER);
		coolingControl.add(lblCurrentFan);
		
		lblFanLevel = new JLabel("0");
		lblFanLevel.setBorder(new BevelBorder(BevelBorder.RAISED));
		lblFanLevel.setBounds(72, 69, 117, 25);
		lblFanLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblFanLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		coolingControl.add(lblFanLevel);
		
		spinnerCool = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerCool.setBorder(new BevelBorder(BevelBorder.LOWERED));
		spinnerCool.setBounds(72, 127, 84, 37);
		spinnerCool.setEnabled(false);
		spinnerCool.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		coolingControl.add(spinnerCool);

		tglStartCooling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectionButtonPressed(e);
			}
		});
		sliderCool.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				coolChange(e);
			}
		});
		spinnerCool.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				coolChange(e);
			}
		});
		// Pressure control panel
		JPanel pressureControl = new JPanel();
		// pressureControl.setBackground(new Color(255, 255, 240));
		controlPanel.add(pressureControl);
		pressureControl.setLayout(null);
		
		JLabel lblPressure = new JLabel("Presure controls");
		lblPressure.setBounds(10, 0, 179, 16);
		lblPressure.setToolTipText("Change pressure");
		lblPressure.setHorizontalAlignment(SwingConstants.CENTER);
		pressureControl.add(lblPressure);
		
		JLabel lblValve_3_1 = new JLabel("1       2");
		lblValve_3_1.setBounds(10, 38, 64, 29);
		lblValve_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		pressureControl.add(lblValve_3_1);
		
		// presSlider_2 = new JSlider(0, 10, 0);
		presSlider_2.setBounds(45, 65, 29, 190);
		presSlider_2.setBorder(new BevelBorder(BevelBorder.RAISED));
		presSlider_2.setOrientation(SwingConstants.VERTICAL);
		
		pressureControl.add(presSlider_2);
		
		JLabel lblValve_1 = new JLabel("Valves");
		lblValve_1.setBounds(10, 24, 64, 16);
		lblValve_1.setHorizontalAlignment(SwingConstants.CENTER);
		pressureControl.add(lblValve_1);
		
		//presSlider_1 = new JSlider(0, 10, 0);
		presSlider_1.setBounds(10, 65, 29, 190);
		presSlider_1.setBorder(new BevelBorder(BevelBorder.RAISED));
		presSlider_1.setOrientation(SwingConstants.VERTICAL);
		pressureControl.add(presSlider_1);
		
		// Initialize checkboxes
		checkBox1 = new JCheckBox("Valve 1" );
		checkBox1.setBounds(78, 66, 77, 30);
		checkBox1.setBorder(new BevelBorder(BevelBorder.LOWERED));
		checkBox1.setVerticalAlignment(SwingConstants.TOP);

		checkBox2 = new JCheckBox("Valve 2");
		checkBox2.setBounds(74, 98, 77, 30);
		checkBox2.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox2.setBorder(new BevelBorder(BevelBorder.LOWERED));

        // Initialize labels
        lblPress_1 = new JLabel("0");
        lblPress_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblPress_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPress_1.setBounds(155, 63, 30, 30);
        lblPress_2 = new JLabel("0");
        lblPress_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPress_2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblPress_2.setBounds(155, 100, 30, 30);
        lblPress_2.setVerticalAlignment(SwingConstants.TOP);

        // Initialize buttons
        upButton = new JButton("Up");
        upButton.setBounds(78, 167, 120, 29);
        downButton = new JButton("Down");
        downButton.setBounds(78, 204, 120, 29);
		
        pressureControl.add(checkBox1);
        pressureControl.add(checkBox2);
        pressureControl.add(lblPress_1);
        pressureControl.add(lblPress_2);
        pressureControl.add(upButton);
        pressureControl.add(downButton);
        
        ActionListener checkBoxListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };
        
        checkBox1.addActionListener(checkBoxListener);
        checkBox2.addActionListener(checkBoxListener);
        
     // Add ActionListener to buttons
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpButton();
            }
        });

        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDownButton();
            }
        });

		ChangeListener presSliderListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				handlePressureChange(e);
			}
		};
	
		presSlider_1.addChangeListener(presSliderListener);
		presSlider_2.addChangeListener(presSliderListener);

		JPanel randomControl = new JPanel();
		randomControl.setBackground(new Color(255, 255, 240));
		controlPanel.add(randomControl);
		randomControl.setLayout(null);
		//randomControl.setFocusTraversalKeysEnabled(false);
		
		JLabel lblRandomControl = new JLabel("Random Control");
		lblRandomControl.setHorizontalAlignment(SwingConstants.CENTER);
		lblRandomControl.setBounds(48, 5, 102, 16);
		randomControl.add(lblRandomControl);
		
		scrollVer = new JScrollBar();
		scrollVer.setBounds(6, 56, 15, 154);
		scrollVer.setBorder(new BevelBorder(BevelBorder.LOWERED));
		randomControl.add(scrollVer);
		
		scrollHor = new JScrollBar();
		scrollHor.setOrientation(JScrollBar.HORIZONTAL);
		scrollHor.setBounds(22, 33, 160, 16);
		scrollHor.setBorder(new BevelBorder(BevelBorder.LOWERED));
		randomControl.add(scrollHor);
		
		btnControl = new JButton("Show and move image");
		btnControl.setBounds(22, 232, 168, 29);
		btnControl.setEnabled(false);
		randomControl.add(btnControl);
		
		JLabel lblNewLabel_2 = new JLabel("X coordinate");
		lblNewLabel_2.setBounds(33, 172, 87, 26);
		randomControl.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Y coordinate");
		lblNewLabel_2_1.setBounds(33, 200, 87, 26);
		randomControl.add(lblNewLabel_2_1);
		
		xField = new JTextField();
		xField.setToolTipText("Set horizontal coordinate.");
		xField.setBounds(130, 172, 60, 26);
		xField.setEnabled(false);
		randomControl.add(xField);
		xField.setColumns(1);
		
		yField = new JFormattedTextField();
		yField.setToolTipText("Set vertical coordinate.");
		yField.setColumns(1);
		yField.setBounds(130, 200, 60, 26);
		yField.setEnabled(false);
		randomControl.add(yField);
		
		tglbtnActivateKeyControl = new JToggleButton("Box controls");
		tglbtnActivateKeyControl.setBounds(21, 105, 172, 29);
		tglbtnActivateKeyControl.setSelected(true);
		randomControl.add(tglbtnActivateKeyControl);
		
		lblText1 = new JLabel();
		lblText1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblText1.setBounds(32, 56, 150, 16);
		randomControl.add(lblText1);
		
		lblText2 = new JLabel();
		lblText2.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblText2.setBounds(33, 74, 150, 16);
		
		lblText3 = new JLabel();
		lblText3.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblText3.setBounds(32, 90, 150, 16);
		
		JLabel lblText4 = new JLabel();
		lblText4.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblText4.setBounds(31, 140, 150, 16);

		JLabel lblText5 = new JLabel();
		lblText5.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblText5.setBounds(31, 156, 150, 16);

		lblText1.setText("Box will be controlled with");
		lblText2.setText("the arrows in the Random");
		lblText3.setText("upper Display.");
		lblText4.setText("Enter coordinate to control");
		lblText5.setText("the circle.");

		randomControl.add(lblText1);
		randomControl.add(lblText2);
		randomControl.add(lblText3);
		randomControl.add(lblText4);
		randomControl.add(lblText5);
		
		ActionListener toggActionListener = new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				changeFocus(e);
            }
		};

		tglbtnActivateKeyControl.addActionListener(toggActionListener);

		ActionListener btnControlListener = new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				createBox();

            }
		};
		btnControl.addActionListener(btnControlListener);

		KeyAdapter textCheck = new KeyAdapter() {
			@Override
        	public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				if (!((key >= '0') && (key <= '9') ||
					(key == KeyEvent.VK_BACK_SPACE) ||
					(key == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_TAB || keyCode == KeyEvent.VK_SPACE) {
                    createBox();
                }
			}
		};

		xField.addKeyListener(textCheck);
		yField.addKeyListener(textCheck);

		AdjustmentListener scrolListener = new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				handleScrolling(e);
			}		
		};
		scrollHor.addAdjustmentListener(scrolListener);
		scrollVer.addAdjustmentListener(scrolListener);


		mainDisplay = new JPanel();
		mainDisplay.setBackground(new Color(255, 255, 240));
		controlPanel.add(mainDisplay);
		mainDisplay.setLayout(null);
		
		String[] comboCommands = {"Start production", "Increase heating", "Increase cooling", "Increase pressure", "Automate production"};
		comboBox = new JComboBox<String>(comboCommands);
		comboBox.setBackground(SystemColor.scrollbar);

		comboBox.setToolTipText("Make some basic controls");
		comboBox.setBounds(6, 47, 187, 27);
		mainDisplay.add(comboBox);

		// Add action Listener to the comboBox
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboExecuter();
			}
		});

		JLabel lblMainHeading = new JLabel("Main Control");
		lblMainHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainHeading.setBounds(6, 6, 187, 16);
		mainDisplay.add(lblMainHeading);
		
		JLabel lblMainStatus = new JLabel("Control Status");
		lblMainStatus.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblMainStatus.setBounds(6, 111, 187, 16);
		mainDisplay.add(lblMainStatus);
		
		lblMainStatusUpdate = new JLabel("Main Control updates");
		lblMainStatusUpdate.setForeground(UIManager.getColor("Button.light"));
		lblMainStatusUpdate.setBackground(new Color(137, 255, 252));
		lblMainStatusUpdate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblMainStatusUpdate.setBounds(6, 141, 187, 51);
		mainDisplay.add(lblMainStatusUpdate);


		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				simulation.setVariables();
				simulation.startSimulation();
				updateBarsCorrectly();
			}


		});
		timer.start();

	}
	
	public void initializeComponents() {
        firePanel = new FirePanel(186, 100);
        firePanel.setForeground(SystemColor.window);
		fanRotation = new FanRotation(0.0);
		fanRotation.addImage("resources/fan1.png", 6, 50, 50, 50);
        fanRotation.addImage("resources/fan1.png", 66, 50, 50, 50);
        fanRotation.addImage("resources/fan1.png", 126, 50, 50, 50);

		valveRotation1 = new ValveRotation();
		valveRotation2 = new ValveRotation();

		circle = new BlueCirclePanel();
		simulation = new UpdateSimulation(this);

		pressureGauge = new PressureGauge(this);
		heatingGauge = new HeatingGauge(this);
		coolingGauge = new CoolingGauge(this);

		menuBar = new MenuBar(this);
    }

	private void heatChange(EventObject e) {
		if (e.getSource() == sliderHeat) {
			int value = sliderHeat.getValue();
			lblHeatLevel.setText("" + value);
			spinnerHeat.setValue(value);
			firePanel.startFireAnimation(value);
			heatingBar.setValue(value * 10);
			//System.out.println("Heating is on: " + value);
		} else if (e.getSource() == spinnerHeat) {
			int value = (int) spinnerHeat.getValue();
			lblHeatLevel.setText("" + value);
			sliderHeat.setValue(value);
			firePanel.startFireAnimation(value);
			heatingBar.setValue(value * 10);
		}
		if (sliderHeat.getValue() == 0) {
				firePanel.stopFireAnimation();
		}
	}

	private void coolChange(EventObject e) {
		if (e.getSource() == sliderCool) {
			int value = sliderCool.getValue();
			lblFanLevel.setText("" + value);
			spinnerCool.setValue(value);
			fanRotation.setSpeed(value);
			coolingBar.setValue(value * 10);
		} else if (e.getSource() == spinnerCool) {
			int value = (int) spinnerCool.getValue();
			lblFanLevel.setText("" + value);
			sliderCool.setValue(value);
			fanRotation.setSpeed(value);
			coolingBar.setValue(value * 10);
		}
		if (sliderCool.getValue() == 0) {
				firePanel.stopFireAnimation();
				fanRotation.setSpeed(0);
		}
	}

	private void selectionButtonPressed(ActionEvent e) {
		if (e.getSource() == tglStartHeating) {
			if (tglStartHeating.isSelected()) {
				tglStartHeating.setText("Stop heating");
				sliderHeat.setEnabled(true);
				sliderHeat.setValue(1);
				spinnerHeat.setEnabled(true);
				terminateAutomateThread("Manual Control");
			} else {
				tglStartHeating.setText("Start heating");
				sliderHeat.setEnabled(false);
				sliderHeat.setValue(0);
				spinnerHeat.setEnabled(false);
				firePanel.stopFireAnimation();
				terminateAutomateThread("Manual Control");
			}
		} else if (e.getSource() == tglStartCooling) {
			if (tglStartCooling.isSelected()) {
				tglStartCooling.setText("Stop cooling");
				sliderCool.setEnabled(true);
				sliderCool.setValue(1);
				spinnerCool.setEnabled(true);
				fanRotation.startRotation();
				fanRotation.setSpeed(1);
				terminateAutomateThread("Manual Control");
			} else {
				tglStartCooling.setText("Start cooling");
				sliderCool.setEnabled(false);
				sliderCool.setValue(0);
				spinnerCool.setEnabled(false);
				fanRotation.setSpeed(0);
				terminateAutomateThread("Manual Control");
			}

		}

	}

	private void handlePressureChange(EventObject e) {
		if (e.getSource() == presSlider_1) {
			if (valvePriv1 == null) {
				valvePriv1 = 0;
			}
			int value1 = presSlider_1.getValue();
			lblPress_1.setText("" + value1);

			if (value1 > valvePriv1) {
				valveRotation1.rotateValveRight();
				valvePriv1 = value1;
				pressureBar.setValue((presSlider_1.getValue() + presSlider_2.getValue()) * 5);
			} else if (value1 < valvePriv1) {
				valveRotation1.rotateValveLeft();
				valvePriv1 = value1;
				pressureBar.setValue((presSlider_1.getValue() + presSlider_2.getValue()) * 5);
			} else {
				//System.out.println("Valve 1 is not rotating: " + value1 + " " + valvePriv1);
			}
		}
		if (e.getSource() == presSlider_2) {
			if (valvePriv2 == null) {
				valvePriv2 = 0;
			}
			int value2 = (int) presSlider_2.getValue();
			lblPress_2.setText("" + value2);

			if (value2 > valvePriv2) {
				pressureBar.setValue((presSlider_1.getValue() + presSlider_2.getValue()) * 5);
				valveRotation2.rotateValveRight();
				valvePriv2 = value2;
			} else if (value2 < valvePriv2) {
				pressureBar.setValue((presSlider_1.getValue() + presSlider_2.getValue()) * 5);
				valveRotation2.rotateValveLeft();
				valvePriv2 = value2;
			} else {
				//System.out.println("Valve 2 is not rotating: " + value2 + " " + valvePriv2);
			}
		}
	}

	private void handleUpButton() {
		if (checkBox1.isSelected()) {
			handleSlideAndLabel(presSlider_1, lblPress_1, presSlider_1.getValue() + 1);
        } 
		
		if (checkBox2.isSelected()) {
            handleSlideAndLabel(presSlider_2, lblPress_2, presSlider_2.getValue() + 1);
        } 

		if (!(checkBox1.isSelected() || checkBox2.isSelected())) {
			JOptionPane.showMessageDialog(contentPane, "Select at least one Valve", "Warning", JOptionPane.WARNING_MESSAGE);
		}

	}

    private void handleDownButton() {
		if (checkBox1.isSelected()) {
			handleSlideAndLabel(presSlider_1, lblPress_1, presSlider_1.getValue() - 1);
        } 
		
		if (checkBox2.isSelected()) {
			handleSlideAndLabel(presSlider_2, lblPress_2, presSlider_2.getValue() - 1);
		} 

		if (!(checkBox1.isSelected() || checkBox2.isSelected())) {
			JOptionPane.showMessageDialog(contentPane, "Select at least one Valve", "Warning", JOptionPane.WARNING_MESSAGE);
		}
    }

	private void handleSlideAndLabel(JSlider slider, JLabel label, int value) {
		if (value < 0) {
			value = 0;
		} else if (value > 10) {
			value = 10;
		}
		slider.setValue(value);
    	label.setText("" + value);
	}

	public void updateBarsCorrectly()  {

	//	heatingBar.setValue();
	}

	public void restartSimulation() {
        System.out.println("Restarting Simulation...");
        ZRunner.main(null);;
    }

    public void exitSimulation() {
        System.out.println("Exiting Simulation...");
        this.dispose();
        System.exit(0);
    }

	public void comboExecuter(){
		String selectedCommand = (String) comboBox.getSelectedItem();

		if ("Start production".equals(selectedCommand)) {
			terminateAutomateThread("Starting production...");
			tglStartHeating.setSelected(true);
			tglStartHeating.setText("Stop heating");
			sliderHeat.setValue(2);
			sliderHeat.setEnabled(true);
			spinnerHeat.setEnabled(true);
			tglStartCooling.setSelected(true);
			tglStartCooling.setText("Stop cooling");
			sliderCool.setValue(1);
			sliderCool.setEnabled(true);
			spinnerCool.setEnabled(true);
			presSlider_1.setValue(4);
			presSlider_2.setValue(3);
        } else if ("Increase heating".equals(selectedCommand)) {
			terminateAutomateThread("Increasing heating...");
			sliderHeat.setValue(sliderHeat.getValue() + 1);
			sliderHeat.setEnabled(true);
        } else if ("Increase cooling".equals(selectedCommand)) {
			terminateAutomateThread("Increasing cooling...");
			sliderCool.setValue(sliderCool.getValue() + 1);
			sliderCool.setEnabled(true);
        } else if ("Increase pressure".equals(selectedCommand)) {
			terminateAutomateThread("Increasing pressure.");
			if (pressureSliderBoolean) {
				presSlider_1.setValue(presSlider_1.getValue() + 1);
				pressureSliderBoolean = false;
			} else {
				presSlider_2.setValue(presSlider_2.getValue() + 1);
				pressureSliderBoolean = true;
			}
        } else if ("Automate production".equals(selectedCommand)) {
			automatorThread = new AutomatorThread(this);	
			automatorThread.start();
			lblMainStatusUpdate.setText("Automate controls.");
            //System.out.println("Automating production...");
        }
	}

	private void terminateAutomateThread(String statusUpdateString) {	
		if (Objects.nonNull(automatorThread)) {
			if (!automatorThread.isShutdown()) { 
				automatorThread.terminate();
				//System.out.println(statusUpdateString);
			}
		}
		lblMainStatusUpdate.setText(statusUpdateString);
	}

	public void handleScrolling(AdjustmentEvent e) {
		if (e.getSource() == scrollVer) {
			box.verticalMovement(e.getValue());
		}
		if (e.getSource() == scrollHor) {
			box.horizontalMovement(e.getValue());
		}	
	}

	public void createBox() {
		int xCoordinate, yCoordinate;
		int xMax = 142;
    	int yMax = 180;

		if (xField.getText().isBlank()){
			xCoordinate = 0;
			xField.setText(Integer.toString(xCoordinate));
		} else {
			xCoordinate = Integer.parseInt(xField.getText());
		}
		if (yField.getText().isBlank()){
			yCoordinate = 0;
			yField.setText(Integer.toString(yCoordinate));
		} else {
			yCoordinate = Integer.parseInt(yField.getText());
		}

		if ( xCoordinate > xMax ){ 
            xCoordinate = xMax;
            xField.setText(Integer.toString(xMax));
        }
        if ( yCoordinate > yMax ) { 
            yCoordinate = yMax;
            yField.setText(Integer.toString(yMax));
        }   

		//BlueCirclePanel circle = new BlueCirclePanel(xCoordinate, yCoordinate);
		circle.setCoordinates(xCoordinate,yCoordinate);
		circle.setBounds(0, 0, 187, 257);
		circle.setForeground(Color.black);
		circle.setOpaque(false);
		panel.revalidate();
    	panel.repaint();
	}

	public void changeFocus(ActionEvent e) {
		if (tglbtnActivateKeyControl.isSelected()) {
			if (!panel.isAncestorOf(box)) {
				panel.add(box);
				panel.remove(circle);
			} 
			box.setFocusable(true);
			box.requestFocusInWindow();
			box.setEnabled(true);
			tglbtnActivateKeyControl.setText("Box control");
			btnControl.setEnabled(false);
			scrollVer.setEnabled(true);
			scrollHor.setEnabled(true);
			xField.setEnabled(false);
			yField.setEnabled(false);
		} else if (!tglbtnActivateKeyControl.isSelected()) {
			panel.remove(box);
			if (!panel.isAncestorOf(circle)) {
				panel.add(circle);
			}
			box.setFocusable(false);
			box.setEnabled(false);
			tglbtnActivateKeyControl.setText("Circle control");
			btnControl.setEnabled(true);
			scrollVer.setEnabled(false);
			scrollHor.setEnabled(false);
			xField.setEnabled(true);
			yField.setEnabled(true);
		}

		panel.revalidate();
		panel.repaint();
	}

	public JSpinner getSpinnerHeat() {
		return spinnerHeat;
	}

	public JSpinner getSpinnerCool() {
		return spinnerCool;
	}

	public double getElectricity(){
		return simulation.getElectricity();
	}

	public JSlider getPresSlider_2() {
		return presSlider_2;
	}


	public JSlider getPresSlider_1() {
		return presSlider_1;
	}


	public JSlider getSliderHeat() {
		return sliderHeat;
	}

	public JScrollBar getScrollHor() {
		return scrollHor;
	}

	public JScrollBar getScrollVer() {
		return scrollVer;
	}

	public JPanel getRandomDisplay() {
		return randomDisplay;
	}

	public JSlider getSliderCool() {
		return sliderCool;
	}

	public JTextField getxField() {
		return xField;
	}

	public JTextField getyField() {
		return yField;
	}

	public JToggleButton getTglStartHeating() {
		return tglStartHeating;
	}

	public JToggleButton getTglStartCooling() {
		return tglStartCooling;
	}

	public JProgressBar getHeatingBar() {
		return heatingBar;
	}

	public JLabel getLblElectricity() {
		return lblElectricity;
	}

	public JLabel getLblBoilerPressure() {
		return lblBoilerPressure;
	}

	public Double getBoilerPressure() {
		return simulation.getPressure();
	}

	public JLabel getLblBoilerTemperature() {
		return lblBoilerTemperature;
	}

	public JLabel getLblTurbineTemperature() {
		return lblTurbineTemperature;
	}

	public JLabel getLblRunningStatus() {
		return lblRunningStatus;
	}

	public JProgressBar getPressureBar() {
		return pressureBar;
	}

	public JProgressBar getCoolingBar() {
		return coolingBar;
	}

	public JPanel getStatusDisplay() {
		return statusDisplay;
	}

	public Double getCurrentTemp(){
		return simulation.getTemperature();
	}
}