package asgn2GUIs;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;

import javax.swing.JFrame;

import java.awt.*;
import javax.swing.*;


/**
 * This class is the graphical user interface for the rest of the system. 
 * Currently it is a ‘dummy’ class which extends JFrame and implements Runnable and ActionLister. 
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
 * interact with the rest of the system. You may choose to implement this class as you like, including changing 
 * its class signature – as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Person A and Person B
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	
	private ArrayList<Pizza> pizzas;
	private ArrayList<Customer> customers;
	
	private PizzaRestaurant restaurant;
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 400;
	
	// Panels
	private JPanel pnlDisplay;
	private JPanel pnlNorth;
	private JPanel pnlWest;
	private JPanel pnlEast;
	private JPanel pnlBtn;
	
	// Buttons
	private JButton btnTotalProfitMade;
	private JButton btnTotalDistanceTravelled;
	private JButton btnReset;
	private JButton btnLoadFile;
	
	// Displays
	private JTextArea txtDisplay;
	
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		super(title);
	}

	
	public void createGUI() {
		// Setting the main frame
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// Setting the panels
		pnlDisplay = createPanel(Color.WHITE);
		pnlNorth = createPanel(Color.LIGHT_GRAY);
		pnlEast = createPanel(Color.LIGHT_GRAY);
		pnlBtn = createPanel(Color.LIGHT_GRAY);
		pnlWest = createPanel(Color.LIGHT_GRAY);
		
		// Setting the buttons
		btnLoadFile = createButton("Load File");
		btnReset = createButton("Reset");
		btnTotalProfitMade = createButton("Total Profit Made");
		btnTotalDistanceTravelled = createButton("Total Distance Travelled");
		
		// Setting the displays
		txtDisplay = createTextArea();
		
		pnlDisplay.setLayout(new BorderLayout());
		pnlDisplay.add(txtDisplay, BorderLayout.CENTER);
		
		layoutButtonPanel();
		
		this.getContentPane().add(pnlDisplay,BorderLayout.CENTER);
	    this.getContentPane().add(pnlNorth,BorderLayout.NORTH);
	    this.getContentPane().add(pnlBtn,BorderLayout.SOUTH);
	    this.getContentPane().add(pnlEast,BorderLayout.EAST);
	    this.getContentPane().add(pnlWest,BorderLayout.WEST);
	    repaint();
	    this.setVisible(true);
	}
	
	@Override
	public void run() {
		createGUI();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		// Check whichever buttons gets pressed
		if (src == btnLoadFile) {
			fileChooser();
		}
	}

	private JPanel createPanel(Color c) {
		// Create a JPanel object and store it in a local var
		// set the background colour that passed in c
		// Return the JPanel object
		JPanel panel = new JPanel();
		panel.setBackground(c);
		return panel;
	}

	private JButton createButton(String str) {
		JButton jb = new JButton(str);
		jb.addActionListener(this);
		return jb;
	}

	private JTextArea createTextArea() {
		JTextArea jta = new JTextArea(); 
		jta.setEditable(false);
		jta.setLineWrap(true);
		jta.setFont(new Font("Arial",Font.BOLD,24));
		jta.setBorder(BorderFactory.createEtchedBorder());
		return jta;
	}
	
	private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
	      constraints.gridx = x;
	      constraints.gridy = y;
	      constraints.gridwidth = w;
	      constraints.gridheight = h;
	      jp.add(c, constraints);
	}
	
	private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
	    pnlBtn.setLayout(layout);
	    
	    //add components to grid
	    GridBagConstraints constraints = new GridBagConstraints(); 
	    
	    //Defaults
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 100;
	    constraints.weighty = 100;
	    
	    addToPanel(pnlBtn, btnLoadFile,constraints,0,0,2,1); 
	    addToPanel(pnlBtn, btnReset,constraints,3,0,2,1); 
	    addToPanel(pnlBtn, btnTotalProfitMade,constraints,0,2,2,1); 
	    addToPanel(pnlBtn, btnTotalDistanceTravelled,constraints,3,2,2,1); 	
	}
	
	private void fileChooser() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		chooser.setFileFilter(filter);
		
		int returnValue = chooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			try {
				java.awt.Desktop.getDesktop().open(selectedFile);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		// Populate the pizza and customer data
		//pizzas = LogHandler.populatePizzaDataset(chooser);
	}
	
}
