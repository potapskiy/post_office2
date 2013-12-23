package office.comands;

import java.io.IOException;
import java.util.ArrayList;

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
public class CourierSubmitCommand implements Command {

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
		
		HttpSession session = request.getSession(true);
		if (session.getAttribute("parcels") != null) {
			ParcelDAO pDao = new ParcelDAO();
			ArrayList<Parcel> parcels = (ArrayList<Parcel>)session.getAttribute("parcels");
			ArrayList<Parcel> newParcels = new ArrayList<Parcel>();
			for (Parcel p : parcels) {
				String h = "check" + p.getId();
				String parcelId = request.getParameter(h);
				if (parcelId == null) {
					newParcels.add(p);
				} else {
					pDao.setParcelStatus(Parcel.STATUS_DONE, p.getId());
					
				}
			}
			session.setAttribute("parcels", newParcels);
			session.setAttribute("success", "success");
		}
		
		dispatcher = request.getRequestDispatcher("/pages/viewparcels.jsp");	

	}

	/**
	 * @return dispatcher for further processing
	 */
	public Object getResult() {
		return dispatcher;
	}
}
