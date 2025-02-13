package airlinemanagementsystem;  // Declares that this class belongs to the 'airlinemanagementsystem' package

import javax.swing.*;   // Importing Swing components for the GUI
import java.awt.*;      // Importing AWT classes for layout and color management
import java.awt.event.*;  // Importing event handling classes, needed for action listeners
import java.sql.*;  // Importing SQL classes for database connectivity
import java.util.*;  // Importing utility classes, in this case, `Random` for generating a random cancellation number

// `Cancel` class extends `JFrame` to create a graphical window for ticket cancellation and implements `ActionListener` for button actions
public class Cancel extends JFrame implements ActionListener {

    // GUI components (text fields, labels, buttons)
    JTextField tfpnr;
    JLabel tfname, cancellationno, lblfcode, lbldateoftravel;
    JButton fetchButton, flight;

    // Constructor method, which sets up the layout and components of the window
    public Cancel() {
        getContentPane().setBackground(Color.WHITE);  // Setting the background color of the content pane to white
        setLayout(null);  // Using no layout manager, so components are manually positioned

        Random random = new Random();  // Creating a random object to generate the cancellation number
        
        // Heading label with text "CANCELLATION"
        JLabel heading = new JLabel("CANCELLATION");
        heading.setBounds(180, 20, 250, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);  // Adding the heading label to the frame
        
        // Adding an image icon of cancellation
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);  // Scaling the image to fit a specific size
        ImageIcon i3 = new ImageIcon(i2);  // Creating a new ImageIcon with the resized image
        JLabel image = new JLabel(i3);
        image.setBounds(470, 120, 250, 250);
        add(image);  // Adding the image to the frame
        
        // Label and text field for entering the PNR number
        JLabel lblaadhar = new JLabel("PNR Number");
        lblaadhar.setBounds(60, 80, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);
        
        tfpnr = new JTextField();  // Text field for inputting PNR
        tfpnr.setBounds(220, 80, 150, 25);
        add(tfpnr);
        
        // Button to fetch ticket details using the PNR number
        fetchButton = new JButton("Show Details");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);  // Adding action listener to the button
        add(fetchButton);
        
        // Label for showing the customer name
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);
        
        tfname = new JLabel();  // Label where the customer name will be displayed after fetching details
        tfname.setBounds(220, 130, 150, 25);
        add(tfname);
        
        // Label for showing the randomly generated cancellation number
        JLabel lblnationality = new JLabel("Cancellation No");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);
        
        cancellationno = new JLabel("" + random.nextInt(1000000));  // Randomly generating a cancellation number
        cancellationno.setBounds(220, 180, 150, 25);
        add(cancellationno);
        
        // Label for showing the flight code
        JLabel lbladdress = new JLabel("Flight Code");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);
        
        lblfcode = new JLabel();  // Label where the flight code will be displayed after fetching details
        lblfcode.setBounds(220, 230, 150, 25);
        add(lblfcode);
        
        // Label for showing the date of travel
        JLabel lblgender = new JLabel("Date");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);
        
        lbldateoftravel = new JLabel();  // Label where the date of travel will be displayed
        lbldateoftravel.setBounds(220, 280, 150, 25);
        add(lbldateoftravel);
        
        // Button to cancel the flight
        flight = new JButton("Cancel");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(220, 330, 120, 25);
        flight.addActionListener(this);  // Adding action listener to the cancel button
        add(flight);
        
        // Setting the size, location, and visibility of the window
        setSize(800, 450);
        setLocation(350, 150);
        setVisible(true);
    }

    // Overriding the actionPerformed method to define the actions for the buttons
    public void actionPerformed(ActionEvent ae) {
        // If the "Show Details" button is clicked
        if (ae.getSource() == fetchButton) {
            String pnr = tfpnr.getText();  // Get the PNR number entered by the user
            
            try {
                // Establish connection to the database
                Conn conn = new Conn();
                
                // SQL query to fetch reservation details based on the PNR number
                String query = "select * from reservation where PNR = '"+pnr+"'";
                ResultSet rs = conn.s.executeQuery(query);
                
                // If a record is found, populate the labels with the fetched data
                if (rs.next()) {
                    tfname.setText(rs.getString("name"));  // Set name
                    lblfcode.setText(rs.getString("flightcode"));  // Set flight code
                    lbldateoftravel.setText(rs.getString("ddate"));  // Set date of travel
                } else {
                    // Show a message dialog if the PNR is not found
                    JOptionPane.showMessageDialog(null, "Please enter correct PNR");
                }
            } catch (Exception e) {
                e.printStackTrace();  // Print any exceptions that occur
            }
        } 
        // If the "Cancel" button is clicked
        else if (ae.getSource() == flight) {
            // Get the details for the cancellation
            String name = tfname.getText();
            String pnr = tfpnr.getText();
            String cancelno = cancellationno.getText();
            String fcode = lblfcode.getText();
            String date = lbldateoftravel.getText();
            
            try {
                // Establish connection to the database
                Conn conn = new Conn();

                // SQL query to insert cancellation details into the `cancel` table
                String query = "insert into cancel values('"+pnr+"', '"+name+"', '"+cancelno+"', '"+fcode+"', '"+date+"')";
                conn.s.executeUpdate(query);  // Execute the insertion query
                
                // SQL query to delete the reservation from the `reservation` table after cancellation
                conn.s.executeUpdate("delete from reservation where PNR = '"+pnr+"'");
                
                // Show a message dialog confirming the cancellation
                JOptionPane.showMessageDialog(null, "Ticket Cancelled");
                setVisible(false);  // Close the window after cancellation
            } catch (Exception e) {
                e.printStackTrace();  // Print any exceptions that occur
            }
        } 
    }

    // Main method to run the Cancel window
    public static void main(String[] args) {
        new Cancel();  // Create and display the Cancel window
    }
}
