package pl.kti.dbservlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductTypeDBUtil {

	public ProductTypeDBUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public ArrayList<ProductType> getProductTypes() throws Exception {

		ArrayList<ProductType> types = new ArrayList<>();

		// this section will be changed
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;

		try {

			// get the connection
			myConn = DBManager.getConnection();

			// create sql statement
			String sql = "select * from product_types";
			myStmt = myConn.createStatement();

			// execute query
			myRes = myStmt.executeQuery(sql);

			// process result set
			while (myRes.next()) {

				// retrieve data
				int tID = myRes.getInt("tID");
				String name = myRes.getString("type_name");

				// create a client object
				ProductType type = new ProductType(tID, name);

				// add it to a list
				types.add(type);

			}

			return types;
		} finally {

			close(myConn, myStmt, myRes);

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



	public void delete(int id) throws ClassNotFoundException, SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		PreparedStatement myStmt2 = null;
		PreparedStatement myStmt3 = null;

		try {

			int thisID = id;
			myConn = DBManager.getConnection();
			String query = "SET FOREIGN_KEY_CHECKS=0";
			String query2 = "SET FOREIGN_KEY_CHECKS=1";
			String sql = "DELETE from product_types WHERE tID=?";

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



	public ProductType getThis(int id) throws Exception {
		ProductType type = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;
		int ID;
		ArrayList<ProductType> types = new ArrayList<ProductType>();

		try {
			ID = id;
			myConn = DBManager.getConnection();
			String sql = "select * from product_types where tID = ?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, ID);
			myRes = myStmt.executeQuery();

			if (myRes.next()) {
				int tID = myRes.getInt("tID");
				String name = myRes.getString("type_name");

				// create a client object
				ProductType temp = new ProductType(tID, name);

				// add it to a list
				types.add(temp);
			} else {
				throw new Exception("Could not find type ID = " +ID);
			}

			type = types.get(0);
			return type;

		} finally {
			close(myConn, myStmt, myRes);
		}
	}



	public void updateType(ProductType type) throws SQLException, ClassNotFoundException {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = DBManager.getConnection();

			String sql = "UPDATE product_types SET type_name=? WHERE tID=?";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, type.getTypeName());
			myStmt.setInt(2, type.gettID());

			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}
		
	}

}
