package office.servlets.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import office.dao.DepartmentsDAO;
import office.dao.UsersDAO;
import office.entities.User;

import com.google.gson.Gson;

@WebServlet("/get_client_name/*")
public class ClientName extends HttpServlet {
    @Override
    protected void doPost(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException,
            IOException {
    	
    	final String telNumb = request.getParameter("term");
    	System.out.println("============ " + telNumb);
    	
    	UsersDAO uDao = new UsersDAO();
    	
        User u = uDao.getUserByTelephone(telNumb);
        
        String name = (u.getFirstName() == null )? "" : u.getFirstName();
        String sname = (u.getSecondName() == null )? "" : u.getSecondName();
        
        
        JSONObject json = new JSONObject();
		json.put( "name", name);
		json.put( "sname", sname);
        
		
        String requestBody = json.toJSONString();
        
        System.out.println(requestBody);
        
        response.setCharacterEncoding("utf-8");
         response.getWriter().write(requestBody);
    }
}
