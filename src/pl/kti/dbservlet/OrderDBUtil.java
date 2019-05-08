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

public class OrderDBUtil {

	public OrderDBUtil() {
		super();
	}

	public ArrayList<Order> getOrders() throws Exception {

		ArrayList<Order> orders = new ArrayList<>();

		// this section will be changed
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;

		try {

			// get the connection
			myConn = DBManager.getConnection();

			// create sql statement
			String sql = "select * from orders order by oID";
			myStmt = myConn.createStatement();

			// execute query
			myRes = myStmt.executeQuery(sql);

			// process result set
			while (myRes.next()) {

				// retrieve data
				int oID = myRes.getInt("oID");
				int client_ID = myRes.getInt("client_ID");
				String client_name = myRes.getString("client_name");
				float NET_price = myRes.getFloat("NET_price");
				float GROSS_price = myRes.getFloat("GROSS_price");
				String client_city = myRes.getString("client_city");
				int warehouse_ID = myRes.getInt("warehouse_ID");
				String status = myRes.getString("status");
				String invoice_status = myRes.getString("invoice_status");

				// create a client object
				Order tempOrder = new Order(oID, client_ID, client_name, NET_price, GROSS_price, client_city,
						warehouse_ID, status, invoice_status);

				// add it to a list
				orders.add(tempOrder);

			}

			return orders;
		} finally {

			close(myConn, myStmt, myRes);

		}

	}

	public void addOrder(Client client, float net, float gross) throws SQLException, ClassNotFoundException {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		String exists = client.getName();
		if (exists != "0") {

			try {

				// get db connection
				myConn = DBManager.getConnection();

				// create sql for insert
				String sql = "INSERT into orders(client_ID, client_name, NET_price, GROSS_price, client_city, warehouse_ID, status, invoice_status) VALUES (?,?,?,?,?,?,?,?)";
				myStmt = myConn.prepareStatement(sql);

				// set the param values for the student
				myStmt.setInt(1, client.getcID());
				myStmt.setString(2, client.getName());
				myStmt.setFloat(3, net);
				myStmt.setFloat(4, gross);
				myStmt.setString(5, client.getCity());
				myStmt.setInt(6, 1);
				myStmt.setString(7, "pending");
				myStmt.setString(8, "no invoice");

				// execute sql insert
				myStmt.execute();

			} finally {
				// clean up the JDBC objects
				close(myConn, myStmt, null);
			}
		}

	}

	public int lastOrder() throws ClassNotFoundException, SQLException {
		int count = 0;

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;

		// get the connection
		myConn = DBManager.getConnection();

		// create sql statement
		String sql = "SELECT * FROM orders order by oID DESC LIMIT 0,1";
		myStmt = myConn.createStatement();

		// execute query
		myRes = myStmt.executeQuery(sql);

		while (myRes.next()) {
			count = myRes.getInt(1);
		}

		return count;
	}

	public ArrayList<Order> getMyOrders(int id) throws ClassNotFoundException, SQLException {

		ArrayList<Order> orders = new ArrayList<>();

		Connection myConn = null;
		PreparedStatement pstmt = null;
		ResultSet myRes = null;

		try {
			// get the connection
			myConn = DBManager.getConnection();

			// create sql statement
			String sql = "SELECT*FROM orders WHERE client_ID=?";

			pstmt = myConn.prepareStatement(sql);

			pstmt.setInt(1, id);

			myRes = pstmt.executeQuery();

			while (myRes.next()) {

				// retrieve data
				int oID = myRes.getInt(1);
				int client_ID = myRes.getInt(2);
				String client_name = myRes.getString(3);
				float NET_price = myRes.getFloat(4);
				float GROSS_price = myRes.getFloat(5);
				String client_city = myRes.getString(6);
				int warehouse_ID = myRes.getInt(7);
				String status = myRes.getString(8);
				String invoice_status = myRes.getString(9);

				// create a client object
				Order tempOrder = new Order(oID, client_ID, client_name, NET_price, GROSS_price, client_city,
						warehouse_ID, status, invoice_status);

				// add it to a list
				orders.add(tempOrder);

			}

			return orders;
		} finally {

			close(myConn, pstmt, myRes);

		}

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

	public void changeInvoiceStatus(int orderID) throws SQLException, ClassNotFoundException {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {

			// get db connection
			myConn = DBManager.getConnection();

			// create sql for insert
			String sql = "UPDATE orders SET invoice_status=\" paid\" WHERE oID=?";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1, orderID);

			myStmt.execute();

		} finally {
			// clean up the JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public ArrayList<Cart> getThisOrder(int orderID) throws SQLException, ClassNotFoundException {
		ArrayList<Cart> orders = new ArrayList<>();

		// this section will be changed
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;

		try {

			// get the connection
			myConn = DBManager.getConnection();

			// create sql statement
			String sql = "select * from order_?";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1, orderID);
			myRes = myStmt.executeQuery();

			// process result set
			while (myRes.next()) {
				int pID = myRes.getInt("product_ID");
				String name = myRes.getString("product_name");
				float NET_price = myRes.getFloat("NET_price");
				float GROSS_price = myRes.getFloat("GROSS_price");

				Cart tempProduct = new Cart(pID, name, NET_price, GROSS_price);

				orders.add(tempProduct);
			}

			return orders;
		} finally {

			close(myConn, myStmt, myRes);

		}

	}

	public void changeOrderStatus(int id, String status) throws SQLException, ClassNotFoundException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;

		// get the connection
		myConn = DBManager.getConnection();

		// create sql statement
		String sql = "UPDATE orders SET status=? WHERE oID=?";
		myStmt = myConn.prepareStatement(sql);

		myStmt.setString(1, status);
		myStmt.setInt(2, id);
		myStmt.executeUpdate();

		close(myConn, myStmt, myRes);
	}

	public void updateOrder(Order updatedOrder) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = DBManager.getConnection();

			String sql = "UPDATE orders SET client_ID=?, client_name=?, NET_price=?, GROSS_price=?, client_city=?, warehouse_ID=?, status=?, invoice_status=? WHERE oID=?";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1, updatedOrder.getClient_ID());
			myStmt.setString(2, updatedOrder.getClient_name());
			myStmt.setFloat(3, updatedOrder.getNET_price());
			myStmt.setFloat(4, updatedOrder.getGROSS_price());
			myStmt.setString(5, updatedOrder.getClient_city());
			myStmt.setInt(6, updatedOrder.getWarehouse_ID());
			myStmt.setString(7, updatedOrder.getStatus());
			myStmt.setString(8, updatedOrder.getInvoice_status());
			myStmt.setInt(9, updatedOrder.getoID());

			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}

	}

	public Order getOrder(String theID) throws Exception {

		Order theOrder = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;
		int ID;
		ArrayList<Order> orders = new ArrayList<Order>();

		try {
			ID = Integer.parseInt(theID);
			myConn = DBManager.getConnection();
			String sql = "select * from orders where oID = ?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, ID);
			myRes = myStmt.executeQuery();

			if (myRes.next()) {
				int client_ID = myRes.getInt("client_ID");
				String client_name = myRes.getString("client_name");
				Float NET_price = myRes.getFloat("NET_price");
				Float GROSS_price = myRes.getFloat("GROSS_price");
				String client_city = myRes.getString("client_city");
				int warehouse_ID = myRes.getInt("warehouse_ID");
				String status = myRes.getString("status");
				String invoice_status = myRes.getString("invoice_status");

				theOrder = new Order(ID, client_ID, client_name, NET_price, GROSS_price, client_city, warehouse_ID,
						status, invoice_status);
				orders.add(theOrder);
			} else {
				orders.add(0, new Order(0,0,"0",0,0,"0",0,"0","0"));
			}

			theOrder = orders.get(0);
			return theOrder;

		} finally {
			close(myConn, myStmt, myRes);
		}

	}

	public void deleteOrder(String id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt2 = null;
		PreparedStatement myStmt3 = null;

		try {

			int thisID = Integer.parseInt(id);
			myConn = DBManager.getConnection();
			String query = "SET FOREIGN_KEY_CHECKS=0";
			String query2 = "SET FOREIGN_KEY_CHECKS=1";
			String sql = "DELETE from orders WHERE oID=?";

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

	public ArrayList<Order> getNotPayed() throws SQLException, ClassNotFoundException {
		ArrayList<Order> orders = new ArrayList<>();

		Connection myConn = null;
		PreparedStatement pstmt = null;
		ResultSet myRes = null;

		try {
			// get the connection
			myConn = DBManager.getConnection();

			// create sql statement
			String sql = "SELECT*FROM orders WHERE invoice_status=\"no invoice\"";

			pstmt = myConn.prepareStatement(sql);

			myRes = pstmt.executeQuery();

			while (myRes.next()) {

				// retrieve data
				int oID = myRes.getInt("oID");
				int client_ID = myRes.getInt("client_ID");
				String client_name = myRes.getString("client_name");
				float NET_price = myRes.getFloat("NET_price");
				float GROSS_price = myRes.getFloat("GROSS_price");
				String client_city = myRes.getString("client_city");
				int warehouse_ID = myRes.getInt("warehouse_ID");
				String status = myRes.getString("status");
				String invoice_status = myRes.getString("invoice_status");

				// create a client object
				Order tempOrder = new Order(oID, client_ID, client_name, NET_price, GROSS_price, client_city,
						warehouse_ID, status, invoice_status);

				// add it to a list
				orders.add(tempOrder);

			}

			return orders;
		} finally {

			close(myConn, pstmt, myRes);

		}

	}

}
