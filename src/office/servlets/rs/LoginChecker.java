package office.servlets.rs;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import office.dao.UsersDAO;


@Path("/check_login")
public class LoginChecker {
	@Context
	ServletContext context;
	
	@POST
	public String getNumbers(String incomData,@Context HttpServletRequest req) {
		
		String decodedUrl;
		try {
			decodedUrl = URLDecoder.decode(incomData, "UTF-8");
			String login = decodedUrl.replaceFirst("userlogin=", "");
			
			boolean isLogin = new UsersDAO().isUserRegistered(login);
			
			
			return (isLogin)?"RESERVED":"FREE";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
			
		}
		

	}

}
