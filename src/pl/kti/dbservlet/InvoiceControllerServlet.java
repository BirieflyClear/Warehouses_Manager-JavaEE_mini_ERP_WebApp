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
 * Servlet implementation class InvoiceControllerServlet
 */
@WebServlet("/InvoiceControllerServlet")
public class InvoiceControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private LoggedUserDBUtil loggedUserDBUtil;
	private OrderDBUtil orderDBUtil;
	private InvoiceDBUtil invoiceDBUtil;

	public InvoiceControllerServlet() {

		invoiceDBUtil = new InvoiceDBUtil();
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
				theCommand = "ListInvoices";
			}

			switch (theCommand) {

			case "ListInvoices":
				listInvoices(request, response);
				break;

			case "ADD":
				addInvoice(request, response);
				break;

			case "ShowMyInvoices":
				listMyInvoices(request, response);
				break;

			case "LOAD":
				loadInvoice(request, response);
				break;

			case "UPDATE":
				updateInvoice(request, response);
				break;

			case "DELETE":
				deleteInvoice(request, response);
				break;
				
			case "AddForm":
				showAddForm(request, response);
				break;

			default:
				listInvoices(request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		ArrayList <Order> orders = orderDBUtil.getNotPayed();
		
		request.setAttribute("ORDERS", orders);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/add_invoice.jsp");
		dispatcher.forward(request, response);
		
	}

	private void deleteInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		invoiceDBUtil.deleteInvoice(id);
		listInvoices(request, response);

	}

	private void updateInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("id"));
		int client_ID = Integer.parseInt(request.getParameter("client_ID"));
		String client_name = request.getParameter("client_name");
		int order_ID = Integer.parseInt(request.getParameter("order_ID"));

		// new Client object
		Invoice updatedInvoice = new Invoice(id, client_ID, client_name, order_ID);

		// perform operation on the DB
		invoiceDBUtil.updateInvoice(updatedInvoice);

		// back to the table
		listInvoices(request, response);

	}

	private void loadInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String theID = request.getParameter("id");

		Invoice SelectedInvoice = invoiceDBUtil.getInvoice(theID);
		request.setAttribute("THE_INVOICE", SelectedInvoice);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_invoice.jsp");
		dispatcher.forward(request, response);

	}

	private void addInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read user data from the form
		
		String order_ID = request.getParameter("order_ID");
		
		Order order = orderDBUtil.getOrder(order_ID);
		
		String status = order.getInvoice_status();
		
		if(status.length() == 10) {
			Invoice theInvoice = new Invoice(order.getClient_ID(), order.getClient_name(), order.getoID(), order.getNET_price(), order.getGROSS_price());

			// add user to the data base
			invoiceDBUtil.addInvoice(theInvoice);
			// send back to main page
			listInvoices(request, response);
		}else {
			String message = "Invoice exists or wrong order ID!";
			request.setAttribute("MSG", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/invoice-message.jsp");
			dispatcher.forward(request, response);
		}
		
		

	}

	private void listInvoices(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArrayList<Invoice> invoices = invoiceDBUtil.getInvoices();
		int function = loggedUserDBUtil.getLoggedUser().getFunction().length();

		request.setAttribute("INVOICE_LIST", invoices);
		request.setAttribute("FUNCTION", function);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-invoices.jsp");
		dispatcher.forward(request, response);

	}

	private void listMyInvoices(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Client client = loggedUserDBUtil.getLoggedClient();
		int id = client.getcID();
		int function = loggedUserDBUtil.getLoggedUser().getFunction().length();
		ArrayList<Invoice> invoices = invoiceDBUtil.getMyInvoices(id);

		request.setAttribute("INVOICE_LIST", invoices);
		request.setAttribute("FUNCTION", function);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-invoices.jsp");
		dispatcher.forward(request, response);

	}

}
