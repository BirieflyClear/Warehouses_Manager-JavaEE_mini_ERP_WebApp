package pl.kti.dbservlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class DBManager {

	private static String _host = "localhost";
	private static String _port = "3306";
	private static String _user = "root";
	private static String _pass = "WRq76A3zx";
	private static String _db = "project";

	private static Connection connection;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + _host + ":" + _port + "/" + _db + "";
			connection = DriverManager.getConnection(url, _user, _pass);
		
		return connection;
	}


}