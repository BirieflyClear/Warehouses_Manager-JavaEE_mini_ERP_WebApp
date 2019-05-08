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
 * Servlet implementation class ClientControllerServlet
 */
@WebServlet("/ProductTypeControllerServlet")
public class ProductTypeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private ProductTypeDBUtil productTypeDBUtil;

	public ProductTypeControllerServlet() {
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
				theCommand = "ListTypes";
			}

			switch (theCommand) {
			case "ListTypes":
				listTypes(request, response);
				break;
				
			case "LOAD":
				load(request, response);
				break;
				
			case "DELETE":
				delete(request, response);
				break;
				
			case "UPDATE":
				updateType(request, response);
				break;

			default:
				listTypes(request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void updateType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("type_name");	
		//new User object
		ProductType type = new ProductType(id, name);
		
		//perform operation on the DB
		productTypeDBUtil.updateType(type);
		
		//back to the table
		listTypes(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		productTypeDBUtil.delete(id);
		listTypes(request,response);
		
	}

	private void load(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		
		ProductType type = productTypeDBUtil.getThis(id);
		request.setAttribute("THIS", type);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_type.jsp");
		dispatcher.forward(request, response);
		
	}

	private void listTypes(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<ProductType> types = productTypeDBUtil.getProductTypes();

		request.setAttribute("TYPES_LIST", types);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-types.jsp");
		dispatcher.forward(request, response);
		
	}

}
