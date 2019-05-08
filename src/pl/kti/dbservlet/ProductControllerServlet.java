package pl.kti.dbservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ProductControllerServlet
 */
@WebServlet("/ProductControllerServlet")
public class ProductControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private ProductDBUtil productDBUtil;
	private LoggedUserDBUtil loggedUserDBUtil;
	private WarehouseDBUtil warehouseDBUtil;

	public ProductControllerServlet() {

		productDBUtil = new ProductDBUtil();
		loggedUserDBUtil = new LoggedUserDBUtil();
		warehouseDBUtil = new WarehouseDBUtil();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String theCommand = request.getParameter("command");

			if (theCommand == null) {
				theCommand = "ListProducts";
			}

			switch (theCommand) {

			case "ListProducts":
				listProducts(request, response);
				break;

			case "ADD":
				addProduct(request, response);
				break;

			case "ListThisWarehouse":
				listThisWarehouse(request, response);
				break;

			case "LOAD":
				loadProduct(request, response);
				break;

			case "UPDATE":
				updateProduct(request, response);
				break;

			case "DELETE":
				deleteProduct(request, response);
				break;

			default:
				listProducts(request, response);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		productDBUtil.deleteProduct(id);
		listProducts(request, response);

	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		float NET_price = Float.parseFloat(request.getParameter("NET_price"));
		float GROSS_price = Float.parseFloat(request.getParameter("GROSS_price"));
		int warehouse_ID = Integer.parseInt(request.getParameter("warehouse_ID"));
		float quantity = Float.parseFloat(request.getParameter("quantity"));

		// new Product object
		ArrayList<Warehouse> wh = warehouseDBUtil.getWarehouses();
		
		int j=0;
		for(int i = 0; i<wh.size(); i++) {
			if(warehouse_ID == wh.get(i).getwID()) {
				Product updatedProduct = new Product(id, name, type, NET_price, GROSS_price, warehouse_ID, quantity);

				// perform operation on the DB
				productDBUtil.updateProduct(updatedProduct);

				// back to the table
				listProducts(request, response);
			}else {
				j++;
			}
			
		}
		if(j==wh.size()) {
			String message = "Warehouse doesn't exist!";
			request.setAttribute("MSG", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/product-message.jsp");
			dispatcher.forward(request, response);
		}
		
		

	}

	private void loadProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String theID = request.getParameter("id");
		Product SelectedProduct = productDBUtil.getProduct(theID);
		request.setAttribute("THE_PRODUCT", SelectedProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_product.jsp");
		dispatcher.forward(request, response);

	}

	private void listThisWarehouse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));

		ArrayList<Product> products = productDBUtil.getProductsFromWarehouse(id);

		int function = loggedUserDBUtil.getLoggedUser().getFunction().length();

		request.setAttribute("PRODUCT_LIST", products);
		request.setAttribute("FUNCTION", function);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-products.jsp");
		dispatcher.forward(request, response);

	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read user data from the form
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		float NET_price = Float.parseFloat(request.getParameter("NET_price"));
		float GROSS_price = Float.parseFloat(request.getParameter("GROSS_price"));
		int warehouse_ID = Integer.parseInt(request.getParameter("warehouse_ID"));
		float quantity = Float.parseFloat(request.getParameter("quantity"));

		// create a new user object
		Product theProduct = new Product(name, type, NET_price, GROSS_price, warehouse_ID, quantity);

		// add user to the data base
		ProductDBUtil.addProduct(theProduct);
		// send back to main page
		listProducts(request, response);

	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArrayList<Product> products = productDBUtil.getProducts();

		int function = loggedUserDBUtil.getLoggedUser().getFunction().length();

		request.setAttribute("PRODUCT_LIST", products);
		request.setAttribute("FUNCTION", function);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-products.jsp");
		dispatcher.forward(request, response);

	}

}
