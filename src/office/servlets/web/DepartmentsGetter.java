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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import office.dao.DepartmentsDAO;
import office.dao.UsersDAO;
import office.entities.User;

import com.google.gson.Gson;

@WebServlet("/get_depart/*")

public class DepartmentsGetter extends HttpServlet {
    @Override
    protected void doPost(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException,
            IOException {
    	
    	final String townName = request.getParameter("term");
    	System.out.println("=====DEP==== " + townName);
    	
    	DepartmentsDAO dDao = new DepartmentsDAO();
    	List<String> depList =  dDao.getDepartmInfo(townName);
        
    	
    	JSONObject jsonObject = new JSONObject();
    	JSONArray jsonArray = new JSONArray();
    	
    	for(String s : depList){
    		   jsonArray.add(s);
    		}
    	
    	jsonObject.put("Departments", jsonArray);
    	
    	
     
        String requestBody = jsonObject.toJSONString();
        
        System.out.println(requestBody);
        
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(requestBody);
    }
}
