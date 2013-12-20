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
public class FindParcelCommand implements Command{

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
        
        String parcelId = request.getParameter("parcelIdField");
        Parcel parcel = new Parcel(0,"123", "456", "0508276721", "Ivan", "Ivanchuk", "12.12.13", "14.12.13", 2, "Kiev", 1, 30.0f, 30.0f);
        HttpSession session = request.getSession(true);
        session.setAttribute("parcel", parcel);
        dispatcher =  request.getRequestDispatcher("/pages/findparcel.jsp");
    }

    /**
     * @return dispatcher for further processing
     */
    public Object getResult() {
        return dispatcher;
    }
}
