package airlinemanagementsystem;

import javax.swing.*;  // Import necessary classes for GUI components
import java.awt.*;  // For layout and styling options
import java.awt.event.*;  // To handle events like button clicks
import java.sql.*;  // For database connectivity and executing SQL queries

// The class extends JFrame to create a window and implements ActionListener to respond to button clicks
public class BoardingPass extends JFrame implements ActionListener {
    
    // Declare components (text fields, labels, buttons)
    JTextField tfpnr;  // Text field for PNR input
    JLabel tfname, tfnationality, lblsrc, lbldest, labelfname, labelfcode, labeldate;  // Labels to display boarding pass details
    JButton fetchButton;  // Button to fetch boarding pass details based on PNR
    
    // Constructor to initialize the UI components and set up the GUI layout
    public BoardingPass() {
        // Set the background color of the frame to white
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);  // Use null layout for absolute positioning of components
        
        // Heading of the boarding pass page
        JLabel heading = new JLabel("AIR INDIA");
        heading.setBounds(380, 10, 450, 35);  // Set position and size
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));  // Font style
        add(heading);  // Add the heading to the frame
        
        // Subheading for boarding pass
        JLabel subheading = new JLabel("Boarding Pass");
        subheading.setBounds(360, 50, 300, 30);  // Set position and size
        subheading.setFont(new Font("Tahoma", Font.PLAIN, 24));  // Font style
        subheading.setForeground(Color.BLUE);  // Set text color
        add(subheading);  // Add the subheading to the frame
        
        // Label for PNR input
        JLabel lblaadhar = new JLabel("PNR DETAILS");
        lblaadhar.setBounds(60, 100, 150, 25);  // Set position and size
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));  // Font style
        add(lblaadhar);  // Add the label to the frame
        
        // Text field for entering the PNR number
        tfpnr = new JTextField();
        tfpnr.setBounds(220, 100, 150, 25);  // Set position and size
        add(tfpnr);  // Add the text field to the frame
        
        // Button to fetch boarding pass details based on the entered PNR number
        fetchButton = new JButton("Enter");
        fetchButton.setBackground(Color.BLACK);  // Set button background color
        fetchButton.setForeground(Color.WHITE);  // Set button text color
        fetchButton.setBounds(380, 100, 120, 25);  // Set position and size
        fetchButton.addActionListener(this);  // Add action listener to handle button click
        add(fetchButton);  // Add the button to the frame
        
        // Labels to display fetched boarding pass details
        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60, 140, 150, 25);  // Label for name
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lblname);
        
        tfname = new JLabel();  // Field to display name fetched from the database
        tfname.setBounds(220, 140, 150, 25);
        add(tfname);
        
        JLabel lblnationality = new JLabel("NATIONALITY");
        lblnationality.setBounds(60, 180, 150, 25);  // Label for nationality
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lblnationality);
        
        tfnationality = new JLabel();  // Field to display nationality fetched from the database
        tfnationality.setBounds(220, 180, 150, 25);
        add(tfnationality);
        
        // Labels and fields for source and destination
        JLabel lbladdress = new JLabel("SRC");
        lbladdress.setBounds(60, 220, 150, 25);  // Label for source
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lbladdress);
        
        lblsrc = new JLabel();  // Field to display source fetched from the database
        lblsrc.setBounds(220, 220, 150, 25);
        add(lblsrc);
        
        JLabel lblgender = new JLabel("DEST");
        lblgender.setBounds(380, 220, 150, 25);  // Label for destination
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lblgender);
        
        lbldest = new JLabel();  // Field to display destination fetched from the database
        lbldest.setBounds(540, 220, 150, 25);
        add(lbldest);
        
        // Labels and fields for flight name and flight code
        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60, 260, 150, 25);  // Label for flight name
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lblfname);
        
        labelfname = new JLabel();  // Field to display flight name fetched from the database
        labelfname.setBounds(220, 260, 150, 25);
        add(labelfname);
        
        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(380, 260, 150, 25);  // Label for flight code
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lblfcode);
        
        labelfcode = new JLabel();  // Field to display flight code fetched from the database
        labelfcode.setBounds(540, 260, 150, 25);
        add(labelfcode);
        
        // Label and field for travel date
        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60, 300, 150, 25);  // Label for travel date
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));  
        add(lbldate);
        
        labeldate = new JLabel();  // Field to display travel date fetched from the database
        labeldate.setBounds(220, 300, 150, 25);
        add(labeldate);
        
        // Image placeholder for airline logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/airindia.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 230, Image.SCALE_DEFAULT);  // Scale the image to fit
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);  // Create a label to hold the image
        lblimage.setBounds(600, 0, 300, 300);
        add(lblimage);  // Add the image to the frame
        
        // Set the window size and position, and make it visible
        setSize(1000, 450);
        setLocation(300, 150);
        setVisible(true);
    }
    
    // Action listener to handle button clicks
    public void actionPerformed(ActionEvent ae) {
        String pnr = tfpnr.getText();  // Get the PNR number entered by the user

        try {
            Conn conn = new Conn();  // Create a new database connection
            String query = "select * from reservation where PNR = '"+pnr+"'";  // SQL query to fetch reservation details using PNR
            ResultSet rs = conn.s.executeQuery(query);  // Execute the query and get the result set

            // If a record is found, display the boarding pass details
            if (rs.next()) {
                tfname.setText(rs.getString("name")); 
                tfnationality.setText(rs.getString("nationality")); 
                lblsrc.setText(rs.getString("src")); 
                lbldest.setText(rs.getString("des"));  
                labelfname.setText(rs.getString("flightname"));  
                labelfcode.setText(rs.getString("flightcode"));  
                labeldate.setText(rs.getString("ddate")); 
            } else {
                // Show error message if PNR is not found
                JOptionPane.showMessageDialog(null, "Please enter correct PNR");                
            }
        } catch (Exception e) {
            e.printStackTrace();  // Handle exceptions
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        new BoardingPass();
    }
}
