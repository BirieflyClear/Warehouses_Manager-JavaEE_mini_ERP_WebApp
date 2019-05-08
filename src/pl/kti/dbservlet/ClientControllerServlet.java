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
@WebServlet("/ClientControllerServlet")
public class ClientControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private UserDBUtil userDBUtil;
	private ClientDBUtil clientDBUtil;

	public ClientControllerServlet() {

		clientDBUtil = new ClientDBUtil();
		userDBUtil = new UserDBUtil();
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
				theCommand = "ListClients";
			}

			switch (theCommand) {
			case "ListClients":
				listClients(request, response);
				break;

			case "ADD":
				addClient(request, response);
				break;

			case "LOAD":
				loadClient(request, response);
				break;
			case "UPDATE":
				updateClient(request, response);
				break;
			case "DELETE":
				deleteClient(request, response);
				break;
			default:
				listClients(request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void addClient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read user data from the form
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// create a new user object
		Client theClient = new Client(name, city, username);
		User theUser = new User(username, password, "client");

		// add user to the data base
		clientDBUtil.addClient(theClient);
		userDBUtil.addUser(theUser);
		// send back to main page
		listClients(request, response);

	}

	private void listClients(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArrayList<Client> clients = clientDBUtil.getClients();

		request.setAttribute("CLIENT_LIST", clients);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-clients.jsp");
		dispatcher.forward(request, response);

	}

	private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		clientDBUtil.deleteClient(id);
		listClients(request, response);

	}

	private void updateClient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		String username = request.getParameter("username");

		// new Client object
		Client updatedClient = new Client(id, name, city, username);

		// perform operation on the DB
		clientDBUtil.updateClient(updatedClient);

		// back to the table
		listClients(request, response);

	}

	private void loadClient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String theID = request.getParameter("id");

		Client SelectedClient = clientDBUtil.getClient(theID);
		request.setAttribute("THE_CLIENT", SelectedClient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_client.jsp");
		dispatcher.forward(request, response);

	}

}
