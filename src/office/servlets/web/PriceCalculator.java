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

import office.dao.ConfigDAO;
import office.dao.DepartmentsDAO;
import office.dao.TownsDAO;
import office.servlets.rs.distanse.DistanseGetter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/get_price/*")
public class PriceCalculator extends HttpServlet {
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		try {
			String from = request.getParameter("from_t");
			String to = request.getParameter("to_t");
			String weightS = request.getParameter("weight");
			float weight = Float.parseFloat(weightS);
			String type = request.getParameter("type");
			
			TownsDAO tDao = new TownsDAO();
			int fid= tDao.getCode(from);
			int tid= tDao.getCode(to);
			DepartmentsDAO d = new DepartmentsDAO();
			
			if (!d.isTowmInTable(fid) || !d.isTowmInTable(tid)){
				JSONObject json = new JSONObject();
				json.put( "price", "ERROR");
			    String requestBody = json.toJSONString();
			    System.out.println(requestBody);
				response.getWriter().write(new Gson().toJson(requestBody));
			}

			System.out.println("============ " + from + "  " + to + " "
					+ weightS + " " + type);

			double price = new PriseCalcServ().getPrice(from, to, type, weight);
			
			String rez = (price > 0)? String.valueOf(price) : "ERROR";
			
			JSONObject json = new JSONObject();
			json.put( "price", rez);
			
			
	        String requestBody = json.toJSONString();
	        System.out.println(requestBody);
			response.getWriter().write(new Gson().toJson(requestBody));
			
			return;
		} catch (Exception e) {
			JSONObject json = new JSONObject();
			json.put( "price", "ERROR");
		    String requestBody = json.toJSONString();
		    System.out.println(requestBody);
			response.getWriter().write(new Gson().toJson(requestBody));
		}
	}
}
