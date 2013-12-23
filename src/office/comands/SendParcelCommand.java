package office.comands;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import office.dao.ParcelDAO;
import office.entities.Parcel;

/**
 * PageCommand is Command, which return some static page
 * 
 */
public class SendParcelCommand implements Command {

	/**
	 * Dispatcher for further processing request
	 */
	protected RequestDispatcher dispatcher = null;
		

	/**
	 * Make redirect to another page
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException {
		request.setCharacterEncoding("UTF-8");		       
		
		String parcelId = request.getParameter("parcelIdField");
		HttpSession session = request.getSession(true);
		session.removeAttribute("resultLoadParcel");
		ParcelDAO pDao = new ParcelDAO();
		
		boolean f = pDao.isParselInTable(parcelId);
		
		
		if (f && (pDao.getParcel(parcelId).getStatus() == Parcel.STATUS_IN_WAY)) {
			new ParcelDAO().setParcelStatus(Parcel.STATUS_IN_WAY, parcelId);
			session.setAttribute("resultLoadParcel", Parcel.PARCEL_LOADED);
			dispatcher = request.getRequestDispatcher("/pages/sendparcel.jsp");
		} else {
			session.setAttribute("resultLoadParcel", Parcel.PARCEL_NOT_LOADED);
			dispatcher = request.getRequestDispatcher("/pages/sendparcel.jsp");
		}
		
		

	}

	/**
	 * @return dispatcher for further processing
	 */
	public Object getResult() {
		return dispatcher;
	}
}
