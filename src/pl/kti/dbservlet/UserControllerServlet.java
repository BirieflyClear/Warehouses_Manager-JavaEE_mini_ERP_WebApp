package pl.kti.dbservlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class UserControllerServlet
 */
@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private CartDBUtil cartDBUtil;
	private UserDBUtil userDBUtil;
	private LoggedUserDBUtil loggedUserDBUtil;

	public UserControllerServlet() {

		userDBUtil = new UserDBUtil();
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
				theCommand = "ListUsers";
			}

			switch (theCommand) {
			case "LOGIN":
				login(request, response);
				break;

			case "ListUsers":
				listUsers(request, response);
				break;

			case "MENU":
				showMenu(request, response);
				break;

			case "ADD":
				addUser(request, response);
				break;

			case "LogOut":
				logOut(request, response);
				break;

			case "LOAD":
				loadUsers(request, response);
				break;

			case "UPDATE":
				updateUser(request, response);
				break;

			case "DELETE":
				deleteUser(request, response);
				break;

			case "FUNCTION":
				chooseFunction(request, response);
				break;

			default:
				showMenu(request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void chooseFunction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function = request.getParameter("function");

		// prepare form depending on a chosen function
		if (function.length() == 6) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/add_new_client.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("theFunction", function);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/add_new_user.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		userDBUtil.deleteUser(id);
		listUsers(request, response);

	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String function = request.getParameter("function");

		// new User object
		User updatedUser = new User(id, username, password, function);

		// perform operation on the DB
		userDBUtil.updateUser(updatedUser);

		// back to the table
		listUsers(request, response);

	}

	private void loadUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String theID = request.getParameter("id");

		User SelectedUser = userDBUtil.getUser(theID);
		request.setAttribute("THE_USER", SelectedUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_user.jsp");
		dispatcher.forward(request, response);

	}

	private void logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {

		loggedUserDBUtil.clear();
		cartDBUtil.clear();
		response.sendRedirect("login-form.jsp");
	}

	private void showMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoggedUser loggedUser = loggedUserDBUtil.getLoggedUser();

		String function = loggedUser.getFunction();

		switch (function) {
		case "admin":
			response.sendRedirect("menu-admin.jsp");
			break;

		case "accountant":
			response.sendRedirect("menu-accountant.jsp");
			break;

		case "client":
			response.sendRedirect("menu-client.jsp");
			break;

		default:
			response.sendRedirect("login-form.jsp");
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read info from data
		String username = request.getParameter("usernameValue");
		String password = request.getParameter("passwordValue");

		// clear list of logged users and cart
		loggedUserDBUtil.clear();
		cartDBUtil.clear();

		// count of not matched pairs of username and password with existing ones
		int wrong = 0;

		// check if the data is correct
		int rowCount = userDBUtil.rowCount();
		ArrayList<User> users = userDBUtil.getUsers();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {

				// create new LoggedUser object
				LoggedUser loggedUser = new LoggedUser(users.get(i).getuID(), users.get(i).getUsername(),
						users.get(i).getPassword(), users.get(i).getFunction());
				loggedUserDBUtil.setLoggedUser(loggedUser);

				// show respond info
				request.setAttribute("USER", loggedUser);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login-success.jsp");
				dispatcher.forward(request, response);

			} else {
				wrong++;
			}
		}
		
		if(wrong==users.size()) {
			request.setAttribute("USER", username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login-fail.jsp");
			dispatcher.forward(request, response);

		}
			
	

	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArrayList<User> users = userDBUtil.getUsers();
		request.setAttribute("USER_LIST", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-users.jsp");
		dispatcher.forward(request, response);
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read user data from the form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String function = request.getParameter("function");

		// create a new user object
		User theUser = new User(username, password, function);

		// add user to the data base
		userDBUtil.addUser(theUser);
		// send back to main page
		listUsers(request, response);
	}

}
