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
public class EditProfileCommand implements Command{

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
        UsersDAO uDao = new UsersDAO();
        
        if (pass == null) {
        	// pass �� �������
        }
        
        HttpSession session = request.getSession(true);
    	User user = (User)session.getAttribute("user");
        
        boolean isLogin = uDao.isUserRegistered(login);
        if (isLogin && (!login.equals(user.getTelephone()))) { // ���� �������, ��� ����� ��� � � ���        	
        	request.setAttribute("Error","error.autofailed");
            dispatcher =  request.getRequestDispatcher("/pages/edit_profile.jsp");
        } else {
        	
        	user.setTelephone(login);
        	user.setFirstName(firstName);
        	user.setSecondName(lastName);
        	user.setAddress(address);
        	
        	if ((pass == null) || (pass == "")){
        		uDao.insertUserWP(user);
        	}else{
        		
        		String passHash = SHAHashing.getHash(pass);
        		user.setPassword(passHash);
        		uDao.insertUser(user);
        	}
        	
        	session.setAttribute("user", user);
        	request.setAttribute("Success","Success");
            dispatcher =  request.getRequestDispatcher("/pages/profile.jsp");
        }
        
    }

    /**
     * @return dispatcher for further processing
     */
    public Object getResult() {
        return dispatcher;
    }
}
