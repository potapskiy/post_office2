package office.comands;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import office.crypto.SHAHashing;
import office.dao.UsersDAO;
import office.entities.Parcel;
import office.entities.User;

/**
 * RegisterCommand is Command, which creates user account
 * 
 */
public class ReceiveParcelCommand implements Command {

	/**
	 * Dispatcher for further processing request
	 */
	protected RequestDispatcher dispatcher = null;

	/**
	 * Makes user authorization
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		// change status of parsel from request.getAttribute("parcel") to
		// "DONE";
		request.setAttribute("receiveParcelSuccess", "Success");
		dispatcher = request.getRequestDispatcher("/pages/receiveparcel.jsp");
	}

	/**
	 * @return dispatcher for further processing
	 */
	public Object getResult() {
		return dispatcher;
	}
}
