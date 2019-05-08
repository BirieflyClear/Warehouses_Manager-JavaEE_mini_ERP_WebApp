package pl.kti.dbservlet;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class UserDBUtil {
	// this section will be changed
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRes = null;

	public UserDBUtil() {
		super();

	}

	public ArrayList<User> getUsers() throws Exception {

		ArrayList<User> users = new ArrayList<>();

		try {

			// get the connection
			myConn = DBManager.getConnection();
			myStmt = myConn.createStatement();

			// create sql statement
			String sql = "select * from users";

			// execute query
			myRes = myStmt.executeQuery(sql);

			// process result set
			while (myRes.next()) {

				// retrieve data
				int uID = myRes.getInt("uID");
				String username = myRes.getString("username");
				String password = myRes.getString("password");
				String function = myRes.getString("user_function");

				// create a client object
				User tempUser = new User(uID, username, password, function);

				// add it to a list
				users.add(tempUser);

			}

			return users;
		} finally {

			close(myConn, myStmt, myRes);

		}

	}

	public int rowCount() throws SQLException, ClassNotFoundException {
		int count = 0;
		// this section will be changed

		try {

			myConn = DBManager.getConnection();
			myStmt = myConn.createStatement();
			// create sql statement
			String sql = "select * from users";

			// execute query
			myRes = myStmt.executeQuery(sql);

			// process result set
			while (myRes.next()) {
				count = myRes.getInt(1);
			}
			return count;
		} finally {
			close(myConn, myStmt, myRes);
		}

	}

	static void close(Connection myConn, Statement myStmt, ResultSet myRes) {

		try {

			if (myRes != null) {
				myRes.close();
			}
			if (myConn != null) {
				myConn.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	public void addUser(User theUser) throws ClassNotFoundException, SQLException {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {

			// get db connection
			myConn = DBManager.getConnection();

			// create sql for insert
			String sql = "INSERT into users(username, password, user_function) VALUES (?,?,?)";
			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, theUser.getUsername());
			myStmt.setString(2, theUser.getPassword());
			myStmt.setString(3, theUser.getFunction());

			// execute sql insert
			myStmt.execute();

		} finally {
			// clean up the JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public User getUser(String theID) throws Exception {

		User theUser = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;
		int ID;
		ArrayList<User> users = new ArrayList<User>();

		try {
			ID = Integer.parseInt(theID);
			myConn = DBManager.getConnection();
			String sql = "select * from users where uID = ?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, ID);
			myRes = myStmt.executeQuery();

			if (myRes.next()) {
				String username = myRes.getString("username");
				String password = myRes.getString("password");
				String function = myRes.getString("user_function");
				theUser = new User(ID, username, password, function);
				users.add(theUser);
			} else {
				throw new Exception("Could not find user ID: " + ID);
			}

			theUser = users.get(0);
			return theUser;

		} finally {
			close(myConn, myStmt, myRes);
		}
	}

	public void updateUser(User updatedUser) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = DBManager.getConnection();

			String sql = "UPDATE users SET username=?, password=?, user_function=? WHERE uID=?";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, updatedUser.getUsername());
			myStmt.setString(2, updatedUser.getPassword());
			myStmt.setString(3, updatedUser.getFunction());
			myStmt.setInt(4, updatedUser.getuID());

			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}

	}

	public void deleteUser(String id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt2 = null;
		PreparedStatement myStmt3 = null;

		try {

			int thisID = Integer.parseInt(id);
			myConn = DBManager.getConnection();
			String query = "SET FOREIGN_KEY_CHECKS=0";
			String query2 = "SET FOREIGN_KEY_CHECKS=1";
			String sql = "DELETE from users WHERE uID=?";

			myStmt = myConn.prepareStatement(sql);
			myStmt2 = myConn.prepareStatement(query);
			myStmt3 = myConn.prepareStatement(query2);
			myStmt.setInt(1, thisID);

			myStmt2.execute();
			myStmt.execute();
			myStmt3.execute();

		} finally {
			close(myConn, myStmt, null);
		}
	}

}
