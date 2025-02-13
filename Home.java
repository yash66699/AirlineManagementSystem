package airlinemanagementsystem;  // Declares the package this class belongs to

import javax.swing.*;    // Imports the Swing library for building GUI components
import java.awt.*;       // Imports AWT (Abstract Window Toolkit) classes for graphical functionality
import java.awt.event.*; // Imports event handling classes for GUI interactions

// Class `Home` extends `JFrame` and implements `ActionListener` for handling menu item click events
public class Home extends JFrame implements ActionListener {
    
    // Constructor for the `Home` class, responsible for setting up the main GUI window
    public Home() {
        // Disables any layout manager; components will be manually positioned with setBounds()
        setLayout(null);
        
        // Load an image from the "icons" folder within the classpath
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/front.jpg"));
        
        // Create a label to display the loaded image
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1600, 800); // Set position and size of the image
        add(image);  // Add the image to the frame
        
        // Create a heading label with text "AIR INDIA WELCOMES YOU"
        JLabel heading = new JLabel("AIR INDIA WELCOMES YOU");
        heading.setBounds(500, 40, 1000, 40); // Set the position of the heading
        heading.setForeground(Color.BLUE);    // Set the font color to blue
        heading.setFont(new Font("Tahoma", Font.PLAIN, 36)); // Set font family, style, and size
        image.add(heading);  // Add the heading on top of the image
        
        // Create a menu bar
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);  // Set the menu bar for the frame
        
        // Create a "Details" menu in the menu bar
        JMenu details = new JMenu("Details");
        menubar.add(details);  // Add "Details" menu to the menu bar
        
        // Create menu items under the "Details" menu, and attach action listeners to each
        JMenuItem flightDetails = new JMenuItem("Flight Details");
        flightDetails.addActionListener(this); // Register action listener to handle clicks
        details.add(flightDetails);  // Add the item to the "Details" menu
        
        JMenuItem customerDetails = new JMenuItem("Add Customer Details");
        customerDetails.addActionListener(this); // Register action listener
        details.add(customerDetails); // Add the item to the "Details" menu
        
        JMenuItem bookFlight = new JMenuItem("Book Flight");
        bookFlight.addActionListener(this); // Register action listener
        details.add(bookFlight); // Add the item to the "Details" menu
        
        JMenuItem journeyDetails = new JMenuItem("Journey Details");
        journeyDetails.addActionListener(this); // Register action listener
        details.add(journeyDetails); // Add the item to the "Details" menu
        
        JMenuItem ticketCancellation = new JMenuItem("Cancel Ticket");
        ticketCancellation.addActionListener(this); // Register action listener
        details.add(ticketCancellation); // Add the item to the "Details" menu
        
        // Create a separate "Ticket" menu in the menu bar
        JMenu ticket = new JMenu("Ticket");
        menubar.add(ticket); // Add the "Ticket" menu to the menu bar
        
        // Create a menu item for "Boarding Pass" under the "Ticket" menu
        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        ticket.add(boardingPass); // Add the item to the "Ticket" menu
        
        // Set the window to take up the entire screen (maximized)
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Make the window visible
        setVisible(true);
    }
    
    // This method is called when a menu item is clicked
    public void actionPerformed(ActionEvent ae) {
        // Get the command (text) of the action event (i.e., the clicked menu item)
        String text = ae.getActionCommand();
        
        // Based on the text of the clicked menu item, open the corresponding window or perform an action
        if (text.equals("Add Customer Details")) {
            new AddCustomer(); // Open the "Add Customer" window
        } else if (text.equals("Flight Details")) {
            new FlightInfo();  // Open the "Flight Info" window
        } else if (text.equals("Book Flight")) {
            new BookFlight();  // Open the "Book Flight" window
        } else if (text.equals("Journey Details")) {
            new JourneyDetails();  // Open the "Journey Details" window
        } else if (text.equals("Cancel Ticket")) {
            new Cancel();  // Open the "Cancel Ticket" window
        }
    }
    
    // Main method to launch the "Home" window
    public static void main(String[] args) {
        new Home(); // Create an instance of Home, which displays the GUI
    }
}
