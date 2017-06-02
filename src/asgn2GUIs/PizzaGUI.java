package asgn2GUIs;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
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
 * @author Ji Su Choi (n9678166)  and Jia Sheng Chong (n9901990)
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {

	// Essential GUI attributes
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 700;
	public static final int HEIGHT = 600;
	
	// Private attributes
	private boolean fileOpen = false;
	private PizzaRestaurant restaurant;
	
	// Panels
	private JPanel pnlDisplayBtn;
	private JPanel pnlWest;
	private JPanel pnlEast;
	private JPanel pnlBtn;
	
	// Tabs
	private JTabbedPane tabbedPane;
	
	// Cards 
	private JPanel pnlMainMenuCard;
	private JPanel pnlCustomerCard;
	private JPanel pnlOrderCard;
	
	// Buttons
	private JButton btnTotalProfitMade;
	private JButton btnTotalDistanceTravelled;
	private JButton btnReset;
	private JButton btnLoadFile;
	
	// Displays
	private JTextArea txtDisplay;
	private JTextArea txtTotalDistance;
	private JTextArea txtTotalProfit;
	
	
	// Tables
	private JTable customersTable;
	private JTable ordersTable;
	
	// Setting up the Columns
	private String[] customerColumn = {
			"Name",
			"Mobile Number",
			"Type",
			"X, Y",
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

	/**
	 * Sets up the GUI so that it is ready to be deployed
	 */
	public void createGUI() {
		// Setting the main frame
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		// Initialize the cards
		pnlMainMenuCard = createPanel(Color.WHITE);
		pnlCustomerCard = createPanel(Color.WHITE);
		pnlOrderCard = createPanel(Color.WHITE);
		
		// Initialize some panels
		pnlDisplayBtn = createPanel(Color.WHITE);
		pnlEast = createPanel(Color.DARK_GRAY);
		pnlWest = createPanel(Color.DARK_GRAY);
		pnlBtn = createPanel(Color.DARK_GRAY);
		
		// Initialize the buttons
		btnLoadFile = createButton("Load File");
		btnReset = createButton("Reset");
		btnTotalProfitMade = createButton("Total Profit Made");
		btnTotalDistanceTravelled = createButton("Total Distance Travelled");
		
		// Setting text displays for total distance and text displays for total profit
		txtDisplay = createTextArea(5, 20, true);
		txtTotalDistance = createTextArea(2, 10, false);
		txtTotalProfit = createTextArea(2, 10, false);
		
		// Main Menu
		pnlMainMenuCard.add(txtDisplay);
		pnlMainMenuCard.add(pnlDisplayBtn);
		txtDisplay.setText("Click on \"Load File\" to start");
		
		// Customers & Orders
		int border = 45;
		pnlCustomerCard.setBorder(new EmptyBorder(0,border,border+20,border));
		pnlOrderCard.setBorder(new EmptyBorder(0,border,border+20,border));
		
		// Initialize the tabs
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Main Menu", pnlMainMenuCard);
		tabbedPane.addTab("Customer Details", pnlCustomerCard);
		tabbedPane.addTab("Order Details", pnlOrderCard);
		
		layoutButtonPanel();
		reCreateLayout();
		
	}
	
	/**
	 * Add the tabs and borders of the GUI
	 */
	public void reCreateLayout() {
		this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	    this.getContentPane().add(pnlBtn, BorderLayout.SOUTH);
	    this.getContentPane().add(pnlEast,BorderLayout.EAST);
	    this.getContentPane().add(pnlWest,BorderLayout.WEST);
	    repaint();
	    this.setVisible(true);
	}
	
	/**
	 * Runs the createGUI function
	 */
	@Override
	public void run() {
		createGUI();
	}

	/**
	 * Checks for user actions within the GUI 
	 * and do specific stuff based on the user action
	 * 
	 * @param e An action event by the user
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		JButton button = (JButton) e.getSource();
		String command = button.getActionCommand();
		
		// Check whichever buttons gets pressed
		if (src == btnLoadFile && !fileOpen) {
			fileChooser();
		} 
		else if (src == btnReset && fileOpen) {
			fileOpen = false;
			pnlCustomerCard.removeAll();
			pnlOrderCard.removeAll();
			
			pnlCustomerCard.updateUI();
			pnlOrderCard.updateUI();
			fileChooser();
		}
		else if (src == btnTotalDistanceTravelled && fileOpen) {
			double total_distance = restaurant.getTotalDeliveryDistance();
			txtTotalDistance.setText("Total Distance: \n" + Double.toString(total_distance));
			pnlCustomerCard.add(txtTotalDistance);
			pnlCustomerCard.updateUI();
		} else if(src == btnTotalProfitMade && fileOpen ){
			double total_profit = restaurant.getTotalProfit();
			txtTotalProfit.setText("Total profit: \n" + Double.toString(total_profit));
			pnlOrderCard.add(txtTotalProfit);
			pnlOrderCard.updateUI();
		}
		
		// Check if it's a file then load that file
		// Check if it's a file then load that file
				if (command.substring(command.length() - 4).equals(".txt") && !fileOpen) {
					try {
						restaurant = new PizzaRestaurant();
						restaurant.processLog("logs/" + command);
						fileOpen = true;
						generateTable();
					} catch (PizzaException e1) {
						JOptionPane.showMessageDialog(pnlMainMenuCard,
							    "Something is wrong with the pizzas in this file.\n"
							    + "Please choose another file!",
							    "Pizza Error",
							    JOptionPane.ERROR_MESSAGE);
					} catch (CustomerException e1) {
						JOptionPane.showMessageDialog(pnlMainMenuCard,
							    "Something is wrong with the customers in this file.\n"
							    + "Please choose another file!",
							    "Customer Error",
							    JOptionPane.ERROR_MESSAGE);
					} catch (LogHandlerException e1) {
						JOptionPane.showMessageDialog(pnlMainMenuCard,
							    "Something is wrong with the file!\n"
							    + "Please choose another file!",
							    "File Error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}
		
	}

	/**
	 * Creates a panel
	 * 
	 * @param Colour 
	 * @return Panel object
	 */
	private JPanel createPanel(Color c) {
		// Create a JPanel object and store it in a local var
		// set the background colour that passed in c
		// Return the JPanel object
		JPanel panel = new JPanel();
		panel.setBackground(c);
		return panel;
	}
	
	/**
	 * Creates a button
	 * 
	 * @param Name of button
	 * @return Button object
	 */
	private JButton createButton(String str) {
		JButton jb = new JButton(str);
		jb.addActionListener(this);
		return jb;
	}

	/**
	 * Creates a text area
	 * 
	 * @param width
	 * @param height
	 * @param boolean - to interchange between small and big font size
	 * @return Text Area object
	 */
	private JTextArea createTextArea(int x, int y, boolean isHuge) {
		int textSize = 24;
		if (!isHuge) {
			textSize = 12;
		}
		JTextArea jta = new JTextArea(x, y); 
		jta.setEditable(false);
		jta.setLineWrap(true);
		jta.setFont(new Font("Arial",Font.BOLD,textSize));
		jta.setBorder(BorderFactory.createEtchedBorder());
		return jta;
	}
	
	/**
	 * Adds a component to a panel
	 * 
	 * @param JPanel object that is about to receive something
	 * @param JPanel that is going to be added to the first JPanel Object
	 * @param constraints of the JPanel object
	 * @param x coordinates
	 * @param y coordinates
	 * @param width
	 * @param height
	 */
	private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
	      constraints.gridx = x;
	      constraints.gridy = y;
	      constraints.gridwidth = w;
	      constraints.gridheight = h;
	      jp.add(c, constraints);
	}
	
	/**
	 * Initialize the compulsory buttons of the GUI
	 */
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
	}
	
	/**
	 * Displays the files that are contained within the log folder
	 */
	private void fileChooser() {
		File folder = new File("logs");
		File[] listOfFiles = folder.listFiles();
		txtDisplay.setText("Here\'s a list of available files!"
				+ "\nChoose one of your liking."
				+ "\nInvalid ones won\'t result in anything");
		
		// Initialize display constraints
		GridBagLayout layout = new GridBagLayout();
	    pnlDisplayBtn.setLayout(layout);
	    GridBagConstraints constraints = new GridBagConstraints(); 
		constraints.fill = GridBagConstraints.NONE;
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 250;
	    constraints.weighty = 250;
		
	    pnlDisplayBtn.removeAll();
	    
	    boolean left = true;
	    int n = 0; // Next line after true is false
	    
		for (int i = 0; i < listOfFiles.length; i+=1) {
			if(listOfFiles[i].isFile()) {
				int nxtRow = 1;
				if (left) {
					nxtRow = 1;
					left = false;
				} else {
					nxtRow = 3;
					left = true;
					n += 1;
				}
				
				JButton tempBtn = createButton(listOfFiles[i].getName());
				addToPanel(pnlDisplayBtn, tempBtn, constraints, nxtRow, 1 + n, 2, 1);
				tempBtn.addActionListener(this);
			}
		}
		
		pnlDisplayBtn.updateUI();
	}
	
	/**
	 * Generates customer and order tables from the restaurant object.
	 * Then updates the table onto the respective customer and order card panels.
	 * 
	 * @throws CustomerException
	 * @throws PizzaException
	 */
	private void generateTable() throws CustomerException, PizzaException {
		
		// Initialize a double array to create tables
		String[][] tmpCustomerTable = new String[restaurant.getNumCustomerOrders()][5];
		String[][] tmpOrderTable = new String[restaurant.getNumPizzaOrders()][5];
		
		
		for (int i = 0; i < restaurant.getNumCustomerOrders(); i+=1) {
			Customer tmpC = restaurant.getCustomerByIndex(i);			
			tmpCustomerTable[i][0] = tmpC.getName();
			tmpCustomerTable[i][1] = tmpC.getMobileNumber();
			tmpCustomerTable[i][2] = tmpC.getCustomerType();
			tmpCustomerTable[i][3] = Integer.toString(tmpC.getLocationX()) + ", " + Integer.toString(tmpC.getLocationY());
			tmpCustomerTable[i][4] = String.valueOf(tmpC.getDeliveryDistance());
			
			Pizza tmpP = restaurant.getPizzaByIndex(i);
			tmpOrderTable[i][0] = tmpP.getPizzaType();
			tmpOrderTable[i][1] = Integer.toString(tmpP.getQuantity());
			tmpOrderTable[i][2] = Double.toString(tmpP.getOrderPrice());
			tmpOrderTable[i][3] = Double.toString(tmpP.getOrderCost());
			tmpOrderTable[i][4] = Double.toString(tmpP.getOrderProfit());
		}
		
		// Ensure the tables cannot be modified/editable
		customersTable = new JTable(tmpCustomerTable, customerColumn) {
			private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		ordersTable = new JTable(tmpOrderTable, orderColumn) {
			private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		
		
		// Make X, Y Column and Distance Travelled of Customer Table align center
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		customersTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		customersTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		
		// Reset the customer Table columns
		customersTable.getColumnModel().getColumn(0).setPreferredWidth(90);
		customersTable.getColumnModel().getColumn(1).setPreferredWidth(90);
		customersTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		customersTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		customersTable.getColumnModel().getColumn(4).setPreferredWidth(90);
		
		// Give the tables some scrollable 
		JScrollPane scrollPaneCustomers = new JScrollPane(customersTable);
		JScrollPane scrollPaneOrders = new JScrollPane(ordersTable);
		
	    pnlCustomerCard.add(scrollPaneCustomers);
	    pnlOrderCard.add(scrollPaneOrders);
	    
	    pnlDisplayBtn.removeAll();
	    txtDisplay.setText(	"A file has been loaded, check out:"
	    					+ "\nCustomer Details or Order Details");
	    
	    // Initialize display constraints
 		GridBagLayout layout = new GridBagLayout();
 	    pnlDisplayBtn.setLayout(layout);
 	    GridBagConstraints constraints = new GridBagConstraints(); 
 		constraints.fill = GridBagConstraints.NONE;
 	    constraints.anchor = GridBagConstraints.CENTER;
 	    constraints.weightx = 250;
 	    constraints.weighty = 250;
	    
	    addToPanel(pnlOrderCard, btnTotalProfitMade,constraints,0,2,2,1); 
	    addToPanel(pnlCustomerCard, btnTotalDistanceTravelled,constraints,3,2,2,1); 	
	    
	    pnlDisplayBtn.updateUI();
	    pnlCustomerCard.updateUI();
	    pnlOrderCard.updateUI();
	}
	
	
}
