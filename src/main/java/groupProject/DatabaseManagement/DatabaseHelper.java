package groupProject.DatabaseManagement;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseHelper {

	// JDBC driver name and database URL 
	static final String JDBC_DRIVER = "org.h2.Driver";   
	static final String DB_URL = "jdbc:h2:~/SystemDatabase";  

	//  Database credentials 
	static final String USER = "sa"; 
	static final String PASS = ""; 

	private Connection connection = null;
	private Statement statement = null; 
	
	
	// Create tables function altered to create the necessary database fields for the user class
	private void createTables() throws SQLException {
	    String userTable = "CREATE TABLE IF NOT EXISTS cse360users ("
	        + "id INT AUTO_INCREMENT PRIMARY KEY, "
	        + "username VARCHAR(255) UNIQUE, "
	        + "password VARCHAR(255), "
	        + "hasAdminRole BOOLEAN, "
	        + "hasStudentRole BOOLEAN, "
	        + "hasInstructorRole BOOLEAN, "
	        + "email VARCHAR(255) UNIQUE, "
	        + "firstName VARCHAR(255), "
	        + "lastName VARCHAR(255), "
	        + "middleName VARCHAR(255), "
	        + "preferredName VARCHAR(255), "
	        + "accountNeedsSetup BOOLEAN"
	        + ")";
	    statement.execute(userTable);
	}
	
	
	// BELOW FUNCTIONS ARE FROM ORIGINAL 360 FILE DOWNLOAD
	// USED FOR REFERENCE FOR OTHER DATABASE MANAGEMENT FUNCTIONS
	public void connectToDatabase() throws SQLException {
		try {
			Class.forName(JDBC_DRIVER); // Load the JDBC driver
			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement(); 
			createTables();  // Create the necessary tables if they don't exist
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: " + e.getMessage());
		}
	}


	// Check if the database is empty
	public boolean isDatabaseEmpty() throws SQLException {
		String query = "SELECT COUNT(*) AS count FROM cse360users";
		ResultSet resultSet = statement.executeQuery(query);
		if (resultSet.next()) {
			return resultSet.getInt("count") == 0;
		}
		return true;
	}

	public void register(String email, String password, String role) throws SQLException {
		String insertUser = "INSERT INTO cse360users (email, password, role) VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(insertUser)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setString(3, role);
			pstmt.executeUpdate();
		}
	}

	public boolean login(String email, String password, String role) throws SQLException {
		String query = "SELECT * FROM cse360users WHERE email = ? AND password = ? AND role = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setString(3, role);
			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next();
			}
		}
	}
	
	public boolean doesUserExist(String email) {
	    String query = "SELECT COUNT(*) FROM cse360users WHERE email = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        pstmt.setString(1, email);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            // If the count is greater than 0, the user exists
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false; // If an error occurs, assume user doesn't exist
	}

	public void displayUsersByAdmin() throws SQLException{
		String sql = "SELECT * FROM cse360users"; 
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql); 

		while(rs.next()) { 
			// Retrieve by column name 
			int id  = rs.getInt("id"); 
			String  email = rs.getString("email"); 
			String password = rs.getString("password"); 
			String role = rs.getString("role");  

			// Display values 
			System.out.print("ID: " + id); 
			System.out.print(", Age: " + email); 
			System.out.print(", First: " + password); 
			System.out.println(", Last: " + role); 
		} 
	}
	
	public void displayUsersByUser() throws SQLException{
		String sql = "SELECT * FROM cse360users"; 
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql); 

		while(rs.next()) { 
			// Retrieve by column name 
			int id  = rs.getInt("id"); 
			String  email = rs.getString("email"); 
			String password = rs.getString("password"); 
			String role = rs.getString("role");  

			// Display values 
			System.out.print("ID: " + id); 
			System.out.print(", Age: " + email); 
			System.out.print(", First: " + password); 
			System.out.println(", Last: " + role); 
		} 
	}


	public void closeConnection() {
		try{ 
			if(statement!=null) statement.close(); 
		} catch(SQLException se2) { 
			se2.printStackTrace();
		} 
		try { 
			if(connection!=null) connection.close(); 
		} catch(SQLException se){ 
			se.printStackTrace(); 
		} 
	}

}
