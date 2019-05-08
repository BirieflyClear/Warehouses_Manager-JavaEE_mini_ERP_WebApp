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
 * Servlet implementation class OrderControllerServlet
 */
@WebServlet("/OrderControllerServlet")
public class OrderControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private LoggedUserDBUtil loggedUserDBUtil;
	private OrderDBUtil orderDBUtil;
	private CartDBUtil cartDBUtil;

	public OrderControllerServlet() {

		orderDBUtil = new OrderDBUtil();
		loggedUserDBUtil = new LoggedUserDBUtil();
		cartDBUtil = new CartDBUtil();
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
				theCommand = "ListOrders";
			}

			switch (theCommand) {
			case "ListOrders":
				listOrders(request, response);
				break;

			case "ShowMyOrders":
				listMyOrders(request, response);
				break;

			case "PAY":
				payOrder(request, response);
				break;

			case "ShowDetails":
				showDetails(request, response);
				break;

			case "ChangeStatus":
				changeStatus(request, response);
				break;

			case "LOAD":
				loadOrder(request, response);
				break;

			case "UPDATE":
				updateOrder(request, response);
				break;

			case "DELETE":
				deleteOrder(request, response);
				break;

			default:
				listOrders(request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		orderDBUtil.deleteOrder(id);
		listOrders(request, response);

	}

	private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("id"));
		int client_ID = Integer.parseInt(request.getParameter("client_ID"));
		String client_name = request.getParameter("client_name");
		float NET_price = Float.parseFloat(request.getParameter("NET_price"));
		float GROSS_price = Float.parseFloat(request.getParameter("GROSS_price"));
		String client_city = request.getParameter("client_city");
		int warehouse_ID = Integer.parseInt(request.getParameter("warehouse_ID"));
		String status = request.getParameter("status");
		String invoice_status = request.getParameter("invoice_status");

		// new Client object
		Order updatedOrder = new Order(id, client_ID, client_name, NET_price, GROSS_price, client_city, warehouse_ID,
				status, invoice_status);

		// perform operation on the DB
		orderDBUtil.updateOrder(updatedOrder);

		// back to the table
		listOrders(request, response);

	}

	private void loadOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String theID = request.getParameter("id");

		Order SelectedOrder = orderDBUtil.getOrder(theID);
		request.setAttribute("THE_ORDER", SelectedOrder);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_order.jsp");
		dispatcher.forward(request, response);

	}

	private void changeStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String status = request.getParameter("status");
		int id = Integer.parseInt(request.getParameter("id"));

		orderDBUtil.changeOrderStatus(id, status);

		listOrders(request, response);
	}

	private void showDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int orderID = Integer.parseInt(request.getParameter("id"));
		int function = loggedUserDBUtil.getLoggedUser().getFunction().length();

		ArrayList<Cart> orders = orderDBUtil.getThisOrder(orderID);
		float net = cartDBUtil.getNET(orders);
		float gross = cartDBUtil.getGROSS(orders);

		request.setAttribute("ORDER_LIST", orders);
		request.setAttribute("NET", net);
		request.setAttribute("GROSS", gross);
		request.setAttribute("FUNCTION", function);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-this-order.jsp");
		dispatcher.forward(request, response);

	}

	private void payOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int orderID = Integer.parseInt(request.getParameter("id"));

		orderDBUtil.changeInvoiceStatus(orderID);
		listMyOrders(request, response);

	}

	private void listMyOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Client client = loggedUserDBUtil.getLoggedClient();
		int id = client.getcID();
		int function = loggedUserDBUtil.getLoggedUser().getFunction().length();
		ArrayList<Order> orders = orderDBUtil.getMyOrders(id);

		request.setAttribute("ORDER_LIST", orders);
		request.setAttribute("FUNCTION", function);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-orders.jsp");
		dispatcher.forward(request, response);

	}

	private void listOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArrayList<Order> orders = orderDBUtil.getOrders();
		int function = loggedUserDBUtil.getLoggedUser().getFunction().length();

		request.setAttribute("ORDER_LIST", orders);
		request.setAttribute("FUNCTION", function);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-orders.jsp");
		dispatcher.forward(request, response);

	}

}
