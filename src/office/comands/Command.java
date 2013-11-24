package office.comands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


public interface Command {
    
    public void execute(HttpServletRequest request) 
            throws IOException, ServletException;
    
    public Object getResult();
}
