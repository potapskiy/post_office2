package office.servlets.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import office.entities.Parcel;

import org.apache.log4j.Logger;

/**
 * WORK NOW! Archive page view servlet
 * 
 */
@WebServlet("/loadparcel")
public class LoadParcelPageShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected RequestDispatcher dispatcher = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadParcelPageShow() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println("GET");
		HttpSession session = request.getSession(true);
		session.removeAttribute("resultLoadParcel");
		dispatcher = request.getRequestDispatcher("pages/loadparcel.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// HttpSession session = request.getSession(false);

		System.out.println("POST");
		dispatcher = request.getRequestDispatcher("pages/loadparcel.jsp");
		dispatcher.forward(request, response);
	}

}
