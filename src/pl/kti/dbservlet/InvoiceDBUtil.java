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

public class InvoiceDBUtil {

	public InvoiceDBUtil() {
		super();
	}

	public ArrayList<Invoice> getInvoices() throws Exception {

		ArrayList<Invoice> invoices = new ArrayList<>();

		// this section will be changed
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;

		try {

			// get the connection
			myConn = DBManager.getConnection();

			// create sql statement
			String sql = "select * from invoices order by iID";
			myStmt = myConn.createStatement();

			// execute query
			myRes = myStmt.executeQuery(sql);

			// process result set
			while (myRes.next()) {

				// retrieve data
				int iID = myRes.getInt("iID");
				int client_ID = myRes.getInt("client_ID");
				String client_name = myRes.getString("client_name");
				int order_ID = myRes.getInt("order_ID");
				float netValue = myRes.getFloat("NET_Value");
				float grossValue = myRes.getFloat("GROSS_Value");

				// create a client object
				Invoice tempInvoice = new Invoice(iID, client_ID, client_name, order_ID, netValue, grossValue);

				// add it to a list
				invoices.add(tempInvoice);

			}

			return invoices;
		} finally {

			close(myConn, myStmt, myRes);

		}

	}

	public ArrayList<Invoice> getMyInvoices(int id) throws ClassNotFoundException, SQLException {

		ArrayList<Invoice> invoices = new ArrayList<>();

		Connection myConn = null;
		PreparedStatement pstmt = null;
		ResultSet myRes = null;

		try {
			// get the connection
			myConn = DBManager.getConnection();

			// create sql statement
			String sql = "SELECT*FROM invoices WHERE client_ID=?";

			pstmt = myConn.prepareStatement(sql);

			pstmt.setInt(1, id);

			myRes = pstmt.executeQuery();

			while (myRes.next()) {

				// retrieve data
				int iID = myRes.getInt("iID");
				int client_ID = myRes.getInt("client_ID");
				String client_name = myRes.getString("client_name");
				int order_ID = myRes.getInt("order_ID");

				// create a client object
				Invoice tempInvoice = new Invoice(iID, client_ID, client_name, order_ID);

				// add it to a list
				invoices.add(tempInvoice);

			}

			return invoices;
		} finally {

			close(myConn, pstmt, myRes);

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

	public void addInvoice(Invoice theInvoice) throws SQLException, ClassNotFoundException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt2 = null;
		PreparedStatement myStmt3 = null;
		PreparedStatement myStmt4 = null;

		try {

			// get db connection
			myConn = DBManager.getConnection();

			// create sql for insert
			String query = "SET FOREIGN_KEY_CHECKS=0";
			String query2 = "SET FOREIGN_KEY_CHECKS=1";
			String query3 = "UPDATE orders SET invoice_status=\"not paid \" WHERE oID =?";
			String sql = "INSERT into invoices(client_ID, client_name, order_ID, NET_Value, GROSS_Value) VALUES (?,?,?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			myStmt2 = myConn.prepareStatement(query);
			myStmt3 = myConn.prepareStatement(query2);
			myStmt4 = myConn.prepareStatement(query3);

			// set the param values for the student
			myStmt.setInt(1, theInvoice.getClientID());
			myStmt.setString(2, theInvoice.getClient_name());
			myStmt.setInt(3, theInvoice.getOrder_ID());
			myStmt.setFloat(4, theInvoice.getNetValue());
			myStmt.setFloat(5, theInvoice.getGrossValue());
			
			myStmt4.setInt(1, theInvoice.getOrder_ID());

			// execute sql insert
			myStmt2.execute();
			myStmt.execute();
			myStmt3.execute();
			myStmt4.execute();

		} finally {
			// clean up the JDBC objects
			close(myConn, myStmt, null);
		}

	}

	public Invoice getInvoice(String theID) throws Exception {

		Invoice theInvoice = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;
		int ID;
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();

		try {
			ID = Integer.parseInt(theID);
			myConn = DBManager.getConnection();
			String sql = "select * from invoices where iID = ?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, ID);
			myRes = myStmt.executeQuery();

			if (myRes.next()) {
				int client_ID = Integer.parseInt(myRes.getString("client_ID"));
				String client_name = myRes.getString("client_name");
				int order_ID = Integer.parseInt(myRes.getString("order_ID"));
				theInvoice = new Invoice(ID, client_ID, client_name, order_ID);
				invoices.add(theInvoice);
			} else {
				throw new Exception("Could not find invoice ID: " + ID);
			}

			theInvoice = invoices.get(0);
			return theInvoice;

		} finally {
			close(myConn, myStmt, myRes);
		}
	}

	public void updateInvoice(Invoice updatedInvoice) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = DBManager.getConnection();

			String sql = "UPDATE invoices SET client_ID=?, client_name=?, order_ID=? WHERE iID=?";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1, updatedInvoice.getClientID());
			myStmt.setString(2, updatedInvoice.getClient_name());
			myStmt.setInt(3, updatedInvoice.getOrder_ID());
			myStmt.setInt(4, updatedInvoice.getiID());

			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}

	}

	public void deleteInvoice(String id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt2 = null;
		PreparedStatement myStmt3 = null;

		try {

			int thisID = Integer.parseInt(id);
			myConn = DBManager.getConnection();
			String query = "SET FOREIGN_KEY_CHECKS=0";
			String query2 = "SET FOREIGN_KEY_CHECKS=1";
			String sql = "DELETE from invoices WHERE iID=?";

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

	public void makeInvoice(int j) {
		// TODO Auto-generated method stub
		
	}

}
