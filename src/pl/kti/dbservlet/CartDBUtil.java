package pl.kti.dbservlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartDBUtil {

	public CartDBUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Cart> getCart() throws Exception {

		ArrayList<Cart> cart = new ArrayList<>();

		// this section will be changed
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;

		try {

			// get the connection
			myConn = DBManager.getConnection();

			// create sql statement
			String sql = "select * from cart";
			myStmt = myConn.createStatement();

			// execute query
			myRes = myStmt.executeQuery(sql);

			// process result set
			while (myRes.next()) {

				int ID = myRes.getInt("ID");
				int pID = myRes.getInt("product_ID");
				String name = myRes.getString("product_name");
				
				float NET_price = myRes.getFloat("NET_price");
				float GROSS_price = myRes.getFloat("GROSS_price");

				// create a client object
				Cart tempProduct = new Cart(ID, pID, name, NET_price, GROSS_price);

				// add it to a list
				cart.add(tempProduct);

			}

			return cart;
		} finally {

			close(myConn, myStmt, myRes);

		}

	}

	public float getNET(ArrayList<Cart> cart) {
		float sum = 0;

		for (int i = 0; i < cart.size(); i++) {

			sum += cart.get(i).getNET_price();
		}

		return sum;
	}

	public float getGROSS(ArrayList<Cart> cart) {
		float sum = 0;

		for (int i = 0; i < cart.size(); i++) {

			sum += cart.get(i).getGROSS_price();
		}

		return sum;
	}

	public void addToCart(Product product) throws ClassNotFoundException, SQLException {

		Connection myConn = null;
		PreparedStatement pstmt = null;

		// get the connection
		myConn = DBManager.getConnection();

		// create sql statement
		String sql = "INSERT INTO cart(product_ID, product_name, NET_price, GROSS_price) VALUES(?, ?, ?, ?)";

		int positive;

		int pID = product.getpID();
		String name = product.getName();
		// String type = product.getType();
		float NET_price = product.getNET_price();
		float GROSS_price = product.getGROSS_price();
		// int warehouse_ID = product.getWarehouse_ID();
		// float quantity = product.getQuantity();

		pstmt = myConn.prepareStatement(sql);

		pstmt.setInt(1, pID);
		pstmt.setString(2, name);
		// pstmt.setString(3, type);
		pstmt.setFloat(3, NET_price);
		pstmt.setFloat(4, GROSS_price);
		// pstmt.setInt(4, warehouse_ID);
		// pstmt.setFloat(4, quantity);

		positive = pstmt.executeUpdate();

		close(myConn, pstmt);

	}

	public void removeFromCart(int id) throws ClassNotFoundException, SQLException {

		Connection myConn = null;
		PreparedStatement pstmt = null;

		// get the connection
		myConn = DBManager.getConnection();

		// create sql statement
		String sql = "DELETE FROM cart WHERE ID=?";

		pstmt = myConn.prepareStatement(sql);

		pstmt.setInt(1, id);

		pstmt.executeUpdate();

		close(myConn, pstmt);

	}

	public void clear() throws ClassNotFoundException, SQLException {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;
		PreparedStatement myStmt2 = null;

		// get the connection
		myConn = DBManager.getConnection();

		// create sql statement
		String sql = "delete from cart";
		String sql2 = "ALTER TABLE cart AUTO_INCREMENT = 1";
		myStmt = myConn.createStatement();
		myStmt2 = myConn.prepareStatement(sql2);

		// execute query
		myStmt.executeUpdate(sql);
		myStmt2.execute();
		close(myConn, myStmt, myRes);

	}

	public void saveOrder() throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;

		ArrayList<Cart> cart = getCart();

		OrderDBUtil orderDBUtil = new OrderDBUtil();
		int count = orderDBUtil.lastOrder();

		myConn = DBManager.getConnection();
		String sql = "create table order_? (product_ID VARCHAR(100), product_name VARCHAR(100), NET_price FLOAT(7,2), GROSS_price FLOAT(7,2))";

		pstmt = myConn.prepareStatement(sql);
		pstmt.setInt(1, count);
		pstmt.executeUpdate();

		String sql2 = "INSERT INTO order_? (product_ID, product_name, NET_price, GROSS_price) VALUES(?, ?, ?, ?)";
		pstmt1 = myConn.prepareStatement(sql2);

		for (int i = 0; i < cart.size(); i++) {
			pstmt1.setInt(1, count);
			pstmt1.setInt(2, cart.get(i).getpID());
			pstmt1.setString(3, cart.get(i).getName());
			pstmt1.setFloat(4, cart.get(i).getNET_price());
			pstmt1.setFloat(5, cart.get(i).getGROSS_price());
			pstmt1.executeUpdate();
		}

		close(myConn, myStmt, myRes, pstmt, pstmt1);

	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRes, PreparedStatement pstmt,
			PreparedStatement pstmt1) {
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
			if (pstmt != null) {
				pstmt.close();
			}
			if (pstmt1 != null) {
				pstmt1.close();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	private void close(Connection myConn, PreparedStatement pstmt) {
		try {

			if (pstmt != null) {
				pstmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}

		} catch (Exception exception) {
			exception.printStackTrace();
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

}
