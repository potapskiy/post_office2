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
import office.entities.User;

/**
 * RegisterCommand is Command, which creates user account
 * 
 */
public class RegisterCommand implements Command{

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
        
        String login = request.getParameter("loginField");
        String pass = request.getParameter("passField");        
        String firstName = request.getParameter("firstNameField");
        String lastName = request.getParameter("lastNameField");
        String address = request.getParameter("addressField");
               		
		//String passHash = SHAHashing.getHash(pass);		
		//UsersDAO usersDAO = new UsersDAO();		
		//boolean userExists = usersDAO.isUserTelAndPassCorrect(login, passHash);		
				
		if (!login.equals("000011112")){
			//User user = usersDAO.getUserByTelephone(login);
			User user = new User(login, pass, firstName, lastName, address);
			request.removeAttribute("Error");
			HttpSession session = request.getSession(true);
			session.removeAttribute("Error");
            session.setAttribute("user", user);
            dispatcher =  request.getRequestDispatcher("/pages/index.jsp");
		}else {
            request.setAttribute("Error","error.autofailed");
            dispatcher =  request.getRequestDispatcher("/pages/createacc.jsp");
        }
//        
//        
//        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
//        UserDAO userdao = factory.getUserDao();
//        User user = userdao.find(login, pass);
//        if(user != null) {
//           request.removeAttribute("Error");
//          HttpSession session = request.getSession(true);
//            session.setAttribute("user", user);
//            dispatcher =  request.getRequestDispatcher("/pages/main.jsp");
//        } else {
//            request.setAttribute("Error","error.autofailed");
//            dispatcher =  request.getRequestDispatcher("/pages/index.jsp");
//        }
    }

    /**
     * @return dispatcher for further processing
     */
    public Object getResult() {
        return dispatcher;
    }
}
