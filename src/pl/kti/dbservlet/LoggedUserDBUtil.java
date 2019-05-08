package pl.kti.dbservlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class LoggedUserDBUtil {

	public LoggedUserDBUtil() {
		super();
	}

	public LoggedUser getLoggedUser() throws Exception {

		LoggedUser loggedUser;
		ArrayList<LoggedUser> loggedUsers = new ArrayList<>();

		// this section will be changed
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;

		try {

			// get the connection
			myConn = DBManager.getConnection();

			// create sql statement
			String sql = "select * from logged_user";
			myStmt = myConn.createStatement();

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
				LoggedUser tempUser = new LoggedUser(uID, username, password, function);

				// add it to a list
				loggedUsers.add(tempUser);

			}
			if (loggedUsers.isEmpty()) {
				loggedUser = new LoggedUser(0, "0", "0", "0");
			} else {
				loggedUser = loggedUsers.get(0);
			}

			return loggedUser;
		} finally {

			close(myConn, myStmt, myRes);

		}

	}

	public boolean clear() throws SQLException, ClassNotFoundException {
		// this section will be changed
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;

		// get the connection
		myConn = DBManager.getConnection();

		// create sql statement
		String sql = "delete from logged_user";
		myStmt = myConn.createStatement();

		// execute query
		int positive = myStmt.executeUpdate(sql);

		close(myConn, myStmt, myRes);
		if (positive == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean setLoggedUser(LoggedUser loggedUser) throws ClassNotFoundException, SQLException {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;

		// get the connection
		myConn = DBManager.getConnection();

		// create sql statement
		String sql = "INSERT INTO logged_user(uID, username, password, user_function) VALUES(?, ?, ?, ?)";

		int positive;

		int id = loggedUser.getuID();
		String username = loggedUser.getUsername();
		String password = loggedUser.getPassword();
		String function = loggedUser.getFunction();

		PreparedStatement pstmt = myConn.prepareStatement(sql);

		pstmt.setInt(1, id);
		pstmt.setString(2, username);
		pstmt.setString(3, password);
		pstmt.setString(4, function);

		positive = pstmt.executeUpdate();

		close(myConn, myStmt, myRes);
		if (positive == 1) {
			return true;
		} else {
			return false;
		}
	}

	public Client getLoggedClient() throws Exception {
		Connection myConn = null;
		PreparedStatement pstmt = null;
		ResultSet myRes = null;

		LoggedUser user = getLoggedUser();
		String function = user.getFunction();
		Client loggedClient;
		ArrayList <Client> clients = new ArrayList<>();

		if (function == "accountant" || function == "admin") {
			loggedClient = new Client(0, "0", "0", "0");
			}
		else{
			String username = user.getUsername();

			

				// get the connection
				myConn = DBManager.getConnection();

				// create sql statement
				String sql = "select * from clients where username=?";
				pstmt = myConn.prepareStatement(sql);
				pstmt.setString(1, username);
				
				myRes = pstmt.executeQuery();
				
				while(myRes.next()) {
					int cID = myRes.getInt("cID");
					String name = myRes.getString("name");
					String city = myRes.getString("city");
					Client tempClient = new Client (cID, name, city, username);
					clients.add(tempClient);
				}
				loggedClient = clients.get(0);
		}
			
		return loggedClient;
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRes) {

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

}
