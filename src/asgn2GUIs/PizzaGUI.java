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
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
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
	public static final int WIDTH = 700;
	public static final int HEIGHT = 500;
	
	// Panels
	private JPanel pnlDisplay;
	private JPanel pnlDisplayBtn;
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
	
	// Tables
	private JTable customersTable;
	private JTable ordersTable;
	
	private String[] customerColumn = {
			"Name",
			"Mobile Number",
			"Type",
			"X",
			"Y",
			"Delivery Distance"
	};
	
	private String[] orderColumn = {
			"Type",
			"Quantity",
			"Price",
			"Cost",
			"Profit"
	};
	
	
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
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		// Setting the panels
		pnlDisplay = createPanel(Color.WHITE);
		pnlDisplayBtn = createPanel(Color.WHITE);
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
		pnlDisplay.add(pnlDisplayBtn, BorderLayout.PAGE_END);
		
		layoutButtonPanel();
		
		reCreateLayout();
	}
	
	public void reCreateLayout() {
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
		JButton button = (JButton) e.getSource();
		String command = button.getActionCommand();
		
		// Check whichever buttons gets pressed
		if (src == btnLoadFile) {
			fileChooser();
		}
		
		// Check if it's a file then load that file
		if (command.substring(command.length() - 4).equals(".txt")) {
			try {
				System.out.println("logs/" + command);
				pizzas = LogHandler.populatePizzaDataset("logs/" + command);
				customers = LogHandler.populateCustomerDataset("logs/" + command);
				generateTable();
			} catch (PizzaException e1) {
				e1.printStackTrace();
				System.out.println(e1.getMessage());
			} catch (CustomerException e1) {
				e1.printStackTrace();
				System.out.println(e1.getMessage());
			} catch (LogHandlerException e1) {
				e1.printStackTrace();
				System.out.println(e1.getMessage());
			}
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
		File folder = new File("logs");
		File[] listOfFiles = folder.listFiles();
		txtDisplay.setText("List of available files");
		
		// Initialize display constraints
		GridBagLayout layout = new GridBagLayout();
	    pnlDisplayBtn.setLayout(layout);
	    GridBagConstraints constraints = new GridBagConstraints(); 
		constraints.fill = GridBagConstraints.NONE;
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 250;
	    constraints.weighty = 250;
		
	    pnlDisplayBtn.removeAll();
	    
		for (int i = 0; i < listOfFiles.length; i+=1) {
			if(listOfFiles[i].isFile()) {
				JButton tempBtn = createButton(listOfFiles[i].getName());
				addToPanel(pnlDisplayBtn, tempBtn, constraints, 2, i + 10, 5, 2);
				tempBtn.addActionListener(this);
			}
		}
		
		pnlDisplayBtn.updateUI();
	}
	
	private void generateTable() {
		String[][] tmpCustomerTable = {};
		String[][] tmpOrderTable = {};
		
		System.out.println("Generating Table");
		
		for (int i = 0; i < customers.size(); i+=1) {
			Customer tmpC = customers.get(i);
			tmpCustomerTable[i][0] = tmpC.getName();
			tmpCustomerTable[i][1] = tmpC.getMobileNumber();
			tmpCustomerTable[i][2] = tmpC.getCustomerType();
			tmpCustomerTable[i][3] = Integer.toString(tmpC.getLocationX()) + ", " + Integer.toString(tmpC.getLocationY());
			tmpCustomerTable[i][4] = Double.toString(tmpC.getDeliveryDistance());
			
			Pizza tmpP = pizzas.get(i);
			tmpOrderTable[i][0] = tmpP.getPizzaType();
			tmpOrderTable[i][1] = Integer.toString(tmpP.getQuantity());
			tmpOrderTable[i][2] = Double.toString(tmpP.getOrderPrice());
			tmpOrderTable[i][3] = Double.toString(tmpP.getOrderCost());
			tmpOrderTable[i][4] = Double.toString(tmpP.getOrderProfit());
		}
		
		System.out.println("Generating complete!");
		
		customersTable = new JTable(tmpCustomerTable, customerColumn);
		ordersTable = new JTable(tmpOrderTable, orderColumn);
		
		System.out.println(customersTable);
		System.out.println(ordersTable);
		
		JScrollPane scrollPaneCustomers = new JScrollPane(customersTable);
		JScrollPane scrollPaneOrders = new JScrollPane(ordersTable);
		customersTable.setPreferredScrollableViewportSize(customersTable.getPreferredSize());
		ordersTable.setPreferredScrollableViewportSize(ordersTable.getPreferredSize());
		add(scrollPaneCustomers);
		add(scrollPaneOrders);
		
		// Initialize display constraints
		GridBagLayout layout = new GridBagLayout();
	    pnlDisplayBtn.setLayout(layout);
	    GridBagConstraints constraints = new GridBagConstraints(); 
		constraints.fill = GridBagConstraints.NONE;
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 250;
	    constraints.weighty = 250;
		
	    pnlDisplayBtn.removeAll();
	    
	    pnlDisplayBtn.add(customersTable.getTableHeader(), BorderLayout.PAGE_START);
	    pnlDisplayBtn.add(customersTable, BorderLayout.CENTER);
	    
	    pnlDisplayBtn.updateUI();
	}
	
	
}
