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
@WebServlet("/CartControllerServlet")
public class CartControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private OrderDBUtil orderDBUtil;
	private CartDBUtil cartDBUtil;
	private ProductDBUtil productDBUtil;
	private LoggedUserDBUtil loggedUserDBUtil;

	public CartControllerServlet() {
		cartDBUtil = new CartDBUtil();
		productDBUtil = new ProductDBUtil();
		loggedUserDBUtil = new LoggedUserDBUtil();
		orderDBUtil = new OrderDBUtil();
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
				theCommand = "ShowCart";
			}

			switch (theCommand) {
			case "ShowCart":
				showCart(request, response);
				break;

			case "AddToCart":
				addToCart(request, response);
				break;

			case "CLEAR":
				deleteCart(request, response);
				break;

			case "PAY":
				placeOrder(request, response);
				break;
				
			case "DELETE":
				removeFromCart(request, response);
				break;

			default:
				showCart(request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		cartDBUtil.removeFromCart(id);
		showCart(request, response);
		
	}

	private void placeOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Cart> cart = cartDBUtil.getCart();
		Client client = loggedUserDBUtil.getLoggedClient();
		float net = cartDBUtil.getNET(cart);
		float gross = cartDBUtil.getGROSS(cart);

		if (!cart.isEmpty()) {
			orderDBUtil.addOrder(client, net, gross);
			cartDBUtil.saveOrder();
			cartDBUtil.clear();
			showCart(request, response);
		}
		else {
			response.sendRedirect("cart-is-empty.jsp");
		}

	}

	private void deleteCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		cartDBUtil.clear();
		showCart(request, response);

	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));

		ArrayList<Product> products = productDBUtil.getProducts();
		Product product = products.get(id - 1);
		cartDBUtil.addToCart(product);
		showCart(request, response);

	}

	private void showCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Cart> cart = cartDBUtil.getCart();

		float netSum = cartDBUtil.getNET(cart);
		float grossSum = cartDBUtil.getGROSS(cart);

		request.setAttribute("CART_LIST", cart);
		request.setAttribute("NET", netSum);
		request.setAttribute("GROSS", grossSum);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/display-cart.jsp");
		dispatcher.forward(request, response);
	}

}
