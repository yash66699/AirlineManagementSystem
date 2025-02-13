package airlinemanagementsystem;  // Declares the package this class belongs to

import javax.swing.*;  // Importing Swing components for the GUI
import java.awt.*;     // Importing AWT for additional GUI elements and layout management
import java.sql.*;     // Importing SQL classes for database interactions
import java.awt.event.*; // Importing event handling classes
import net.proteanit.sql.DbUtils;  // Importing DbUtils to help populate JTable with data from ResultSet

// Class `JourneyDetails` extends `JFrame` and implements `ActionListener` to handle button click events
public class JourneyDetails extends JFrame implements ActionListener {
    JTable table;  // A `JTable` to display journey details in a tabular format
    JTextField pnr;  // A `JTextField` to take PNR input from the user
    JButton show;  // A `JButton` to trigger the action of showing journey details

    // Constructor for the `JourneyDetails` class, responsible for setting up the GUI
    public JourneyDetails() {
        // Set background color of the window to white
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);  // Disable layout manager for manual component positioning
        
        // Create a label for "PNR Details"
        JLabel lblpnr = new JLabel("PNR Details");
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));  // Set font type and size
        lblpnr.setBounds(50, 50, 100, 25);  // Set label's position and size
        add(lblpnr);  // Add the label to the window
        
        // Create a text field for PNR input
        pnr = new JTextField();
        pnr.setBounds(160, 50, 120, 25);  // Set text field's position and size
        add(pnr);  // Add the text field to the window
        
        // Create a "Show Details" button
        show = new JButton("Show Details");
        show.setBackground(Color.BLACK);  // Set button background color to black
        show.setForeground(Color.WHITE);  // Set button text color to white
        show.setBounds(290, 50, 120, 25);  // Set button's position and size
        show.addActionListener(this);  // Register the button with an action listener
        add(show);  // Add the button to the window
        
        // Create a JTable for displaying journey details
        table = new JTable();
        
        // Create a scroll pane and add the table to it, to handle large data that may require scrolling
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 800, 150);  // Set the scroll pane's position and size
        jsp.setBackground(Color.WHITE);  // Set the scroll pane background color
        add(jsp);  // Add the scroll pane to the window
        
        // Set the size of the window (width and height)
        setSize(800, 600);
        // Set the window's location on the screen
        setLocation(400, 150);
        // Make the window visible to the user
        setVisible(true);
    }
    
    // This method is called when an action is performed (like when the button is clicked)
    public void actionPerformed(ActionEvent ae) {
        try {
            // Establish a connection to the database using the `Conn` class
            Conn conn = new Conn();
            
            // Execute a query to retrieve journey details for the entered PNR
            ResultSet rs = conn.s.executeQuery("select * from reservation where PNR = '" + pnr.getText() + "'");
            
            // If no rows are returned, show a message indicating no information was found
            if (!rs.isBeforeFirst()) {  // `isBeforeFirst()` checks if the ResultSet has any rows
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;  // Exit the method if no data is found
            }
            
            // Use DbUtils to convert the ResultSet into a table model and display it in the JTable
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            // If there's an exception, print the stack trace for debugging purposes
            e.printStackTrace();
        }
    }

    // Main method to run the `JourneyDetails` window
    public static void main(String[] args) {
        new JourneyDetails();  // Create and display the JourneyDetails window
    }
}
