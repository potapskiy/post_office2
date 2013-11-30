package office.comands;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import office.crypto.SHAHashing;
import office.dao.UsersDAO;

/**
 * LoginCommand is Command, which make user authorization
 * 
 */
public class LoginCommand implements Command{

    /**
     * Dispatcher for further processing request
     */
    protected RequestDispatcher dispatcher = null;
    
    
    /**
     * Makes user authorization
     */
    public void execute(HttpServletRequest request) 
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        
        String login = request.getParameter("loginField");
        String pass = request.getParameter("pass");
        
        System.out.println(login + "  "+ pass);
        
        
		
		String passHash = SHAHashing.getHash(pass);
		
		UsersDAO usersDAO = new UsersDAO();
		
		System.out.println(usersDAO.isUserTelAndPassCorrect(login, passHash));
        
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
            request.setAttribute("Error","error.autofailed");
            dispatcher =  request.getRequestDispatcher("/pages/index.jsp");
//        }
    }

    /**
     * @return dispatcher for further processing
     */
    public Object getResult() {
        return dispatcher;
    }
}
