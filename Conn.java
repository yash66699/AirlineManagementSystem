package airlinemanagementsystem;  // Declares the package this class belongs to

import java.sql.*;  // Imports the Java SQL package, which contains classes for database connections and queries

// Class `Conn` is responsible for establishing a connection to the database and executing SQL statements.
public class Conn {
    
    Connection c;  // `Connection` object used to establish a connection with the database
    Statement s;   // `Statement` object used to execute SQL queries

    // Constructor for the `Conn` class, which will establish the database connection when an object of this class is created
    public Conn() {
        try {
            // Loads the MySQL JDBC driver class, which allows Java to communicate with a MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establishes a connection to the database. The URL format is:
            // "jdbc:mysql://<hostname>/<database_name>", followed by the username and password for the database.
            c = DriverManager.getConnection("jdbc:mysql:///airlinemanagementsystem", "root", "IITG@2025");

            // Creates a `Statement` object to send SQL statements to the database
            s = c.createStatement();
        } catch (Exception e) {
            // Catches any exceptions (like SQL or ClassNotFound exceptions) and prints the error stack trace for debugging purposes
            e.printStackTrace();
        }
    }
}
