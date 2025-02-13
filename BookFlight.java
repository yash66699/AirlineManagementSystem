package airlinemanagementsystem;

import javax.swing.*;  // Import necessary classes for GUI components
import java.awt.*;  // For layout and styling options
import java.awt.event.*;  // To handle events like button clicks
import java.sql.*;  // For database connectivity and executing SQL queries
import com.toedter.calendar.JDateChooser;  // For choosing travel dates using a calendar component
import java.util.*;  // For utility classes, like Random

// The class extends JFrame to create a window, and implements ActionListener to respond to button clicks.
public class BookFlight extends JFrame implements ActionListener {
    
    // Declare components (text fields, labels, buttons, and date chooser)
    JTextField tfaadhar;  // Text field for Aadhar number input
    JLabel tfname, tfnationality, tfaddress, labelgender, labelfname, labelfcode;  // Labels to display information fetched from the database or input
    JButton bookflight, fetchButton, flight;  // Buttons for user actions (Fetch User, Fetch Flights, and Book Flight)
    Choice source, destination;  // Dropdown lists for selecting source and destination
    JDateChooser dcdate;  // Component for selecting the travel date
    
    // Constructor to initialize the UI components and set up the GUI layout
    public BookFlight() {
        // Set the background color of the frame to white
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);  // Use null layout for absolute positioning of components
        
        // Heading of the booking page
        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 35);  // Set position and size
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));  // Font style
        heading.setForeground(Color.BLUE);  // Set text color
        add(heading);  // Add the heading to the frame
        
        // Label for Aadhar input
        JLabel lblaadhar = new JLabel("Aadhar");
        lblaadhar.setBounds(60, 80, 150, 25);  // Set position and size
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));  // Font style
        add(lblaadhar);  // Add the label to the frame
        
        // Text field for entering the Aadhar number
        tfaadhar = new JTextField();
        tfaadhar.setBounds(220, 80, 150, 25);  // Set position and size
        add(tfaadhar);  // Add the text field to the frame
        
        // Button to fetch user details based on the entered Aadhar number
        fetchButton = new JButton("Fetch User");
        fetchButton.setBackground(Color.BLACK);  // Set button background color
        fetchButton.setForeground(Color.WHITE);  // Set button text color
        fetchButton.setBounds(380, 80, 120, 25);  // Set position and size
        fetchButton.addActionListener(this);  // Add action listener to handle button click
        add(fetchButton);  // Add the button to the frame
        
        // Labels and fields to display fetched user details (Name, Nationality, Address, Gender)
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);  // Label for name
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lblname);
        
        tfname = new JLabel();  // Field to display name fetched from the database
        tfname.setBounds(220, 130, 150, 25);
        add(tfname);
        
        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 180, 150, 25);  // Label for nationality
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lblnationality);
        
        tfnationality = new JLabel();  // Field to display nationality fetched from the database
        tfnationality.setBounds(220, 180, 150, 25);
        add(tfnationality);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);  // Label for address
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lbladdress);
        
        tfaddress = new JLabel();  // Field to display address fetched from the database
        tfaddress.setBounds(220, 230, 150, 25);
        add(tfaddress);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);  // Label for gender
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lblgender);
        
        labelgender = new JLabel();  // Field to display gender fetched from the database
        labelgender.setBounds(220, 280, 150, 25);
        add(labelgender);
        
        // Dropdown for selecting source of flight
        JLabel lblsource = new JLabel("Source");
        lblsource.setBounds(60, 330, 150, 25);  // Label for source
        lblsource.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lblsource);
        
        source = new Choice();  // Dropdown for source selection
        source.setBounds(220, 330, 150, 25);
        add(source);
        
        // Dropdown for selecting destination of flight
        JLabel lbldest = new JLabel("Destination");
        lbldest.setBounds(60, 380, 150, 25);  // Label for destination
        lbldest.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lbldest);
        
        destination = new Choice();  // Dropdown for destination selection
        destination.setBounds(220, 380, 150, 25);
        add(destination);
        
        // Fetch available flights from the database based on source and destination
        try {
            Conn c = new Conn();  // Database connection class
            String query = "select * from flight";  // SQL query to get flight data
            ResultSet rs = c.s.executeQuery(query);  // Execute the query and get the result set
            
            // Populate source and destination dropdowns with data from the database
            while (rs.next()) {
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();  // Handle exceptions by printing stack trace
        }
        
        // Button to fetch flight details for the selected source and destination
        flight = new JButton("Fetch Flights");
        flight.setBackground(Color.BLACK);  // Set button background color
        flight.setForeground(Color.WHITE);  // Set button text color
        flight.setBounds(380, 380, 120, 25);  // Set position and size
        flight.addActionListener(this);  // Add action listener to handle button click
        add(flight);  // Add the button to the frame
        
        // Labels and fields to display flight details (Flight Name and Flight Code)
        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60, 430, 150, 25);  // Label for flight name
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lblfname);
        
        labelfname = new JLabel();  // Field to display flight name fetched from the database
        labelfname.setBounds(220, 430, 150, 25);
        add(labelfname);
        
        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(60, 480, 150, 25);  // Label for flight code
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lblfcode);
        
        labelfcode = new JLabel();  // Field to display flight code fetched from the database
        labelfcode.setBounds(220, 480, 150, 25);
        add(labelfcode);
        
        // Label and date chooser for selecting travel date
        JLabel lbldate = new JLabel("Date of Travel");
        lbldate.setBounds(60, 530, 150, 25);  // Label for travel date
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lbldate);
        
        dcdate = new JDateChooser();  // Calendar component to select date of travel
        dcdate.setBounds(220, 530, 150, 25);
        add(dcdate);
        
        // Image placeholder for flight details
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/details.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(550, 80, 500, 410);
        add(lblimage);  // Add the image to the frame
        
        // Button to finalize and book the flight
        bookflight = new JButton("Book Flight");
        bookflight.setBackground(Color.BLACK);  // Set button background color
        bookflight.setForeground(Color.WHITE);  // Set button text color
        bookflight.setBounds(220, 580, 150, 25);  // Set position and size
        bookflight.addActionListener(this);  // Add action listener to handle button click
        add(bookflight);
        
        // Set the window size and position, and make it visible
        setSize(1100, 700);
        setLocation(200, 50);
        setVisible(true);
    }
    
    // Action listener to handle button clicks
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            // Fetch user details from the database based on the entered Aadhar number
            String aadhar = tfaadhar.getText();
            
            try {
                Conn conn = new Conn();
                String query = "select * from passenger where aadhar = '"+aadhar+"'";
                ResultSet rs = conn.s.executeQuery(query);
                
                // If the user is found, display their details
                if (rs.next()) {
                    tfname.setText(rs.getString("name"));
                    tfnationality.setText(rs.getString("nationality"));
                    tfaddress.setText(rs.getString("address"));
                    labelgender.setText(rs.getString("gender"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct aadhar");  // Show error if Aadhar is not found
                }
            } catch (Exception e) {
                e.printStackTrace();  // Handle exceptions
            }
        } else if (ae.getSource() == flight) {
            // Fetch flight details based on the selected source and destination
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();
            
            try {
                Conn conn = new Conn();
                String query = "select * from flight where source = '"+src+"' and destination = '"+dest+"'";
                ResultSet rs = conn.s.executeQuery(query);
                
                // If a flight is found, display its details
                if (rs.next()) {
                    labelfname.setText(rs.getString("f_name"));
                    labelfcode.setText(rs.getString("f_code"));
                } else {
                    JOptionPane.showMessageDialog(null, "No Flights Found");  // Show error if no flights are found
                }
            } catch (Exception e) {
                e.printStackTrace();  // Handle exceptions
            }
        } else {
            // Book the flight and insert the reservation details into the database
            Random random = new Random();
            String aadhar = tfaadhar.getText();
            String name = tfname.getText();
            String nationality = tfnationality.getText();
            String flightname = labelfname.getText();
            String flightcode = labelfcode.getText();
            String src = source.getSelectedItem();
            String des = destination.getSelectedItem();
            String ddate = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
            
            try {
                Conn conn = new Conn();
                String query = "insert into reservation values('PNR-"+random.nextInt(1000000)+"', 'TIC-"+random.nextInt(10000)+"', '"+aadhar+"', '"+name+"', '"+nationality+"', '"+flightname+"', '"+flightcode+"', '"+src+"', '"+des+"', '"+ddate+"')";
                conn.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Ticket Booked Successfully");  // Show success message
                
                setVisible(false);  // Close the window after booking
            } catch (Exception e) {
                e.printStackTrace();  // Handle exceptions
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        new BookFlight();
    }
}
