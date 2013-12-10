package office.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import office.comands.Command;

/**
 * LoginServlet is servlet, which make user authorization
 * 
 * @author ARTIST
 */
public class ControllerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	RequestHelper requestHelper = RequestHelper.getInstance();

	@Override
	public void init() {

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		performTask(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		performTask(request, response);

	}

	/**
	 * Makes user authorization
	 */
	public void performTask(HttpServletRequest request,
			HttpServletResponse response) {
		// String action = request.getParameter("command");

		try {
			// Command command = CommandFactory.getCommand(
			// CommandFactory.Login);
			Command command = requestHelper.getCommand(request);
			command.execute(request);
			RequestDispatcher rd = (RequestDispatcher) command.getResult();
			rd.forward(request, response);
		} catch (UnsupportedEncodingException ex) {
			System.out.println(ex.toString());
		} catch (ServletException ex) {
			System.out.println(ex.toString());
		} catch (IOException ex) {
			System.out.println(ex.toString());
		} catch (NullPointerException ex) {
			System.out.println(ex.toString());
		}
	}
}
