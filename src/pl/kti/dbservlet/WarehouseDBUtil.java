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

public class WarehouseDBUtil {
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRes = null;

	public WarehouseDBUtil() {
		super();
	}

	public ArrayList<Warehouse> getWarehouses() throws Exception {

		ArrayList<Warehouse> warehouses = new ArrayList<>();

		// this section will be changed

		try {

			// get the connection
			myConn = DBManager.getConnection();

			// create sql statement
			String sql = "select * from warehouses order by wID";
			myStmt = myConn.createStatement();

			// execute query
			myRes = myStmt.executeQuery(sql);

			// process result set
			while (myRes.next()) {

				// retrieve data
				int wID = myRes.getInt("wID");
				String city = myRes.getString("city");
				String products_type = myRes.getString("products_type");
				float capacity = myRes.getFloat("capacity");

				// create a client object
				Warehouse tempWarehouse = new Warehouse(wID, city, products_type, capacity);

				// add it to a list
				warehouses.add(tempWarehouse);

			}

			return warehouses;
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

	public int rowCount() throws SQLException, ClassNotFoundException {
		int count = 0;

		try {

			myConn = DBManager.getConnection();
			myStmt = myConn.createStatement();
			// create sql statement
			String sql = "select * from warehouses";

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

	public static void addWarehouse(Warehouse theWarehouse) throws SQLException, ClassNotFoundException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt2 = null;
		PreparedStatement myStmt3 = null;
		WarehouseDBUtil exwarehouse = new WarehouseDBUtil();
		int count = exwarehouse.rowCount();

		try {

			// get db connection
			myConn = DBManager.getConnection();

			// create sql for insert
			String query = "SET FOREIGN_KEY_CHECKS=0";
			String query2 = "SET FOREIGN_KEY_CHECKS=1";
			String sql = "INSERT into warehouses(wID, city, products_type, capacity) VALUES (?,?,?,?)";
			myStmt = myConn.prepareStatement(sql);
			myStmt2 = myConn.prepareStatement(query);
			myStmt3 = myConn.prepareStatement(query2);

			// set the param values for the student
			myStmt.setInt(1, count + 1);
			myStmt.setString(2, theWarehouse.getCity());
			myStmt.setString(3, theWarehouse.getProducts_type());
			myStmt.setFloat(4, theWarehouse.getCapacity());

			// execute sql insert
			myStmt2.execute();
			myStmt.execute();
			myStmt3.execute();

		} finally {
			// clean up the JDBC objects
			close(myConn, myStmt, null);
		}

	}

	public void updateWarehouse(Warehouse updatedWarehouse) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt2 = null;
		PreparedStatement myStmt3 = null;

		try {
			myConn = DBManager.getConnection();

			String query = "SET FOREIGN_KEY_CHECKS=0";
			String query2 = "SET FOREIGN_KEY_CHECKS=1";
			String sql = "UPDATE warehouses SET city=?, products_type=?, capacity=? WHERE wID=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt2 = myConn.prepareStatement(query);
			myStmt3 = myConn.prepareStatement(query2);

			myStmt.setString(1, updatedWarehouse.getCity());
			myStmt.setString(2, updatedWarehouse.getProducts_type());
			myStmt.setFloat(3, updatedWarehouse.getCapacity());
			myStmt.setInt(4, updatedWarehouse.getwID());

			myStmt2.execute();
			myStmt.execute();
			myStmt3.execute();

		} finally {
			close(myConn, myStmt, null);
		}

	}

	public Warehouse getWarehouse(String theID) throws Exception {

		Warehouse theWarehouse = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;
		int ID;
		ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();

		try {
			ID = Integer.parseInt(theID);
			myConn = DBManager.getConnection();
			String sql = "select * from warehouses where wID = ?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, ID);
			myRes = myStmt.executeQuery();

			if (myRes.next()) {
				String city = myRes.getString("city");
				String products_type = myRes.getString("products_type");
				Float capacity = myRes.getFloat("capacity");

				theWarehouse = new Warehouse(ID, city, products_type, capacity);
				warehouses.add(theWarehouse);

			} else {
				throw new Exception("Could not find warehouse ID: " + ID);
			}

			theWarehouse = warehouses.get(0);
			return theWarehouse;

		} finally {
			close(myConn, myStmt, myRes);
		}

	}

	public void deleteWarehouse(String id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt2 = null;
		PreparedStatement myStmt3 = null;

		try {

			int thisID = Integer.parseInt(id);
			myConn = DBManager.getConnection();
			String query = "SET FOREIGN_KEY_CHECKS=0";
			String query2 = "SET FOREIGN_KEY_CHECKS=1";
			String sql = "DELETE from warehouses WHERE wID=?";

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
