package office.servlets.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import office.entities.User;

import org.apache.log4j.Logger;

/**
 * WORK NOW! Archive page view servlet
 * 
 */
@WebServlet("/myparcels")
public class MyParcelsShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected RequestDispatcher dispatcher = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyParcelsShow() {
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

		// read parcels of user in session
		ArrayList<Parcel> sentParcels = new ArrayList<Parcel>();
		ArrayList<Parcel> receivedParcels = new ArrayList<Parcel>();
		sentParcels
				.add(new Parcel("0", 123, 456, "0508276721", "Ivan",
						"Ivanchuk", "12.12.13", "14.12.13", 2, "Kiev", 1,
						30.0f, 30.0f));
		sentParcels.add(new Parcel("0", 123, 436, "0935678321", "Olga",
				"Olzhych", "19.12.13", "23.12.13", 2, "Kiev", 1, 40.0f, 30.0f));
		HttpSession session = request.getSession(true);
		
		
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			receivedParcels.add(new Parcel("1", 123, 456,
					user.getTelephone(), user.getFirstName(), user
							.getSecondName(), "12.11.13", "14.12.13", 2, user
							.getAddress(), 1, 30.0f, 30.0f));
			receivedParcels.add(new Parcel("1", 123, 436,
					user.getTelephone(), user.getFirstName(), user
							.getSecondName(), "19.12.13", "23.12.13", 2, user
							.getAddress(), 1, 40.0f, 30.0f));			
		}			
		request.setAttribute("sentparcels", sentParcels);
		request.setAttribute("receivedparcels", receivedParcels);
		
		
		dispatcher = request.getRequestDispatcher("pages/myparcels.jsp");
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
		dispatcher = request.getRequestDispatcher("pages/myparcels.jsp");
		dispatcher.forward(request, response);
	}

}
