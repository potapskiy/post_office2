package office.comands;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

/**
 * PageCommand is Command, which return some static page
 * 
 */
public class PageCommand implements Command {

	/**
	 * Dispatcher for further processing request
	 */
	protected RequestDispatcher dispatcher = null;

	/**
	 * Make redirect to another page
	 */
	public void execute(HttpServletRequest request) throws IOException,
			ServletException {
		request.setCharacterEncoding("UTF-8");
		       
		
		String pageName = request.getParameter("page");
		dispatcher = request.getRequestDispatcher("/pages/" + pageName + ".jsp");

	}

	/**
	 * @return dispatcher for further processing
	 */
	public Object getResult() {
		return dispatcher;
	}
}
