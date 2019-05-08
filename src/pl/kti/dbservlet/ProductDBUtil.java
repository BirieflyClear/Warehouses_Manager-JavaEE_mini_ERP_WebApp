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

public class ProductDBUtil {

	public ProductDBUtil() {
		super();
	}

	public ArrayList<Product> getProducts() throws Exception {

		ArrayList<Product> products = new ArrayList<>();

		// this section will be changed
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;

		try {

			// get the connection
			myConn = DBManager.getConnection();

			// create sql statement
			String sql = "select * from products order by pID";
			myStmt = myConn.createStatement();

			// execute query
			myRes = myStmt.executeQuery(sql);

			// process result set
			while (myRes.next()) {

				// retrieve data
				int pID = myRes.getInt("pID");
				String name = myRes.getString("name");
				String type = myRes.getString("type");
				float NET_price = myRes.getFloat("NET_price");
				float GROSS_price = myRes.getFloat("GROSS_price");
				int warehouse_ID = myRes.getInt("warehouse_ID");
				float quantity = myRes.getFloat("quantity");

				// create a client object
				Product tempProduct = new Product(pID, name, type, NET_price, GROSS_price, warehouse_ID, quantity);

				// add it to a list
				products.add(tempProduct);

			}

			return products;
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
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;

		try {

			myConn = DBManager.getConnection();
			myStmt = myConn.createStatement();
			// create sql statement
			String sql = "select * from products";

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

	public static void addProduct(Product theProduct) throws SQLException, ClassNotFoundException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt2 = null;
		PreparedStatement myStmt3 = null;
		ProductDBUtil exproduct = new ProductDBUtil();
		int count = exproduct.rowCount();

		try {

			// get db connection
			myConn = DBManager.getConnection();

			// create sql for insert
			String query = "SET FOREIGN_KEY_CHECKS=0";
			myStmt2 = myConn.prepareStatement(query);
			String sql = "INSERT into products(pID, name, type, NET_price, GROSS_price, warehouse_ID, quantity) VALUES (?,?,?,?,?,?,?)";
			myStmt = myConn.prepareStatement(sql);
			String query2 = "SET FOREIGN_KEY_CHECKS=1";
			myStmt3 = myConn.prepareStatement(query2);

			// set the param values for the student
			myStmt.setInt(1, count + 1);
			myStmt.setString(2, theProduct.getName());
			myStmt.setString(3, theProduct.getType());
			myStmt.setFloat(4, theProduct.getNET_price());
			myStmt.setFloat(5, theProduct.getGROSS_price());
			myStmt.setInt(6, theProduct.getWarehouse_ID());
			myStmt.setFloat(7, theProduct.getQuantity());

			// execute sql insert
			myStmt2.execute();
			myStmt.execute();
			myStmt3.execute();

		} finally {
			// clean up the JDBC objects
			close(myConn, myStmt, null);
		}

	}

	public ArrayList<Product> getProductsFromWarehouse(int id) throws SQLException, ClassNotFoundException {

		ArrayList<Product> products = new ArrayList<>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;

		// get the connection
		myConn = DBManager.getConnection();

		// create sql statement
		String sql = "SELECT * FROM products WHERE warehouse_ID=?";
		myStmt = myConn.prepareStatement(sql);

		myStmt.setInt(1, id);
		myRes = myStmt.executeQuery();

		while (myRes.next()) {
			int pID = myRes.getInt("pID");
			String name = myRes.getString("name");
			String type = myRes.getString("type");
			float NET_price = myRes.getFloat("NET_price");
			float GROSS_price = myRes.getFloat("GROSS_price");
			int warehouse_ID = myRes.getInt("warehouse_ID");
			float quantity = myRes.getFloat("quantity");

			// create a client object
			Product tempProduct = new Product(pID, name, type, NET_price, GROSS_price, warehouse_ID, quantity);

			// add it to a list
			products.add(tempProduct);
		}

		close(myConn, myStmt, myRes);

		return products;
	}

	public void updateProduct(Product updatedProduct) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = DBManager.getConnection();

			String sql = "UPDATE products SET name=?, type=?, NET_price=?, GROSS_price=?, warehouse_ID=?, quantity=? WHERE pID=?";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, updatedProduct.getName());
			myStmt.setString(2, updatedProduct.getType());
			myStmt.setFloat(3, updatedProduct.getNET_price());
			myStmt.setFloat(4, updatedProduct.getGROSS_price());
			myStmt.setInt(5, updatedProduct.getWarehouse_ID());
			myStmt.setFloat(6, updatedProduct.getQuantity());
			myStmt.setInt(7, updatedProduct.getpID());

			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}

	}

	public Product getProduct(String theID) throws Exception {

		Product theProduct = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;
		int ID;
		ArrayList<Product> products = new ArrayList<Product>();

		try {
			ID = Integer.parseInt(theID);
			myConn = DBManager.getConnection();
			String sql = "select * from products where pID = ?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, ID);
			myRes = myStmt.executeQuery();

			if (myRes.next()) {
				String name = myRes.getString("name");
				String type = myRes.getString("type");
				Float NET_price = myRes.getFloat("NET_price");
				Float GROSS_price = myRes.getFloat("GROSS_price");
				int warehouse_ID = myRes.getInt("warehouse_ID");
				Float quantity = myRes.getFloat("quantity");

				theProduct = new Product(ID, name, type, NET_price, GROSS_price, warehouse_ID, quantity);
				products.add(theProduct);
			} else {
				throw new Exception("Could not find product ID: " + ID);
			}

			theProduct = products.get(0);
			return theProduct;

		} finally {
			close(myConn, myStmt, myRes);
		}

	}

	public void deleteProduct(String id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt2 = null;
		PreparedStatement myStmt3 = null;

		try {

			int thisID = Integer.parseInt(id);
			myConn = DBManager.getConnection();
			String query = "SET FOREIGN_KEY_CHECKS=0";
			String query2 = "SET FOREIGN_KEY_CHECKS=1";
			String sql = "DELETE from products WHERE pID=?";

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
