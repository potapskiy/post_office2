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

import office.dao.ParcelDAO;
import office.entities.Parcel;
import office.entities.User;

import org.apache.log4j.Logger;

/**
 * WORK NOW! Archive page view servlet
 * 
 */
@WebServlet("/viewparcels")
public class ViewCourierParcelsShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected RequestDispatcher dispatcher = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewCourierParcelsShow() {
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
		
		//ArrayList<Parcel> parcels = new ArrayList<Parcel>();
		
		ArrayList<Parcel> parcels = new ParcelDAO().getParcelsToHome();
		
//		parcels
//				.add(new Parcel("0", 123, 456, "0508276721", "Ivan",
//						"Ivanchuk", "12.12.13", "14.12.13", 2, "Kiev", 1,
//						30.0f, 30.0f));
//		parcels.get(0).setId("0");
//		parcels.add(new Parcel("0", 123, 436, "0935678321", "Olga",
//				"Olzhych", "19.12.13", "23.12.13", 2, "Kiev", 1, 40.0f, 30.0f));
//		parcels.get(1).setId("1");
		HttpSession session = request.getSession(true);	
		session.removeAttribute("parcels");
		session.setAttribute("parcels", parcels);
				
		dispatcher = request.getRequestDispatcher("pages/viewparcels.jsp");
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
		dispatcher = request.getRequestDispatcher("pages/viewparcels.jsp");
		dispatcher.forward(request, response);
	}

}
