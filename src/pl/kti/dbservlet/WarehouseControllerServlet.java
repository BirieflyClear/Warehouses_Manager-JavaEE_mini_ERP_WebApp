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
 * Servlet implementation class WarehouseControllerServlet
 */
@WebServlet("/WarehouseControllerServlet")
public class WarehouseControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private WarehouseDBUtil warehouseDBUtil;
	private ProductTypeDBUtil productTypeDBUtil;

	public WarehouseControllerServlet() {

		warehouseDBUtil = new WarehouseDBUtil();
		productTypeDBUtil = new ProductTypeDBUtil();
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
				theCommand = "ListWarehouses";
			}

			switch (theCommand) {
			case "ListWarehouses":
				listWarehouses(request, response);
				break;
			case "ADD":
				addWarehouse(request, response);
				break;
			case "LOAD":
				loadWarehouse(request, response);
				break;
			case "UPDATE":
				updateWarehouse(request, response);
				break;
			case "DELETE":
				deleteWarehouse(request, response);
				break;
			default:
				listWarehouses(request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
private void deleteWarehouse(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String id = request.getParameter("id");
		warehouseDBUtil.deleteWarehouse(id);
		listWarehouses(request,response);
		
	}


	private void updateWarehouse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String city = request.getParameter("city");
		String products_type = request.getParameter("products_type");
		
		Float capacity = Float.parseFloat(request.getParameter("capacity"));
		
		//new Client object
		ArrayList<ProductType> types = productTypeDBUtil.getProductTypes();
	
		int j=0;
		for(int i = 0; i<types.size(); i++) {
			if(products_type == types.get(i).getTypeName()) {
				Warehouse updatedWarehouse = new Warehouse(id, city, products_type, capacity);
				
				//perform operation on the DB
				warehouseDBUtil.updateWarehouse(updatedWarehouse);
				
				//back to the table
				listWarehouses(request, response);
			}else {
				j++;
			}
		}
		if(j == types.size()) {
			String message = "Type doesn't exist!";
			request.setAttribute("MSG", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/warehouse-message.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}


	private void loadWarehouse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String theID = request.getParameter("id");
		
		Warehouse SelectedWarehouse = warehouseDBUtil.getWarehouse(theID);
		request.setAttribute("THE_WAREHOUSE", SelectedWarehouse);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_warehouse.jsp");
		dispatcher.forward(request, response);
		
	}


	private void addWarehouse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read user data from the form
		String city = request.getParameter("city");
		String products_type = request.getParameter("products_type");
		float capacity = Float.parseFloat(request.getParameter("capacity"));

		// create a new user object
		Warehouse theWarehouse = new Warehouse(city, products_type, capacity);

		// add user to the data base
		WarehouseDBUtil.addWarehouse(theWarehouse);
		// send back to main page
		listWarehouses(request, response);

	}

	private void listWarehouses(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArrayList<Warehouse> warehouses = warehouseDBUtil.getWarehouses();

		request.setAttribute("WAREHOUSE_LIST", warehouses);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-warehouses.jsp");
		dispatcher.forward(request, response);

	}

}
