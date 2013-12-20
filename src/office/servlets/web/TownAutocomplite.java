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

import office.dao.DepartmentsDAO;

import com.google.gson.Gson;

@WebServlet("/get_town_name/*")
public class TownAutocomplite extends HttpServlet {
    @Override
    protected void doPost(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException,
            IOException {
    	
    	final String param = request.getParameter("term");
    	System.out.println("============ " + param);
    	
        List<String> townList = new ArrayList<String>();
        townList = new DepartmentsDAO().getTownList(param);
        
//        countryList.add("USA");
//        countryList.add("Pakistan");
//        countryList.add("Britain");
//        countryList.add("India");
//        countryList.add("Italy");
//        countryList.add("Ireland");
//        countryList.add("Bangladesh");
//        countryList.add("Brazil");
//        countryList.add("United Arab Emirates");
        Collections.sort(townList);

        // Map real data into JSON

        response.setContentType("application/json");
        
        
        
        
        final List<AutoCompleteData> result = new ArrayList<AutoCompleteData>();
        for (final String country : townList) {
            if (country.toLowerCase().startsWith(param.toLowerCase())) {
                result.add(new AutoCompleteData(country, country));
            }
        }
        
        
        response.setCharacterEncoding("utf-8");
        System.out.println(new Gson().toJson(result));
        response.getWriter().write(new Gson().toJson(result));
    }
}
