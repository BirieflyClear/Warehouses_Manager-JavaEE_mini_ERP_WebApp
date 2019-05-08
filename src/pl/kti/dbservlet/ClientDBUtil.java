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

public class ClientDBUtil {

	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRes = null;

	public ClientDBUtil() {
		super();
	}

	public ArrayList<Client> getClients() throws Exception {

		ArrayList<Client> clients = new ArrayList<>();

		try {

			// get the connection
			myConn = DBManager.getConnection();

			// create sql statement
			String sql = "select * from clients order by cID";
			myStmt = myConn.createStatement();

			// execute query
			myRes = myStmt.executeQuery(sql);

			// process result set
			while (myRes.next()) {

				// retrieve data
				int cID = myRes.getInt("cID");
				String name = myRes.getString("name");
				String city = myRes.getString("city");
				String username = myRes.getString("username");

				// create a client object
				Client tempClient = new Client(cID, name, city, username);

				// add it to a list
				clients.add(tempClient);

			}

			return clients;
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

	public void addClient(Client theClient) throws SQLException, ClassNotFoundException {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {

			// get db connection
			myConn = DBManager.getConnection();

			// create sql for insert
			String sql = "INSERT into clients(name, city, username) VALUES (?,?,?)";
			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, theClient.getName());
			myStmt.setString(2, theClient.getCity());
			myStmt.setString(3, theClient.getUsername());

			// execute sql insert
			myStmt.execute();

		} finally {
			// clean up the JDBC objects
			close(myConn, myStmt, null);
		}

	}

	public Client getClient(String theID) throws Exception {

		Client theClient = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;
		int ID;
		ArrayList<Client> clients = new ArrayList<Client>();

		try {
			ID = Integer.parseInt(theID);
			myConn = DBManager.getConnection();
			String sql = "select * from clients where cID = ?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, ID);
			myRes = myStmt.executeQuery();

			if (myRes.next()) {
				String name = myRes.getString("name");
				String city = myRes.getString("city");
				String username = myRes.getString("username");
				theClient = new Client(ID, name, city, username);
				clients.add(theClient);
			} else {
				throw new Exception("Could not find client ID: " + ID);
			}

			theClient = clients.get(0);
			return theClient;

		} finally {
			close(myConn, myStmt, myRes);
		}

	}

	public void updateClient(Client updatedClient) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = DBManager.getConnection();

			String sql = "UPDATE clients SET name=?, city=?, username=? WHERE cID=?";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, updatedClient.getName());
			myStmt.setString(2, updatedClient.getCity());
			myStmt.setString(3, updatedClient.getUsername());
			myStmt.setInt(4, updatedClient.getcID());

			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}
	}

	public void deleteClient(String id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt2 = null;
		PreparedStatement myStmt3 = null;

		try {

			int thisID = Integer.parseInt(id);
			myConn = DBManager.getConnection();
			String query = "SET FOREIGN_KEY_CHECKS=0";
			String query2 = "SET FOREIGN_KEY_CHECKS=1";
			String sql = "DELETE from clients WHERE cID=?";

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
