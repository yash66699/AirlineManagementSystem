package airlinemanagementsystem;  // Declares the package this class belongs to

import javax.swing.*;   // Importing Swing components for the GUI
import java.awt.*;      // Importing AWT for layout management and color settings
import java.sql.*;      // Importing SQL classes for database interactions
import net.proteanit.sql.DbUtils;  // Importing DbUtils to help populate JTable with data from ResultSet

// Class `FlightInfo` extends `JFrame` to create a window that shows flight information in a table
public class FlightInfo extends JFrame {
    
    // Constructor for the `FlightInfo` class
    public FlightInfo() {
        
        // Set the background color of the content pane to white
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);  // Disable layout manager to manually position components

        // Create a new `JTable` to display the flight information
        JTable table = new JTable();
        
        try {
            // Establish a connection to the database using the `Conn` class
            Conn conn = new Conn();
            
            // Execute a SQL query to retrieve all records from the `flight` table
            ResultSet rs = conn.s.executeQuery("select * from flight");
            
            // Use DbUtils to convert the ResultSet into a table model, and set it to the JTable
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            // If an exception occurs (like an SQL error), print the stack trace for debugging purposes
            e.printStackTrace();
        }

        // Create a scroll pane for the table to handle larger data sets
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 0, 800, 500);  // Set the position and size of the scroll pane
        add(jsp);  // Add the scroll pane (with the table inside) to the frame
        
        // Set the size of the window (width and height)
        setSize(800, 500);
        // Set the location of the window on the screen
        setLocation(400, 200);
        // Make the window visible to the user
        setVisible(true);
    }

    // Main method to run the `FlightInfo` window
    public static void main(String[] args) {
        new FlightInfo();  // Create and display the FlightInfo window
    }
}
