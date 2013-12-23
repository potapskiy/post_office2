package office.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *Session filter for inhibition unlogged users work with site  
 *
 */
public class SessionFilter implements Filter {
 
    private ArrayList<String> urlList;
 
    public void destroy() {
    }
    
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String url = request.getServletPath();
        boolean allowedRequest = true;
 
        HttpSession session = request.getSession(false);
       
	   // zero position is symbol '/' in url
	   // get url from first position 
	   System.out.println(url);
	   System.out.println(url.substring(1));
	   System.out.println(urlList.contains(url.substring(1)));
        if(urlList.contains(url.substring(1))) {
        	if (session.getAttribute("user") == null) {
        		System.out.println("notAllowed");
        		allowedRequest = false; 
        	} 
        }
 
        if (!allowedRequest) {
        	request.getRequestDispatcher("/index").forward(request, response);
        	return;
        }
 
        chain.doFilter(req, res);   
    }
    
    public void init(FilterConfig config) throws ServletException {
        String urls = config.getInitParameter("filterurl");
        StringTokenizer token = new StringTokenizer(urls, ",");
 
        urlList = new ArrayList<String>();
 
        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());
        }
        
        System.out.println("===init=====");
        for(String s : urlList){
        	System.out.println(s);
        }
    }
}