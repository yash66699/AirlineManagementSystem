package airlinemanagementsystem;  // Declares the package that this class belongs to

import javax.swing.*;    // Imports the Swing library for building GUI components
import java.awt.*;       // Imports the AWT (Abstract Window Toolkit) library for GUI-related functionality
import java.awt.event.*; // Imports event handling for GUI components, like button clicks
import java.sql.*;       // Imports the SQL library for database connectivity and querying

// Class `Login` extends `JFrame` and implements `ActionListener`
// `JFrame` is the main class for creating a window in Swing. `ActionListener` allows listening for events like button clicks.
public class Login extends JFrame implements ActionListener { 
    JButton submit, reset, close;  // Declare buttons for Submit, Reset, and Close
    JTextField tfusername;         // TextField for entering username
    JPasswordField tfpassword;     // PasswordField for entering password (hides text as dots)

    // Constructor for the `Login` class
    public Login(){
        // Set the background color of the content pane to white
        getContentPane().setBackground(Color.WHITE);
        
        
        // Set the layout to null, meaning manual positioning of components using setBounds()
        setLayout(null);
        
        // Create a label for "Username" and set its position using setBounds()
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(20, 20, 100, 20); // (x, y, width, height)
        add(lblusername); // Add the label to the frame

        // Create a text field for entering the username and set its position
        tfusername = new JTextField();
        tfusername.setBounds(130, 20, 200, 20); // Place below the username label
        add(tfusername); // Add the text field to the frame
        
        // Create a label for "Password" and set its position
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(20, 60, 100, 20);
        add(lblpassword);

        // Create a password field (hides the input text) for entering the password
        tfpassword = new JPasswordField();
        tfpassword.setBounds(130, 60, 200, 20);
        add(tfpassword); // Add the password field to the frame
        
        // Create the Reset button and set its position
        reset = new JButton("Reset");
        reset.setBounds(40, 120, 120, 20); // (x, y, width, height)
        
        // Add an ActionListener to handle events like button clicks
        reset.addActionListener(this); 
        add(reset); // Add the reset button to the frame

        // Create the Submit button and set its position
        submit = new JButton("Submit");
        submit.setBounds(190, 120, 120, 20); // Positioned next to the Reset button
        submit.addActionListener(this); // Attach an ActionListener for when the button is clicked
        add(submit); // Add the submit button to the frame

        // Create the Close button and set its position
        close = new JButton("Close");
        close.setBounds(120, 160, 120, 20); // Centered at the bottom
        close.addActionListener(this); // Attach an ActionListener to handle button click events
        add(close); // Add the close button to the frame

        // Set the size of the window (width x height)
        setSize(400, 250);
        
        // Set the location of the window on the screen (x, y coordinates)
        setLocation(600, 250);
        
        // Make the window visible
        setVisible(true);
    }
    
    // This method is called when an event (like a button click) occurs
    public void actionPerformed(ActionEvent ae){
        // If the Submit button is clicked
        if (ae.getSource() == submit) {
            String username = tfusername.getText();    // Get the text entered in the username field
            String password = tfpassword.getText();    // Get the password entered in the password field
            
            try {
                Conn c = new Conn(); // Create an object of the Conn class (assumed to handle DB connection)
                
                // SQL query to check if a user exists with the entered username and password
                String query = "select * from login where username = '" + username + "' and password = '" + password + "'";
                
                // Execute the SQL query and store the result in a ResultSet
                ResultSet rs = c.s.executeQuery(query);
                
                // If a matching username and password is found
                if (rs.next()) {
                    new Home(); // Open the home page (assuming Home class exists)
                    setVisible(false); // Close the login window
                } else {
                    // Show a message dialog indicating invalid login credentials
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    setVisible(false); // Close the login window after showing the message
                }
            } catch (Exception e) {
                // Print any exception to the console (useful for debugging)
                e.printStackTrace();
            }
        }
        // If the Close button is clicked
        else if (ae.getSource() == close) {
            setVisible(false); // Close the login window
        }
        // If the Reset button is clicked
        else if (ae.getSource() == reset) {
            tfusername.setText("");   // Clear the username field
            tfpassword.setText("");   // Clear the password field
        }
    }
    
    // The main method to start the program
    public static void main(String[] args) {
        new Login(); // Create an instance of the Login class, which displays the login window
    }
}
